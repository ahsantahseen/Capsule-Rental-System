package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class CapsuleCheckOut extends JFrame {

	private JPanel contentPane;
	private JTextField txtcapid;
	private JLabel lblCapsule;
	private JTextField txtcstname;
	private final JLabel lblNewLabel_1 = new JLabel("");
	private JTextField txtorderid;
	private JTextField txtrentalfee;
	private JTextField txttimeusage;
	private JTextField txtorderdate;
	private JTextField txtcapsuletype;
	private JTextField txtcustomercontact;
	private JButton customerecordsparse;
	private JButton customerrecordsparse;
	private JButton activerentalparse;
	private JButton button_2;
    private float a,c,d;
    private float b;
    private JButton button_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapsuleCheckOut frame = new CapsuleCheckOut();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CapsuleCheckOut() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 571);
		contentPane = new JPanel();
			
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckoutPanel = new JLabel("Checkout Panel");
		lblCheckoutPanel.setBounds(4, 15, 294, 32);
		lblCheckoutPanel.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		contentPane.add(lblCheckoutPanel);
		
		txtcapid = new JTextField();
		txtcapid.setBounds(130, 179, 138, 28);
		txtcapid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		txtcapid.setEditable(false);
		contentPane.add(txtcapid);
		txtcapid.setColumns(10);
		
		JTextArea receipt = new JTextArea();
		receipt.setBorder(new LineBorder(new Color(0, 0, 0)));
		receipt.setBounds(318, 125, 390, 396);
		receipt.setEditable(false);
		receipt.setBackground(new Color(255, 255, 224));
		contentPane.add(receipt);
		
		JLabel lblNewLabel = new JLabel("Capsule_ID");
		lblNewLabel.setBounds(20, 179, 99, 28);
		contentPane.add(lblNewLabel);
		
		lblCapsule = new JLabel("Customer_Name");
		lblCapsule.setBounds(20, 232, 121, 28);
		contentPane.add(lblCapsule);
		
		txtcstname = new JTextField();
		txtcstname.setBounds(130, 232, 138, 28);
		txtcstname.setEditable(false);
		txtcstname.setColumns(10);
		contentPane.add(txtcstname);
		
		JLabel lblCurrentDate = new JLabel("Order ID");
		lblCurrentDate.setBounds(20, 125, 138, 28);
		contentPane.add(lblCurrentDate);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(142, 458, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu m=new mainmenu();
				m.setVisible(true);
				m.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/iconfinder_Left_132177.png")));
		btnNewButton.setBorder(null);
		contentPane.add(btnNewButton);

		JButton button = new JButton("");
		button.setBounds(52, 458, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				receipt.setText("===================== 7d Capsule Hotel =====================================\n");
				receipt.setText(receipt.getText()+"\n");
				receipt.setText(receipt.getText()+"====================== Order Receipt =========================================\n");
				receipt.setText(receipt.getText()+"\n");
				receipt.setText(receipt.getText()+"=======================================================================================\n");
				receipt.setText(receipt.getText()+"||  Capsule ID:"+ " "+ txtcapid.getText().toString()+"\t \t \t         ||\n");
				receipt.setText(receipt.getText()+"||  Capsule Type:"+ " "+ txtcapsuletype.getText().toString()+"\t \t \t         ||\n");
				
				receipt.setText(receipt.getText()+"=======================================================================================\n");
				receipt.setText(receipt.getText()+"||  Customer Name:"+ " "+ txtcstname.getText().toString()+"\t \t \t        ||\n");
				receipt.setText(receipt.getText()+"||  Contact Number:"+ " "+txtcustomercontact.getText().toString()+"\t \t         ||\n");
				receipt.setText(receipt.getText()+"=======================================================================================\n");
				
				receipt.setText(receipt.getText()+"||  Order ID:"+ " "+txtorderid.getText().toString()+"\t \t \t         ||\n");
				receipt.setText(receipt.getText()+"||  Order Date:"+ " "+txtorderdate.getText().toString()+"\t \t \t         ||\n");
				receipt.setText(receipt.getText()+"||  Time Usage:"+ " "+txttimeusage.getText().toString()+" "+" Hours"+"\t \t \t         ||\n");
				receipt.setText(receipt.getText()+"||  Rental Fee:"+ " "+txtrentalfee.getText().toString()+"\t \t \t         ||\n");
				receipt.setText(receipt.getText()+"=======================================================================================\n");
				
				a=Float.parseFloat(txtrentalfee.getText().toString());
				
				b=Float.parseFloat(txttimeusage.getText().toString());
			    
				if(b>=48.0f) {//1 day extra
			    	d=300f;
			    }	
				else if(b>=168.0f) {//1 week extra
					d=900f;
				}
				else {
					d=0f;
				}
				c=a*0.015f;
				receipt.setText(receipt.getText()+"||  Total Amount:"+a+" "+"RS"+"\t \t\t         || \n");
				receipt.setText(receipt.getText()+"||  Extra-Charges:"+" "+d+" "+"RS"+"\t \t\t         || \n");
				receipt.setText(receipt.getText()+"||  Service Tax(15%):"+" "+c+" "+"RS"+"\t \t \t         || \n");
				receipt.setText(receipt.getText()+"=======================================================================================\n");
				receipt.setText(receipt.getText()+"\n");
				receipt.setText(receipt.getText()+"\n");
				receipt.setText(receipt.getText()+"\t   **2020 V1.0 Prototype verison**\n");
				receipt.setText(receipt.getText()+"\t**Designed by Muhammad Ahsan**\n");

			}
				catch(Exception e2){
					
				    e2.printStackTrace();
				}
			}
			
			
		});
		button.setBackground(new Color(255, 255, 255));
		button.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/iconfinder_Lists_132198.png")));
		button.setBorder(null);
		contentPane.add(button);
		lblNewLabel_1.setBounds(0, -63, 718, 151);
		lblNewLabel_1.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Computerized Generated Recipt");
		lblNewLabel_2.setBounds(69, 507, 199, 14);
		contentPane.add(lblNewLabel_2);
		
		

		
		
		txtorderid = new JTextField();
		txtorderid.setBounds(130, 129, 138, 28);
		txtorderid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				databaseCon con=new databaseCon();
				String query="select * from activerental where Order_ID='"+txtorderid.getText()+"'";
				try {
				  
				  ResultSet st;
                  st=con.getConnection().createStatement().executeQuery(query);                   
                  
                  if(st.next()) {
                	  txtcstname.setText(st.getString("Customer_Name").toString());
                	  txtcapid.setText(st.getString("Capsule_ID").toString());
                	  
                  }
                  

				}
				catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane,e1.getMessage());
				}

			}
		});
		txtorderid.setColumns(10);
		contentPane.add(txtorderid);
		
		txtrentalfee = new JTextField();
		txtrentalfee.setBounds(291, 507, 7, 20);
		txtrentalfee.setVisible(false);
		txtrentalfee.setOpaque(false);
		txtrentalfee.setEditable(false);
		txtrentalfee.setEnabled(false);
		contentPane.add(txtrentalfee);
		txtrentalfee.setColumns(10);
		
		txttimeusage = new JTextField();
		txttimeusage.setBounds(279, 507, 7, 20);
		txttimeusage.setVisible(false);
		txttimeusage.setOpaque(false);
		txttimeusage.setEnabled(false);
		txttimeusage.setEditable(false);
		txttimeusage.setColumns(10);
		contentPane.add(txttimeusage);
		
		txtorderdate = new JTextField();
		txtorderdate.setBounds(267, 507, 7, 20);
		txtorderdate.setVisible(false);
		txtorderdate.setOpaque(false);
		txtorderdate.setEnabled(false);
		txtorderdate.setEditable(false);
		txtorderdate.setColumns(10);
		contentPane.add(txtorderdate);
		
		txtcapsuletype = new JTextField();
		txtcapsuletype.setBounds(250, 507, 7, 20);
		txtcapsuletype.setVisible(false);
		txtcapsuletype.setOpaque(false);
		txtcapsuletype.setEnabled(false);
		txtcapsuletype.setEditable(false);
		txtcapsuletype.setColumns(10);
		contentPane.add(txtcapsuletype);
		
		txtcustomercontact = new JTextField();
		txtcustomercontact.setBounds(239, 507, 7, 20);
		txtcustomercontact.setVisible(false);
		txtcustomercontact.setOpaque(false);
		txtcustomercontact.setEnabled(false);
		txtcustomercontact.setEditable(false);
		txtcustomercontact.setColumns(10);
		contentPane.add(txtcustomercontact);
		
		
		
		activerentalparse = new JButton("");
		activerentalparse.setBounds(62, 406, 70, 32);
		activerentalparse.setBorder(null);
		activerentalparse.setForeground(new Color(255, 255, 255));
		activerentalparse.setBackground(new Color(255, 255, 255));
		activerentalparse.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/iconfinder_Upload_132485.png")));
		activerentalparse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseCon con=new databaseCon();
				String query="select * from activerental where Order_ID='"+txtorderid.getText()+"'";
				String query1="select * from capsuleregistration where Capsule_ID='"+txtcapid.getText()+"'";
				String query2="select * from customerregistration where Customer_Name='"+txtcstname.getText()+"'";;
				if(txtorderid.getText().toString().isBlank()||txtcstname.getText().toString().isBlank()||txtcapid.getText().toString().isBlank())
				{
					JOptionPane.showMessageDialog(contentPane,"Fields are empty! Please enter data into the fields first");
				}
				else {
				try {
				  
				  ResultSet st;
                  st=con.getConnection().createStatement().executeQuery(query);
                  JOptionPane.showMessageDialog(contentPane, "Data Parsed");
                  
                  if(st.next()) {
                	  
                	  txttimeusage.setText(st.getString("Usage_Time").toString());
                	  txtrentalfee.setText(st.getString("Rental_Fee").toString());
                	  txtorderdate.setText(st.getString("Order_Date").toString());
                  }
                  

				}
				catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane,e1.getMessage());
				}
				try {
					  
					  ResultSet st;
	                  st=con.getConnection().createStatement().executeQuery(query1);
	                  
	                  
	                  if(st.next()) {
	                	  
	                	  txtcapsuletype.setText(st.getString("Capsule_Type").toString());
	                	  
	                  }
	                  

					}
					catch (Exception e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(contentPane,e1.getMessage());
					}
				try {
				  
				  ResultSet st;
                st=con.getConnection().createStatement().executeQuery(query2);
                
                
                if(st.next()) {
              	  
              	  txtcustomercontact.setText(st.getString("Customer_Contact").toString());
              	  
                }
                

				}
				catch(Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane,e1.getMessage());
				}
			}
			}
		});
		contentPane.add(activerentalparse);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(142, 406, 89, 41);
		button_1.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/Printer.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean print_complete=receipt.print();
					if(print_complete) {
						JOptionPane.showMessageDialog(contentPane, "Printing Done",
						"Printer", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Please wait printing....");
					}
				}
				catch(PrinterException p) {
					JOptionPane.showMessageDialog(contentPane, p.getMessage());
				}
			}
		});
		button_1.setBorder(null);
		button_1.setBackground(Color.WHITE);
		contentPane.add(button_1);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receipt.setText(null);
			}
		});
		button_2.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/iconfinder_Wizard_132269.png")));
		button_2.setBorder(null);
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(241, 458, 57, 23);
		contentPane.add(button_2);
		
		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcapid.setText(null);
				txtcapsuletype.setText(null);
				txtcstname.setText(null);
				txtcustomercontact.setText(null);
				txtorderdate.setText(null);
				txtorderid.setText(null);
				txtrentalfee.setText(null);
				txttimeusage.setText(null);
			}
		});
		button_3.setIcon(new ImageIcon(CapsuleCheckOut.class.getResource("/images1/icons8-clear-symbol-24.png")));
		button_3.setBorder(null);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(239, 410, 53, 28);
		contentPane.add(button_3);
	}
}
