package com.softeng.misc;

public class Discount {
	
	private int hotel;
	private String strDate;
	private String endDate;
	private int percentage;
	
	public Discount(int hotel, String strDate, String endDate, int percentage){
		this.hotel = hotel;
		this.strDate = strDate;
		this.endDate = endDate;
		this.percentage = percentage;
	}

	public int gethotel() {
		return hotel;
	}

	public String getDate() {
		return strDate;
	}

	public String getendDate() {
		return endDate;
	}

	public int getpercentage() {
		return percentage;
	}

}
