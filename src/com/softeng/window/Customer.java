package com.softeng.window;

public class Customer {

	public static final String DEBIT_CARD = "debit";
	public static final String CREDIT_CARD = "credit";
	
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private Room room;
	private String paymentType;
	
	public Customer(String firstname, String lastName, String email, String contactNumber, String paymentType) {
		this.firstName = firstname;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.paymentType = paymentType;
	}
	
}
