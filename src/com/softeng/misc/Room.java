package com.softeng.misc;

public class Room {
	
	public static final String TYPE_REGULAR = "reg";
	public static final String TYPE_VIP = "vip";
	
	private String type;
	public int id;
	private int singleBeds;
	private int doubleBeds;
	private boolean occupied;
	private boolean sale;
	
	private double price;
	
	public Room (int id, int singleBeds, int doubleBeds,  String type, boolean sale) {
		this.id = id;
		this.singleBeds = singleBeds;
		this.doubleBeds = doubleBeds;
		this.sale = sale;
		
		this.type = type;
		occupied = false;
	}
	

}
