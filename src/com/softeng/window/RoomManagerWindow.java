package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.softeng.misc.DBController;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSplitPane;

public class RoomManagerWindow {

	public JFrame frmRoomManager;
	private DBController database;
	private JTextField textField;

	// TODO Remove main method.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomManagerWindow window = new RoomManagerWindow();
					window.frmRoomManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoomManagerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DBController();
		frmRoomManager = new JFrame();
		frmRoomManager.setTitle("Room manager");
		frmRoomManager.setBounds(100, 100, 450, 300);
		frmRoomManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRoomManager.getContentPane().setLayout(springLayout);
		
		JLabel lblEnterRoomId = new JLabel("Enter room id:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterRoomId, 40, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEnterRoomId, 84, SpringLayout.WEST, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().add(lblEnterRoomId);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 38, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 18, SpringLayout.EAST, lblEnterRoomId);
		frmRoomManager.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnLoadRoom = new JButton("Load room");
		springLayout.putConstraint(SpringLayout.NORTH, btnLoadRoom, -4, SpringLayout.NORTH, lblEnterRoomId);
		springLayout.putConstraint(SpringLayout.WEST, btnLoadRoom, 33, SpringLayout.EAST, textField);
		frmRoomManager.getContentPane().add(btnLoadRoom);
		
		JSplitPane splitPane = new JSplitPane();
		springLayout.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, splitPane, -72, SpringLayout.SOUTH, frmRoomManager.getContentPane());
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frmRoomManager.getContentPane().add(splitPane);
	}
}
