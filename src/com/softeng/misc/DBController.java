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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;




public class DBController {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private java.util.Date d12,d22;
	private java.sql.Date sqldate1,sqldate2;
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
	
	private ArrayList<Room> exclude(ArrayList<Room> list1, ArrayList<Room> list2) {
		// This method removes any element found in list2 from list1.
		
		ArrayList<Room> ret = new ArrayList<Room>();
		
		for (int i = 0; i < list1.size(); i++) {
			Room room = list1.get(i);
			boolean toBeRemoved = false; // True if this element is to be removed.
			
			for (int j = 0; j < list2.size(); j++) {
				Room room2 = list2.get(j);
				
				if (room.getId() == room2.getId()) {
					toBeRemoved = true;
					break;
				}
			}
			
			if (!toBeRemoved)
				ret.add(room);
			
		}
		return ret;
	}

	// TODO Delete.
	public void fillRoomData() {
		// Fill db with random rooms
		
		
		connect();
		Random r = new Random();
		final int amount = 50;
		
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
	
	// TODO Delete.
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

	// TODO Delete.
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
				
				String query = "insert into reservations1 values(" + Integer.toString(i+1) + ",'" + dateStart + "', '" + dateEnd + "'," + customer + "," + room + ",'" + status + "')";
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
	
	// TODO fix for ignore.
	public Room[] findRooms(int hotel, int singleBeds, int doubleBeds, String type, Date availableFrom, Date availableTo, boolean ignoreDate) {
		/*
		 * This method returns all rooms that match the criteria in the arguments and
		 * are available at the date range in the arguments
		 */
		// (StartA <= EndB) and (EndA >= StartB)
		/*
		 * select * from rooms1 left outer join reservations1 on rooms1.id = room_id
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
		String query = "select * from " + table + " left outer join reservations" + Integer.toString(hotel) + " on " + table + ".id=room_id  where ";
		query += condSglBeds + condDblBeds + condType;
		if (rangeStart != null && rangeEnd != null) {
			query += " and (start is null or status = '" + Reservation.STATUS_CANCELLED + "' or start > " + rangeEnd + " or end < " + rangeStart + ")";
		}
		query += " order by " + table + ".id";
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			
			int i = 0;
			while(rs.next()) {
				Room room = new Room(rs.getInt(table + ".id"), rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("type"));
				
				// Omit duplicate rooms.
				if (i != room.getId()) {
					i = room.getId();
					rooms.add(room);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// The final result is the entries of the first query minus the entries of the following query
		ArrayList<Room> rooms2 = new ArrayList<Room>();
		String query2 = "select * from " + table + " inner join reservations" + Integer.toString(hotel) + " on " + table + ".id=room_id  where ";
		query2 += condSglBeds + condDblBeds + condType;
		if (rangeStart != null && rangeEnd != null) {
			query2 += " and (status='" + Reservation.STATUS_ACTIVE + "' and (start <= " + rangeEnd + " and end >= " + rangeStart + "))";
		}
		query2 += " order by " + table + ".id";
		System.out.println(query2);
		
		try {
			rs = stmt.executeQuery(query2);
			
			int i = 0;
			while(rs.next()) {
				Room room = new Room(rs.getInt(table + ".id"), rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("type"));
				
				// Omit duplicate rooms.
				if (i != room.getId()) {
					i = room.getId();
					rooms2.add(room);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
		rooms = exclude(rooms, rooms2); // Remove the contents of rooms2 from rooms.
		
		return rooms.toArray(new Room[0]); // Convert to an array before returning.
	}
	
	public Reservation[] getDate(){
		Reservation res = null;
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		connect();
		
		try {
			rs = stmt.executeQuery("select * from reservati ons1");
			
			while(rs.next()) {
				res = new Reservation(rs.getInt("id"),rs.getDate("start"),rs.getDate("end"), rs.getInt("cust_id"),rs.getInt("room_id"),rs.getString("status"));
				reserv.add(res);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return reserv.toArray(new Reservation[0]);
	}
	
	public User[] showUsers(){
		User Us = null;
		ArrayList<User> Users = new ArrayList<User>();
		connect();
		try {
			rs = stmt.executeQuery("select * from users ;");
			
			while(rs.next()) {
				Us = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"), rs.getString("type"));
				Users.add(Us);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return Users.toArray(new User[0]);
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
		return discount.toArray(new Discount[0]);
	}
	
	public Customer signupCustomer(String firstName, String lastName, String email, String phone, String cardType) {
		/* 
		 * This method adds a customer to the database and returns him. If one with these details already exists
		 * then he is returned instead and nothing is added.
		 */
		
		connect();
		Customer cust = null;
		
		
		try {
			// Get the id of the last entry.
			int lastId = 0;
			rs = stmt.executeQuery("select id from customers order by id desc limit 1");
			if (rs.next()) {
				lastId = rs.getInt("id");
			}
			
			rs = stmt.executeQuery("select * from customers where firstName='" + firstName + "' and lastName = '"
					+ lastName + "' and email='" + email + "' and phone='" + phone + "' and cardType='" + cardType + "'");
			
			if (rs.next()) {
				cust = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("phone"), rs.getString("cardType"));
			}
			else {
				cust = new Customer(lastId+1, firstName, lastName, email, phone, cardType);
				stmt.executeUpdate("insert into customers values (" + cust.getId() + ",'" + cust.getFirstName() + "', '"
						+ cust.getLastName() + "', '" + cust.getEmail() + "', '" + cust.getPhoneNumber() + "', '" + cust.getCardType() + "')");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		System.out.println(cust);
		return cust;
		
	}
	
	public Reservation addReservation(int hotel, Date dateStart, Date dateEnd, int custId, int roomId, String status) {
		Reservation reservation = null;
		connect();
		
		int lastId = 0;
		
		try {
			String table = "reservations" + Integer.toString(hotel);
			rs = stmt.executeQuery("select id from " + table + " order by id desc limit 1");
			
			if (rs.next())
				lastId = rs.getInt("id"); 
			
			Calendar c = Calendar.getInstance();
			c.setTime(dateStart);
			String start = Integer.toString(c.get(Calendar.YEAR)) + "-" + Integer.toString(c.get(Calendar.MONTH)+1) + "-" + Integer.toString(c.get(Calendar.DAY_OF_MONTH));
			c.setTime(dateEnd);
			String end = Integer.toString(c.get(Calendar.YEAR)) + "-" + Integer.toString(c.get(Calendar.MONTH)+1) + "-" + Integer.toString(c.get(Calendar.DAY_OF_MONTH));
			
			stmt.executeUpdate("insert into " + table + " values(" + Integer.toString(lastId+1) + ",'" + start
					+ "', '" + end + "'," + Integer.toString(custId) + "," + Integer.toString(roomId) + ",'" + status + "')");
			
			reservation = new Reservation(lastId+1, dateStart, dateEnd, custId, roomId, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return reservation;
	}
	
	public Room getRoomWithId(int hotel, int id) {
		Room room = null;
		connect();
		
		try {
			rs = stmt.executeQuery("select * from rooms" + Integer.toString(hotel) + " where id=" + Integer.toString(id));
			if (rs.next()) {
				room = new Room(id, rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return room;
	}
	public void deleteStat(){
		connect();
		
		try {
			stmt.executeUpdate("delete from statistics;");
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		disconnect();
	}
	
	public void saveRoomAtId(int hotel, int id, Room room) {
		connect();
		
		try {
			stmt.executeUpdate("update rooms" + Integer.toString(hotel) + " set singleBeds=" + Integer.toString(room.getSingleBeds()) + ",doubleBeds=" + Integer.toString(room.getDoubleBeds()) + ",type='" + room.getType() + "' where id=" + Integer.toString(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	public void addDate(String d, int p){
		connect();
		
		
		
		try {
			stmt.executeUpdate("insert into statistics values('" + d + "'," + p + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public boolean findActiveReservation(int hotel, int resId, int roomId, int custId) {
		// Extra arguments used for verification.
		boolean found = false;
		connect();
		
		try {
			String table = "reservations" + Integer.toString(hotel);
			rs = stmt.executeQuery("select * from " + table + " where id=" + Integer.toString(resId) + " and cust_id=" + Integer.toString(custId) + " and room_id=" + Integer.toString(roomId) + " and status='" + Reservation.STATUS_ACTIVE + "'");
			if (rs.next())
				found = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return found;
	}
	
	public void cancelReservation(int hotel, int id) {
		connect();
		try {
			String table = "reservations" + Integer.toString(hotel);
			stmt.executeUpdate("update " + table + " set status='" + Reservation.STATUS_CANCELLED +"' where id=" + Integer.toString(id));
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
				int r =	JOptionPane.showConfirmDialog(null, "The Hotel "+hotel+" already has a discount. Do you want to remove this and add this one?");
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
	public void signupUser(String username,String password,String type){
		connect();
		
		try {
			
			rs = stmt.executeQuery("select * from users where username='" + username + "'");
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				stmt.executeUpdate("insert into users (username,password,type) values('" + username + "','" + password + "','" + type + "');");
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
		
		
		
		String query ="select months,income from statistics;";
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn,query);
			//dataset.setValue(70, "slap", "columnKey");
			
			JFreeChart chart = ChartFactory.createLineChart("INCOME", "Months", "Euros", dataset);
			
			CategoryPlot plot = chart.getCategoryPlot();
			plot.setRangeGridlinePaint(Color.black);
			chartPanel = new ChartPanel(chart);
	//	rs = stmt.executeQuery(query);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		disconnect();
	
	}
	
	
	public User authenticate(String username, String password) {
		User employee = null;
		connect();
		
		try {
			rs = stmt.executeQuery("select * from users where username='" + username + "' and password='" + password + "'");
			
			if (rs.next()) {
				employee = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return employee;
	}

	public void deleteUser(String username) {
		connect();
		try {
			stmt.executeUpdate("delete from users where username = '" + username + "';");
			JOptionPane.showMessageDialog(null, "Done.");
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		disconnect();
	}
}

