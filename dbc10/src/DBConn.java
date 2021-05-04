import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn { // ½Ì±ÛÅæ
	private static Connection dbConn;
	
	public static Connection getConnection() {
	
	if (dbConn == null) {
		try { // ÇÑ¹ø ¿¬°áµÈ °´Ã¼ °è¼Ó »ç¿ë 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "lion"; 		String pwd = "1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	return dbConn;
}
public static Connection getConnection(String url, String user, String pwd) {
	if (dbConn == null) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	return dbConn;
}
public static void close() {
	if (dbConn != null) {
		try {
			if (!dbConn.isClosed()) dbConn.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			}
		}
		dbConn = null;
	}
}