package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) {
		}
	}
	public static Connection getConnection() {
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "lion", "1234");
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con) {
		try { con.close(); }
		catch (Exception e) {	e.printStackTrace(); }
	}
	public static void close(Statement stmt) {
		try { stmt.close(); } 
		catch (Exception e) {	e.printStackTrace(); }
	}
	public static void close(ResultSet rs) {
		try { rs.close(); } 
		catch (Exception e) {	e.printStackTrace(); }
	}	
}