import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EX25 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "lion";
		String passwd = "1234";
		Connection con = null; 		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT deptno, dname, loc FROM dept "
					+ "WHERE deptno = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 40);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(deptno + " " + dname + " " + loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();	  pstmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}