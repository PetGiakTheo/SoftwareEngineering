package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.softeng.misc.Customer;
import com.softeng.misc.DBController;
import com.softeng.misc.Reservation;
import com.softeng.misc.Room;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerSignWindow {

	JFrame frmCustomerSign;
	private DBController database;
	
	private Room selectedRoom;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtCardNum;
	private JPasswordField txtCSV;
	private JComboBox cbCardType = new JComboBox();
	
	private int selectedHotel;
	private Date start, end;

	// TODO remove main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSignWindow window = new CustomerSignWindow(0, null, null, null);
					window.frmCustomerSign.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerSignWindow(int selectedHotel, Room selectedRoom, Date start, Date end) {
		this.selectedHotel = selectedHotel;
		this.selectedRoom = selectedRoom;
		this.start = start;
		this.end = end;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DBController();
		frmCustomerSign = new JFrame();
		frmCustomerSign.setTitle("Customer signup");
		frmCustomerSign.setBounds(100, 100, 450, 340);
		frmCustomerSign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCustomerSign.getContentPane().setLayout(springLayout);
		
		JLabel lblSelectedRoom = new JLabel("Thank you for selecting room ");
		lblSelectedRoom.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectedRoom, 7, SpringLayout.NORTH, frmCustomerSign.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSelectedRoom, 110, SpringLayout.WEST, frmCustomerSign.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSelectedRoom, -110, SpringLayout.EAST, frmCustomerSign.getContentPane());
		frmCustomerSign.getContentPane().add(lblSelectedRoom);
		
		JLabel lblNewLabel = new JLabel("Please provide your details.");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -110, SpringLayout.EAST, frmCustomerSign.getContentPane());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, lblSelectedRoom);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 110, SpringLayout.WEST, frmCustomerSign.getContentPane());
		frmCustomerSign.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, lblSelectedRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 187, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, lblSelectedRoom);
		frmCustomerSign.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[][][grow][][grow]", "[][][][][][][]"));
		
		JLabel lblFirstName = new JLabel("First name:");
		panel.add(lblFirstName, "cell 1 0,alignx right");
		
		txtFirstName = new JTextField();
		panel.add(txtFirstName, "cell 2 0,growx");
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name:");
		panel.add(lblLastName, "cell 1 1,alignx trailing");
		
		txtLastName = new JTextField();
		panel.add(txtLastName, "cell 2 1,growx");
		txtLastName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		panel.add(lblEmail, "cell 1 2,alignx trailing");
		
		txtEmail = new JTextField();
		panel.add(txtEmail, "cell 2 2,growx");
		txtEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		panel.add(lblPhoneNumber, "cell 1 3,alignx trailing");
		
		txtPhone = new JTextField();
		panel.add(txtPhone, "cell 2 3,growx");
		txtPhone.setColumns(10);
		
		JLabel lblCardType = new JLabel("Card type:");
		panel.add(lblCardType, "cell 1 4,alignx trailing");
		
		cbCardType.setModel(new DefaultComboBoxModel(new String[] {"Credit", "Debit"}));
		panel.add(cbCardType, "cell 2 4,alignx left");
		
		JLabel lblCardNumber = new JLabel("Card number:");
		panel.add(lblCardNumber, "cell 1 5,alignx trailing");
		
		txtCardNum = new JTextField();
		panel.add(txtCardNum, "cell 2 5,growx");
		txtCardNum.setColumns(10);
		
		JLabel lblCsv = new JLabel("CSV:");
		panel.add(lblCsv, "cell 1 6,alignx trailing");
		
		txtCSV = new JPasswordField();
		txtCSV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSignupClick();
			}
		});
		panel.add(txtCSV, "cell 2 6,growx");
		
		JButton btnSignup = new JButton("Sign up and book");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignupClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSignup, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, btnSignup, 120, SpringLayout.WEST, frmCustomerSign.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSignup, -120, SpringLayout.EAST, frmCustomerSign.getContentPane());
		frmCustomerSign.getContentPane().add(btnSignup);
		if (selectedRoom != null)
			lblSelectedRoom.setText(lblSelectedRoom.getText() + Integer.toString(selectedRoom.getId()) + "!");
	}
	
	private void btnSignupClick() {
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String email = txtEmail.getText();
		String phone = txtPhone.getText();
		String cardType = cbCardType.getSelectedIndex() == 0 ? Customer.CREDIT_CARD : Customer.DEBIT_CARD;
		
		Customer cust = database.signupCustomer(firstName, lastName, email, phone, cardType);
		if (cust == null) {
			JOptionPane.showMessageDialog(null, "There was an unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Reservation res;
		res = database.addReservation(selectedHotel, start, end, cust.getId(), selectedRoom.getId(), Reservation.STATUS_ACTIVE);
		if (res == null) {
			JOptionPane.showMessageDialog(null, "There was an unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, "The room was booked successfully!\nWe hope you enjoy your stay.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		frmCustomerSign.dispose();
		ReceiptWindow window = new ReceiptWindow(selectedHotel, selectedRoom, cust, res);
		window.frmReceipt.setVisible(true);
	}
}
