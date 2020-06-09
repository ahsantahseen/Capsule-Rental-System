package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.TextField;

import javax.management.Query;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		
		setBounds(100, 100, 598, 257);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("7d Capsule Facility Login");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblNewLabel.setBounds(20, 25, 209, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images1/iconfinder_Unknown_person_132244.png")));
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblNewLabel_1.setBounds(171, 73, 73, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/images1/iconfinder_Key_132286.png")));
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblNewLabel_2.setBounds(168, 126, 24, 32);
		contentPane.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setBounds(202, 76, 178, 20);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/images1/iconfinder_Accept_132347.png")));
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username=txtusername.getText().toString();
				String userpass=txtpassword.getText().toString();
				if(txtusername.getText().isEmpty()&&txtpassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Please fill all the required fields");
				}
				else {
				try {
					databaseCon con=new databaseCon();
					String query="select * from credentials where username='"+username+"' and password='"+userpass+"'";
					java.sql.PreparedStatement st=con.getConnection().prepareStatement(query);
					ResultSet rs=st.executeQuery();
				if(rs.next()) 
				   {
					   mainmenu m=new mainmenu();
					   m.setVisible(true);
					   m.setLocationRelativeTo(null);
					   dispose();
                        
				   }
				   else
				   {
					   JOptionPane.showInternalMessageDialog(contentPane,"Invalid Username and password");
				   }
				}
				catch(SQLException E) {
					JOptionPane.showMessageDialog(contentPane, E.getMessage());
				}
				}
		}});
		btnNewButton.setBounds(242, 179, 89, 32);
		btnNewButton.setBorder(null);
		contentPane.add(btnNewButton);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(202, 138, 178, 20);
		contentPane.add(txtpassword);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		lblNewLabel_3.setBounds(0, -42, 582, 111);
		contentPane.add(lblNewLabel_3);
	}
}
	