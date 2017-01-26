package com.softeng.misc;

public class Customer {

	public static final String DEBIT_CARD = "debit";
	public static final String CREDIT_CARD = "credit";
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String cardType;
	
	public Customer(int id, String firstname, String lastName, String email, String phoneNumber, String cardType) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardType = cardType;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCardType() {
		return cardType;
	}
	
	public void printData() {
		System.out.println("Id:\t\t" + Integer.toString(id));
		System.out.println("FirstName:\t" + firstName);
		System.out.println("LastName:\t" + lastName);
		System.out.println("Email:\t\t" + email);
		System.out.println("Phone number:\t" + phoneNumber);
		System.out.println("Card type:\t" + cardType);
	}
}
