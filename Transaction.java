package com.abc.transactions;

import org.joda.time.DateTime;

import com.abc.utilities.DateProvider;

public class Transaction {

	private final double amount;

	private DateTime transactionDate;
	private TransactionType type;
	

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	private static int counter = 0;

	public Transaction(double amount, TransactionType type) {
		this.amount = amount;
		this.transactionDate = DateProvider.currentDate();
		this.type = type;
	}

	public Transaction(DateTime date, double amount, TransactionType type) {
		this.amount = amount;
		this.transactionDate = date;
		this.type = type;

	}

	public DateTime getTransactionDate() {
		return transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type +" " +transactionDate + " " + amount + "\n";
	}

}
