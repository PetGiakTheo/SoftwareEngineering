package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class EmployeeWindow {

	public JFrame frmEmployee;

	/**
	 * Create the application.
	 */
	public EmployeeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployee = new JFrame();
		frmEmployee.setTitle("Employee");
		frmEmployee.setBounds(100, 100, 450, 300);
		frmEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployee.getContentPane().setLayout(new SpringLayout());
	}

}
