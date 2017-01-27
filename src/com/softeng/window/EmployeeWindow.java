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
import java.awt.Dialog.ModalExclusionType;

public class EmployeeWindow {

	JFrame frmEmployee;
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
		frmEmployee = new JFrame();
		frmEmployee.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
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
				btnManageReservationsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMngReservations, 20, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnMngReservations, 120, SpringLayout.WEST, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMngReservations, -120, SpringLayout.EAST, frmEmployee.getContentPane());
		frmEmployee.getContentPane().add(btnMngReservations);
		
		JButton btnMngRooms = new JButton("Manage rooms");
		btnMngRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnManageRoomsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMngRooms, 20, SpringLayout.SOUTH, btnMngReservations);
		springLayout.putConstraint(SpringLayout.WEST, btnMngRooms, 0, SpringLayout.WEST, btnMngReservations);
		springLayout.putConstraint(SpringLayout.EAST, btnMngRooms, 0, SpringLayout.EAST, lblNewLabel);
		frmEmployee.getContentPane().add(btnMngRooms);
		
		JButton btnSearchRooms = new JButton("Search for rooms");
		btnSearchRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchRoomsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSearchRooms, 20, SpringLayout.SOUTH, btnMngRooms);
		springLayout.putConstraint(SpringLayout.WEST, btnSearchRooms, 0, SpringLayout.WEST, btnMngReservations);
		springLayout.putConstraint(SpringLayout.EAST, btnSearchRooms, 0, SpringLayout.EAST, lblNewLabel);
		frmEmployee.getContentPane().add(btnSearchRooms);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogoutClick();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogout, -10, SpringLayout.SOUTH, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, frmEmployee.getContentPane());
		frmEmployee.getContentPane().add(btnLogout);
	}
	
	private void btnManageRoomsClick() {
		RoomManagerWindow window = new RoomManagerWindow(this);
		window.frmRoomManager.setVisible(true);
	}
	
	private void btnManageReservationsClick() {
		
	}
	
	private void btnSearchRoomsClick() {
		RoomSearchWindow window = new RoomSearchWindow();
		window.frmRoomSearch.setVisible(true);
	}
	
	private void btnLogoutClick() {
		MainWindow.currentUser = null;
		frmEmployee.setVisible(false);
		MainWindow window = new MainWindow();
		window.frmMain.setVisible(true);
	}
}
