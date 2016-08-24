package com.abc.service;

import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;

public class MaxiSavings extends Account {

	public static final double RATE_1 = 0.05;
	public static final double RATE_2 = 0.001;

	public static double baseRatePerDay = RATE_1 / 365;
	public static double reducedRatePerDay = RATE_2 / 365;

	public void addDailyInterest() {

		double interest = 0.0;

		if (getBalance() > 0) {
			if (checkIfWithdrawedIn(10))
				interest = getBalance() * reducedRatePerDay;
			else {
				interest = getBalance() * baseRatePerDay;

			}
			getTransactions().add(
					new Transaction(interest, TransactionType.INTEREST_EARNED));

		}

	}

}
