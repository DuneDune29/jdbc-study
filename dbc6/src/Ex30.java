import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex30 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "lion";
		String pass = "1234";
		Connection con = null; 		Statement stmt = null;
		ResultSet rs = null; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			stmt = con.createStatement();
			String query = "SELECT * FROM dept";
			rs = stmt.executeQuery(query);
			ResultSetMetaData data = rs.getMetaData();
			System.out.println(data.getColumnCount());
			System.out.println(data.getColumnName(1));
			System.out.println(data.getColumnName(2));
			System.out.println(data.getColumnTypeName(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); 		stmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
