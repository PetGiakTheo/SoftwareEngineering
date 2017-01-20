package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;

public class ReservationManagerWindow {

	public JFrame frmReservationManager;

	/**
	 * Launch the application.
	 */
	// TODO remove main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationManagerWindow window = new ReservationManagerWindow();
					window.frmReservationManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservationManagerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservationManager = new JFrame();
		frmReservationManager.setTitle("Reservation manager");
		frmReservationManager.setType(Type.POPUP);
		frmReservationManager.setBounds(100, 100, 450, 300);
		frmReservationManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmReservationManager.getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmReservationManager.getContentPane().add(tabbedPane, "name_8762231496376");
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
	}
}
