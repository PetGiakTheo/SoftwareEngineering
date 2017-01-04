package com.softeng.window;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ManagerWindow {

	private DBController database = new DBController();
	public JFrame frmManager;
	private JTextField txtdis;
	private JButton btnOk;
	private JDateChooser strDate;
	private JDateChooser endDate;
	private JLabel lblek;
	private JLabel lblek2;
	private JLabel lblek3;
	private JPanel pnlbls;
	private JPanel pnManage;
	private JPanel pn2Discounts;
	private JPanel pnDiscounts;
	private final ButtonGroup HotelGroup = new ButtonGroup();
	private JRadioButton rh1;
	private JRadioButton rh2;
	private JRadioButton rh3;
	private JRadioButton rh4;
	private JRadioButton rh5;
	java.util.Date d1, d2;
	java.sql.Date sqldt;
	private int htl;
	int dis;

	/**
	 * Launch the application.
	 */
	/*
	 * DEN XREIAZETAI DEUTERH MAIN public static void main(String[] args) { EventQueue.invokeLater(new Runnable() { public void run() { try { Manager window = new Manager();
	 * window.frmManager.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public ManagerWindow() {
		initialize();
		event();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManager = new JFrame();
		frmManager.setTitle("Manager");
		frmManager.setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerWindow.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmManager.setBounds(100, 100, 1007, 612);
		frmManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnDiscounts = new JPanel();
		pnDiscounts.setBackground(UIManager.getColor("MenuBar.background"));
		pnDiscounts.setForeground(new Color(0, 0, 0));
		pnDiscounts.setBorder(null);
		pnDiscounts.setLayout(null);
		frmManager.getContentPane().setLayout(new CardLayout(0, 0));
		frmManager.getContentPane().add(pnDiscounts, "name_20917414295454");

		pn2Discounts = new JPanel();
		pn2Discounts.setBorder(new LineBorder(new Color(0, 128, 0)));
		pn2Discounts.setBounds(55, 73, 528, 439);
		pnDiscounts.add(pn2Discounts);
		pn2Discounts.setLayout(null);

		pnlbls = new JPanel();
		pnlbls.setBounds(89, 297, 346, 103);
		pn2Discounts.add(pnlbls);
		pnlbls.setBackground(Color.LIGHT_GRAY);
		pnlbls.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlbls.setLayout(null);

		lblek = new JLabel("skata");
		lblek.setBounds(10, 11, 325, 20);
		pnlbls.add(lblek);
		lblek.setVerticalAlignment(SwingConstants.TOP);
		lblek.setHorizontalAlignment(SwingConstants.LEFT);

		lblek2 = new JLabel("skata");
		lblek2.setBounds(10, 42, 325, 20);
		pnlbls.add(lblek2);

		lblek3 = new JLabel("skata");
		lblek3.setBounds(10, 73, 249, 20);
		pnlbls.add(lblek3);

		btnOk = new JButton("ok");
		btnOk.setBounds(273, 222, 58, 23);
		pn2Discounts.add(btnOk);

		txtdis = new JTextField();
		txtdis.setBounds(245, 145, 86, 20);
		pn2Discounts.add(txtdis);
		txtdis.setColumns(10);

		JLabel label_2 = new JLabel("Discount   %");
		label_2.setBounds(245, 122, 70, 14);
		pn2Discounts.add(label_2);

		endDate = new JDateChooser();
		endDate.setBounds(220, 66, 111, 20);
		pn2Discounts.add(endDate);
		endDate.setDateFormatString("yyyy MMM d");

		strDate = new JDateChooser();
		strDate.setBounds(89, 66, 111, 20);
		pn2Discounts.add(strDate);

		strDate.setDateFormatString("yyyy MMM d");

		JLabel label = new JLabel("\u0391\u03C1\u03C7\u03AE \u0395\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7\u03C2 :");
		label.setBounds(89, 41, 83, 14);
		pn2Discounts.add(label);

		JLabel label_1 = new JLabel("\u03A4\u03AD\u03BB\u03BF\u03C2 \u0395\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7\u03C2 :");
		label_1.setBounds(220, 41, 86, 14);
		pn2Discounts.add(label_1);

		rh1 = new JRadioButton("hotel 1");
		rh1.setSelected(true);
		HotelGroup.add(rh1);
		rh1.setBounds(63, 118, 109, 23);
		pn2Discounts.add(rh1);

		rh2 = new JRadioButton("hotel 2");
		HotelGroup.add(rh2);
		rh2.setBounds(63, 144, 109, 23);
		pn2Discounts.add(rh2);

		rh3 = new JRadioButton("hotel 3");
		HotelGroup.add(rh3);
		rh3.setBounds(63, 170, 109, 23);
		pn2Discounts.add(rh3);

		rh4 = new JRadioButton("hotel 4");
		HotelGroup.add(rh4);
		rh4.setBounds(63, 196, 109, 23);
		pn2Discounts.add(rh4);

		rh5 = new JRadioButton("hotel 5");
		HotelGroup.add(rh5);
		rh5.setBounds(63, 222, 109, 23);
		pn2Discounts.add(rh5);

		pnManage = new JPanel();
		frmManager.getContentPane().add(pnManage, "name_21247557707400");
		pnManage.setLayout(null);

		JLabel lblManageStaff = new JLabel("Manage Staff");
		lblManageStaff.setBounds(465, 28, 77, 51);
		pnManage.add(lblManageStaff);

	}

	public void event() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOKClick();
			}
		});

	}

	private void btnOKClick() {
		if (rh1.isSelected())
			htl = 1;
		else if (rh2.isSelected())
			htl = 2;
		else if (rh3.isSelected())
			htl = 3;
		else if (rh4.isSelected())
			htl = 4;
		else if (rh5.isSelected())
			htl = 5;

		try {

			dis = Integer.parseInt(txtdis.getText());

			if (dis >= 0 && dis <= 100) {

				d1 = strDate.getDate();
				d2 = endDate.getDate();

				database.addDiscount(htl, d1, d2, dis);

				lblek3.setText("kai h ekptwsh einai  " + dis + "%");
				lblek.setText("Η εκπτωση αρχιζει στις " + strDate.getDate());
				lblek2.setText("τελιωνει στις " + endDate.getDate());
			} else
				JOptionPane.showMessageDialog(null, "Invalid credentials.Discount must be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);

		} catch (Exception error) {

			JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
