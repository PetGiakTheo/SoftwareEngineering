package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerSearch {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSearch window = new CustomerSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDgflkgdjflg = new JLabel("Room filters");
		lblDgflkgdjflg.setBounds(188, 11, 64, 14);
		frame.getContentPane().add(lblDgflkgdjflg);
		
		JLabel lblBeds = new JLabel("Beds");
		lblBeds.setBounds(10, 45, 46, 14);
		frame.getContentPane().add(lblBeds);
	}

}
