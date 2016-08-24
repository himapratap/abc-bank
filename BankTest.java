package com.abc;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.rules.ExpectedException;

import com.abc.service.Account;
import com.abc.service.AccountType;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void customerSummary() {
		Bank bank = new Bank();
		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);
		Customer john = new Customer("John");
		john.addAccount(checkingAccount);
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)",
				bank.customerSummary());
	}

	@Test
	public void checkingAccount() {
		Bank bank = new Bank();
		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);
		Customer bill = new Customer("Bill").addAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.deposit(1000000.0);
		checkingAccount.addDailyInterest(); // 2.74397
		assertEquals(2.7397, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	public void checkingAccountWithMinBalance() throws IllegalArgumentException {
		Bank bank = new Bank();
		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);
		Customer bill = new Customer("Bill").addAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.withdraw(1000000.0);
		exception.expect(IllegalArgumentException.class);
		exception
				.expectMessage("Current balance($0.0) too low to withdraw $1000000.0");

	}

	@Test
	public void savings_account_rate_tier1() {
		Bank bank = new Bank();
		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		;
		bank.addCustomer(new Customer("Bill").addAccount(savingsAccount));
		savingsAccount.deposit(800.0);
		savingsAccount.addDailyInterest(); // 0.0022
		assertEquals(0.0022, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account_rate_tier2() {
		Bank bank = new Bank();
		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		;
		bank.addCustomer(new Customer("Bill").addAccount(savingsAccount));
		savingsAccount.deposit(1500.0);
		savingsAccount.addDailyInterest(); // 0.0055
		assertEquals(0.0055, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() {
		Bank bank = new Bank();
		Account maxiSavings = Bank.createAccount(AccountType.MAXI_SAVINGS);
		;
		bank.addCustomer(new Customer("Bill").addAccount(maxiSavings));
		maxiSavings.deposit(3000.0);
		maxiSavings.addDailyInterest(); // 0.1369
		assertEquals(0.411, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_after_withdrawal() {
		Bank bank = new Bank();
		Account maxiSavings = Bank.createAccount(AccountType.MAXI_SAVINGS);
		;
		bank.addCustomer(new Customer("Bill").addAccount(maxiSavings));
		maxiSavings.deposit(3000.0);
		maxiSavings.withdraw(1000.0);
		maxiSavings.deposit(1000.0);
		maxiSavings.addDailyInterest(); // 0.1369
		assertEquals(0.0082, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account_after_withdrawal_1() {

		Bank bank = new Bank();
		Account maxiSavings = Bank.createAccount(AccountType.MAXI_SAVINGS);
		;
		bank.addCustomer(new Customer("Bill").addAccount(maxiSavings));
		maxiSavings.deposit(3000.0);
		maxiSavings.withdraw(1000.0);
		maxiSavings.deposit(1000.0);
		maxiSavings.addDailyInterest(); // 0.1369
		assertEquals(0.0082, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void testTransferFromInsufficientAccount() throws IllegalArgumentException{
		Account accountA = Bank.createAccount(AccountType.CHECKING);
		Account accountB = Bank.createAccount(AccountType.CHECKING);
 		Customer tom = new Customer("Tom").addAccount(accountA);
		Customer jerry = new Customer("Jerry").addAccount(accountB);
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Insufficient funds to transfer.");
 		accountA.transfer(accountB, 100.0);


	}

	@Test
	public void testInvalidTransfer() throws IllegalArgumentException{
		Account accountA = Bank.createAccount(AccountType.CHECKING);
		Account accountB = Bank.createAccount(AccountType.CHECKING);

 		Customer tom = new Customer("Tom").addAccount(accountA);
		Customer jerry = new Customer("Jerry").addAccount(accountB);
		// accountA.deposit(500);
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Transfer amount must be greater than 0.");
		accountA.transfer(accountB, 0.0);

	}

	@Test
	public void testTransfer() {
		Account accountA = Bank.createAccount(AccountType.CHECKING);
		Account accountB = Bank.createAccount(AccountType.CHECKING);

		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		Customer tom = new Customer("Tom").addAccount(accountA);
		Customer jerry = new Customer("Jerry").addAccount(accountB);
	    accountA.deposit(500);
		accountA.transfer(accountB, 100.0);

		assertEquals(400, accountA.getBalance(), DOUBLE_DELTA);
		assertEquals(100, accountB.getBalance(), DOUBLE_DELTA);
		 

	}

}
