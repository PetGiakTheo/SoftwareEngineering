package com.softeng.window;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import com.softeng.misc.DBController;
import com.softeng.misc.GlobalItems;
import com.softeng.misc.Room;

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
		frmRoomManager.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/softeng/resources/icon.png")));
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
		SpringLayout slPnRoomInfo = new SpringLayout();
		pnRoomInfo.setLayout(slPnRoomInfo);
		
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, lblRoomInfoTitle, 10, SpringLayout.NORTH, pnRoomInfo);
		slPnRoomInfo.putConstraint(SpringLayout.WEST, lblRoomInfoTitle, 130, SpringLayout.WEST, pnRoomInfo);
		pnRoomInfo.add(lblRoomInfoTitle);
		
		JLabel lblRoomType = new JLabel("Room type:");
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, lblRoomType, 18, SpringLayout.SOUTH, lblRoomInfoTitle);
		slPnRoomInfo.putConstraint(SpringLayout.WEST, lblRoomType, 137, SpringLayout.WEST, pnRoomInfo);
		pnRoomInfo.add(lblRoomType);
		
		JLabel lblSingleBeds = new JLabel("Single beds:");
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, lblSingleBeds, 21, SpringLayout.SOUTH, lblRoomType);
		slPnRoomInfo.putConstraint(SpringLayout.EAST, lblSingleBeds, 0, SpringLayout.EAST, lblRoomType);
		pnRoomInfo.add(lblSingleBeds);
		
		slPnRoomInfo.putConstraint(SpringLayout.WEST, spnSingleBeds, 6, SpringLayout.EAST, lblSingleBeds);
		spnSingleBeds.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, spnSingleBeds, -3, SpringLayout.NORTH, lblSingleBeds);
		pnRoomInfo.add(spnSingleBeds);
		
		JLabel lblDoubleBeds = new JLabel("Double beds:");
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, lblDoubleBeds, 23, SpringLayout.SOUTH, lblSingleBeds);
		slPnRoomInfo.putConstraint(SpringLayout.EAST, lblDoubleBeds, 0, SpringLayout.EAST, lblRoomType);
		pnRoomInfo.add(lblDoubleBeds);
		
		slPnRoomInfo.putConstraint(SpringLayout.WEST, spnDoubleBeds, 6, SpringLayout.EAST, lblDoubleBeds);
		spnDoubleBeds.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, spnDoubleBeds, -3, SpringLayout.NORTH, lblDoubleBeds);
		pnRoomInfo.add(spnDoubleBeds);
		
		JButton btnSaveRoom = new JButton("Save room");
		slPnRoomInfo.putConstraint(SpringLayout.WEST, btnSaveRoom, 144, SpringLayout.WEST, pnRoomInfo);
		slPnRoomInfo.putConstraint(SpringLayout.EAST, btnSaveRoom, -144, SpringLayout.EAST, pnRoomInfo);
		btnSaveRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveRoomClick();
			}
		});
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, btnSaveRoom, 20, SpringLayout.SOUTH, spnDoubleBeds);
		pnRoomInfo.add(btnSaveRoom);
		
		slPnRoomInfo.putConstraint(SpringLayout.NORTH, cbRoomType, -3, SpringLayout.NORTH, lblRoomType);
		slPnRoomInfo.putConstraint(SpringLayout.WEST, cbRoomType, 6, SpringLayout.EAST, lblRoomType);
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
			cbHotelContents[i] = "Hotel " + Integer.toString(i+1) + " - " + GlobalItems.HOTEL_NAMES[i];
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
		
		lblRoomInfoTitle.setText("Information for room " + Integer.toString(selectedRoomId) + " in hotel " + GlobalItems.HOTEL_NAMES[selectedHotel-1]);
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
		JOptionPane.showMessageDialog(null, "Room was changed successfully.", "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void btnBackClick() {
		frmRoomManager.dispose();
		if (parentWindow != null)
			parentWindow.frmEmployee.setVisible(true);
	}
}
