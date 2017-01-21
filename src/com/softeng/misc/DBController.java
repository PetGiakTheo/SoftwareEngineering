package com.softeng.misc;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import org.jfree.chart.ChartFactory;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
/*	public Discount[] showDiscount(){
		connect();
		ArrayList<Discount> discs = new ArrayList<Discount>();
		
		try {
			rs = stmt.executeQuery("select * from discounts ;");
			
			while(rs.next()) {
				Discount disc = new Discount(rs.getInt("id"),rs.getInt("hotel"),rs.getString("strDate"),rs.getString("endDate"), rs.getInt("percentage"));
				discs.add(disc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		disconnect();
		return discs.toArray(new Discount[1]);
	}   */
	
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
		
			
			String query ="select type,singleBeds from rooms1;";
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn,query);
			
			JFreeChart chart = ChartFactory.createBarChart3D("RoomsPerType", "Rooms", "Type", dataset);
			
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

