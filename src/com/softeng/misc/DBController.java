package com.softeng.misc;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


import org.jfree.chart.ChartFactory;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.data.jdbc.JDBCCategoryDataset;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;




public class DBController {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	java.util.Date d12,d22;
	java.sql.Date sqldate1,sqldate2;
	public ChartPanel chartPanel;

	public DBController() {
		
	}
	
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connURL = "jdbc:mysql://localhost:3306/hotel";
			String user = "root";
			String pw = "test123";
			conn = DriverManager.getConnection(connURL, user, pw);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void disconnect() {
		try {
			conn.close();
			if(rs != null && !rs.isClosed())
				rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fillRoomData() {
		// Fill db with random rooms
		
		
		connect();
		Random r = new Random();
		final int amount = 100;
		
		try {
			for (int i = 0; i < amount;i++) {
				if ((i+1) % (amount/5) == 0)
					System.out.println(Integer.toString(i+1));
				
				int singleBeds = r.nextInt(4);
				int doubleBeds = r.nextInt(4);
				
				if (Math.max(singleBeds, doubleBeds) == 0) {
					if (r.nextInt(2) == 0)
						singleBeds++;
					else
						doubleBeds++;
				}
				
				int children = r.nextInt(4);
				boolean sale = r.nextInt(3) == 2;
				String type = r.nextInt(2) == 0 ? "'vip'" : "'reg'";
				
				
				stmt.executeUpdate("insert into rooms1 values(" + Integer.toString(i+1) + ", " + Integer.toString(singleBeds) + ", " + Integer.toString(doubleBeds) + ", " + type + ", " + Boolean.toString(sale) + ", " + Integer.toString(children) + ")");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public void fillCustomerData() {
		String chars = "1234567890";
		connect();
		Random r = new Random();
		final int amount = 100;
		
		try {
			for (int i = 0; i < amount;i++) {
				if ((i+1) % 20 == 0)
					System.out.println(i+1);
				
				
				String firstName = "name_" + Integer.toString(i+1);
				String lastName = "surname_" + Integer.toString(i+1);
				String email = lastName + "@gmail.com";
				String number = "";
				for (int j = 0; j < 10; j++)
					number += chars.charAt(r.nextInt(chars.length()));
				
				String paymentType = r.nextInt(2) == 0 ? Customer.CREDIT_CARD : Customer.DEBIT_CARD;
				
				stmt.executeUpdate("insert into customers values(" + Integer.toString(i+1) + ",'" + firstName + "', '" + lastName + "', '" + email + "', '" + number + "', '" + paymentType + "')");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public void fillReservationData() {
		connect();
		Random r = new Random();
		
		try {
			for (int i = 0 ;; i++) {
				
				int month = (i%12)+1;
				int day = (i/12) * 10 + r.nextInt(3) + 1;
				String dateStart = "2017-" + Integer.toString(month) + "-" + Integer.toString(day);
				String dateEnd = "2017-" + Integer.toString(month) + "-" + Integer.toString(day + r.nextInt(4)+1);
				String customer = Integer.toString(r.nextInt(100)+1);
				String room = Integer.toString(r.nextInt(50)+1);
				String status = r.nextInt(4) == 0 ? Reservation.STATUS_CANCELLED : Reservation.STATUS_ACTIVE;
				
				String query = "insert into reservations values(" + Integer.toString(i+1) + ",'" + dateStart + "', '" + dateEnd + "'," + customer + "," + room + ",1,'" + status + "')";
				stmt.executeUpdate(query);
				
				if (month == 12 && day >= 20) {
					System.out.println("Finishing at i = " + Integer.toString(i));
					break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public Room[] findRooms(int hotel, int singleBeds, int doubleBeds, int children, String type, boolean sale) {
		connect();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		String table = "rooms" + Integer.toString(hotel);
		String strSingleBeds = Integer.toString(singleBeds);
		String strDoubleBeds = Integer.toString(doubleBeds);
		String strChildren = Integer.toString(children);
		String strSale = sale ? "true" : "false";
		
		try {
			rs = stmt.executeQuery("select * from " + table + " where singleBeds=" + strSingleBeds + " and doubleBeds=" + strDoubleBeds + " and children=" + strChildren + " and type='" + type + "' and sale=" + strSale);
			
			while(rs.next()) {
				Room room = new Room(rs.getInt("id"), rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("children"));
				rooms.add(room);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return rooms.toArray(new Room[1]); // Convert to an array before returning.
	}
	
	public Room[] findRooms2(int hotel, int singleBeds, int doubleBeds, String type, Date availableFrom, Date availableTo, boolean ignoreDate) {
		// (StartA <= EndB) and (EndA >= StartB)
		/*
		 * select * from rooms1 left outer join reservations on rooms1.id = room_id and hotel = 1
		 * where start is null or start > '2017-2-1' or end < '2017-1-1' order by rooms1.id
		 */
		connect();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		String table = "rooms" + Integer.toString(hotel);
		
		String condSglBeds = "singleBeds=" + Integer.toString(singleBeds);
		String condDblBeds = " and doubleBeds=" + Integer.toString(doubleBeds);
		String condType = type == null ? "" : " and type='" + type + "'";
		String rangeStart, rangeEnd;
		rangeStart = rangeEnd = null;
		if (!ignoreDate && availableFrom != null && availableTo != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(availableFrom);
			rangeStart = "'" + Integer.toString(calendar.get(Calendar.YEAR)) + "-" + Integer.toString(calendar.get(Calendar.MONTH)+1) + "-" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "'";
			calendar.setTime(availableTo);
			rangeEnd = "'" + Integer.toString(calendar.get(Calendar.YEAR)) + "-" + Integer.toString(calendar.get(Calendar.MONTH)+1) + "-" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "'";
		}
		
		// Build query.
		String query = "select * from " + table + " left outer join reservations on " + table + ".id=room_id and hotel = " + Integer.toString(hotel) + " where ";
		query += condSglBeds + condDblBeds + condType;
		if (rangeStart != null && rangeEnd != null) {
			query += " and (start is null or start > " + rangeEnd + " or end < " + rangeStart + ")";
		}
		query += " order by " + table + ".id";
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				Room room = new Room(rs.getInt(table + ".id"), rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("type"));
				
				// Omit duplicate duplicate rooms.
				if (i != room.getId()) {
					i = room.getId();
					rooms.add(room);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return rooms.toArray(new Room[1]); // Convert to an array before returning.
	}
	
	public Discount[] showDiscount(){
		Discount disc = null;
		ArrayList<Discount> discount = new ArrayList<Discount>();
		connect();
		try {
			rs = stmt.executeQuery("select * from discounts ORDER BY hotel;");
			
			while(rs.next()) {
				disc = new Discount(rs.getInt("id"),rs.getInt("hotel"),rs.getString("strDate"),rs.getString("endDate"), rs.getInt("percentage"));
				discount.add(disc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return discount.toArray(new Discount[1]);
	}
	
	
	public Room getRoomWithId(int id) {
		Room room = null;
		connect();
		
		try {
			rs = stmt.executeQuery("select * from rooms1 where id=" + Integer.toString(id));
			if (rs.next()) {
				room = new Room(id, rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return room;
	}
	
	public void saveRoomAtId(int id, Room room) {
		connect();
		
		try {
			stmt.executeUpdate("update rooms1 set singleBeds=" + Integer.toString(room.getSingleBeds()) + ",doubleBeds=" + Integer.toString(room.getDoubleBeds()) + ",type='" + room.getType() + "' where id=" + Integer.toString(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	
	public void addDiscount(int hotel, Date d1, Date d2, int dis){
		
		connect();
		
			
			sqldate1 = new java.sql.Date(d1.getTime());
			sqldate2 = new java.sql.Date(d2.getTime());
			
			try {
				
				rs = stmt.executeQuery("select * from discounts where hotel='" + hotel + "'");
				
				if (rs.next()) {
				int r =	JOptionPane.showConfirmDialog(null, "The Hotel "+hotel+" has already a discount, do you want to remove this and add this one?");
				if (r==JOptionPane.YES_OPTION){
					stmt.executeUpdate("delete from discounts where hotel = '" + hotel + "';");
					stmt.executeUpdate("insert into discounts (hotel,strDate,endDate,percentage) values(" + Integer.toString(hotel) + ", '" + sqldate1 + "' , '" + sqldate2 + "' , " + Integer.toString(dis) + ")" );
					JOptionPane.showMessageDialog(null, "Discount Changed");
					
				}else{
					JOptionPane.showMessageDialog(null, "The old discount remained");
				}
				}else{
					stmt.executeUpdate("insert into discounts (hotel,strDate,endDate,percentage) values(" + Integer.toString(hotel) + ", '" + sqldate1 + "' , '" + sqldate2 + "' , " + Integer.toString(dis) + ")" );
					JOptionPane.showMessageDialog(null, "Discount Changed");
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		
		
		disconnect();
		
	}
	public void signup(String username,String password,String type){
		connect();
		
		try {
			
			rs = stmt.executeQuery("select * from employees1 where username='" + username + "'");
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
			stmt.executeUpdate("insert into employees1 (username,password,type) values('" + username + "','" + password + "','" + type + "');");
			JOptionPane.showMessageDialog(null, "Done.");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		disconnect();
	}

	public void showStats(){
		connect();
		try {
		
			
			String query ="select strDate,percentage from discounts;";
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn,query);
			
			JFreeChart chart = ChartFactory.createBarChart3D("RoomsPerType", "Type", "Rooms", dataset);
			
			CategoryPlot plot = chart.getCategoryPlot();
			plot.setRangeGridlinePaint(Color.black);
			chartPanel = new ChartPanel(chart);
	//	rs = stmt.executeQuery(query);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	
	}
	
	
	public User authenticate(String username, String password) {
		User employee = null;
		connect();
		
		try {
			rs = stmt.executeQuery("select * from employees1 where username='" + username + "' and password='" + password + "'");
			
			if (rs.next()) {
				employee = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return employee;
	}

	public void delete(String username) {
		connect();
		try {
			stmt.executeUpdate("delete from employees1 where username = '" + username + "';");
			JOptionPane.showMessageDialog(null, "Done.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
}

