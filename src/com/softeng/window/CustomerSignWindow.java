package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.softeng.misc.Room;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomerSignWindow {

	JFrame frmCustomerSign;
	
	private Room selectedRoom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSignWindow window = new CustomerSignWindow(null);
					window.frmCustomerSign.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerSignWindow(Room selectedRoom) {
		this.selectedRoom = selectedRoom;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustomerSign = new JFrame();
		frmCustomerSign.setBounds(100, 100, 450, 300);
		frmCustomerSign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerSign.getContentPane().setLayout(new MigLayout("", "[141.00][][][][][][][][][][][][]", "[]"));
		
		JLabel lblSelectedRoom = new JLabel("Thank you for selecting room ");
		frmCustomerSign.getContentPane().add(lblSelectedRoom, "cell 4 0");
		if (selectedRoom != null)
			lblSelectedRoom.setText(lblSelectedRoom.getText() + Integer.toString(selectedRoom.getId()) + "!");
	}

}
