package com.abc.service;

 
public enum AccountType {
	
	CHECKING("Checking Account"), SAVINGS("Savings Account"), MAXI_SAVINGS(
			"Maxi Savings Account");

	private final String name;

	private AccountType(String s) {
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
