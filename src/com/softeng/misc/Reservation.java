package com.softeng.misc;

import java.util.Date;

public class Reservation {

	public static final String STATUS_ACTIVE = "active";
	public static final String STATUS_CANCELLED = "cancelled";
	
	private int id, customerId, roomId;
	private Date dateStart, dateEnd;
	private String status;
	
	public Reservation(int id, Date dateStart, Date dateEnd, int customerId, int roomId, String status) {
		this.id = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.customerId = customerId;
		this.roomId = roomId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getRoomId() {
		return roomId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}
	
	public String getStatus() {
		return status;
	}
	
}
