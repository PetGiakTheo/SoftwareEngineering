package com.softeng.window;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MainWindow {
	DBController t = new DBController();
	private JFrame frmHotelKati;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private final ButtonGroup rgHotels = new ButtonGroup();
	private final JPanel pnLogin = new JPanel();
	private final JPanel pnManager = new JPanel();
	private final JPanel pnCustomer = new JPanel();
	private JButton btnRoomfinder;
	private JButton btnGotoLogin;
	private JButton btnLogin;
	private DBController database = new DBController();
	 
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 MainWindow window = new MainWindow();
					window.frmHotelKati.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		Events();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHotelKati = new JFrame();
		frmHotelKati.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmHotelKati.setTitle("Hotel Manager");
		frmHotelKati.setBounds(100, 100, 481, 448);
		frmHotelKati.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHotelKati.getContentPane().setLayout(new CardLayout(0, 0));
	
		
		
		
		
		frmHotelKati.getContentPane().add(pnLogin, "name_17134849150110");
		SpringLayout sl_panel = new SpringLayout();
		pnLogin.setLayout(sl_panel);
		
		txtPassword = new JPasswordField();
		pnLogin.add(txtPassword);
		
		JLabel lblPassword = new JLabel("Password:");
		sl_panel.putConstraint(SpringLayout.NORTH, txtPassword, -3, SpringLayout.NORTH, lblPassword);
		sl_panel.putConstraint(SpringLayout.WEST, txtPassword, 6, SpringLayout.EAST, lblPassword);
		pnLogin.add(lblPassword);
		
		txtUsername = new JTextField();
		sl_panel.putConstraint(SpringLayout.EAST, txtPassword, 0, SpringLayout.EAST, txtUsername);
		pnLogin.add(txtUsername);
		txtUsername.setColumns(10);
		
	
		
		JLabel lblUsername = new JLabel("Username:");
		sl_panel.putConstraint(SpringLayout.NORTH, txtUsername, -3, SpringLayout.NORTH, lblUsername);
		sl_panel.putConstraint(SpringLayout.WEST, txtUsername, 6, SpringLayout.EAST, lblUsername);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblUsername, -338, SpringLayout.SOUTH, pnLogin);
		sl_panel.putConstraint(SpringLayout.EAST, lblUsername, -299, SpringLayout.EAST, pnLogin);
		sl_panel.putConstraint(SpringLayout.NORTH, lblPassword, 17, SpringLayout.SOUTH, lblUsername);
		sl_panel.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
		pnLogin.add(lblUsername);
		
		frmHotelKati.getContentPane().add(pnCustomer, "name_17134869989315");
		SpringLayout sl_panel_1 = new SpringLayout();
		pnCustomer.setLayout(sl_panel_1);
		
		
		JLabel lblNewLabel = new JLabel("Select a hotel");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel, 50, SpringLayout.NORTH, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel, 14, SpringLayout.WEST, pnCustomer);
		pnCustomer.add(lblNewLabel);
		
		JRadioButton rdbtnHotel = new JRadioButton("Hotel 1");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnHotel, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_1.putConstraint(SpringLayout.EAST, rdbtnHotel, 0, SpringLayout.EAST, lblNewLabel);
		rgHotels.add(rdbtnHotel);
		pnCustomer.add(rdbtnHotel);
		
		JRadioButton rdbtnHotel_1 = new JRadioButton("Hotel 2");
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnHotel_1, 99, SpringLayout.NORTH, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnHotel_1, 10, SpringLayout.WEST, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnHotel, 0, SpringLayout.WEST, rdbtnHotel_1);
		rgHotels.add(rdbtnHotel_1);
		pnCustomer.add(rdbtnHotel_1);
		
		JRadioButton rdbtnHotel_2 = new JRadioButton("Hotel 3");
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnHotel_2, 10, SpringLayout.WEST, pnCustomer);
		rgHotels.add(rdbtnHotel_2);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnHotel_2, 6, SpringLayout.SOUTH, rdbtnHotel_1);
		pnCustomer.add(rdbtnHotel_2);
		
		JRadioButton rdbtnHotel_3 = new JRadioButton("Hotel 4");
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnHotel_3, 10, SpringLayout.WEST, pnCustomer);
		rgHotels.add(rdbtnHotel_3);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnHotel_3, 6, SpringLayout.SOUTH, rdbtnHotel_2);
		pnCustomer.add(rdbtnHotel_3);
		
		JRadioButton rdbtnHotel_4 = new JRadioButton("Hotel 5");
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnHotel_4, 10, SpringLayout.WEST, pnCustomer);
		rgHotels.add(rdbtnHotel_4);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnHotel_4, 6, SpringLayout.SOUTH, rdbtnHotel_3);
		pnCustomer.add(rdbtnHotel_4);
		
		JLabel lblCustomerPanel = new JLabel("Customer panel");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblCustomerPanel, 31, SpringLayout.NORTH, pnCustomer);
		pnCustomer.add(lblCustomerPanel);
		

		
		
		btnGotoLogin = new JButton("Login");
	
		
		sl_panel_1.putConstraint(SpringLayout.EAST, lblCustomerPanel, -119, SpringLayout.WEST, btnGotoLogin);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnGotoLogin, 10, SpringLayout.NORTH, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnGotoLogin, -10, SpringLayout.EAST, pnCustomer);
		pnCustomer.add(btnGotoLogin);
		
		btnRoomfinder = new JButton("Search for rooms");
		
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnRoomfinder, 173, SpringLayout.SOUTH, lblCustomerPanel);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnRoomfinder, 0, SpringLayout.EAST, lblCustomerPanel);
		pnCustomer.add(btnRoomfinder);
		
		
		
		JButton btnCustomerLogin = new JButton("I am a customer");
		sl_panel.putConstraint(SpringLayout.NORTH, btnCustomerLogin, 60, SpringLayout.SOUTH, txtPassword);
		sl_panel.putConstraint(SpringLayout.EAST, btnCustomerLogin, 0, SpringLayout.EAST, txtPassword);
		btnCustomerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnLogin.setVisible(false);
				pnCustomer.setVisible(true);
				
			}
		});
		pnLogin.add(btnCustomerLogin);
		
		btnLogin = new JButton("Login");
		sl_panel.putConstraint(SpringLayout.WEST, btnLogin, 175, SpringLayout.WEST, pnLogin);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnLogin, -6, SpringLayout.NORTH, btnCustomerLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginClick();
				
			
					
			
			}
		});
		pnLogin.add(btnLogin);
		
		
		
		pnLogin.setVisible(false);
		pnCustomer.setVisible(true);
	
	
	}
	private void Events() {
	
		btnRoomfinder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRoomfinderClick();
			}
		});
		
		btnGotoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGotoLoginClick();
			}
		});
	
	
	}
	
	private void btnGotoLoginClick() {
		pnCustomer.setVisible(false);
		pnLogin.setVisible(true);
		txtUsername.requestFocus();
		
	
	
	
		
	}
	
	
	private void btnLoginClick() {
		
		if(checkLogin(txtUsername.getText(),txtPassword.getText())){
			
			
			
				
					try {
						
						frmHotelKati.setVisible(false);
						Manager window1 = new Manager();																				
						window1.frmManager.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
		else
			JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
	}
			
	
	private void btnRoomfinderClick() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSearch window = new CustomerSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean checkLogin(String username, String password) {
		if (database.authenticate(username, password) == null)
			return false;
		return true;
	}
}
