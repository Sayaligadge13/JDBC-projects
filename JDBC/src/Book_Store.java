import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
public class Book_Store {

	public JFrame frame;
	private JTextField txtedition;
	private JTextField txtbname;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Store window = new Book_Store();
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
	public Book_Store() {
		initialize();
		connect();
		//table_load();//first time call here and run then delete from here and call after record  added successfully message & run
	
		
	}
	
	String url="jdbc:mysql://localhost:3306/Bookshop";
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
		pst=con.prepareStatement("select *from book");
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
		frame.setBounds(100, 100, 702, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		lblNewLabel.setBounds(280, 15, 178, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 75, 306, 181);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 29, 106, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Cambria", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 79, 106, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Cambria", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 130, 106, 30);
		panel.add(lblNewLabel_1_1_1);
		
		txtedition = new JTextField();
		txtedition.setBounds(111, 86, 148, 22);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(111, 36, 148, 22);
		panel.add(txtbname);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(111, 137, 148, 22);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String name,edition,price;
			name=txtbname.getText();
			edition=txtedition.getText();
			price=txtprice.getText();
			if(txtbname.getText().equals("")||txtedition.getText().equals("")||txtprice.getText().equals(""))
			{
			JOptionPane.showMessageDialog(null,"Please enter data....");
			}
			else
			{
			try
			{
				pst=con.prepareStatement("insert into book(name,edition,price) values(?,?,?)");
			
			    pst.setString(1,name);
			    pst.setString(2,edition);
			    pst.setString(3,price);
			    pst.executeUpdate();
			    JOptionPane.showMessageDialog(null, "Record Added Successfully......");
				table_load();
			 txtbname.setText("");
			 txtedition.setText("");
			 txtprice.setText("");
			 txtbname.requestFocus();//means mouse focus on this textbox
			 }
			catch(SQLException ae)
			{
				ae.printStackTrace();
			}
		
			}
			}
		});
		btnNewButton.setBounds(10, 266, 81, 36);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 16));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(115, 266, 81, 36);
		frame.getContentPane().add(btnExit);
		btnExit.setFont(new Font("Cambria", Font.BOLD, 16));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				 txtedition.setText("");
				 txtprice.setText("");
				 txtbname.requestFocus();	
			}
		});
		btnClear.setBounds(220, 266, 81, 36);
		frame.getContentPane().add(btnClear);
		btnClear.setFont(new Font("Cambria", Font.BOLD, 16));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(36, 332, 350, 58);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(10, 18, 95, 20);
		lblNewLabel_1_1_2.setFont(new Font("Cambria", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
			try
			{
				String bid=txtbid.getText();
				pst=con.prepareStatement("select name,edition,price from book where id= ?");
				pst.setString(1,bid);
				ResultSet rs=pst.executeQuery();
				if(rs.next()==true)
				{
					String name=rs.getString(1);
					String edition=rs.getString(2);
					String price=rs.getString(3);
					
					txtbname.setText(name);
					txtedition.setText(edition);
					txtprice.setText(price);
					
				}
				else
				{
					 txtbname.setText("");
					 txtedition.setText("");
					 txtprice.setText("");
				}
			}
			catch(Exception ce)
			{
				
			}
			}
			});
		txtbid.setBounds(92, 20, 160, 19);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,edition,price,bid;
				name=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				if(txtbid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter ID..");
				}
				else
				{
				try
				{
					pst=con.prepareStatement("update book set name=?, edition=?, price=? where id=?");
				
				    pst.setString(1,name);
				    pst.setString(2,edition);
				    pst.setString(3,price);
				    pst.setString(4,bid);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Record Updated Successfully......");
					table_load();
				 txtbname.setText("");
				 txtedition.setText("");
				 txtprice.setText("");
				 txtbname.requestFocus();//means mouse focus on this textbox
				 }
				catch(SQLException ae)
				{
					ae.printStackTrace();
				}	
			}}
		});
		btnUpdate.setFont(new Font("Cambria", Font.BOLD, 16));
		btnUpdate.setBounds(402, 332, 101, 36);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				
				bid=txtbid.getText();
				if(txtbid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter ID..");
				}
				else
				{
				try
				{
					pst=con.prepareStatement("delete from book where id=?");
				
				    pst.setString(1,bid);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Record Deleted Successfully......");
					table_load();
				 txtbname.setText("");
				 txtedition.setText("");
				 txtprice.setText("");
				 txtbname.requestFocus();//means mouse focus on this textbox
				 }
				catch(SQLException ae)
				{
					ae.printStackTrace();
				}	
			}}
		});
		btnDelete.setFont(new Font("Cambria", Font.BOLD, 16));
		btnDelete.setBounds(513, 332, 101, 36);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(395, 74, 283, 240);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Edition", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
}