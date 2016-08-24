package com.abc.service;

import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;

public class Savings extends Account {

	public static final double RATE_TIER_1 = 0.001;
	public static final double RATE_TIER_2 = 0.002;
	public static double tier1RatePerDay = RATE_TIER_1 / 365;
	public static double tier2RatePerDay = RATE_TIER_2 / 365;

 

	public void addDailyInterest() {

		double interest = 0.0;

		if (getBalance() > 0) {
			if (getBalance() <= 1000)
				interest = getBalance() * tier1RatePerDay;
			else {
				interest = (1000 * tier1RatePerDay)
						+ ((getBalance() - 1000) * tier2RatePerDay);

			}
			getTransactions().add(
					new Transaction(interest, TransactionType.INTEREST_EARNED));

		}

	}

}
