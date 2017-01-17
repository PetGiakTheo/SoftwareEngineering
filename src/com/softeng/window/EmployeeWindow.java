package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.softeng.misc.DBController;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeWindow {

	public JFrame frmEmployee;
	private DBController database;
	
	// TODO Delete main method.
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try {
					EmployeeWindow window = new EmployeeWindow();
					window.frmEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} 
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DBController();
		// TODO Delete following line.
		MainWindow.currentUser = database.authenticate("stelios", "ntou");
		frmEmployee = new JFrame();
		if (MainWindow.currentUser != null)
			frmEmployee.setTitle("Employee - " + MainWindow.currentUser.getUsername());
		else
			frmEmployee.setTitle("Employee - Unknown");
		frmEmployee.setBounds(100, 100, 450, 300);
		frmEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployee.setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		frmEmployee.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 120, SpringLayout.WEST, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -120, SpringLayout.EAST, frmEmployee.getContentPane());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmEmployee.getContentPane().add(lblNewLabel);
		
		JButton btnMngReservations = new JButton("Manage reservations");
		btnMngReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnManageReservationsClicked();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMngReservations, 20, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnMngReservations, 120, SpringLayout.WEST, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMngReservations, -120, SpringLayout.EAST, frmEmployee.getContentPane());
		frmEmployee.getContentPane().add(btnMngReservations);
		
		JButton btnMngRooms = new JButton("Manage rooms");
		btnMngRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnManageRoomsClicked();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMngRooms, 20, SpringLayout.SOUTH, btnMngReservations);
		springLayout.putConstraint(SpringLayout.WEST, btnMngRooms, 0, SpringLayout.WEST, btnMngReservations);
		springLayout.putConstraint(SpringLayout.EAST, btnMngRooms, 0, SpringLayout.EAST, lblNewLabel);
		frmEmployee.getContentPane().add(btnMngRooms);
		
		JButton btnSearchRooms = new JButton("Search for rooms");
		btnSearchRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchRoomsClicked();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSearchRooms, 20, SpringLayout.SOUTH, btnMngRooms);
		springLayout.putConstraint(SpringLayout.WEST, btnSearchRooms, 0, SpringLayout.WEST, btnMngReservations);
		springLayout.putConstraint(SpringLayout.EAST, btnSearchRooms, 0, SpringLayout.EAST, lblNewLabel);
		frmEmployee.getContentPane().add(btnSearchRooms);
	}
	
	private void btnManageRoomsClicked() {
		
	}
	
	private void btnManageReservationsClicked() {
		
	}
	
	private void btnSearchRoomsClicked() {
		
	}
}
