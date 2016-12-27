package com.softeng.window;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.Component;

public class Manager {

	public JFrame frmManager;
	private JTextField txtdis;
	private JButton button;
	private JDateChooser strDate;
	private JDateChooser endDate;
	private JCheckBox ch1;
	private JCheckBox ch2;
	private JCheckBox ch3;
	private JCheckBox ch4;
	private JCheckBox ch5;
	private JLabel lblek;
	private JLabel lblek2;
	private JLabel lblek3;
	private JPanel panel;
	private JPanel pnStatics;
	private JPanel panel_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager window = new Manager();
					window.frmManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manager() {
		initialize();
		event();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManager = new JFrame();
		frmManager.setTitle("Manager");
		frmManager.setIconImage(Toolkit.getDefaultToolkit().getImage(Manager.class.getResource("/com/softeng/resources/hotel (2).png")));
		frmManager.setBounds(100, 100, 1349, 748);
		frmManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmManager.getContentPane().setLayout(null);
												
												panel = new JPanel();
												panel.setBounds(-73, 11, 1294, 699);
												frmManager.getContentPane().add(panel);
												panel.setAutoscrolls(true);
												
												JPanel pnDiscounts = new JPanel();
												pnDiscounts.setBounds(95, 126, 366, 429);
												pnDiscounts.setBackground(UIManager.getColor("MenuBar.background"));
												pnDiscounts.setForeground(new Color(0, 0, 0));
												pnDiscounts.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
												
												strDate = new JDateChooser();
												
												strDate.setDateFormatString("d MMM, yyyy");
												strDate.setBounds(10, 84, 111, 20);
												
												JLabel label = new JLabel("\u0391\u03C1\u03C7\u03AE \u0395\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7\u03C2 :");
												label.setBounds(10, 59, 83, 14);
												
												endDate = new JDateChooser();
												
						
												endDate.addPropertyChangeListener(new PropertyChangeListener() {								
													public void propertyChange(PropertyChangeEvent evt) {
														
														if(endDate.getDate()!=null)
														{
														lblek2.setText("τελιωνει στις "+endDate.getDate());
														}
													}
												});
												
												
												
												endDate.setDateFormatString("d MMM, yyyy");
												endDate.setBounds(141, 84, 111, 20);
												
												
												JLabel label_1 = new JLabel("\u03A4\u03AD\u03BB\u03BF\u03C2 \u0395\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7\u03C2 :");
												label_1.setBounds(141, 59, 86, 14);
												
												ch1 = new JCheckBox("Hotel 1");
												ch1.setBackground(new Color(255, 255, 255));
												ch1.setBounds(6, 136, 67, 23);
												
												ch2 = new JCheckBox("Hotel 2");
												ch2.setBounds(6, 162, 67, 23);
												
												ch3 = new JCheckBox("Hotel 3");
												ch3.setBounds(6, 188, 67, 23);
												
												ch4 = new JCheckBox("Hotel 4");
												ch4.setBounds(6, 214, 67, 23);
												
												ch5 = new JCheckBox("Hotel 5");
												ch5.setBounds(6, 240, 67, 23);
												
												button = new JButton("ok");
												
													button.setBounds(194, 240, 58, 23);
													
													txtdis = new JTextField();
													txtdis.setBounds(166, 163, 86, 20);
													txtdis.setColumns(10);
													
													JLabel label_2 = new JLabel("Discount   %");
													label_2.setBounds(166, 140, 70, 14);
													pnDiscounts.setLayout(null);
													pnDiscounts.add(label);
													pnDiscounts.add(label_1);
													pnDiscounts.add(strDate);
													pnDiscounts.add(endDate);
													pnDiscounts.add(ch1);
													pnDiscounts.add(ch2);
													pnDiscounts.add(label_2);
													pnDiscounts.add(txtdis);
													pnDiscounts.add(ch3);
													pnDiscounts.add(ch4);
													pnDiscounts.add(ch5);
													pnDiscounts.add(button);
													
													panel_1 = new JPanel();
													panel_1.setBackground(Color.LIGHT_GRAY);
													panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
													panel_1.setBounds(10, 315, 346, 103);
													pnDiscounts.add(panel_1);
													panel_1.setLayout(null);
													
													lblek = new JLabel("skata");
													lblek.setBounds(10, 11, 325, 20);
													panel_1.add(lblek);
													lblek.setVerticalAlignment(SwingConstants.TOP);
													lblek.setHorizontalAlignment(SwingConstants.LEFT);
													
													lblek2 = new JLabel("skata");
													lblek2.setBounds(10, 42, 325, 20);
													panel_1.add(lblek2);
													
													lblek3 = new JLabel("skata");
													lblek3.setBounds(10, 73, 249, 20);
													panel_1.add(lblek3);
													
													pnStatics = new JPanel();
													pnStatics.setBounds(479, 126, 509, 397);
													pnStatics.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Statistics", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
													pnStatics.setLayout(null);
													
													JLabel label_3 = new JLabel("Εσοδα");
													label_3.setBounds(10, 43, 46, 14);
													pnStatics.add(label_3);
													panel.setLayout(null);
													panel.add(pnDiscounts);
													panel.add(pnStatics);
													
													JPanel panel_2 = new JPanel();
													panel_2.setBorder(new TitledBorder(null, "Manage Staff", TitledBorder.LEADING, TitledBorder.TOP, null, null));
													panel_2.setBounds(959, 259, 289, 429);
													panel.add(panel_2);
													panel_2.setLayout(null);
		
	
		
	}
	
	public void event(){
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//String end = endDate.getDateFormatString();
				String dis = txtdis.getText();
				
				lblek.setText("Η εκπτωση αρχιζει στις "+strDate.getDate());
				lblek2.setText("τελιωνει στις "+endDate.getDate());
				lblek3.setText("kai h ekptwsh einai  "+dis+"%");
				
				
				
				
				
				
			}
		});
		
	}
}
