package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.service.Account;
import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;

import static java.lang.Math.abs;

public class Customer {

	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer addAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double totalInterest = 0;
		for (Account account : accounts) {
			for (Transaction transaction : account.getTransactions()) {
				if (transaction.getType().equals(
						TransactionType.INTEREST_EARNED)) {
					totalInterest += transaction.getAmount();

				}

			}
		}
		return totalInterest;
	}

	public String getStatement() {
		StringBuffer statement = new StringBuffer("Statement for ")
				.append(name).append("\n");
	    statement.append("=================================");
		double total = 0.0;
		for (Account account : accounts) {
			statement.append("\n");
			statement.append(statementForAccount(account));
			statement.append("\n");
			total += account.getBalance();
		}
		statement.append("\nTotal In All Accounts ").append(toDollars(total));
   		return statement.toString();
	}

	private String statementForAccount(Account account) {
		StringBuffer statement = new StringBuffer();
		statement.append(account.getAccountType()).append("\n");
		// Now total up all the transactions
		for (Transaction transaction : account.getTransactions()) {
			statement.append("  ").append(transaction.getType()).append(" ")
					.append(toDollars(transaction.getAmount())).append("\n");
		}
		statement.append("Total ").append(toDollars(account.getBalance()));
		return statement.toString();
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

}
