package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.service.Account;
import com.abc.service.AccountType;
import com.abc.service.Checking;
import com.abc.service.MaxiSavings;
import com.abc.service.Savings;

public class Bank {

	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<Customer>();
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public String customerSummary() {
		StringBuffer summary = new StringBuffer("Customer Summary");
		for (Customer c : customers)
			summary.append("\n - ").append(c.getName()).append(" (")
					.append(format(c.getNumberOfAccounts(), "account"))
					.append(")");
		return summary.toString();
	}

	// Make sure correct plural of word is created based on the number passed
	// in:
	// If number passed in is 1 just return the word otherwise add an 's' at the
	// end
	private String format(int number, String word) {
		return number + " " + (number <= 1 ? word : word + "s");
	}

	public double totalInterestPaid() {
		double total = 0;
		for (Customer customer : customers)
			total += customer.totalInterestEarned();
		return roundOff(total);
	}

	public double roundOff( double value){
		return Math.round(value * 10000D) / 10000D;
		
	}
	public String getFirstCustomer() {
		try {
			// customers = null;
			return customers.get(0).getName();
		} catch (Exception e) {
 			return "Error";
		}
	}

	public static Account createAccount(AccountType type) {

		Account account = null;
		switch (type) {

		case SAVINGS:

			account = new Savings();
			break;

		case MAXI_SAVINGS:

			account = new MaxiSavings();
			break;

		case CHECKING:
			account = new Checking();
			break;

		default:
			throw new IllegalArgumentException("Invalid account-type requested");
 
		}

		return account;
	}
}
