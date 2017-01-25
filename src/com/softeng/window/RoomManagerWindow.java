package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.softeng.misc.DBController;
import com.softeng.misc.Room;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class RoomManagerWindow {

	JFrame frmRoomManager;
	private EmployeeWindow parentWindow;
	
	private DBController database;
	private JTextField txtRoomId;
	private JPanel pnRoomInfo = new JPanel();
	private JLabel lblRoomInfoTitle = new JLabel("Information for room with id");
	private JSpinner spnSingleBeds = new JSpinner();
	private JSpinner spnDoubleBeds = new JSpinner();
	private JComboBox cbRoomType = new JComboBox();
	private JComboBox cbHotel = new JComboBox();
	
	private int selectedHotel = -1;
	private int selectedRoomId = -1;

	// TODO Remove main method.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomManagerWindow window = new RoomManagerWindow(null);
					window.frmRoomManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoomManagerWindow(EmployeeWindow parent) {
		this.parentWindow = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new DBController();
		frmRoomManager = new JFrame();
		JDialog d = new JDialog();
		frmRoomManager.setType(Type.UTILITY);
		frmRoomManager.setTitle("Room manager");
		frmRoomManager.setBounds(100, 100, 450, 380);
		frmRoomManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		
		springLayout.putConstraint(SpringLayout.NORTH, pnRoomInfo, 110, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnRoomInfo, 10, SpringLayout.WEST, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().setLayout(springLayout);
		
		JLabel lblEnterRoomId = new JLabel("Enter room id:");
		frmRoomManager.getContentPane().add(lblEnterRoomId);
		
		txtRoomId = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtRoomId, 38, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtRoomId, 206, SpringLayout.WEST, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtRoomId, -142, SpringLayout.EAST, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterRoomId, 3, SpringLayout.NORTH, txtRoomId);
		springLayout.putConstraint(SpringLayout.EAST, lblEnterRoomId, -31, SpringLayout.WEST, txtRoomId);
		txtRoomId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLoadRoomClick();
			}
		});
		frmRoomManager.getContentPane().add(txtRoomId);
		txtRoomId.setColumns(10);
		
		JButton btnLoadRoom = new JButton("Load room");
		springLayout.putConstraint(SpringLayout.NORTH, btnLoadRoom, 10, SpringLayout.SOUTH, txtRoomId);
		springLayout.putConstraint(SpringLayout.WEST, btnLoadRoom, 144, SpringLayout.WEST, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLoadRoom, -144, SpringLayout.EAST, frmRoomManager.getContentPane());
		btnLoadRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoadRoomClick();
			}
		});
		frmRoomManager.getContentPane().add(btnLoadRoom);
		pnRoomInfo.setBackground(SystemColor.control);
		pnRoomInfo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnRoomInfo.setVisible(false);
		frmRoomManager.getContentPane().add(pnRoomInfo);
		SpringLayout sl_pnRoomInfo = new SpringLayout();
		pnRoomInfo.setLayout(sl_pnRoomInfo);
		
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, lblRoomInfoTitle, 10, SpringLayout.NORTH, pnRoomInfo);
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, lblRoomInfoTitle, 130, SpringLayout.WEST, pnRoomInfo);
		pnRoomInfo.add(lblRoomInfoTitle);
		
		JLabel lblRoomType = new JLabel("Room type:");
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, lblRoomType, 18, SpringLayout.SOUTH, lblRoomInfoTitle);
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, lblRoomType, 137, SpringLayout.WEST, pnRoomInfo);
		pnRoomInfo.add(lblRoomType);
		
		JLabel lblSingleBeds = new JLabel("Single beds:");
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, lblSingleBeds, 21, SpringLayout.SOUTH, lblRoomType);
		sl_pnRoomInfo.putConstraint(SpringLayout.EAST, lblSingleBeds, 0, SpringLayout.EAST, lblRoomType);
		pnRoomInfo.add(lblSingleBeds);
		
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, spnSingleBeds, 6, SpringLayout.EAST, lblSingleBeds);
		spnSingleBeds.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, spnSingleBeds, -3, SpringLayout.NORTH, lblSingleBeds);
		pnRoomInfo.add(spnSingleBeds);
		
		JLabel lblDoubleBeds = new JLabel("Double beds:");
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, lblDoubleBeds, 23, SpringLayout.SOUTH, lblSingleBeds);
		sl_pnRoomInfo.putConstraint(SpringLayout.EAST, lblDoubleBeds, 0, SpringLayout.EAST, lblRoomType);
		pnRoomInfo.add(lblDoubleBeds);
		
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, spnDoubleBeds, 6, SpringLayout.EAST, lblDoubleBeds);
		spnDoubleBeds.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, spnDoubleBeds, -3, SpringLayout.NORTH, lblDoubleBeds);
		pnRoomInfo.add(spnDoubleBeds);
		
		JButton btnSaveRoom = new JButton("Save room");
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, btnSaveRoom, 144, SpringLayout.WEST, pnRoomInfo);
		sl_pnRoomInfo.putConstraint(SpringLayout.EAST, btnSaveRoom, -144, SpringLayout.EAST, pnRoomInfo);
		btnSaveRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveRoomClick();
			}
		});
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, btnSaveRoom, 20, SpringLayout.SOUTH, spnDoubleBeds);
		pnRoomInfo.add(btnSaveRoom);
		
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, cbRoomType, -3, SpringLayout.NORTH, lblRoomType);
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, cbRoomType, 6, SpringLayout.EAST, lblRoomType);
		cbRoomType.setModel(new DefaultComboBoxModel(new String[] {"Regular", "VIP"}));
		pnRoomInfo.add(cbRoomType);
		
		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.SOUTH, pnRoomInfo, -6, SpringLayout.NORTH, btnBack);
		springLayout.putConstraint(SpringLayout.EAST, pnRoomInfo, 0, SpringLayout.EAST, btnBack);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, frmRoomManager.getContentPane());
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClick();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().add(btnBack);
		
		JLabel lblSelectHotel = new JLabel("Select hotel:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectHotel, 10, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSelectHotel, 0, SpringLayout.EAST, lblEnterRoomId);
		frmRoomManager.getContentPane().add(lblSelectHotel);
		
		springLayout.putConstraint(SpringLayout.NORTH, cbHotel, -3, SpringLayout.NORTH, lblSelectHotel);
		springLayout.putConstraint(SpringLayout.EAST, cbHotel, -78, SpringLayout.EAST, frmRoomManager.getContentPane());
		String[] cbHotelContents = new String[5];
		for (int i = 0; i < 5; i++)
			cbHotelContents[i] = "Hotel " + Integer.toString(i+1) + " - " + MainWindow.hotelNames[i];
		cbHotel.setModel(new DefaultComboBoxModel(cbHotelContents));
		frmRoomManager.getContentPane().add(cbHotel);
	}
	
	private void btnLoadRoomClick() {
		selectedHotel = cbHotel.getSelectedIndex() + 1;
		try {
			selectedRoomId = Integer.parseInt(txtRoomId.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Not a valid room id.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Room room = database.getRoomWithId(selectedHotel, selectedRoomId);
		
		if (room == null) {
			JOptionPane.showMessageDialog(null, "Room not found.", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		pnRoomInfo.setVisible(true);
		
		lblRoomInfoTitle.setText("Information for room " + Integer.toString(selectedRoomId) + " in hotel " + MainWindow.hotelNames[selectedHotel-1]);
		cbRoomType.setSelectedIndex(room.getType().equals(Room.TYPE_REGULAR) ? 0 : 1);
		spnSingleBeds.setValue(room.getSingleBeds());
		spnDoubleBeds.setValue(room.getDoubleBeds());
	}
	
	private void btnSaveRoomClick() {
		int hotel = cbHotel.getSelectedIndex() + 1;
		String roomType = cbRoomType.getSelectedIndex() == 0 ? Room.TYPE_REGULAR : Room.TYPE_VIP;
		int singleBeds = (Integer)spnSingleBeds.getValue();
		int doubleBeds = (Integer)spnDoubleBeds.getValue();
		
		Room room = new Room(selectedRoomId, singleBeds, doubleBeds, roomType);
		database.saveRoomAtId(hotel, selectedRoomId, room);
	}
	
	private void btnBackClick() {
		frmRoomManager.dispose();
		if (parentWindow != null)
			parentWindow.frmEmployee.setVisible(true);
	}
}
