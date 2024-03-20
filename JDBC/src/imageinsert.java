import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class imageinsert {

	public static void main(String[] args) {
	String url="jdbc:mysql://localhost:3306/aaradhya";
	String uname="root";
	String pass="Sayu@2670";
	Connection con;
	PreparedStatement st;
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection(url,uname,pass);
	String sql="insert into images(pic)values(?)";
	st=con.prepareStatement(sql);
	FileInputStream fis=new FileInputStream("D://Sayu@2670//image/4.jfif");
	st.setBinaryStream(1, null)
	
	}

}
