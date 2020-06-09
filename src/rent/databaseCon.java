package rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseCon {
	String URL="jdbc:mysql://localhost:3306/ok";
	String userName="root";
	String password="";
	Connection cn;
	public Connection getConnection() throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection(URL, userName, password);
			return cn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return cn;
		
		
	}

}
