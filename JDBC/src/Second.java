import java.sql.*;

public class Second {

	public static void main(String[] args) throws Exception
	{
	
		String url="jdbc:mysql://localhost:3306/saya";
		String uname="root";
		String pass="Sayu@2670";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		int tno=3;
		String name="Anjali";
		int salary=40000;
		String query="Insert into teacher values(?,?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,tno);
		st.setString(2,name);
		st.setInt(3,salary);
		int count=st.executeUpdate();
		System.out.println("affected rows:"+count);
		
	}

}
