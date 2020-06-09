package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class CustomerRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtcstid;
	private JTextField txtcstname;
	private JTextField txtcstcnic;
	private JTextField txtcstcontact;
	private JTable CstRegTable;
	int cusid=0;
	Statement st;
	ResultSet rs;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegistration frame = new CustomerRegistration();
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
	public CustomerRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(269, 28, 851, 409);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerRegistrationPanel = new JLabel("Customer Registration Panel");
		lblCustomerRegistrationPanel.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		lblCustomerRegistrationPanel.setBounds(10, 11, 294, 32);
		contentPane.add(lblCustomerRegistrationPanel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		lblNewLabel.setBounds(-10, -45, 845, 111);
		contentPane.add(lblNewLabel);
		
		JLabel labcstid = new JLabel("Customer ID");
		labcstid.setBounds(34, 77, 87, 14);
		contentPane.add(labcstid);
		
		JLabel labcstname = new JLabel("Customer Name");
		labcstname.setBounds(34, 113, 132, 14);
		contentPane.add(labcstname);
		
		JLabel labcstcnic = new JLabel("Customer Cnic Number");
		labcstcnic.setBounds(34, 150, 115, 14);
		contentPane.add(labcstcnic);
		
		JLabel labcstcontact = new JLabel("Customer Contact Number");
		labcstcontact.setBounds(34, 190, 139, 14);
		contentPane.add(labcstcontact);
		
		txtcstid = new JTextField();
		txtcstid.setBounds(176, 74, 135, 20);
		contentPane.add(txtcstid);
		txtcstid.setColumns(10);
		txtcstid.enable(false);
		
		txtcstname = new JTextField();
		txtcstname.setColumns(10);
		txtcstname.setBounds(176, 110, 135, 20);
		contentPane.add(txtcstname);
		
		txtcstcnic = new JTextField();
		txtcstcnic.setColumns(10);
		txtcstcnic.setBounds(176, 147, 135, 20);
		contentPane.add(txtcstcnic);
		
		txtcstcontact = new JTextField();
		txtcstcontact.setColumns(10);
		txtcstcontact.setBounds(176, 187, 135, 20);
		contentPane.add(txtcstcontact);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(356, 73, 458, 269);
		contentPane.add(scrollPane);
		
		CstRegTable = new JTable();
		CstRegTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CstRegTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer ID", "Customer Name", "Customer Cnic ", "Customer Contact"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		CstRegTable.getColumnModel().getColumn(2).setPreferredWidth(107);
		CstRegTable.getColumnModel().getColumn(3).setPreferredWidth(113);
		CstRegTable.setFillsViewportHeight(true);
		CstRegTable.setColumnSelectionAllowed(true);
		CstRegTable.setCellSelectionEnabled(true);
		scrollPane.setViewportView(CstRegTable);
		CstRegTable.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model=(DefaultTableModel) CstRegTable.getModel();
			int selectedrowindex=CstRegTable.getSelectedRow();
			txtcstid.setText(model.getValueAt(selectedrowindex,0).toString());
			txtcstname.setText(model.getValueAt(selectedrowindex, 1).toString());
			txtcstcnic.setText(model.getValueAt(selectedrowindex, 2).toString());
			txtcstcontact.setText(model.getValueAt(selectedrowindex, 3).toString());
		}
	});
		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/iconfinder_Add_132335.png")));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setBorder(null);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtcstname.getText().isEmpty()||txtcstcnic.getText().isEmpty()||txtcstcontact.getText().isEmpty())
				{
				 JOptionPane.showMessageDialog(contentPane, "Error! pls fill all the required fields");	
				}
				else {
				try {
					
					databaseCon con=new databaseCon();
					String validate_query="select * from customerregistration where Customer_ID='"+txtcstid.getText()+"'";
					ResultSet rs=con.getConnection().createStatement().executeQuery(validate_query);
					if(rs.next()) {
						JOptionPane.showMessageDialog(contentPane,"Record Duplication Error!");
					}
					else {
					try {
					String query="INSERT INTO customerregistration(Customer_ID,Customer_Name,Customer_Cnic,Customer_Contact) VALUES('"+txtcstid.getText().toString()+"','"+txtcstname.getText().toString()+"','"+txtcstcnic.getText().toString()+"','"+txtcstcontact.getText().toString()+"')";
				    st=con.getConnection().createStatement();
				    st.executeUpdate(query);
				    JOptionPane.showMessageDialog(contentPane, "Added");
				    autoID();
				    UpdateTable();
				    txtcstcnic.setText(null);
					txtcstname.setText(null);
					txtcstcontact.setText(null);
					
				    st.close();
	                
				}
					catch(SQLException ex) {
						System.out.println(ex.getMessage());
					}
					}
					}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
	
			}
			}
		});
		btnAdd.setBounds(26, 241, 106, 44);
		contentPane.add(btnAdd);
	
	
		JButton btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/icons8-edit-24.png")));
		btnEdit.setBackground(new Color(255, 255, 255));
		btnEdit.setBorder(null);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CstRegTable.getSelectedColumnCount()==1)
				{
				try {
				String cst_id=txtcstid.getText();
				String cst_name=txtcstname.getText();
                String cst_cnic=txtcstcnic.getText();
				String cst_contact=txtcstcontact.getText();
				
				String query="update customerregistration set Customer_Name='"+cst_name+"',Customer_Cnic='"+cst_cnic+"',Customer_Contact='"+cst_contact+"' where Customer_ID='"+cst_id+"'";
				databaseCon con=new databaseCon();
				st=con.getConnection().createStatement();
				st.execute(query);
				JOptionPane.showMessageDialog(contentPane, "Updated");
				UpdateTable();
				autoID();
				
				txtcstcnic.setText(null);
				txtcstname.setText(null);
				txtcstcontact.setText(null);
				}
				catch(SQLException es)
				{
					JOptionPane.showMessageDialog(contentPane,es.getMessage());
				}
				}
				else if(CstRegTable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane,"No Record Selected");
					UpdateTable();
				}
				
			}
						});
		btnEdit.setBounds(145, 241, 106, 44);
		contentPane.add(btnEdit);
		
		JButton btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/iconfinder_Left_132177.png")));
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setBorder(null);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu m=new mainmenu();
				m.setVisible(true);
				m.setLocationRelativeTo(null);
				dispose();

			}
		});
		btnCancel.setBounds(145, 298, 106, 44);
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/iconfinder_Delete_132192.png")));
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setBorder(null);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=new DefaultTableModel();
				String cst_id=txtcstid.getText();
				String cst_name=txtcstname.getText();
                String cst_cnic=txtcstcnic.getText();
				String cap_contact=txtcstcontact.getText();

				String alter_query="ALTER TABLE capsuleregistration AUTO_INCREMENT = 1";
				String query="delete from customerregistration where Customer_ID='"+cst_id+"'";
				databaseCon con=new databaseCon();
				
				
				
				if(CstRegTable.getSelectedColumnCount()==1)
				{
				try {
					st=con.getConnection().prepareStatement(query);
					st.execute(query);
					st.execute(alter_query);
					
					JOptionPane.showMessageDialog(contentPane, "Deleted");
					
					UpdateTable();
                    autoID();
					txtcstcnic.setText(null);
					txtcstname.setText(null);
					txtcstcontact.setText(null);
					}
					catch(SQLException es)
					{
						JOptionPane.showMessageDialog(contentPane,es.getMessage());
					}
				}
				else if(CstRegTable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane, "NO RECORD SELECTED");
					UpdateTable();
				}

			}

			
			}
		);
		btnDelete.setBounds(26, 298, 106, 44);
		contentPane.add(btnDelete);
		
			JButton btntruncate = new JButton("");
			btntruncate.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/iconfinder_Wizard_132269.png")));
			btntruncate.setBackground(new Color(255, 255, 255));
			btntruncate.setBorder(null);
		btntruncate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query1="truncate table customerregistration";		
				databaseCon con=new databaseCon();
				try{
				
				st=con.getConnection().createStatement();
				st.execute(query1);
				autoID();
				UpdateTable();
				
			}
				catch(Exception ex){
					ex.getMessage();
				
			}
		}});
		btntruncate.setBounds(261, 298, 61, 44);
		contentPane.add(btntruncate);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
						autoID();
					    txtcstcnic.setText(null);
					    txtcstname.setText(null);
					    txtcstcontact.setText(null);
					    
				
			}
		});
		button.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/images1/icons8-clear-symbol-24.png")));
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setBounds(261, 241, 61, 44);
		contentPane.add(button);
		autoID();
		show_customer_records();
		
	}
	public void autoID() {
	    try{
				
				databaseCon con=new databaseCon();
				Statement state=con.getConnection().createStatement();
				ResultSet rs= state.executeQuery("select max(id) from customerregistration");
				if(txtcstid.getText()==null) {
					txtcstid.setText("CS001");
				}
				else if(rs.next())
				{
					cusid=	rs.getInt(1);
					cusid++;
	                
					txtcstid.setText("CS00"+Integer.toString(cusid));
					
				}
			}
			catch(SQLException e) {
			 e.printStackTrace();
			}
			
		}
		public ArrayList<CustomerRecords> customerList(){
			ArrayList<CustomerRecords> customerList=new ArrayList();
			try {
				databaseCon con=new databaseCon();
				Statement state=con.getConnection().createStatement();
				ResultSet rs= state.executeQuery("select Customer_ID,Customer_Name,Customer_Cnic,Customer_Contact from customerregistration");
			    CustomerRecords customerRecords;
			    while(rs.next()) {
			    	customerRecords=new CustomerRecords(rs.getString("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Cnic"),rs.getString("Customer_Contact"));
			        customerList.add(customerRecords);
			    }
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
			return customerList;
		}
		public void show_customer_records() {
			ArrayList<CustomerRecords> customer_list =customerList();
			DefaultTableModel model=(DefaultTableModel)CstRegTable.getModel();
			Object[] col=new Object[4];
			for(int i=0;i<customer_list.size();i++)
			{
				col[0]=customer_list.get(i).getCustomer_ID();
				col[1]=customer_list.get(i).getCustomer_Name();
				col[2]=customer_list.get(i).getCustomer_Cnic();
				col[3]=customer_list.get(i).getCustomer_Contact();
				model.addRow(col);
				
				
			}
		}
		public void UpdateTable() {
			ResultSet rs;
			Statement state;
			String query="select Customer_ID,Customer_Name,Customer_Cnic,Customer_Contact from customerregistration";
			try {
				databaseCon con=new databaseCon();
				state=con.getConnection().createStatement();
				rs=state.executeQuery(query);	
				CstRegTable.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(SQLException E){
				JOptionPane.showInternalMessageDialog(contentPane, E.getMessage());
			}
			
		}
	}


