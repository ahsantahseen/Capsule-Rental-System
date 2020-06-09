package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

public class RentalMenu extends JFrame {

	private JPanel contentPane;
	private JTextField srtxtcapid1;
	private JTextField srtxtcstid1;
	private JTextField txtrentalfee;
	private JTextField srtxtrentalstatus;
	private JTextField srtxtcstname;
	private JTextField srtxtcaptype;
	private JTable rentaltable;
	Statement st;
	ResultSet rs;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	int orderid=0;
	private JTextField txtorderid;
	private JTextField txtdays;
	private JTextField txtorderdate;
	private JTextField txtcapid1;
	private JTextField txtcstname;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalMenu frame = new RentalMenu();
					
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
	public RentalMenu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 438);
		contentPane = new JPanel();
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnAddRental = new JButton("");
		btnAddRental.setBackground(new Color(255, 255, 255));
		btnAddRental.setBorder(null);
		btnAddRental.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/iconfinder_Add_132335.png")));
		btnAddRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             if(txtcapid1.getText().isEmpty()||txtcstname.getText().isEmpty()||txtdays.getText().isEmpty()||txtrentalfee.getText().isEmpty()) {
               JOptionPane.showMessageDialog(contentPane, "Please fill all the required fields!");	 
             }
             else {
            	 try {
				    
					
					java.sql.Statement st;
					
				
				databaseCon con=new databaseCon();
		
				String validate_query="select * from activerental where Order_ID='"+txtorderid.getText()+"'";
				ResultSet rs=con.getConnection().createStatement().executeQuery(validate_query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(contentPane,"Record Duplication Error!");
				}
				else {
					try {
				String query="INSERT INTO activerental(Order_ID,Capsule_ID,Customer_Name,Rental_Fee,Usage_Time,Order_Date)"
						+ " VALUES('"+txtorderid.getText()+"','"+txtcapid1.getText()+"','"
						+txtcstname.getText()+"','"+txtrentalfee.getText()+"','"+txtdays.getText()+"','"+txtorderdate.getText()+"')";
			    st=con.getConnection().createStatement();
			    st.executeUpdate(query);
			    JOptionPane.showMessageDialog(contentPane, "Added");
		
			    autoID();
			    autoDate();
			    UpdateTable();
                txtcapid1.setText(null);
			    
			    txtrentalfee.setText(null);
			    
			    txtcstname.setText(null);
			    
			   
			    txtdays.setText(null);
			    st.close();
                
			}
				catch(SQLException ex) {
				  JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				}
            	}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				}
			
			
		}});
		
		JLabel lblRentalMenu = new JLabel("Rental Menu");
		lblRentalMenu.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblRentalMenu.setBounds(10, 11, 294, 32);
		contentPane.add(lblRentalMenu);
		btnAddRental.setBounds(24, 275, 88, 33);
		contentPane.add(btnAddRental);
		
		JButton btnCancelRental = new JButton("");
		btnCancelRental.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelRental.setBackground(new Color(255, 255, 255));
		btnCancelRental.setBorder(null);
		btnCancelRental.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/iconfinder_Left_132177.png")));
		btnCancelRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu m=new mainmenu();
				m.setVisible(true);
				m.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCancelRental.setBounds(122, 318, 95, 33);
		btnCancelRental.setBorder(null);
		contentPane.add(btnCancelRental);
		
		JLabel lblCapsuleID = new JLabel("Capsule ID");
		lblCapsuleID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCapsuleID.setBounds(569, 98, 81, 14);
		contentPane.add(lblCapsuleID);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomerId.setBounds(813, 98, 81, 14);
		contentPane.add(lblCustomerId);
		
		JLabel lblRentalFee = new JLabel("Rental Fee");
		lblRentalFee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRentalFee.setBounds(10, 184, 81, 14);
		contentPane.add(lblRentalFee);
		
		JLabel lblCurrentDate = new JLabel("Usage Time(hrs)");
		lblCurrentDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurrentDate.setBounds(10, 209, 102, 14);
		contentPane.add(lblCurrentDate);
		
		JLabel lblAvailibleForRent = new JLabel("Type");
		lblAvailibleForRent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvailibleForRent.setBounds(569, 148, 165, 21);
		contentPane.add(lblAvailibleForRent);
		
		JLabel lblDueDate = new JLabel("Date of order");
		lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDueDate.setBounds(10, 234, 99, 14);
		contentPane.add(lblDueDate);
		
		srtxtcapid1 = new JTextField();
		srtxtcapid1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				databaseCon con=new databaseCon();
				String query="select * from capsuleregistration where Capsule_ID='"+srtxtcapid1.getText()+"'";
				try {
				  
				  ResultSet st;
                  st=con.getConnection().createStatement().executeQuery(query);                   
                  
                  if(st.next()) {
                	  srtxtrentalstatus.setText(st.getString("Capsule_Available_for_rent").toString());
                	  srtxtcaptype.setText(st.getString("Capsule_Type").toString());
                	  
                  }
                  

				}
				catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane,e1.getMessage());
				}
			}
		});
		srtxtcapid1.setBounds(684, 97, 106, 20);
		contentPane.add(srtxtcapid1);
		srtxtcapid1.setColumns(10);
		
		srtxtcstid1 = new JTextField();
		srtxtcstid1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query="select * from customerregistration where Customer_ID='"+srtxtcstid1.getText()+"'";
				try {
					databaseCon con = new databaseCon();
					ResultSet rs;
					rs=con.getConnection().createStatement().executeQuery(query);
					if(rs.next())
					{
						srtxtcstname.setText(rs.getString("Customer_Name"));
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(contentPane,ex.getMessage());
				}
			}
		});
		srtxtcstid1.setBounds(918, 97, 105, 20);
		contentPane.add(srtxtcstid1);
		srtxtcstid1.setColumns(10);
		
		txtrentalfee = new JTextField();
		txtrentalfee.setColumns(10);
		txtrentalfee.setBounds(114, 183, 115, 20);
		contentPane.add(txtrentalfee);
		
		srtxtrentalstatus = new JTextField();
		srtxtrentalstatus.setEditable(false);
		srtxtrentalstatus.setColumns(10);
		srtxtrentalstatus.setBounds(684, 123, 106, 20);
		contentPane.add(srtxtrentalstatus);
		
		srtxtcstname = new JTextField();
		srtxtcstname.setEditable(false);
		srtxtcstname.setColumns(10);
		srtxtcstname.setBounds(919, 123, 134, 20);
		contentPane.add(srtxtcstname);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomerName.setBounds(813, 124, 127, 14);
		contentPane.add(lblCustomerName);
		
		JButton buttonEditRental = new JButton("");
		buttonEditRental.setBackground(new Color(255, 255, 255));
		buttonEditRental.setBorder(null);
		buttonEditRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rentaltable.getSelectedColumnCount()==1)
				{
				try {
				String cap_id1=txtcapid1.getText();
				String cst_name=txtcstname.getText();
                String rentalfee=txtrentalfee.getText();
                String order_date=txtorderdate.getText();
                String Usage_Time=txtdays.getText();
   
				
				String order_id=txtorderid.getText();
				
				String query="update activerental set Capsule_ID='"+cap_id1+"',Customer_Name='"+cst_name+"',Rental_Fee='"+rentalfee+"',Usage_Time='"+Usage_Time+"' where Order_ID='"+order_id+"'";
				databaseCon con=new databaseCon();
				st=(Statement) con.getConnection().createStatement();
				st.execute(query);
				JOptionPane.showMessageDialog(contentPane, "Updated");
				
				UpdateTable();
				autoID();
			    txtcapid1.setText(null);
			    
			    txtrentalfee.setText(null);
			    
			    txtcstname.setText(null);
			    
			   
			    txtdays.setText(null);
			    st.close();
				
				
				
				}
				catch(SQLException es)
				{
					JOptionPane.showMessageDialog(contentPane,es.getMessage());
				}
				}
				else if(rentaltable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane,"No Record Selected");
					UpdateTable();
				}
				
			}
		});
		buttonEditRental.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/icons8-edit-24.png")));
		buttonEditRental.setBounds(122, 275, 95, 33);
		contentPane.add(buttonEditRental);
		
		JButton buttonDeleteRental = new JButton("");
		buttonDeleteRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=new DefaultTableModel();
				String order_id=txtorderid.getText();
				String query="delete from activerental where Order_ID='"+order_id+"'";

				String alter_query="ALTER TABLE capsuleregistration AUTO_INCREMENT = 1";
				databaseCon con=new databaseCon();
				
				
				
				if(rentaltable.getSelectedColumnCount()==1)
				{
				try {
					st=(Statement) con.getConnection().prepareStatement(query);
					st.execute(query);
					st.execute(alter_query);
					
					JOptionPane.showMessageDialog(contentPane, "Deleted");
					
					UpdateTable();
					txtcapid1.setText(null);
				    
				    txtrentalfee.setText(null);
				    
				    txtcstname.setText(null);
				    autoID();
					autoDate();
					
				   
				   
				    txtdays.setText(null);
					
					}
					catch(SQLException es)
					{
						JOptionPane.showMessageDialog(contentPane,es.getMessage());
					}
				}
				else if(rentaltable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane, "NO RECORD SELECTED");
					UpdateTable();
			}
		}});
		buttonDeleteRental.setBackground(new Color(255, 255, 255));
		buttonDeleteRental.setBorder(null);
		buttonDeleteRental.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/iconfinder_Delete_132192.png")));
		buttonDeleteRental.setBounds(24, 319, 88, 32);
		contentPane.add(buttonDeleteRental);
		
		JLabel label = new JLabel("Availible For Rent");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(569, 122, 165, 14);
		contentPane.add(label);
		
		srtxtcaptype = new JTextField();
		srtxtcaptype.setEditable(false);
		srtxtcaptype.setColumns(10);
		srtxtcaptype.setBounds(684, 150, 106, 20);
		contentPane.add(srtxtcaptype);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 185, 790, 184);
		contentPane.add(scrollPane);
		
		rentaltable = new JTable();
		rentaltable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rentaltable.setCellSelectionEnabled(true);
		rentaltable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order_ID", "Capsule_ID", "Customer_Name", "Rental_Fee", "Usage_Time", "Order_Date"
			}
		));
		rentaltable.getColumnModel().getColumn(2).setPreferredWidth(142);
		rentaltable.getColumnModel().getColumn(3).setPreferredWidth(85);
		scrollPane.setViewportView(rentaltable);
        rentaltable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model= (DefaultTableModel) rentaltable.getModel();
				int selectedrowindex=rentaltable.getSelectedRow();
				txtorderid.setText(model.getValueAt(selectedrowindex,0).toString());
				txtcapid1.setText(model.getValueAt(selectedrowindex, 1).toString());
				txtcstname.setText(model.getValueAt(selectedrowindex, 2).toString());
				txtrentalfee.setText(model.getValueAt(selectedrowindex, 3).toString());
				txtdays.setText(model.getValueAt(selectedrowindex, 4).toString());
				
				txtorderdate.setText(model.getValueAt(selectedrowindex, 5).toString());
				
			
			
			
			
		}});
		
		JButton buttonTruncate = new JButton("");
		buttonTruncate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query1="truncate table activerental";		
				databaseCon con=new databaseCon();
				try{
				
				st=(Statement) con.getConnection().createStatement();
				st.execute(query1);
				autoID();
				autoDate();
				UpdateTable();
				
			}
				catch(Exception ex){
					ex.getMessage();
				
			
			}
		}});
		buttonTruncate.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/iconfinder_Wizard_132269.png")));
		buttonTruncate.setBorder(null);
		buttonTruncate.setBackground(Color.WHITE);
		buttonTruncate.setBounds(227, 319, 47, 32);
		contentPane.add(buttonTruncate);
		
		txtorderid = new JTextField();
		txtorderid.setEditable(false);
		txtorderid.setBounds(123, 97, 135, 20);
		contentPane.add(txtorderid);
		txtorderid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Order ID");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 99, 73, 14);
		contentPane.add(lblNewLabel_1);
		
		txtdays = new JTextField();
		txtdays.setBounds(114, 209, 115, 20);
		contentPane.add(txtdays);
		txtdays.setColumns(10);
		
		txtorderdate = new JTextField();
		txtorderdate.setEditable(false);
		txtorderdate.setColumns(10);
		txtorderdate.setBounds(114, 233, 144, 20);
		contentPane.add(txtorderdate);
		
		JLabel label_1 = new JLabel("Capsule ID");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 122, 81, 14);
		contentPane.add(label_1);
		
		txtcapid1 = new JTextField();
		txtcapid1.setBounds(122, 125, 135, 20);
		contentPane.add(txtcapid1);
		txtcapid1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Order Placement SubMenu");
		lblNewLabel_2.setForeground(new Color(0, 102, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(137, 72, 221, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("Customer Name");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(277, 101, 127, 14);
		contentPane.add(label_2);
		
		txtcstname = new JTextField();
		txtcstname.setBounds(381, 97, 144, 21);
		contentPane.add(txtcstname);
		txtcstname.setColumns(10);
		
		JLabel lblExistanceCheck = new JLabel("To check if the records exists ");
		lblExistanceCheck.setForeground(new Color(255, 0, 204));
		lblExistanceCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblExistanceCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblExistanceCheck.setBounds(695, 71, 221, 14);
		contentPane.add(lblExistanceCheck);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		lblNewLabel.setBounds(0, 0, 1093, 67);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoID();
				txtcapid1.setText(null);
			    
			    txtrentalfee.setText(null);
			    
			    txtcstname.setText(null);
			    
			   
			   
			    txtdays.setText(null);
			    
				
			}
		});
		button.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/icons8-clear-symbol-24.png")));
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setBounds(227, 275, 47, 32);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				srtxtcapid1.setText(null);
			    srtxtcaptype.setText(null);
			    srtxtcstname.setText(null);
			    srtxtrentalstatus.setText(null);
			    srtxtcstid1.setText(null);
			    
				
			}
		});
		button_1.setIcon(new ImageIcon(RentalMenu.class.getResource("/images1/icons8-clear-symbol-24.png")));
		button_1.setBorder(null);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1006, 149, 47, 32);
		contentPane.add(button_1);
		show_active_rental();
		autoID();
		autoDate();
		
	}
	public ArrayList<ActiveRentalsRecords> rentalList(){
		ArrayList<ActiveRentalsRecords> rentalList=new ArrayList();
		try {
			databaseCon con=new databaseCon();
			Statement state=(Statement) con.getConnection().createStatement();
			ResultSet rs= state.executeQuery("select Order_ID,Capsule_ID,Customer_Name,Rental_Fee,Usage_Time,Order_Date from activerental");
		    ActiveRentalsRecords rentalRecords;
		    while(rs.next()) {
		    	rentalRecords=new ActiveRentalsRecords(rs.getString("Order_ID"),rs.getString("Capsule_ID"),rs.getString("Customer_Name"),rs.getString("Rental_Fee"),rs.getString("Usage_Time"),rs.getString("Order_Date"));
		        rentalList.add(rentalRecords);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(contentPane,e.getMessage());
			e.printStackTrace();
		}
		return rentalList;
	}
	public void show_active_rental() {
		ArrayList<ActiveRentalsRecords> rental_list =rentalList();
		DefaultTableModel model=(DefaultTableModel) rentaltable.getModel();
		Object[] col=new Object[6];
		for(int i=0;i<rental_list.size();i++)
		{
			col[0]=rental_list.get(i).getOrder_ID();
			col[1]=rental_list.get(i).getCapsule_ID();
			
			col[2]=rental_list.get(i).getCustomer_Name();
			
			col[3]=rental_list.get(i).getRental_Fee();
			col[4]=rental_list.get(i).getdays();
			
			col[5]=rental_list.get(i).getorder_date().toString();
			
			
			model.addRow(col);
			
			
		}
	}
	public void UpdateTable() {
		ResultSet rs;
		Statement state;
		String query="select Order_ID,Capsule_ID,Customer_Name,Rental_Fee,Usage_Time,Order_Date from activerental";
		try {
			databaseCon con=new databaseCon();
			state=(Statement) con.getConnection().createStatement();
			rs=state.executeQuery(query);	
			rentaltable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException E){
			JOptionPane.showInternalMessageDialog(contentPane, E.getMessage());
		}
		
	}
	public void autoID() {
	    try{
				
				databaseCon con=new databaseCon();
				Statement state=(Statement) con.getConnection().createStatement();
				ResultSet rs= state.executeQuery("select max(id) from activerental");
				if(txtorderid.getText()==null) {
					txtorderid.setText("RC001");
				}
				else if(rs.next())
				{
					orderid=rs.getInt(1);
					orderid++;
	                
					txtorderid.setText("RC00"+Integer.toString(orderid));
					
				}
			}
			catch(SQLException e) {
			 e.printStackTrace();
			}
			
		}
	public void autoDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   String s=dtf.format(now);
		   txtorderdate.setText(s);  
	}
}
