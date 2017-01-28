package com.softeng.window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.softeng.misc.Customer;
import com.softeng.misc.Reservation;
import com.softeng.misc.Room;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ReceiptWindow {

	JFrame frmReceipt;
	private JTextArea txtReceipt = new JTextArea();
	private int hotel;
	private Customer customer;
	private Reservation reservation;
	private Room room;
	private final JLabel lblNewLabel = new JLabel("The receipt was sent to your email address");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptWindow window = new ReceiptWindow(4, null, null, null);
					window.frmReceipt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		int dayDiff = diffInDays(reservation.getDateStart(), reservation.getDateEnd());
		String price = String.format("%.2f", dayDiff * (room.getType().equals(Room.TYPE_REGULAR) ? Room.PRICE_PER_DAY_REGULAR : Room.PRICE_PER_DAY_VIP) );
		
		String text = "";
		text += "PetGiakTheo hotels legal receipt begin\n";
		text += MainWindow.hotelNames[hotel-1] + " hotel\n\n";
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
		if (MainWindow.currentUser == null) {
			MainWindow window = new MainWindow();
			window.frmMain.setVisible(true);
		}
		frmReceipt.dispose();
	}
	
	private int diffInDays(Date d1, Date d2) {
		
		// This method returns the difference in days between 2 date objects.
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

	    if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
	        return Math.abs(c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR)) + 1;
	    else {
	        if (c2.get(Calendar.YEAR) > c1.get(Calendar.YEAR)) {
	            // Swap
	            Calendar temp = c1;
	            c1 = c2;
	            c2 = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = c1.get(Calendar.DAY_OF_YEAR);

	        while (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
	            c1.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years.
	            extraDays += c1.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - c2.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays + 1;
	    }
	}
}
