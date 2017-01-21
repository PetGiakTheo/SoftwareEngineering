package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.softeng.misc.DBController;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class RoomSearchWindow {

	public JFrame frmRoomSearch;
	private DBController database = new DBController();
	private EmployeeWindow parent;
	private JSpinner spnSingle = new JSpinner();
	private JSpinner spnDouble = new JSpinner();
	private JComboBox cbType = new JComboBox();
	private JDateChooser dtFrom = new JDateChooser();
	private JDateChooser dtTo = new JDateChooser();
	private JCheckBox chkIgnore = new JCheckBox("Ignore date");
	

	// TODO remove main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomSearchWindow window = new RoomSearchWindow(null);
					window.frmRoomSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoomSearchWindow(EmployeeWindow parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoomSearch = new JFrame();
		frmRoomSearch.setType(Type.POPUP);
		frmRoomSearch.setTitle("Room search");
		frmRoomSearch.setBounds(100, 100, 450, 350);
		frmRoomSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRoomSearch.getContentPane().setLayout(springLayout);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClick();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, frmRoomSearch.getContentPane());
		frmRoomSearch.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Room filters");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 170, SpringLayout.WEST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -170, SpringLayout.EAST, frmRoomSearch.getContentPane());
		frmRoomSearch.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Single beds:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 18, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 139, SpringLayout.WEST, frmRoomSearch.getContentPane());
		frmRoomSearch.getContentPane().add(lblNewLabel_1);
		
		spnSingle.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		springLayout.putConstraint(SpringLayout.NORTH, spnSingle, -3, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, spnSingle, 28, SpringLayout.EAST, lblNewLabel_1);
		frmRoomSearch.getContentPane().add(spnSingle);
		
		JLabel lblDoubleBeds = new JLabel("Double beds:");
		springLayout.putConstraint(SpringLayout.WEST, lblDoubleBeds, 0, SpringLayout.WEST, lblNewLabel_1);
		frmRoomSearch.getContentPane().add(lblDoubleBeds);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblDoubleBeds, 3, SpringLayout.NORTH, spnDouble);
		spnDouble.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		springLayout.putConstraint(SpringLayout.NORTH, spnDouble, 14, SpringLayout.SOUTH, spnSingle);
		springLayout.putConstraint(SpringLayout.EAST, spnDouble, 0, SpringLayout.EAST, spnSingle);
		frmRoomSearch.getContentPane().add(spnDouble);
		
		JLabel lblRoomType = new JLabel("Room type:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomType, 25, SpringLayout.SOUTH, lblDoubleBeds);
		springLayout.putConstraint(SpringLayout.EAST, lblRoomType, 0, SpringLayout.EAST, lblNewLabel_1);
		frmRoomSearch.getContentPane().add(lblRoomType);
		
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Regular", "VIP", "Both"}));
		springLayout.putConstraint(SpringLayout.NORTH, cbType, -3, SpringLayout.NORTH, lblRoomType);
		springLayout.putConstraint(SpringLayout.WEST, cbType, 0, SpringLayout.WEST, spnSingle);
		frmRoomSearch.getContentPane().add(cbType);
		
		dtFrom.setMinSelectableDate(new Date());
		springLayout.putConstraint(SpringLayout.NORTH, dtFrom, 21, SpringLayout.SOUTH, cbType);
		springLayout.putConstraint(SpringLayout.WEST, dtFrom, 0, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, dtFrom, -190, SpringLayout.EAST, frmRoomSearch.getContentPane());
		dtFrom.setDateFormatString("d MMM YYYY");
		dtFrom.setAlignmentY(1.0f);
		dtFrom.setAlignmentX(1.0f);
		frmRoomSearch.getContentPane().add(dtFrom);
		
		JLabel lblAvailableFrom = new JLabel("Available from");
		springLayout.putConstraint(SpringLayout.WEST, lblAvailableFrom, 44, SpringLayout.WEST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblAvailableFrom, 0, SpringLayout.SOUTH, dtFrom);
		springLayout.putConstraint(SpringLayout.EAST, lblAvailableFrom, -5, SpringLayout.WEST, dtFrom);
		frmRoomSearch.getContentPane().add(lblAvailableFrom);
		
		JLabel lblTo = new JLabel("to");
		springLayout.putConstraint(SpringLayout.SOUTH, lblTo, 0, SpringLayout.SOUTH, dtFrom);
		springLayout.putConstraint(SpringLayout.EAST, lblTo, 0, SpringLayout.EAST, lblNewLabel);
		frmRoomSearch.getContentPane().add(lblTo);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, chkIgnore, 6, SpringLayout.SOUTH, dtFrom);
		springLayout.putConstraint(SpringLayout.WEST, chkIgnore, 0, SpringLayout.WEST, lblNewLabel);
		frmRoomSearch.getContentPane().add(chkIgnore);
		
		dtTo.setMinSelectableDate(new Date());
		springLayout.putConstraint(SpringLayout.WEST, dtTo, 11, SpringLayout.EAST, lblTo);
		springLayout.putConstraint(SpringLayout.SOUTH, dtTo, 0, SpringLayout.SOUTH, dtFrom);
		springLayout.putConstraint(SpringLayout.EAST, dtTo, -54, SpringLayout.EAST, frmRoomSearch.getContentPane());
		dtTo.setDateFormatString("d MMM YYYY");
		dtTo.setAlignmentY(1.0f);
		dtTo.setAlignmentX(1.0f);
		frmRoomSearch.getContentPane().add(dtTo);
		
		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 17, SpringLayout.SOUTH, chkIgnore);
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 170, SpringLayout.WEST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -170, SpringLayout.EAST, frmRoomSearch.getContentPane());
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchClick();
			}
		});
		frmRoomSearch.getContentPane().add(btnSearch);
	}
	
	private void btnBackClick() {
		// TODO remove cooment
		//parent.frmEmployee.setVisible(true);
		frmRoomSearch.dispose();
	}
	
	private void btnSearchClick() {
		database.fillCustomerData();
	}
}
