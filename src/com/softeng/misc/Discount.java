package com.softeng.misc;

import java.sql.Date;

public class Discount {
	
	private int id;
	private int hotel;
	private String strDate;
	private String endDate;
	private int percentage;
	
	
	
	public Discount(int id,int hotel,String strDate,String endDate,int percentage){
		this.id = id;
		this.hotel = hotel;
		this.strDate = strDate;
		this.endDate = endDate;
		this.percentage = percentage;
	}
	
	public int getid() {
		return id;
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
