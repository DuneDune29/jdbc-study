import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex28 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "lion";
		String passwd = "1234";
		Connection con = null; 		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "DELETE FROM dept WHERE deptno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 60); 		
			int n = pstmt.executeUpdate();
			System.out.println(n + " 개 레코드 삭제");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
