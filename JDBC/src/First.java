import java.sql.*;
public class First {

	public static void main(String[] args)throws SQLException,ClassNotFoundException {
		String url="jdbc:mysql://localhost:3306/login";
		String uname="root";
		String pass="Sayu@2670";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		//int rno;
		//String name;
		//int marks;
		
		
		String query="insert into student values(1,'Sayali',94)";
	    st.executeUpdate(query);
		System.out.println("Data inserted successfully");

	}
}
