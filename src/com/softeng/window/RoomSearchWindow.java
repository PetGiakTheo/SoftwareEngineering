package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.softeng.misc.Room;
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
	private JCheckBox chkIgnore = new JCheckBox("Ignore date");
	private JDateChooser dtFrom = new JDateChooser();
	private JDateChooser dtTo = new JDateChooser();
	

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
		frmRoomSearch.setBounds(100, 100, 450, 420);
		frmRoomSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, dtFrom, 11, SpringLayout.SOUTH, cbType);
		springLayout.putConstraint(SpringLayout.WEST, dtFrom, 0, SpringLayout.WEST, cbType);
		springLayout.putConstraint(SpringLayout.EAST, dtFrom, 0, SpringLayout.EAST, dtTo);
		springLayout.putConstraint(SpringLayout.WEST, dtTo, 0, SpringLayout.WEST, spnSingle);
		springLayout.putConstraint(SpringLayout.EAST, dtTo, 120, SpringLayout.WEST, spnSingle);
		springLayout.putConstraint(SpringLayout.NORTH, cbType, 19, SpringLayout.SOUTH, spnDouble);
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
		springLayout.putConstraint(SpringLayout.EAST, chkIgnore, 0, SpringLayout.EAST, lblNewLabel);
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
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomType, 3, SpringLayout.NORTH, cbType);
		springLayout.putConstraint(SpringLayout.WEST, lblRoomType, 0, SpringLayout.WEST, lblNewLabel_1);
		frmRoomSearch.getContentPane().add(lblRoomType);
		
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Regular", "VIP", "Both"}));
		springLayout.putConstraint(SpringLayout.WEST, cbType, 0, SpringLayout.WEST, spnSingle);
		frmRoomSearch.getContentPane().add(cbType);
		
		JLabel lblAvailableFrom = new JLabel("Available from:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAvailableFrom, 13, SpringLayout.SOUTH, cbType);
		springLayout.putConstraint(SpringLayout.WEST, lblAvailableFrom, -295, SpringLayout.EAST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblAvailableFrom, -205, SpringLayout.EAST, frmRoomSearch.getContentPane());
		frmRoomSearch.getContentPane().add(lblAvailableFrom);
		
		JLabel lblTo = new JLabel("Available to:");
		springLayout.putConstraint(SpringLayout.NORTH, chkIgnore, 18, SpringLayout.SOUTH, lblTo);
		springLayout.putConstraint(SpringLayout.SOUTH, chkIgnore, 38, SpringLayout.SOUTH, lblTo);
		springLayout.putConstraint(SpringLayout.NORTH, dtTo, -6, SpringLayout.NORTH, lblTo);
		springLayout.putConstraint(SpringLayout.SOUTH, dtTo, 0, SpringLayout.SOUTH, lblTo);
		springLayout.putConstraint(SpringLayout.NORTH, lblTo, 22, SpringLayout.SOUTH, lblAvailableFrom);
		springLayout.putConstraint(SpringLayout.WEST, lblTo, 0, SpringLayout.WEST, lblNewLabel_1);
		frmRoomSearch.getContentPane().add(lblTo);
		frmRoomSearch.getContentPane().add(chkIgnore);
		
		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 6, SpringLayout.SOUTH, chkIgnore);
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 23, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, cbType);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchClick();
			}
		});
		frmRoomSearch.getContentPane().add(btnSearch);
		
		// Set mininum and maximum selectable dates
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		max.add(Calendar.YEAR, 1);
		
		dtFrom.setMinSelectableDate(min.getTime());
		dtFrom.setMaxSelectableDate(max.getTime());
		dtTo.setMinSelectableDate(min.getTime());
		dtTo.setMaxSelectableDate(max.getTime());
		
		dtFrom.setDateFormatString("yyyy MMM d");
		dtFrom.setAlignmentY(1.0f);
		dtFrom.setAlignmentX(1.0f);
		frmRoomSearch.getContentPane().add(dtFrom);
		dtTo.setDateFormatString("yyyy MMM d");
		dtTo.setAlignmentY(1.0f);
		dtTo.setAlignmentX(1.0f);
		frmRoomSearch.getContentPane().add(dtTo);
	}
	
	private void btnBackClick() {
		// TODO remove comment
		//parent.frmEmployee.setVisible(true);
		frmRoomSearch.dispose();
	}
	
	private void btnSearchClick() {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dtFrom.getDate());
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dtTo.getDate());
		if (c1.after(c2)) {
			JOptionPane.showMessageDialog(null, "The second date must be after the first.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int hotel = 1;
		int singleBeds = (Integer)spnSingle.getValue();
		int doubleBeds = (Integer)spnDouble.getValue();
		String type = null;
		if (cbType.getSelectedIndex() == 0)
			type = Room.TYPE_REGULAR;
		else if (cbType.getSelectedIndex() == 1)
			type = Room.TYPE_VIP;
		boolean ignoreAvail = chkIgnore.isSelected();
		
		Room[] rooms = database.findRooms(hotel, singleBeds, doubleBeds, type, dtFrom.getDate(), dtTo.getDate(), ignoreAvail);
		
		
	}
}
