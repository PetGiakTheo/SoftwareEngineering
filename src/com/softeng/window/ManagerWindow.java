package com.softeng.window;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;

import com.softeng.misc.DBController;
import com.softeng.misc.Discount;
import com.softeng.misc.GlobalItems;
import com.softeng.misc.Reservation;
import com.softeng.misc.Room;
import com.softeng.misc.User;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class ManagerWindow {

	private DBController database = new DBController();
	public JFrame frmManager;
	private JTextField txtdis;
	private JButton btnOk;  
	private JDateChooser strDate;
	private JDateChooser endDate;
	private JPanel pnlbls;
	private JPanel pnManageStaff;
	private JPanel pnDiscounts;
	private final ButtonGroup HotelGroup = new ButtonGroup();
	private JRadioButton rh1;
	private JRadioButton rh2;
	private JRadioButton rh3;
	private JRadioButton rh4;
	private JRadioButton rh5;
	private java.util.Date d1, d2;
	private java.sql.Date sqldt;
	private int htl;
	private int dis;
	private JTextField txtUsernameQ;
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
	private JScrollPane scrollPane;
	private JList lstDiscount;
	private DefaultListModel modellist = new DefaultListModel();
	private DefaultListModel modellist2 = new DefaultListModel();
	private Discount[] disc = null;
	private User[] users = null;
	private Reservation[] resDate = null;
	public ChartPanel chartPanel1;
	private JList lstDel;

	/**
	 * Create the application.
	 */
	public ManagerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { 
		
		frmManager = new JFrame();
		frmManager.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/icon.png")));
		frmManager.setLocationByPlatform(true);
		frmManager.getContentPane().setBackground(new Color(119, 136, 153));
		frmManager.setTitle("Manager");
		frmManager.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ManagerWindow.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmManager.setBounds(100, 100, 568, 519);

		frmManager.getContentPane().setLayout(new CardLayout(0, 0));
		frmManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (GlobalItems.currentUser != null)
			frmManager.setTitle("Manager - " + GlobalItems.currentUser.getUsername());
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

		GroupLayout glPnManageStaff = new GroupLayout(pnManageStaff);
		glPnManageStaff.setHorizontalGroup(
			glPnManageStaff.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnManageStaff.createSequentialGroup()
					.addContainerGap(472, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addContainerGap())
				.addGroup(glPnManageStaff.createSequentialGroup()
					.addComponent(pnAdd, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(pnDelete, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
		);
		glPnManageStaff.setVerticalGroup(
			glPnManageStaff.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnManageStaff.createSequentialGroup()
					.addGap(33)
					.addGroup(glPnManageStaff.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
					.addGap(24)
					.addComponent(btnLogout)
					.addContainerGap())
		);

		JLabel lblDeleteEmployee = new JLabel("Delete Employee");

		btnDelete = new JButton("Delete");
		
		JScrollPane scrollPane1 = new JScrollPane();
		


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
		cbType.setModel(new DefaultComboBoxModel(new String[] { "Admin", "Staff" }));
		cbType.setBounds(163, 176, 72, 25);
		pnAdd.add(cbType);
		pnManageStaff.setLayout(glPnManageStaff);

		pnDiscounts = new JPanel();
		pnDiscounts.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("ManageDiscounts", null, pnDiscounts, null);
		pnDiscounts.setBorder(null);

		pnlbls = new JPanel();
		pnlbls.setBackground(SystemColor.inactiveCaption);
		pnlbls.setBorder(new TitledBorder(null, "All discounts", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnOk = new JButton("ok");

		txtdis = new JTextField();
		txtdis.setColumns(10);
		


		JLabel label2 = new JLabel("Discount   %");

		endDate = new JDateChooser();
		endDate.setDateFormatString("yyyy MMM d");

		strDate = new JDateChooser();
		strDate.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		strDate.setAlignmentX(Component.RIGHT_ALIGNMENT);

		strDate.setDateFormatString("yyyy MMM d");

		JLabel lblStartDate = new JLabel("Start Date :");

		JLabel lblEndDate = new JLabel("End Date :");
		
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		max.add(Calendar.YEAR, 1);
		
		strDate.setMinSelectableDate(min.getTime());
		strDate.setMaxSelectableDate(max.getTime());
		endDate.setMinSelectableDate(min.getTime());
		endDate.setMaxSelectableDate(max.getTime());

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
		scrollPane = new JScrollPane();
		
		lstDiscount = new JList();
		lstDiscount.setBorder(null);
		lstDiscount.setModel(modellist);

		lstDiscount.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(lstDiscount);
		
		lstDel = new JList();

		lstDel.setBorder(null);
		lstDel.setModel(modellist2);
		

		

		
		lstDel.setBackground(SystemColor.inactiveCaption);
		scrollPane1.setViewportView(lstDel);
		GroupLayout glpnDelete = new GroupLayout(pnDelete);
		glpnDelete.setHorizontalGroup(
			glpnDelete.createParallelGroup(Alignment.LEADING)
				.addGroup(glpnDelete.createSequentialGroup()
					.addGap(95)
					.addComponent(lblDeleteEmployee, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
				.addGroup(glpnDelete.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(glpnDelete.createSequentialGroup()
					.addGap(155)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
		);
		glpnDelete.setVerticalGroup(
			glpnDelete.createParallelGroup(Alignment.LEADING)
				.addGroup(glpnDelete.createSequentialGroup()
					.addGap(8)
					.addComponent(lblDeleteEmployee)
					.addGap(11)
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnDelete)
					.addGap(8))
		);
		pnDelete.setLayout(glpnDelete);

		GroupLayout glPnDiscounts = new GroupLayout(pnDiscounts);
		glPnDiscounts.setHorizontalGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnDiscounts.createSequentialGroup().addGap(120)
						.addComponent(rh2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE).addGap(139)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(glPnDiscounts.createSequentialGroup().addGap(120)
						.addComponent(rh3, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE).addGap(139)
						.addComponent(txtdis, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(glPnDiscounts.createSequentialGroup().addGap(120).addComponent(rh4,
						GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
				.addGroup(glPnDiscounts.createSequentialGroup().addGap(120)
						.addComponent(rh5, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE).addGap(151)
						.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, glPnDiscounts.createSequentialGroup().addGroup(glPnDiscounts
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, glPnDiscounts.createParallelGroup(Alignment.LEADING)
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(118)
										.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)
										.addGap(111).addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(118)
										.addComponent(strDate, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE)
										.addGap(98).addComponent(endDate, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(120).addComponent(rh1,
										GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glPnDiscounts.createSequentialGroup().addGap(22).addComponent(pnlbls,
								GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)))
						.addGap(33)));
		glPnDiscounts.setVerticalGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnDiscounts.createSequentialGroup().addGap(41)
						.addGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING).addComponent(lblStartDate)
								.addComponent(lblEndDate))
						.addGap(11)
						.addGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING)
								.addComponent(strDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(endDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(39).addComponent(rh1).addGap(3)
						.addGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING).addComponent(rh2)
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(4).addComponent(label2)))
						.addGap(3)
						.addGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING).addComponent(rh3)
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(1).addComponent(txtdis,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(3).addComponent(rh4).addGap(3)
						.addGroup(glPnDiscounts.createParallelGroup(Alignment.LEADING).addComponent(rh5)
								.addGroup(glPnDiscounts.createSequentialGroup().addGap(18).addComponent(btnOk)))
						.addGap(27).addComponent(pnlbls, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addContainerGap()));
		GroupLayout glPnlbls = new GroupLayout(pnlbls);
		glPnlbls.setHorizontalGroup(
				glPnlbls.createParallelGroup(Alignment.LEADING).addGroup(glPnlbls.createSequentialGroup().addGap(7)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE).addGap(7)));
		glPnlbls.setVerticalGroup(
				glPnlbls.createParallelGroup(Alignment.LEADING).addGroup(glPnlbls.createSequentialGroup().addGap(8)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE).addGap(8)));
		pnlbls.setLayout(glPnlbls);
		pnDiscounts.setLayout(glPnDiscounts);
		JPanel pnStatistics = new JPanel();
		pnStatistics.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Statistics", null, pnStatistics, null);
		btnShow = new JButton("Show");
		pnBox = new JPanel();
		pnBox.setBackground(SystemColor.inactiveCaption);
		pnBox.setLayout(new BoxLayout(pnBox, BoxLayout.X_AXIS));
		GroupLayout glPnStatistics = new GroupLayout(pnStatistics);
		glPnStatistics.setHorizontalGroup(glPnStatistics.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnStatistics.createSequentialGroup().addContainerGap(223, Short.MAX_VALUE)
						.addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE).addGap(261))
				.addComponent(pnBox, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE));
		glPnStatistics.setVerticalGroup(glPnStatistics.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnStatistics.createSequentialGroup().addGap(11).addComponent(btnShow).addGap(11)
						.addComponent(pnBox, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)));
		pnStatistics.setLayout(glPnStatistics);

		disc = database.showDiscount();
		users = database.showUsers();
		
		for(int i = 0; i< users.length; i++)
			modellist2.addElement("Username : " + users[i].getUsername() + "  |   Type : " + users[i].getType());
			
		
		for (int i = 0; i < disc.length; i++)
			modellist.addElement("Hotel : " + disc[i].gethotel() + " , Start Date " + disc[i].getDate() + " , End Date " + disc[i].getendDate() + " , Percentage " + disc[i].getpercentage() + "%");
	
		event();
	}

	public void event() {
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalItems.currentUser = null;
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
	private void btnDelete() {
		for(int i = 0; i< users.length; i++){
			if(i == lstDel.getSelectedIndex() ){	
				database.deleteUser(users[i].getUsername());
			}	
		}
		
		modellist2.removeAllElements();
		users = database.showUsers();
		for(int i = 0; i< users.length; i++)
			modellist2.addElement("Username : " + users[i].getUsername() + "  |   Type : " + users[i].getType());
			
		
	}
	private void btnsignup() { 
		if (txtPasswordQ.getText().equals(txtCfPassword.getText())) {

			
			if ( cbType.getSelectedIndex() == 0) {
				database.signupUser(txtUsernameQ.getText(), txtPasswordQ.getText(), User.TYPE_ADMIN);
				txtUsernameQ.setText("");
				txtPasswordQ.setText("");
				txtCfPassword.setText("");
				modellist2.removeAllElements();
				users = database.showUsers();
				for(int i = 0; i< users.length; i++)
					modellist2.addElement("Username : " + users[i].getUsername() + "  |   Type : " + users[i].getType());
					
			} else {
				database.signupUser(txtUsernameQ.getText(), txtPasswordQ.getText(), User.TYPE_STAFF);
				txtUsernameQ.setText("");
				txtPasswordQ.setText("");
				txtCfPassword.setText("");
				modellist2.removeAllElements();
				users = database.showUsers();
				for(int i = 0; i< users.length; i++)
					modellist2.addElement("Username : " + users[i].getUsername() + "  |   Type : " + users[i].getType());
					
			}
		} else
			JOptionPane.showMessageDialog(null, "Passwords must match.", "Error", JOptionPane.ERROR_MESSAGE);

	}

	private void btnShowClick() {
		
		String month ;
		int startD = 0;
		int endD = 0;
		int income = 0;
	
		resDate = database.getReservations();
		
		for(int i = 0; i< resDate.length; i++){
			startD = resDate[i].getDateStart().getMonth() + 1;
			endD = resDate[i].getDateEnd().getMonth() + 1;
			
			switch (startD){
			case 1: 
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "January";
				database.addDate(month, income);
				break;
			case 2:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "February";
				database.addDate(month, income);
				break;
			case 3:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "March";
				database.addDate(month, income);
				break;
			case 4:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "April";
				database.addDate(month, income);
				break;
			case 5:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "May";
				database.addDate(month, income);
				break;
			case 6:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "June";
				database.addDate(month, income);
				break;
			case 7:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "July";
				database.addDate(month, income);
				break;
			case 8:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "August";
				database.addDate(month, income);
				break;
			case 9:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "September";
				database.addDate(month, income);
				break;
			case 10:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "October";
				database.addDate(month, income);
				break;
			case 11:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "November";
				database.addDate(month, income);
				break;
			case 12:
				income = (int) (GlobalItems.diffInDays(resDate[i].getDateStart(), resDate[i].getDateEnd()) * (database.getRoomWithId(1, resDate[i].getRoomId()).getType().equals(Room.TYPE_REGULAR)? Room.PRICE_PER_DAY_REGULAR:Room.PRICE_PER_DAY_VIP));
				month = "December";
				database.addDate(month, income);
				break;
				
			
			}
			
			
		}
		
			
		//DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		//dcd.setValue(78.80, "$", "January");
		//dcd.setValue(7, "$", "February");
		//dcd.setValue(78.80, "$", "March");
		//dcd.setValue(78.80, "$", "April");
		//dcd.setValue(78.80, "$", "May");
		
		
		//JFreeChart chart = ChartFactory.createLineChart("INCOME", "Date", "cust", dcd);
		
		//CategoryPlot plot = chart.getCategoryPlot();
		//plot.setRangeGridlinePaint(Color.black);
		//chartPanel1 = new ChartPanel(chart);
		//pnBox.add(chartPanel1);


		database.showStats();
		pnBox.removeAll();
		pnBox.add(database.chartPanel);
		pnBox.updateUI();
		
		database.deleteStat();
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

		
				
				Calendar d1 = Calendar.getInstance();
				d1.setTime(strDate.getDate());

				Calendar d2 = Calendar.getInstance();
				d2.setTime(endDate.getDate());
				if (d1.after(d2)) {
					JOptionPane.showMessageDialog(null, "The second date must be after the first.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
					

				database.addDiscount(htl, strDate.getDate(), endDate.getDate(), dis);
				modellist.removeAllElements();

				disc = database.showDiscount();


				for (int i = 0; i < disc.length; i++)
					modellist.addElement("Hotel : " + disc[i].gethotel() + " , Start Date " + disc[i].getDate()+ " , End Date " + disc[i].getendDate() + " , Percentage " + disc[i].getpercentage() + "%");

			} else
				JOptionPane.showMessageDialog(null, "Invalid input. Discount must be between 0 and 100", "Error",
						JOptionPane.ERROR_MESSAGE);

		} catch (Exception error) {

			JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
