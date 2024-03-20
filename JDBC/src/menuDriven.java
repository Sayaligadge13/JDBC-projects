import java.sql.*;
import java.util.*;
public class menuDriven {

	public static void main(String[] args)throws Exception 
	{
		Scanner s=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/rutu";
		String uname="root";
		String pass="Sayu@2670";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		ResultSet rs;
		System.out.println("\n1.Insert\n2.Display\n3.Update\n4.Delete\n5.Search");
		int choice;
		do
		{
			System.out.println("Enter your choice:");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter rno,name and marks:");
				int rno=s.nextInt();
				String name=s.next();
				int marks=s.nextInt();
				String s1="insert into students values("+rno+",'"+name+"',"+marks+")";
				st.executeUpdate(s1);
				System.out.println("Data inserted successfully.....");
				break;
			case 2:
				System.out.println("Students details are");
				String s2="select * from students";
				rs=st.executeQuery(s2);
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getInt(3));
				}
				break;
			case 3:
				int rno1;
				System.out.println("Enter rno");
				rno1=s.nextInt();
				String s3="Update students set marks=marks+5 where rno="+rno1+"";
				st.executeUpdate(s3);
				System.out.println("Record updated successfully....");
				break;
			case 4:
				System.out.println("Enter any roll no whose record you want to delete");
				int rno2=s.nextInt();
				String s4="delete from students where rno="+rno2+"";
				st.executeUpdate(s4);
				System.out.println("Data deleted successfully....");
				break;
			case 5:
				System.out.println("Enter any roll no from database:");
				int rno3=s.nextInt();
				String s5="select * from students where rno="+rno3+"";
			
				
				}
				
				
			}
		while(choice>0);
	
		}
		
		
		

	}


