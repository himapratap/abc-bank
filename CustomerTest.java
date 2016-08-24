package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.service.Account;
import com.abc.service.AccountType;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	@Test
	// Test customer statement generation
	public void testApp() {

		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);

		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);

		Customer henry = new Customer("Henry").addAccount(checkingAccount)
				.addAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);
  		assertEquals("Statement for Henry\n"+ "=================================" + "\n" + "Checking Account\n"
				+ "  Deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  Deposit $4,000.00\n"
				+ "  Withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount() {
		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		;
		Customer oscar = new Customer("Oscar").addAccount(savingsAccount);
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);
		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		Customer oscar = new Customer("Oscar").addAccount(savingsAccount);
		oscar.addAccount(checkingAccount);
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		Account checkingAccount = Bank.createAccount(AccountType.CHECKING);
		Account savingsAccount = Bank.createAccount(AccountType.SAVINGS);
		Account maxAccount = Bank.createAccount(AccountType.MAXI_SAVINGS);
		Customer oscar = new Customer("Oscar");
		oscar.addAccount(checkingAccount);
		oscar.addAccount(savingsAccount);
		oscar.addAccount(maxAccount);
		assertEquals(3, oscar.getNumberOfAccounts());
	}
	
    
	@Test
    
    public void checkWithdrawal(){
    	Bank bank = new Bank();
        Account maxiSavings = Bank.createAccount(AccountType.MAXI_SAVINGS);;
        bank.addCustomer(new Customer("Bill").addAccount(maxiSavings));

        maxiSavings.deposit(50.0);
        maxiSavings.deposit(200.0);
        maxiSavings.deposit(100.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.withdraw(1000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(3000.0);
        maxiSavings.deposit(9000.0);
        assertEquals(true, maxiSavings.checkIfWithdrawedIn(10));
    	
    }
	
	 
	
}
