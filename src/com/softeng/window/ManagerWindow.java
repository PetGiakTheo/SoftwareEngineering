package com.softeng.window;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


import com.softeng.misc.DBController;
import com.softeng.misc.User;
import com.toedter.calendar.JDateChooser;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;


public class ManagerWindow {

	private DBController database = new DBController();
	public JFrame frmManager;
	private JTextField txtdis;
	private JButton btnOk;
	private JDateChooser strDate;
	private JDateChooser endDate;
	private JLabel lblek;
	private JLabel lblek2;
	private JLabel lblek3;
	private JPanel pnlbls;
	private JPanel pnManageStaff;
	private JPanel pnDiscounts;
	private final ButtonGroup HotelGroup = new ButtonGroup();
	private JRadioButton rh1;
	private JRadioButton rh2;
	private JRadioButton rh3;
	private JRadioButton rh4;
	private JRadioButton rh5;
	java.util.Date d1, d2;
	java.sql.Date sqldt;
	private int htl;
	int dis;
	private JTextField txtUsernameQ;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordQ;
	private JPasswordField txtCfPassword;
	private JButton btnShow;
	private JButton btnDelete;
	private JButton btnSignup;
	private JButton btnLogout;
	private JComboBox cbType;
	public JPanel pnBox;
	private Toolkit t;
	private Dimension s;
	

	/**
	 * Launch the application.
	 */
	// /* TODO Remove main.
	 // DEN XREIAZETAI DEUTERH MAIN
	  public static void main(String[] args) { 
		  EventQueue.invokeLater(new Runnable() { 
			  public void run() { 
				  try {
				  ManagerWindow window = new ManagerWindow();
				  window.frmManager.setVisible(true);
				  } catch (Exception e) {
					  e.printStackTrace(); 
				  } 
			  } 
		  });
	  }
	 // */

	/**
	 * Create the application.
	 */
	public ManagerWindow() {
		initialize();
		event();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManager = new JFrame();
		frmManager.setLocationByPlatform(true);
		frmManager.getContentPane().setBackground(new Color(119, 136, 153));
		frmManager.setTitle("Manager");
		frmManager.setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerWindow.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmManager.setBounds(100, 100, 594, 510);

		frmManager.getContentPane().setLayout(new CardLayout(0, 0));
		frmManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (MainWindow.currentUser != null)
			frmManager.setTitle("Manager - " + MainWindow.currentUser.getUsername());
		else
			frmManager.setTitle("Manager - Unknown");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(105, 105, 105));
		frmManager.getContentPane().add(tabbedPane, "name_17059372492913");
				
				pnManageStaff = new JPanel();
				pnManageStaff.setBackground(SystemColor.activeCaption);
				tabbedPane.addTab("ManageStaff", null, pnManageStaff, null);
								
								JPanel pnAdd = new JPanel();
								pnAdd.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
								pnAdd.setBackground(new Color(176, 196, 222));
								pnAdd.setLayout(null);
								
										JLabel lblManageStaff = new JLabel("Sign up new Employee");
										lblManageStaff.setBounds(56, 11, 182, 51);
										pnAdd.add(lblManageStaff);
										
										JLabel lblUsername = new JLabel("            Username :");
										lblUsername.setBounds(38, 73, 125, 25);
										pnAdd.add(lblUsername);
										
										txtUsernameQ = new JTextField();
										txtUsernameQ.setBounds(163, 75, 103, 20);
										pnAdd.add(txtUsernameQ);
										txtUsernameQ.setColumns(10);
										
										JLabel lblPassword = new JLabel("             Password :");
										lblPassword.setBounds(38, 109, 125, 25);
										pnAdd.add(lblPassword);
										
										JLabel lblNewLabel = new JLabel("Confirm Password :");
										lblNewLabel.setBounds(28, 140, 125, 25);
										pnAdd.add(lblNewLabel);
										
										JPanel pnDelete = new JPanel();
										pnDelete.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
										pnDelete.setBackground(new Color(176, 196, 222));
										
										btnLogout = new JButton("Logout");
									
