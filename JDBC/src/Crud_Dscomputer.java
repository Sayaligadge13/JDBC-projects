import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Crud_Dscomputer {

	private JFrame frame;
	private JTextField txteid;
	private JTextField txtname;
	private JTextField txtitem;
	private JTextField txtprice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crud_Dscomputer window = new Crud_Dscomputer();
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
	public Crud_Dscomputer() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
		
		//table_load();//first time call here and run then delete from here and call after record  added successfully message & run
	
		
	
	
	String url="jdbc:mysql://localhost:3306/dscomp";
	String uname="root";
	String pass="Sayu@2670";
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	public void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,uname,pass);
		}
		catch(Exception e)
		{
		}
		}
	public void table_load()
	{
	try
	{
		pst=con.prepareStatement("select *from comp");
		rs=pst.executeQuery();
table.setModel(DbUtils.resultSetToTableModel(rs));//for DbUtils download rs2xms jar file and set it like mysql connector jar
	}
	catch(SQLException be)
	{
		be.printStackTrace();
	}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD-DSCOMPUTER");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 25));
		lblNewLabel.setBounds(99, 11, 246, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter ID");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_1.setBounds(78, 55, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2.setBounds(99, 115, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Item");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_3.setBounds(99, 140, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_4.setBounds(99, 165, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		txteid = new JTextField();
		txteid.setBounds(157, 52, 86, 20);
		frame.getContentPane().add(txteid);
		txteid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(157, 112, 86, 20);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		txtitem = new JTextField();
		txtitem.setBounds(157, 137, 86, 20);
		frame.getContentPane().add(txtitem);
		txtitem.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(157, 162, 86, 20);
		frame.getContentPane().add(txtprice);
		txtprice.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
				
					String eid=txteid.getText();
					pst=con.prepareStatement("select name,item,price from comp where id= ?");
					pst.setString(1,eid);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						
						
						String name=rs.getString(2);
						String item=rs.getString(3);
						String price=rs.getString(4);
						
						txtname.setText(name);
						txtitem.setText(item);
						txtprice.setText(price);
						
						
					}
					else
					{JOptionPane.showMessageDialog(null, "Record not found......");
					}
				}
				catch(Exception ce)
				{
					
				}

			}
		});
				
		btnSearch.setFont(new Font("Cambria", Font.BOLD, 12));
		btnSearch.setBounds(268, 51, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						
					String eid,name,item,price;
					eid=txteid.getText();
					name=txtname.getText();
					item=txtitem.getText();
					price=txtprice.getText();
					if( txteid.getText().equals("")||txtname.getText().equals("")||txtitem.getText().equals("")||txtprice.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null,"Please enter data....");
					}
					else
					{
					try
					{
						pst=con.prepareStatement("insert into comp(eid,name,item,price) values(?,?,?,?)");
					
						pst.setString(1,eid);
					    pst.setString(2,name);
					    pst.setString(3,item);
					    pst.setString(4,price);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Record Added Successfully......");
						table_load();
					 txteid.setText("");
					 txtname.setText("");
					 txtitem.setText("");
					 txtprice.setText("");
					 txtname.requestFocus();//means mouse focus on this textbox
					 }
					catch(SQLException ae)
					{
						ae.printStackTrace();
					}
				
					}
					
				
			}
		});
		btnSave.setFont(new Font("Cambria", Font.BOLD, 12));
		btnSave.setBounds(30, 209, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
						String eid,name,item,price;
						name=txtname.getText();
						item=txtitem.getText();
						price=txtprice.getText();
						eid=txteid.getText();
						if(txteid.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please enter ID..");
						}
						else
						{
						try
						{
							pst=con.prepareStatement("update comp set name=?, item=?, price=? where eid=?");
						
						    
							
							pst.setString(1,eid);
							pst.setString(2,name);
							pst.setString(3,item);
						    pst.setString(4,price);
						    pst.executeUpdate();
						    JOptionPane.showMessageDialog(null, "Record Updated Successfully......");
							table_load();
						 txteid.setText("");
						 txtname.setText("");
						 txtitem.setText("");
						 txtprice.setText("");
						 txtname.requestFocus();//means mouse focus on this textbox
						 }
						catch(SQLException ae)
						{
							ae.printStackTrace();
						}	
					}}
				});
				
				
		btnUpdate.setFont(new Font("Cambria", Font.BOLD, 12));
		btnUpdate.setBounds(129, 209, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						String eid;
						
						eid=txteid.getText();
						if(txteid.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please enter ID..");
						}
						else
						{
						try
						{
							pst=con.prepareStatement("delete from comp where eid=?");
						
						    pst.setString(1,eid);
						    pst.executeUpdate();
						    JOptionPane.showMessageDialog(null, "Record Deleted Successfully......");
							table_load();
						 txtname.setText("");
						 txtitem.setText("");
						 txtprice.setText("");
						 txtname.requestFocus();//means mouse focus on this textbox
						 }
						catch(SQLException ae)
						{
							ae.printStackTrace();
						}	
					}
			}
		});
		btnDelete.setFont(new Font("Cambria", Font.BOLD, 12));
		btnDelete.setBounds(228, 209, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				 txtitem.setText("");
				 txtprice.setText("");
				 txtname.requestFocus();	
			
		
			}
		});
		btnClear.setFont(new Font("Cambria", Font.BOLD, 12));
		btnClear.setBounds(327, 209, 89, 23);
		frame.getContentPane().add(btnClear);
	}
}
