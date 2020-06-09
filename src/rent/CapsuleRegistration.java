package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class CapsuleRegistration extends JFrame {
	
    
	private JPanel contentPane;
	private JTextField txtcapid;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private JButton cancel;
	private JTable capRegTable;
	private JScrollPane scrollPane;
    
	Connection con;
	PreparedStatement prs;
	private JComboBox combocapmanufacturer;
	private JComboBox combocaptype;
	private JComboBox combocapavaliblity;
	int capsid=0;
	Statement st;
	ResultSet rs;
	private JLabel lblNewLabel;
	private JLabel lblCapsuleRegistrationPanel;
	private JButton button;

	

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapsuleRegistration frame = new CapsuleRegistration();
					
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
	public CapsuleRegistration() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 391);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCapsuleRegistrationPanel = new JLabel("Capsule Registration Panel");
		lblCapsuleRegistrationPanel.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		lblCapsuleRegistrationPanel.setBounds(10, 0, 294, 32);
		contentPane.add(lblCapsuleRegistrationPanel);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 65, 443, 274);
		contentPane.add(scrollPane);
		
		capRegTable = new JTable();
		capRegTable.setCellSelectionEnabled(true);
		capRegTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel) capRegTable.getModel();
				int selectedrowindex=capRegTable.getSelectedRow();
				txtcapid.setText(model.getValueAt(selectedrowindex,0).toString());
				combocapmanufacturer.setSelectedItem(model.getValueAt(selectedrowindex, 1));
				combocaptype.setSelectedItem(model.getValueAt(selectedrowindex, 2));
				combocapavaliblity.setSelectedItem(model.getValueAt(selectedrowindex, 3));
			}
			
		});
		capRegTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(capRegTable);
		capRegTable.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		capRegTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		capRegTable.setColumnSelectionAllowed(true);
		capRegTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Capsule ID", "Manufacturer", "Capsule Type", "Availible For Rent"
			}
		));
		
		JLabel capid = new JLabel("Capsule ID");
		capid.setBounds(18, 83, 106, 14);
		contentPane.add(capid);
		
		JLabel capmanufacturer = new JLabel("Manufacturer ");
		capmanufacturer.setBounds(18, 122, 106, 14);
		contentPane.add(capmanufacturer);
		
		JLabel captype = new JLabel("Capsule Type");
		captype.setBounds(18, 166, 106, 14);
		contentPane.add(captype);
		
		JLabel capavailibilty = new JLabel("Availible for rent");
		capavailibilty.setBounds(18, 207, 106, 14);
		contentPane.add(capavailibilty);
		
		txtcapid = new JTextField();
		txtcapid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcapid.setBounds(140, 81, 127, 20);
		txtcapid.enable(false);
		
		contentPane.add(txtcapid);
		txtcapid.setColumns(10);
        
		
		add = new JButton("");
		add.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/iconfinder_Add_132335.png")));
		add.setBackground(new Color(255, 255, 255));
		add.setBorder(null);
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			if(combocapavaliblity.getSelectedIndex()==-1 || combocapmanufacturer.getSelectedIndex()==-1 || combocaptype.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(contentPane, "Please select all the fields required !");
			}
			else {
			
			
				try {
				
				databaseCon con=new databaseCon();
				String validate_query="select * from capsuleregistration where Capsule_ID='"+txtcapid.getText()+"'";
				ResultSet rs=con.getConnection().createStatement().executeQuery(validate_query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(contentPane, "Record Duplication Error!");
				}
				else {
				try {
					String query="INSERT INTO capsuleregistration(Capsule_ID,Capsule_Manufacturer,Capsule_Type,Capsule_Available_for_rent)"
						+ " VALUES('"+txtcapid.getText()+"','"+combocapmanufacturer.getSelectedItem().toString()+"','"
						+combocaptype.getSelectedItem().toString()+"','"+combocapavaliblity.getSelectedItem().toString()+"')";
			    st=con.getConnection().createStatement();
			    st.executeUpdate(query);
			    JOptionPane.showMessageDialog(contentPane, "Added");
			    
			    UpdateTable();
			    autoID();
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
		}
		}
		);
		add.setBounds(18, 244, 102, 36);
		contentPane.add(add);
		
		edit = new JButton("");
		edit.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/icons8-edit-24.png")));
		edit.setBackground(new Color(255, 255, 255));
		edit.setBorder(null);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(capRegTable.getSelectedColumnCount()==1)
				{
				try {
				String cap_id=txtcapid.getText();
				String cap_manufacturer=combocapmanufacturer.getSelectedItem().toString();
                String cap_availibility=combocapavaliblity.getSelectedItem().toString();
				String cap_type=combocaptype.getSelectedItem().toString();
				
				String query="update capsuleregistration set Capsule_Manufacturer='"+cap_manufacturer+"',Capsule_Type='"+cap_type+"',Capsule_Available_for_rent='"+cap_availibility+"' where Capsule_ID='"+cap_id+"'";
				databaseCon con=new databaseCon();
				st=con.getConnection().createStatement();
				st.execute(query);
				JOptionPane.showMessageDialog(contentPane, "Updated");
				autoID();

				combocapavaliblity.setSelectedItem(null);
				combocapmanufacturer.setSelectedItem(null);
				combocaptype.setSelectedItem(null);
				UpdateTable();
				}
				catch(SQLException es)
				{
					JOptionPane.showMessageDialog(contentPane,es.getMessage());
				}

				}
				else if(capRegTable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane,"No Record Selected");
					UpdateTable();
				}
				
			}
		});
		edit.setBounds(130, 244, 103, 36);
		contentPane.add(edit);
		
		delete = new JButton("");
		delete.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/iconfinder_Delete_132192.png")));
		delete.setBackground(new Color(255, 255, 255));
		delete.setBorder(null);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=new DefaultTableModel();
				String cap_id=txtcapid.getText();
				String alter_query="ALTER TABLE capsuleregistration AUTO_INCREMENT = 1";
				String query="delete from capsuleregistration where Capsule_ID='"+cap_id+"'";
				databaseCon con=new databaseCon();
				
				
				
				if(capRegTable.getSelectedColumnCount()==1)
				{
				try {
					st=con.getConnection().prepareStatement(query);
					st.execute(query);
					st.execute(alter_query);
					
					JOptionPane.showMessageDialog(contentPane, "Deleted");
					autoID();
					UpdateTable();
					
					
					combocapavaliblity.setSelectedItem(null);
					combocapmanufacturer.setSelectedItem(null);
					combocaptype.setSelectedItem(null);
					
					}
					catch(SQLException es)
					{
						JOptionPane.showMessageDialog(contentPane,es.getMessage());
					}
				}
				else if(capRegTable.getSelectedColumnCount()==0)
				{
					JOptionPane.showMessageDialog(contentPane, "NO RECORD SELECTED");
					UpdateTable();
				}
				
				
		}});
		delete.setBounds(18, 286, 102, 36);
		contentPane.add(delete);
		
		cancel = new JButton("");
		cancel.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/iconfinder_Left_132177.png")));
		cancel.setBackground(new Color(255, 255, 255));
		cancel.setBorder(null);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainmenu m=new mainmenu();
				m.setVisible(true);
				m.setLocationRelativeTo(null);
				dispose();
			}
		});
		cancel.setBounds(130, 286, 103, 36);
		contentPane.add(cancel);
		
		combocapmanufacturer = new JComboBox();
		combocapmanufacturer.setBackground(new Color(255, 255, 255));
		combocapmanufacturer.setModel(new DefaultComboBoxModel(new String[] {"Mitsubishi", "PanaTech", "C-Tech"}));
		combocapmanufacturer.setBounds(140, 119, 127, 22);
		contentPane.add(combocapmanufacturer);
		
		combocaptype = new JComboBox();
		combocaptype.setBackground(new Color(255, 255, 255));
		combocaptype.setModel(new DefaultComboBoxModel(new String[] {"Gaming", "Normal", "Luxry"}));
		combocaptype.setBounds(140, 163, 127, 22);
		contentPane.add(combocaptype);
		
		combocapavaliblity = new JComboBox();
		combocapavaliblity.setBackground(new Color(255, 255, 255));
		combocapavaliblity.setModel(new DefaultComboBoxModel(new String[] {"Yes ", "No"}));
		combocapavaliblity.setBounds(140, 204, 127, 22);
		contentPane.add(combocapavaliblity);
		
		JButton reset = new JButton("");
		reset.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/iconfinder_Wizard_132269.png")));
		reset.setBackground(new Color(255, 255, 255));
		reset.setBorder(null);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    
					String query1="truncate table capsuleregistration";		
					databaseCon con=new databaseCon();
					try{
					
					st=con.getConnection().createStatement();
					st.execute(query1);
					autoID();
					
					UpdateTable();
					
					
				    }
					catch(Exception ex){
						JOptionPane.showConfirmDialog(contentPane, ex.getMessage());
					
				}
				
				
			}
		});
		reset.setBounds(258, 286, 46, 36);
		contentPane.add(reset);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		lblNewLabel.setBounds(0, -82, 808, 136);
		contentPane.add(lblNewLabel);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            autoID();
				
			    combocapmanufacturer.setSelectedItem(null);
			    combocapavaliblity.setSelectedItem(null);
			    combocaptype.setSelectedItem(null);
			
			}
		});
		button.setIcon(new ImageIcon(CapsuleRegistration.class.getResource("/images1/icons8-clear-symbol-24.png")));
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setBounds(258, 244, 46, 36);
		contentPane.add(button);

	    combocapmanufacturer.setSelectedItem(null);
	    combocapavaliblity.setSelectedItem(null);
	    combocaptype.setSelectedItem(null);
		autoID();
	    show_capusle_records();
		

		UpdateTable();
		
	}
	public void autoID() {
    try{
			
			databaseCon con=new databaseCon();
			Statement state=con.getConnection().createStatement();
			ResultSet rs= state.executeQuery("select max(id) from capsuleregistration");
			if(txtcapid.getText()==null) {
				txtcapid.setText("CP001");
			}
			else if(rs.next())
			{
				capsid=rs.getInt(1);
				capsid++;
                
				txtcapid.setText("CP00"+Integer.toString(capsid));
				
			}
		}
		catch(SQLException e) {
		 JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
		
	}
	public ArrayList<CapsuleRecords> capsuleList(){
		ArrayList<CapsuleRecords> capsuleList=new ArrayList<CapsuleRecords>();
		try {
			databaseCon con=new databaseCon();
			Statement state=con.getConnection().createStatement();
			ResultSet rs= state.executeQuery("select Capsule_ID,Capsule_Manufacturer,Capsule_Type,Capsule_Available_for_rent from capsuleregistration");
		    CapsuleRecords capRecords;
		    while(rs.next()) {
		    	capRecords=new CapsuleRecords(rs.getString("Capsule_ID"),rs.getString("Capsule_Manufacturer"),rs.getString("Capsule_Type"),rs.getString("Capsule_Available_for_rent"));
		        capsuleList.add(capRecords);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(contentPane,e.getMessage());
			e.printStackTrace();
		}
		return capsuleList;
	}
	public void show_capusle_records() {
		ArrayList<CapsuleRecords> capsule_list =capsuleList();
		DefaultTableModel model=(DefaultTableModel)capRegTable.getModel();
		Object[] col=new Object[4];
		for(int i=0;i<capsule_list.size();i++)
		{
			col[0]=capsule_list.get(i).getCapsule_ID();
			col[1]=capsule_list.get(i).getCapsule_Manufacturer();
			col[2]=capsule_list.get(i).getCapsule_Type();
			col[3]=capsule_list.get(i).getCapsule_Availibility();
			model.addRow(col);
			
			
		}
	}
	public void UpdateTable() {
		ResultSet rs;
		Statement state;
		String query="select Capsule_ID,Capsule_Manufacturer,Capsule_Type,Capsule_Available_for_rent from capsuleregistration";
		try {
			databaseCon con=new databaseCon();
			state=con.getConnection().createStatement();
			rs=state.executeQuery(query);	
			capRegTable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException E){
			JOptionPane.showInternalMessageDialog(contentPane, E.getMessage());
		}
		
	}
}