										GroupLayout gl_pnManageStaff = new GroupLayout(pnManageStaff);
										gl_pnManageStaff.setHorizontalGroup(
											gl_pnManageStaff.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnManageStaff.createSequentialGroup()
													.addComponent(pnAdd, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
													.addComponent(pnDelete, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.TRAILING, gl_pnManageStaff.createSequentialGroup()
													.addContainerGap(474, Short.MAX_VALUE)
													.addComponent(btnLogout)
													.addContainerGap())
										);
										gl_pnManageStaff.setVerticalGroup(
											gl_pnManageStaff.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnManageStaff.createSequentialGroup()
													.addGap(33)
													.addGroup(gl_pnManageStaff.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(pnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(pnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
													.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
													.addComponent(btnLogout)
													.addContainerGap())
										);
										pnDelete.setLayout(null);
										
										JLabel lblDeleteEmployee = new JLabel("Delete Employee");
										lblDeleteEmployee.setBounds(98, 34, 104, 14);
										pnDelete.add(lblDeleteEmployee);
										
										JLabel label_3 = new JLabel("            Username :");
										label_3.setBounds(10, 85, 125, 25);
										pnDelete.add(label_3);
										
										txtUsername = new JTextField();
										txtUsername.setColumns(10);
										txtUsername.setBounds(135, 87, 86, 20);
										pnDelete.add(txtUsername);
										
										JLabel label_4 = new JLabel("             Password :");
										label_4.setBounds(10, 119, 125, 25);
										pnDelete.add(label_4);
										
										txtPassword = new JPasswordField();
										txtPassword.setBounds(135, 121, 86, 20);
										pnDelete.add(txtPassword);
										
										btnDelete = new JButton("Delete");
										
										btnDelete.setBounds(150, 200, 71, 23);
										pnDelete.add(btnDelete);
										
										btnSignup = new JButton("Sign Up");
										
										btnSignup.setBounds(187, 279, 89, 23);
										pnAdd.add(btnSignup);
										
										txtPasswordQ = new JPasswordField();
										txtPasswordQ.setBounds(163, 111, 103, 20);
										pnAdd.add(txtPasswordQ);
										
										txtCfPassword = new JPasswordField();
										txtCfPassword.setBounds(163, 145, 103, 20);
										pnAdd.add(txtCfPassword);
										
										JLabel lblType = new JLabel("                          Type :");
										lblType.setBounds(28, 176, 125, 20);
										pnAdd.add(lblType);
										
										cbType = new JComboBox();
										cbType.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Staff"}));
										cbType.setBounds(163, 176, 72, 25);
										pnAdd.add(cbType);
										pnManageStaff.setLayout(gl_pnManageStaff);
		
				pnDiscounts = new JPanel();
				pnDiscounts.setBackground(SystemColor.activeCaption);
				tabbedPane.addTab("ManageDiscounts", null, pnDiscounts, null);
				pnDiscounts.setBorder(null);
				
						pnlbls = new JPanel();
						pnlbls.setBackground(SystemColor.inactiveCaption);
						pnlbls.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
						pnlbls.setLayout(null);
						
								lblek = new JLabel("");
								lblek.setBounds(10, 11, 325, 20);
								pnlbls.add(lblek);
lblek.setVerticalAlignment(SwingConstants.TOP);
lblek.setHorizontalAlignment(SwingConstants.LEFT);

lblek2 = new JLabel("");
lblek2.setBounds(10, 42, 325, 20);
pnlbls.add(lblek2);

lblek3 = new JLabel("");
lblek3.setBounds(10, 73, 249, 20);
pnlbls.add(lblek3);

btnOk = new JButton("ok");

txtdis = new JTextField();
txtdis.setColumns(10);

JLabel label_2 = new JLabel("Discount   %");

endDate = new JDateChooser();
endDate.setDateFormatString("yyyy MMM d");

strDate = new JDateChooser();
strDate.setAlignmentY(Component.BOTTOM_ALIGNMENT);
strDate.setAlignmentX(Component.RIGHT_ALIGNMENT);

strDate.setDateFormatString("yyyy MMM d");

JLabel lblStartDate = new JLabel("Start Date :");

JLabel lblEndDate = new JLabel("End Date :");

rh1 = new JRadioButton("hotel 1");
rh1.setBackground(SystemColor.activeCaption);
rh1.setSelected(true);
HotelGroup.add(rh1);

	rh2 = new JRadioButton("hotel 2");
	rh2.setBackground(SystemColor.activeCaption);
	HotelGroup.add(rh2);
	
	rh3 = new JRadioButton("hotel 3");
	rh3.setBackground(SystemColor.activeCaption);
	HotelGroup.add(rh3);

rh4 = new JRadioButton("hotel 4");
rh4.setBackground(SystemColor.activeCaption);
HotelGroup.add(rh4);

rh5 = new JRadioButton("hotel 5");
rh5.setBackground(SystemColor.activeCaption);
HotelGroup.add(rh5);
SpringLayout sl_pnDiscounts = new SpringLayout();
sl_pnDiscounts.putConstraint(SpringLayout.EAST, txtdis, 357, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, endDate, 271, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.SOUTH, endDate, -78, SpringLayout.NORTH, label_2);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, txtdis, 184, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, txtdis, 287, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, btnOk, 247, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, btnOk, 315, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, btnOk, 373, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, label_2, 164, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, label_2, 287, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, label_2, 357, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, rh1, 118, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, rh1, 78, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, rh1, 187, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, rh3, 170, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, rh3, 78, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, rh3, 187, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, rh2, 144, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, rh2, 78, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, rh2, 187, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, endDate, 66, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, endDate, 382, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, lblEndDate, 41, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, lblEndDate, 271, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, lblEndDate, 382, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, lblStartDate, 41, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, lblStartDate, 89, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, lblStartDate, 187, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, strDate, 66, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, strDate, 89, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, strDate, 200, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, pnlbls, 296, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, pnlbls, 63, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.SOUTH, pnlbls, 397, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, pnlbls, 409, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, rh5, 222, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, rh5, 78, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, rh5, 187, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.NORTH, rh4, 196, SpringLayout.NORTH, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.WEST, rh4, 78, SpringLayout.WEST, pnDiscounts);
sl_pnDiscounts.putConstraint(SpringLayout.EAST, rh4, 187, SpringLayout.WEST, pnDiscounts);
pnDiscounts.setLayout(sl_pnDiscounts);
pnDiscounts.add(rh4);
pnDiscounts.add(rh5);
pnDiscounts.add(pnlbls);
pnDiscounts.add(strDate);
pnDiscounts.add(lblStartDate);
pnDiscounts.add(lblEndDate);
pnDiscounts.add(endDate);
pnDiscounts.add(rh2);
pnDiscounts.add(rh3);
pnDiscounts.add(rh1);
pnDiscounts.add(label_2);
pnDiscounts.add(btnOk);
pnDiscounts.add(txtdis);
JPanel pnStatistics = new JPanel();
pnStatistics.setBackground(SystemColor.activeCaption);
tabbedPane.addTab("Statistics", null, pnStatistics, null);
btnShow = new JButton("Show");
pnBox = new JPanel();
pnBox.setBackground(SystemColor.inactiveCaption);
pnBox.setLayout(new BoxLayout(pnBox, BoxLayout.X_AXIS));
GroupLayout gl_pnStatistics = new GroupLayout(pnStatistics);
gl_pnStatistics.setHorizontalGroup(
	gl_pnStatistics.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_pnStatistics.createSequentialGroup()
			.addContainerGap(223, Short.MAX_VALUE)
			.addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
			.addGap(261))
		.addComponent(pnBox, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
);
gl_pnStatistics.setVerticalGroup(
	gl_pnStatistics.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_pnStatistics.createSequentialGroup()
			.addGap(11)
			.addComponent(btnShow)
			.addGap(11)
			.addComponent(pnBox, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
);
pnStatistics.setLayout(gl_pnStatistics);

	}

	public void event() {
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmManager.setVisible(false);
				MainWindow window1 = new MainWindow();
				window1.frmMain.setVisible(true);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOKClick();
			}
		});
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnShowClick();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDelete();
			}
		});
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsignup();
			}
		});
		
	}
	private void showDisc(){
		
		//database.showDiscount();

	}
	private void btnDelete(){
		User emp = database.authenticate(txtUsername.getText(), txtPassword.getText());
		if(emp==null)
			JOptionPane.showMessageDialog(null, "User Does Not Exist.", "Error", JOptionPane.ERROR_MESSAGE);
		else{
			database.delete(txtUsername.getText());
			txtUsername.setText("");
			txtPassword.setText("");
		}
	}
	private void btnsignup(){
		if(txtPasswordQ.getText().equals(txtCfPassword.getText())){
			
			int  k = cbType.getSelectedIndex();
			if(k == 0){
				database.signup(txtUsernameQ.getText(), txtPasswordQ.getText(),User.TYPE_ADMIN);
				txtUsernameQ.setText("");
				txtPasswordQ.setText("");
				txtCfPassword.setText("");
			}else{
				database.signup(txtUsernameQ.getText(), txtPasswordQ.getText(),User.TYPE_STAFF);
				txtUsernameQ.setText("");
				txtPasswordQ.setText("");
				txtCfPassword.setText("");
			}
		}else
			JOptionPane.showMessageDialog(null, "Passwords must match", "Error", JOptionPane.ERROR_MESSAGE);	
	
	}

	private void btnShowClick(){
		
		database.showStats();
		pnBox.removeAll();
		pnBox.add(database.chartPanel);
		pnBox.updateUI();
	}
	
	private void btnOKClick() {
		if (rh1.isSelected())
			htl = 1;
		else if (rh2.isSelected())
			htl = 2;
		else if (rh3.isSelected())
			htl = 3;
		else if (rh4.isSelected())
			htl = 4;
		else if (rh5.isSelected())
			htl = 5;

		try {

			dis = Integer.parseInt(txtdis.getText());

			if (dis >= 0 && dis <= 100) {

				d1 = strDate.getDate();
				d2 = endDate.getDate();

				database.addDiscount(htl, d1, d2, dis);

				lblek3.setText("kai h ekptwsh einai  " + dis + "%");
				lblek.setText("arxizei stis " + strDate.getDate());
				lblek2.setText("teliwnei stis " + endDate.getDate());
			} else
				JOptionPane.showMessageDialog(null, "Invalid credentials.Discount must be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (Exception error) {

			JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
