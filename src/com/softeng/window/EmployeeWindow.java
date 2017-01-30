package com.softeng.window;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.softeng.misc.GlobalItems;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeWindow {

	JFrame frmEmployee;

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
		JOptionPane.showMessageDialog(null, "Welcome " + GlobalItems.currentUser.getUsername() + "!", "Notice", JOptionPane.INFORMATION_MESSAGE);
		frmEmployee = new JFrame();
		frmEmployee.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/icon.png")));
		if (GlobalItems.currentUser != null)
			frmEmployee.setTitle("Employee - " + GlobalItems.currentUser.getUsername());
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
		
		JButton btnCancelReservation = new JButton("Cancel a reservation");
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelReservationsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelReservation, 20, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelReservation, 120, SpringLayout.WEST, frmEmployee.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancelReservation, -120, SpringLayout.EAST, frmEmployee.getContentPane());
		frmEmployee.getContentPane().add(btnCancelReservation);
		
		JButton btnMngRooms = new JButton("Manage rooms");
		btnMngRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnManageRoomsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMngRooms, 20, SpringLayout.SOUTH, btnCancelReservation);
		springLayout.putConstraint(SpringLayout.WEST, btnMngRooms, 0, SpringLayout.WEST, btnCancelReservation);
		springLayout.putConstraint(SpringLayout.EAST, btnMngRooms, 0, SpringLayout.EAST, lblNewLabel);
		frmEmployee.getContentPane().add(btnMngRooms);
		
		JButton btnSearchRooms = new JButton("Search for rooms");
		btnSearchRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchRoomsClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSearchRooms, 20, SpringLayout.SOUTH, btnMngRooms);
		springLayout.putConstraint(SpringLayout.WEST, btnSearchRooms, 0, SpringLayout.WEST, btnCancelReservation);
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
	
	private void btnCancelReservationsClick() {
		CancelReservationWindow window = new CancelReservationWindow();
		window.frmCancelReservation.setVisible(true);
	}
	
	private void btnSearchRoomsClick() {
		RoomSearchWindow window = new RoomSearchWindow();
		window.frmRoomSearch.setVisible(true);
	}
	
	private void btnLogoutClick() {
		GlobalItems.currentUser = null;
		frmEmployee.setVisible(false);
		MainWindow window = new MainWindow();
		window.frmMain.setVisible(true);
	}
}
