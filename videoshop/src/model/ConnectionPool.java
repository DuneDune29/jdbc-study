package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
	static Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String pass = "tiger";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	private ConnectionPool() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		
	}
	public static Connection getConnection() throws Exception{
		if (con == null) {
			ConnectionPool cp = new ConnectionPool();
		}
		return con;
	}
}
