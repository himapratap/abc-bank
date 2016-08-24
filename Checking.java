package com.abc.service;

import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;

public class Checking extends  Account{
	
	
	private static final double RATE_SLAB_1 = 0.001; 
    private static double ratePerDay = RATE_SLAB_1/365;

   	
	/**
	 * Interest rates should accrue daily (incl. weekends)
	 * 
	 */
	 

	public void addDailyInterest(){
		
		double interest = getBalance() * ratePerDay;
		getTransactions().add(new Transaction(interest, TransactionType.INTEREST_EARNED));
		

	}
	

}
