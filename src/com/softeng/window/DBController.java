package com.softeng.window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class DBController {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	java.util.Date d12,d22;
	java.sql.Date sqldate1,sqldate2;

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
			//stmt.executeUpdate("insert into staff values(1, 'Theofilos', 'malakas')");
			//rs = stmt.executeUpdate("insert into staff values(1, 'Theofilos', 'malakas'");
			//fillData();
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
	
	public void fillData() {
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
	
	public Room[] findRooms(int hotel, int singleBeds, int doubleBeds, int children, boolean vip, boolean sale) {
		connect();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		String table = "rooms" + Integer.toString(hotel);
		String strSingleBeds = Integer.toString(singleBeds);
		String strDoubleBeds = Integer.toString(doubleBeds);
		String strChildren = Integer.toString(children);
		String strVip = vip ? Room.TYPE_VIP : Room.TYPE_REGULAR;
		String strSale = sale ? "true" : "false";
		
		try {
			rs = stmt.executeQuery("select * from " + table + " where singleBeds=" + strSingleBeds + " and doubleBeds=" + strDoubleBeds + " and children=" + strChildren + " and type='" + strVip + "' and sale=" + strSale);
			
			while(rs.next()) {
				Room room = new Room(rs.getInt("id"), rs.getInt("singleBeds"), rs.getInt("doubleBeds"), rs.getString("children"),  rs.getBoolean("sale"));
				rooms.add(room);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return rooms.toArray(new Room[1]); // Convert to an array before returning.
	}
	
	public void addDiscount(int hotel, Date d1, Date d2, int dis){
		
		connect();
		
			
			sqldate1 = new java.sql.Date(d1.getTime());
			sqldate2 = new java.sql.Date(d2.getTime());
			
			try {
				stmt.executeUpdate("insert into discounts (hotel,strDate,endDate,percentage) values(" + Integer.toString(hotel) + ", '" + sqldate1 + "' , '" + sqldate2 + "' , " + Integer.toString(dis) + ")" );
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		
		
		disconnect();
		
	}
	
	public Employee authenticate(String username, String password) {
		Employee employee = null;
		connect();
		
		try {
			rs = stmt.executeQuery("select * from employees1 where username='" + username + "' and password='" + password + "'");
			
			if (rs.next()) {
				employee = new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return employee;
	}
}
