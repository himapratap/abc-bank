package com.abc.transactions;

public enum TransactionType {
	DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal"), INTEREST_EARNED(
			"Interest Earned");

	private final String name;

	private TransactionType(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
