package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.ImageIcon;

public class mainmenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainmenu frame = new mainmenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainmenu() {
	   Login n=new Login();
	   n.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 446);
		setUndecorated(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(85, 11, 208, 37);
		contentPane.add(lblNewLabel);
		
		JButton viewrental = new JButton("Rental Menu");
		viewrental.setBackground(new Color(192, 192, 192));
		viewrental.setFocusPainted(false);
		viewrental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentalMenu r=new RentalMenu();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				dispose();
			}
		});
		viewrental.setBounds(15, 149, 129, 45);
		contentPane.add(viewrental);
		
		JButton customerreg = new JButton("Customer Menu");
		customerreg.setBackground(new Color(192, 192, 192));
		viewrental.setFocusPainted(false);
		customerreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerRegistration cstrs=new CustomerRegistration();
				cstrs.setVisible(true);
				cstrs.setLocationRelativeTo(null);
				dispose();
			}
		});
		customerreg.setBounds(15, 79, 129, 45);
		contentPane.add(customerreg);
		
		JButton capsulereg = new JButton("Capsule Menu");
		capsulereg.setBackground(new Color(192, 192, 192));
		viewrental.setFocusPainted(false);
		capsulereg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				CapsuleRegistration cprs=new CapsuleRegistration();
				cprs.setVisible(true);
				cprs.setLocationRelativeTo(null);
				dispose();
			}
		});
		capsulereg.setBounds(15, 215, 129, 45);
		contentPane.add(capsulereg);
		
		JButton checkout = new JButton("Checkout");
		checkout.setBackground(new Color(192, 192, 192));
		viewrental.setFocusPainted(false);
		checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapsuleCheckOut checkout=new CapsuleCheckOut();
				checkout.setVisible(true);
				checkout.setLocationRelativeTo(null);
				dispose();
			}
		});
		checkout.setBounds(15, 281, 129, 45);
		contentPane.add(checkout);
		
		JButton logout = new JButton("Logout");
		logout.setBackground(new Color(192, 192, 192));
		viewrental.setFocusPainted(false);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login=new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		logout.setBounds(15, 344, 129, 44);
		contentPane.add(logout);
		
		JLabel lblNewLabel_1 = new JLabel("For capusles on rent 's information");
		lblNewLabel_1.setBounds(184, 156, 220, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblForCustomerInformation = new JLabel("For customer information");
		lblForCustomerInformation.setBounds(184, 94, 198, 14);
		contentPane.add(lblForCustomerInformation);
		
		JLabel lblForCapsuleInformation = new JLabel("For capsule information");
		lblForCapsuleInformation.setBounds(184, 230, 198, 14);
		contentPane.add(lblForCapsuleInformation);
		
		JLabel lblForCheckOuts = new JLabel("For checkouts");
		lblForCheckOuts.setBounds(184, 296, 154, 14);
		contentPane.add(lblForCheckOuts);
		
		JLabel lblForLogingOut = new JLabel("For loging out of the system");
		lblForLogingOut.setBounds(184, 359, 175, 14);
		contentPane.add(lblForLogingOut);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(mainmenu.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		label_4.setBounds(0, 0, 404, 69);
		contentPane.add(label_4);
	}

}
