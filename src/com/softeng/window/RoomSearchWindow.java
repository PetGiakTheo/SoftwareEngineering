package com.softeng.window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import com.softeng.misc.DBController;
import com.softeng.misc.GlobalItems;
import com.softeng.misc.Room;
import com.toedter.calendar.JDateChooser;

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
	private JPanel pnFilters = new JPanel();
	private JComboBox cbHotel = new JComboBox();
	private JList lstRooms = new JList();
	private JButton btnBookRoom = new JButton("Book room");
	private Room[] availRooms;
	
	private Date start, end;
	private int selectedHotel;

	// TODO remove main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomSearchWindow window = new RoomSearchWindow();
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
	public RoomSearchWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoomSearch = new JFrame();
		frmRoomSearch.setType(Type.POPUP);
		frmRoomSearch.setTitle("Room search");
		frmRoomSearch.setBounds(100, 100, 500, 420);
		frmRoomSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// The checkbox is only visible to the employees.
		if (GlobalItems.currentUser == null)
			chkIgnore.setVisible(false);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClick();
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, pnFilters, 34, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnFilters, 10, SpringLayout.WEST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnFilters, 259, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnFilters, 286, SpringLayout.WEST, frmRoomSearch.getContentPane());
		frmRoomSearch.getContentPane().setLayout(springLayout);
		pnFilters.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmRoomSearch.getContentPane().add(pnFilters);
		pnFilters.setLayout(new MigLayout("", "[63px][31px][31px][56px][55.00px][][126.00px,grow][72px][60px][83px][65px][27px][27px][1px]", "[grow][20.00px][23px][20px][0.00px][35.00][35.00][][]"));

		JLabel lblHotel = new JLabel("Hotel:");
		pnFilters.add(lblHotel, "cell 4 0,alignx right");

		String[] cbHotelContents = new String[5];
		for (int i = 0; i < 5; i++)
			cbHotelContents[i] = "Hotel " + Integer.toString(i + 1) + " - " + GlobalItems.HOTEL_NAMES[i];
		cbHotel.setModel(new DefaultComboBoxModel(cbHotelContents));
		pnFilters.add(cbHotel, "cell 6 0 4 1,growx");

		JLabel lblRoomType = new JLabel("Room type:");
		lblRoomType.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblRoomType, "cell 4 1,alignx right,aligny center");
		pnFilters.add(cbType, "cell 6 1,alignx left,aligny center");

		cbType.setModel(new DefaultComboBoxModel(new String[] { "Regular", "VIP", "Both" }));
		pnFilters.add(spnSingle, "cell 6 2,alignx left,aligny center");

		spnSingle.setModel(new SpinnerNumberModel(0, 0, 3, 1));

		JLabel lblDoubleBeds = new JLabel("Double beds:");
		lblDoubleBeds.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblDoubleBeds, "cell 4 3,alignx right,aligny center");
		pnFilters.add(spnDouble, "cell 6 3,alignx left,aligny center");
		spnDouble.setModel(new SpinnerNumberModel(0, 0, 3, 1));

		JLabel lblNewLabel1 = new JLabel("Single beds:");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblNewLabel1, "cell 4 2,alignx right,aligny center");

		// Set mininum and maximum selectable dates
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		max.add(Calendar.YEAR, 1);

		frmRoomSearch.getContentPane().add(btnBack);

		JLabel lblNewLabel = new JLabel("Room filters");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 9, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 80, SpringLayout.WEST, pnFilters);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -80, SpringLayout.EAST, pnFilters);

		JLabel lblAvailableFrom = new JLabel("Check-in:");
		lblAvailableFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblAvailableFrom, "cell 4 5,alignx right,aligny center");
		pnFilters.add(dtFrom, "cell 6 5 2 1,growx,aligny center");

		dtFrom.setMinSelectableDate(min.getTime());
		dtFrom.setMaxSelectableDate(max.getTime());

		dtFrom.setDateFormatString("yyyy MMM d");
		dtFrom.setAlignmentY(1.0f);
		dtFrom.setAlignmentX(1.0f);

		JLabel lblTo = new JLabel("Check-out:");
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblTo, "cell 4 6,alignx right,aligny center");
		pnFilters.add(dtTo, "cell 6 6 2 1,growx,aligny center");
		dtTo.setMinSelectableDate(min.getTime());
		dtTo.setMaxSelectableDate(max.getTime());
		dtTo.setDateFormatString("yyyy MMM d");
		dtTo.setAlignmentY(1.0f);
		dtTo.setAlignmentX(1.0f);
		pnFilters.add(chkIgnore, "cell 4 7 3 1,alignx center,aligny center");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmRoomSearch.getContentPane().add(lblNewLabel);

		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 270, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 50, SpringLayout.WEST, pnFilters);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -50, SpringLayout.EAST, pnFilters);
		frmRoomSearch.getContentPane().add(btnSearch);

		JPanel pnRooms = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, pnRooms, -10, SpringLayout.EAST, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, pnRooms, 0, SpringLayout.NORTH, pnFilters);
		springLayout.putConstraint(SpringLayout.WEST, pnRooms, 10, SpringLayout.EAST, pnFilters);
		springLayout.putConstraint(SpringLayout.SOUTH, pnRooms, 0, SpringLayout.SOUTH, pnFilters);
		frmRoomSearch.getContentPane().add(pnRooms);
		pnRooms.setLayout(new MigLayout("", "[grow]", "[grow]"));
		lstRooms.setBackground(Color.LIGHT_GRAY);
		lstRooms.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		pnRooms.add(lstRooms, "cell 0 0,grow");
		
		JLabel lblNewLabel2 = new JLabel("Rooms");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel2, 60, SpringLayout.WEST, pnRooms);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel2, -60, SpringLayout.EAST, pnRooms);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel2, 0, SpringLayout.SOUTH, lblNewLabel);
		frmRoomSearch.getContentPane().add(lblNewLabel2);
		
		btnBookRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBookClick();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnBookRoom, 20, SpringLayout.WEST, pnRooms);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBookRoom, 0, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.EAST, btnBookRoom, -20, SpringLayout.EAST, pnRooms);
		frmRoomSearch.getContentPane().add(btnBookRoom);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchClick();
			}
		});
	}

	private void btnBackClick() {
		if (GlobalItems.currentUser == null) {
			MainWindow window = new MainWindow();
			window.frmMain.setVisible(true);
		}
		frmRoomSearch.dispose();
	}

	private void btnSearchClick() {
		if (!chkIgnore.isSelected()) {
			if (dtFrom.getDate() == null || dtTo.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Invalid date.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Calendar c1 = Calendar.getInstance();
			c1.setTime(dtFrom.getDate());

			Calendar c2 = Calendar.getInstance();
			c2.setTime(dtTo.getDate());
			if (c1.after(c2)) {
				JOptionPane.showMessageDialog(null, "The second date must be after the first.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			btnBookRoom.setEnabled(true);
			start = dtFrom.getDate();
			end = dtTo.getDate();
		}
		else
			btnBookRoom.setEnabled(false);
		

		selectedHotel = cbHotel.getSelectedIndex() + 1;
		int singleBeds = (Integer) spnSingle.getValue();
		int doubleBeds = (Integer) spnDouble.getValue();
		String type = null;
		if (cbType.getSelectedIndex() == 0)
			type = Room.TYPE_REGULAR;
		else if (cbType.getSelectedIndex() == 1)
			type = Room.TYPE_VIP;
		boolean ignoreAvail = chkIgnore.isSelected();

		availRooms = database.findRooms(selectedHotel, singleBeds, doubleBeds, type, dtFrom.getDate(), dtTo.getDate(), ignoreAvail);
		if (availRooms == null) {
			JOptionPane.showMessageDialog(null, "There was an unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (availRooms.length == 0)
			JOptionPane.showMessageDialog(null, "No rooms meet your search criteria.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < availRooms.length; i++)
			model.addElement("Room " + Integer.toString(availRooms[i].getId()) + "  Type " + (availRooms[i].getType().equals(Room.TYPE_REGULAR) ? "Regular" : "VIP") );
		lstRooms.setModel(model);
	}
	
	private void btnBookClick() {
		int index = lstRooms.getSelectedIndex();
		if (index != -1) {
			frmRoomSearch.setVisible(false);
			CustomerSignWindow window = new CustomerSignWindow(selectedHotel, availRooms[index], start, end);
			window.frmCustomerSign.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Please select a room.", "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
}
