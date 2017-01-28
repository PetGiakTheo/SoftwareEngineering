package com.softeng.window;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import com.softeng.misc.DBController;
import com.softeng.misc.GlobalItems;
import com.softeng.misc.User;

public class MainWindow {
	JFrame frmMain;
	private DBController database = new DBController();
	private final JPanel pnLogin = new JPanel();
	private final JPanel pnCustomer = new JPanel();
	private JButton btnMakeRes;
	private JButton btnEmployeeLogin;
	private JButton btnLogin;
	private JButton btnCustomerLogin;
	private JButton btnCancelRes = new JButton("Cancel a reservation");
	private JPasswordField txtPassword = new JPasswordField();
	private JLabel label;
	private JTextField txtUsername = new JTextField();;
	private JLabel label_1;
	private JLabel lblEmployeeLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMain.setVisible(true);

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
		frmMain = new JFrame();
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmMain.setTitle("Hotel Manager");
		frmMain.setBounds(100, 100, 430, 300);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(new CardLayout(0, 0));

		frmMain.getContentPane().add(pnLogin, "name_17134849150110");

		frmMain.getContentPane().add(pnCustomer, "name_17134869989315");
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancelRes, 120, SpringLayout.WEST, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancelRes, -120, SpringLayout.EAST, pnCustomer);
		pnCustomer.setLayout(sl_panel_1);

		JLabel lblCustomerPanel = new JLabel("Customer panel");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblCustomerPanel, 50, SpringLayout.NORTH, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblCustomerPanel, 150, SpringLayout.WEST, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblCustomerPanel, -150, SpringLayout.EAST, pnCustomer);
		lblCustomerPanel.setHorizontalAlignment(SwingConstants.CENTER);
		pnCustomer.add(lblCustomerPanel);

		btnEmployeeLogin = new JButton("Employee login");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnEmployeeLogin, 10, SpringLayout.NORTH, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnEmployeeLogin, -10, SpringLayout.EAST, pnCustomer);
		pnCustomer.add(btnEmployeeLogin);

		btnMakeRes = new JButton("Make a reservation");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnMakeRes, 20, SpringLayout.SOUTH, lblCustomerPanel);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnMakeRes, 120, SpringLayout.WEST, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnMakeRes, -120, SpringLayout.EAST, pnCustomer);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCancelRes, 15, SpringLayout.SOUTH, btnMakeRes);
		pnCustomer.add(btnMakeRes);
		pnLogin.setLayout(new MigLayout("", "[8px][21px][21px][8px][99.00px][121.00,center][][][]", "[17px][23px][23px][23px][][][]"));
				
				lblEmployeeLogin = new JLabel("Employee login");
				pnLogin.add(lblEmployeeLogin, "cell 4 1 2 1,alignx center");
		
				label_1 = new JLabel("Username:");
				pnLogin.add(label_1, "cell 4 2,alignx right,aligny center");
				txtUsername.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
							btnLoginClick();
					}
				});
		
				txtUsername.setColumns(10);
				pnLogin.add(txtUsername, "cell 5 2,growx,aligny bottom");
		
				label = new JLabel("Password:");
				pnLogin.add(label, "cell 4 3,alignx right,aligny center");
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLoginClick();
			}
		});

		pnLogin.add(txtPassword, "cell 5 3,growx,aligny top");
		
				btnLogin = new JButton("Login");
				
						pnLogin.add(btnLogin, "cell 4 5 2 1,alignx center,aligny top");
		
				btnCustomerLogin = new JButton("I am a customer");
				
						pnLogin.add(btnCustomerLogin, "cell 4 6 2 1,alignx center,aligny top");

		pnLogin.setVisible(false);
		pnCustomer.setVisible(true);

	}

	private void Events() {

		btnCustomerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnLogin.setVisible(false);
				pnCustomer.setVisible(true);
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginClick();
			}
		});

		btnMakeRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMakeReservationClick();
			}
		});
		pnCustomer.add(btnCancelRes);

		btnCancelRes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnCancelReservationClick();
			}
		});

		btnEmployeeLogin.addActionListener(new ActionListener() {
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
		User emp = database.authenticate(txtUsername.getText(), txtPassword.getText());
		if (emp == null)
			JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
		else if (emp.getType().equals(User.TYPE_ADMIN)) {
			try {
				GlobalItems.currentUser = emp;
				frmMain.setVisible(false);
				ManagerWindow window1 = new ManagerWindow();
				window1.frmManager.setVisible(true);
				JOptionPane.showMessageDialog(null, "Welcome, " + txtUsername.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (emp.getType().equals(User.TYPE_STAFF)) {
			try {
				GlobalItems.currentUser = emp;
				frmMain.setVisible(false);
				EmployeeWindow window1 = new EmployeeWindow();
				window1.frmEmployee.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			emp.printData();
			JOptionPane.showMessageDialog(null, "Invalid user type.\nThis should never happen.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnMakeReservationClick() {
		frmMain.setVisible(false);
		RoomSearchWindow window = new RoomSearchWindow();
		window.frmRoomSearch.setVisible(true);
	}

	private void btnCancelReservationClick() {
		frmMain.setVisible(false);
		CancelReservationWindow window = new CancelReservationWindow();
		window.frmCancelReservation.setVisible(true);
	}
}
