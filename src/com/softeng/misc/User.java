package com.softeng.misc;

public class User {

	public static final String TYPE_STAFF = "staff";
	public static final String TYPE_ADMIN = "admin";
	
	private int id;
	private String type;
	private String username;
	private String password;
	
	public User(int id, String username, String password, String type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
