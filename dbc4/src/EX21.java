import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EX21 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "lion";
		String passwd = "1234";
		Connection con = null; 		
		Statement stmt = null;
		ResultSet rs = null; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			String query = "SELECT deptno, dname, loc FROM dept";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(deptno + " " + dname + " " + loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();			stmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}