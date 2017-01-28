package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.softeng.misc.DBController;
import com.softeng.misc.GlobalItems;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CancelReservationWindow {

	JFrame frmCancelReservation;
	private JTextField txtCustId;
	private JTextField txtRoomId;
	private JComboBox cbHotel = new JComboBox();
	private DBController database;
	private JTextField txtResId;

	
	// TODO remove main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelReservationWindow window = new CancelReservationWindow();
					window.frmCancelReservation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelReservationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DBController();
		frmCancelReservation = new JFrame();
		frmCancelReservation.setTitle("Reservation cancel");
		frmCancelReservation.setBounds(100, 100, 450, 343);
		frmCancelReservation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCancelReservation.getContentPane().setLayout(springLayout);
		
		JLabel lblReservationCancel = new JLabel("Please fill in the following details to cancel your reservation.");
		springLayout.putConstraint(SpringLayout.WEST, lblReservationCancel, 30, SpringLayout.WEST, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblReservationCancel, -30, SpringLayout.EAST, frmCancelReservation.getContentPane());
		lblReservationCancel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblReservationCancel, 10, SpringLayout.NORTH, frmCancelReservation.getContentPane());
		frmCancelReservation.getContentPane().add(lblReservationCancel);
		
		JLabel lblNewLabel = new JLabel("They can all be found on your receipt.");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 5, SpringLayout.SOUTH, lblReservationCancel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 50, SpringLayout.WEST, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -50, SpringLayout.EAST, frmCancelReservation.getContentPane());
		frmCancelReservation.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 50, SpringLayout.WEST, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -118, SpringLayout.SOUTH, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -50, SpringLayout.EAST, frmCancelReservation.getContentPane());
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, lblNewLabel);
		frmCancelReservation.getContentPane().add(panel);
		
		JButton btnCancelReservation = new JButton("Cancel reservation");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelReservation, 10, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelReservation, 120, SpringLayout.WEST, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancelReservation, -120, SpringLayout.EAST, frmCancelReservation.getContentPane());
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelReservationClick();
			}
		});
		panel.setLayout(new MigLayout("", "[][][][grow][grow]", "[][][][][][][30.00,grow][0.00,grow][]"));
		
		JLabel lblHotel = new JLabel("Hotel:");
		panel.add(lblHotel, "cell 2 0,alignx trailing");
		
		String[] cbHotelContents = new String[5];
		for (int i = 0; i < 5; i++)
			cbHotelContents[i] = "Hotel " + Integer.toString(i + 1) + " - " + GlobalItems.hotelNames[i];
		cbHotel.setModel(new DefaultComboBoxModel(cbHotelContents));
		panel.add(cbHotel, "cell 3 0,growx");
		
		JLabel lblCustomerIdNumber = new JLabel("Customer id number:");
		panel.add(lblCustomerIdNumber, "cell 2 1,alignx right");
		
		txtCustId = new JTextField();
		panel.add(txtCustId, "cell 3 1,growx");
		txtCustId.setColumns(10);
		
		JLabel lblRoomIdNumber = new JLabel("Room id number:");
		panel.add(lblRoomIdNumber, "cell 2 2,alignx right");
		
		txtRoomId = new JTextField();
		panel.add(txtRoomId, "cell 3 2,growx");
		txtRoomId.setColumns(10);
		
		JLabel lblReservationIdNumber = new JLabel("Reservation id number:");
		panel.add(lblReservationIdNumber, "cell 2 3,aligny center");
		
		txtResId = new JTextField();
		txtResId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnCancelReservationClick();
			}
		});
		panel.add(txtResId, "cell 3 3,growx");
		txtResId.setColumns(10);
		frmCancelReservation.getContentPane().add(btnCancelReservation);
		
		JButton btnBack = new JButton("Go back");
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, frmCancelReservation.getContentPane());
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClick();
			}
		});
		frmCancelReservation.getContentPane().add(btnBack);
	}
	
	private void btnCancelReservationClick() {
		int hotel, resId, custId, roomId;
		hotel = cbHotel.getSelectedIndex() + 1;
		try {
			resId = Integer.parseInt(txtResId.getText());
			custId = Integer.parseInt(txtCustId.getText());
			roomId = Integer.parseInt(txtRoomId.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid id numbers.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (database.findActiveReservation(hotel, resId, roomId, custId)) {
			int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the reservation? This action is not reversible.\nPlease note that the penalty for cancelling a reservation is half its price.", "Confirm", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION) {
				database.cancelReservation(hotel, resId);
				JOptionPane.showMessageDialog(null, "Reservation cancelled.", "Notice", JOptionPane.INFORMATION_MESSAGE);
				txtResId.setText("");
				txtCustId.setText("");
				txtRoomId.setText("");
			}
		}
		else
			JOptionPane.showMessageDialog(null, "No active reservation found with that information.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	private void btnBackClick() {
		if (GlobalItems.currentUser == null) {
			MainWindow window = new MainWindow();
			window.frmMain.setVisible(true);
		}
		frmCancelReservation.dispose();
	}
}
