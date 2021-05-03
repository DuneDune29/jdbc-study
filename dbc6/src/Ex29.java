import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex29 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "lion";
		String pass = "1234";
		Connection con = null; 		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			DatabaseMetaData data = con.getMetaData();
			System.out.println(data.getDatabaseProductName());
			System.out.println(data.getDatabaseProductVersion());
			System.out.println(data.getDriverName());
			System.out.println(data.getURL());
			ResultSet rs = data.getSchemas();
			while (rs.next()) {
				System.out.println("∞Ë¡§∏Ì: " + rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
