package com.softeng.misc;

public class Room {
	
	public static final String TYPE_REGULAR = "reg";
	public static final String TYPE_VIP = "vip";
	public static final float PRICE_PER_DAY_REGULAR = 30;
	public static final float PRICE_PER_DAY_VIP = 100;
	
	private String type;
	private int id;
	private int singleBeds;
	private int doubleBeds;
	
	public Room (int id, int singleBeds, int doubleBeds,  String type) {
		this.id = id;
		this.singleBeds = singleBeds;
		this.doubleBeds = doubleBeds;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public int getSingleBeds() {
		return singleBeds;
	}

	public int getDoubleBeds() {
		return doubleBeds;
	}
}
