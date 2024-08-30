package com.mycompany.app;

public class NameNumber {
	private String lastName;
	private String telNumber;

	public NameNumber() {
	}

	public NameNumber(String name, String num) {
		lastName = name;
		telNumber = num;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTelNumber() {
		return telNumber;
	}
}
