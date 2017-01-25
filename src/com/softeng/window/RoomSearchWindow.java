package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

import javax.swing.DefaultListModel;
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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	private Room[] availRooms;

	// private JPanel pnFilters = new JPanel();

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
		frmRoomSearch.setBounds(100, 100, 500, 420);
		frmRoomSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/*
		 * springLayout.putConstraint(SpringLayout.NORTH, pnFilters, 110, SpringLayout.NORTH, frmRoomSearch.getContentPane()); springLayout.putConstraint(SpringLayout.WEST, pnFilters, 10,
		 * SpringLayout.WEST, frmRoomSearch.getContentPane());
		 */

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
			cbHotelContents[i] = "Hotel " + Integer.toString(i + 1) + " - " + MainWindow.hotelNames[i];
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

		JLabel lblNewLabel_1 = new JLabel("Single beds:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblNewLabel_1, "cell 4 2,alignx right,aligny center");

		// Set mininum and maximum selectable dates
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		max.add(Calendar.YEAR, 1);

		frmRoomSearch.getContentPane().add(btnBack);

		JLabel lblNewLabel = new JLabel("Room filters");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 9, SpringLayout.NORTH, frmRoomSearch.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 80, SpringLayout.WEST, pnFilters);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -80, SpringLayout.EAST, pnFilters);

		JLabel lblAvailableFrom = new JLabel("Available from:");
		lblAvailableFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		pnFilters.add(lblAvailableFrom, "cell 4 5,alignx right,aligny center");
		pnFilters.add(dtFrom, "cell 6 5 2 1,growx,aligny center");

		dtFrom.setMinSelectableDate(min.getTime());
		dtFrom.setMaxSelectableDate(max.getTime());

		dtFrom.setDateFormatString("yyyy MMM d");
		dtFrom.setAlignmentY(1.0f);
		dtFrom.setAlignmentX(1.0f);

		JLabel lblTo = new JLabel("Available to:");
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
		
		JLabel lblNewLabel_2 = new JLabel("Rooms");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 60, SpringLayout.WEST, pnRooms);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -60, SpringLayout.EAST, pnRooms);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, lblNewLabel);
		frmRoomSearch.getContentPane().add(lblNewLabel_2);
		
		JButton btnBookRoom = new JButton("Book room");
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
		if (MainWindow.currentUser == null) {
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
		}

		int hotel = cbHotel.getSelectedIndex() + 1;
		int singleBeds = (Integer) spnSingle.getValue();
		int doubleBeds = (Integer) spnDouble.getValue();
		String type = null;
		if (cbType.getSelectedIndex() == 0)
			type = Room.TYPE_REGULAR;
		else if (cbType.getSelectedIndex() == 1)
			type = Room.TYPE_VIP;
		boolean ignoreAvail = chkIgnore.isSelected();

		availRooms = database.findRooms(hotel, singleBeds, doubleBeds, type, dtFrom.getDate(), dtTo.getDate(), ignoreAvail);
		
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
			CustomerSignWindow window = new CustomerSignWindow(availRooms[index]);
			window.frmCustomerSign.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Please select a room.", "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
}
