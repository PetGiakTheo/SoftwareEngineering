package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class CancelReservationWindow {

	JFrame frmCancelReservation;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelReservationWindow window = new CancelReservationWindow();
					window.frmCancelReservation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelReservationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelReservation = new JFrame();
		frmCancelReservation.setBounds(100, 100, 450, 300);
		frmCancelReservation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCancelReservation.getContentPane().setLayout(springLayout);
		
		JLabel lblReservationCancel = new JLabel("Reservation cancel");
		springLayout.putConstraint(SpringLayout.NORTH, lblReservationCancel, 10, SpringLayout.NORTH, frmCancelReservation.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblReservationCancel, 192, SpringLayout.WEST, frmCancelReservation.getContentPane());
		frmCancelReservation.getContentPane().add(lblReservationCancel);
	}
}
