package com.softeng.window;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.softeng.misc.Customer;
import com.softeng.misc.GlobalItems;
import com.softeng.misc.Reservation;
import com.softeng.misc.Room;

public class ReceiptWindow {

	JFrame frmReceipt;
	private JTextArea txtReceipt = new JTextArea();
	private int hotel;
	private Customer customer;
	private Reservation reservation;
	private Room room;
	private final JLabel lblNewLabel = new JLabel("The receipt was sent to your email address");

	/**
	 * Create the application.
	 */
	public ReceiptWindow(int hotel, Room room, Customer customer, Reservation reservation) {
		this.hotel = hotel;
		this.room = room;
		this.customer = customer;
		this.reservation = reservation;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReceipt = new JFrame();
		frmReceipt.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/icon.png")));
		frmReceipt.setResizable(false);
		frmReceipt.setTitle("Receipt");
		frmReceipt.setBounds(100, 100, 300, 358);
		frmReceipt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frmReceipt.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -10, SpringLayout.EAST, frmReceipt.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtReceipt, -10, SpringLayout.EAST, frmReceipt.getContentPane());
		
		springLayout.putConstraint(SpringLayout.NORTH, txtReceipt, 20, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frmReceipt.getContentPane());
		frmReceipt.getContentPane().setLayout(springLayout);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOKClick();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnOK, 100, SpringLayout.WEST, frmReceipt.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnOK, -10, SpringLayout.SOUTH, frmReceipt.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnOK, -100, SpringLayout.EAST, frmReceipt.getContentPane());
		frmReceipt.getContentPane().add(btnOK);
		springLayout.putConstraint(SpringLayout.WEST, txtReceipt, 10, SpringLayout.WEST, frmReceipt.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtReceipt, -6, SpringLayout.NORTH, btnOK);
		txtReceipt.setEditable(false);
		frmReceipt.getContentPane().add(txtReceipt);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		frmReceipt.getContentPane().add(lblNewLabel);
		
		typeReceipt();
	}
	
	private void typeReceipt() {
		Calendar c = Calendar.getInstance();
		c.setTime(reservation.getDateStart());
		String startDate = Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(c.get(Calendar.MONTH)+1) + "/" + Integer.toString(c.get(Calendar.YEAR));
		c.setTime(reservation.getDateEnd());
		String endDate = Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(c.get(Calendar.MONTH)+1) + "/" + Integer.toString(c.get(Calendar.YEAR));
		
		int dayDiff = GlobalItems.diffInDays(reservation.getDateStart(), reservation.getDateEnd());
		String price = String.format("%.2f", dayDiff * (room.getType().equals(Room.TYPE_REGULAR) ? Room.PRICE_PER_DAY_REGULAR : Room.PRICE_PER_DAY_VIP) );
		
		String text = "";
		text += "PetGiakTheo hotels legal receipt begin\n";
		text += GlobalItems.HOTEL_NAMES[hotel-1] + " hotel\n\n";
		text += "Customer id: " + Integer.toString(customer.getId()) + ", " + customer.getLastName() + " " + customer.getFirstName() + "\n" ;
		text += "Booked room id: " + Integer.toString(reservation.getRoomId()) + "\n";
		text += "Reservation id: " + Integer.toString(reservation.getId()) + "\n";
		text += "From:\t" + startDate + "\n";
		text += "To:\t" + endDate + "\n";
		text += "Days staying:\t" + Integer.toString(dayDiff) + "\n";
		text += "Price:\t" + price + " Euros\n\n";
		text += "PetGiakTheo hotels legal receipt end\n";
		txtReceipt.setText(text);
		
	}
	
	private void btnOKClick() {
		if (GlobalItems.currentUser == null) {
			MainWindow window = new MainWindow();
			window.frmMain.setVisible(true);
		}
		frmReceipt.dispose();
	}
}
