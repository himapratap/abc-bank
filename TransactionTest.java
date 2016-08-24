package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.abc.transactions.Transaction;
import com.abc.transactions.TransactionType;
import com.abc.utilities.DateProvider;

public class TransactionTest {

	@Test
	public void getLastNDaysTransactionsSize() {

		List<Transaction> transactions = createTransaction();
		int n = 2;
		DateTime date = DateProvider.currentDate().minusDays(n);

		List<Transaction> lastNDaysTransaction = new ArrayList<Transaction>();
		for (Transaction transaction : transactions) {
			if (!transaction.getType().equals(TransactionType.INTEREST_EARNED)
					&& date.isBefore(transaction.getTransactionDate())) {
				lastNDaysTransaction.add(transaction);
			}
		}

		assertEquals(2, lastNDaysTransaction.size());
	}

	private List<Transaction> createTransaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction(DateProvider.currentDate(), 100,
				TransactionType.DEPOSIT));

		for (int i = 1; i < 5; i++) {
			transactions.add(new Transaction(DateProvider.currentDate()
					.minusDays(i), i * 10, TransactionType.DEPOSIT));
		}
		return transactions;
	}
}
