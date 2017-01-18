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

	public JFrame frmRoomManager;
	private EmployeeWindow parentWindow;
	
	private DBController database;
	private JTextField txtRoomId;
	private JPanel pnRoomInfo = new JPanel();
	private JLabel lblRoomInfoTitle = new JLabel("Information for room with id");
	private JSpinner spnSingleBeds = new JSpinner();
	private JSpinner spnDoubleBeds = new JSpinner();
	private JComboBox cbRoomType = new JComboBox();
	
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
		frmRoomManager.setBounds(100, 100, 450, 320);
		frmRoomManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, pnRoomInfo, -54, SpringLayout.SOUTH, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().setLayout(springLayout);
		
		JLabel lblEnterRoomId = new JLabel("Enter room id:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterRoomId, 14, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblEnterRoomId, -282, SpringLayout.EAST, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().add(lblEnterRoomId);
		
		txtRoomId = new JTextField();
		txtRoomId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLoadRoomClick();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, txtRoomId, 12, SpringLayout.NORTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtRoomId, 18, SpringLayout.EAST, lblEnterRoomId);
		springLayout.putConstraint(SpringLayout.EAST, txtRoomId, -178, SpringLayout.EAST, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().add(txtRoomId);
		txtRoomId.setColumns(10);
		
		JButton btnLoadRoom = new JButton("Load room");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoadRoom, -5, SpringLayout.NORTH, pnRoomInfo);
		btnLoadRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoadRoomClick();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnLoadRoom, 33, SpringLayout.EAST, txtRoomId);
		springLayout.putConstraint(SpringLayout.EAST, btnLoadRoom, -34, SpringLayout.EAST, frmRoomManager.getContentPane());
		frmRoomManager.getContentPane().add(btnLoadRoom);
		
		springLayout.putConstraint(SpringLayout.NORTH, pnRoomInfo, 38, SpringLayout.NORTH, frmRoomManager.getContentPane());
		pnRoomInfo.setBackground(SystemColor.control);
		springLayout.putConstraint(SpringLayout.WEST, pnRoomInfo, 10, SpringLayout.WEST, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnRoomInfo, 424, SpringLayout.WEST, frmRoomManager.getContentPane());
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
		btnSaveRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveRoomClick();
			}
		});
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, btnSaveRoom, 20, SpringLayout.SOUTH, spnDoubleBeds);
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, btnSaveRoom, 0, SpringLayout.WEST, lblRoomInfoTitle);
		sl_pnRoomInfo.putConstraint(SpringLayout.EAST, btnSaveRoom, -2, SpringLayout.EAST, lblRoomInfoTitle);
		pnRoomInfo.add(btnSaveRoom);
		
		sl_pnRoomInfo.putConstraint(SpringLayout.NORTH, cbRoomType, -3, SpringLayout.NORTH, lblRoomType);
		sl_pnRoomInfo.putConstraint(SpringLayout.WEST, cbRoomType, 6, SpringLayout.EAST, lblRoomType);
		cbRoomType.setModel(new DefaultComboBoxModel(new String[] {"Regular", "VIP"}));
		pnRoomInfo.add(cbRoomType);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackClick();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, frmRoomManager.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 0, SpringLayout.EAST, pnRoomInfo);
		frmRoomManager.getContentPane().add(btnBack);
	}
	
	private void btnLoadRoomClick() {
		try {
			selectedRoomId = Integer.parseInt(txtRoomId.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Not a valid room id.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Room room = database.getRoomWithId(selectedRoomId);
		
		if (room == null) {
			JOptionPane.showMessageDialog(null, "Room not found.", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		pnRoomInfo.setVisible(true);
		
		lblRoomInfoTitle.setText("Information for room with id " + Integer.toString(selectedRoomId));
		cbRoomType.setSelectedIndex(room.getType().equals(Room.TYPE_REGULAR) ? 0 : 1);
		spnSingleBeds.setValue(room.getSingleBeds());
		spnDoubleBeds.setValue(room.getDoubleBeds());
	}
	
	private void btnSaveRoomClick() {
		String roomType = cbRoomType.getSelectedIndex() == 0 ? Room.TYPE_REGULAR : Room.TYPE_VIP;
		int singleBeds = (Integer)spnSingleBeds.getValue();
		int doubleBeds = (Integer)spnDoubleBeds.getValue();
		
		Room room = new Room(selectedRoomId, singleBeds, doubleBeds, roomType);
		database.saveRoomAtId(selectedRoomId, room);
	}
	
	private void btnBackClick() {
		frmRoomManager.dispose();
		if (parentWindow != null)
			parentWindow.frmEmployee.setVisible(true);
	}
}
