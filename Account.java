package com.abc.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;
import com.abc.utilities.DateProvider;

public abstract class Account {

	protected double accountBalance = 0.0;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	private List<Transaction> transactions = new ArrayList<Transaction>();

	public AccountType accountType;

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"Amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, TransactionType.DEPOSIT));
			accountBalance += amount;
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"Amount must be greater than zero");
		} else if (amount > getBalance()) {
			throw new IllegalArgumentException("Current balance($"
					+ getBalance() + ")" + "too low to withdraw $" + amount);

		} else {
			transactions.add(new Transaction(-amount,
					TransactionType.WITHDRAWAL));
			accountBalance -= amount;

		}
	}

	public double getBalance() {
		return accountBalance;
	}

	public AccountType getAccountType() {

		return accountType;
	}

	// TODO : Transfer operation should be made atomic

	public void transfer(Account to, double amount) {

		try {
			withdraw(amount);
			to.deposit(amount);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().contains("Amount must be greater than")) {
				throw new IllegalArgumentException(
						"Transfer amount must be greater than 0.");
			} else {
				throw new IllegalArgumentException(
						"Insufficient funds to transfer.");
			}
		}

	}

	public boolean checkIfWithdrawedIn(int days) {
		DateTime date = DateProvider.currentDate().minusDays(days);
		for (Transaction transaction : transactions) {
			if (!transaction.getType().equals(TransactionType.INTEREST_EARNED)
					&& date.isBefore(transaction.getTransactionDate())) {

				if (transaction.getAmount() < 0) {
					return true;
				}
			}
		}
		return false;
	}

	// TODO Daily job should run to add daily interest for the bank. This can be
	// done by scheduling a thread to run thread periodically at the end of day
	// which calls the function.
	public abstract void addDailyInterest();

}
