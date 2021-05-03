import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex26 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "lion";
		String passwd = "1234";
		Connection con = null; 		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO dept (deptno, dname, loc) "
					+ " VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 60); 		pstmt.setString(2, "관리");
			pstmt.setString(3, "부산"); 		int n = pstmt.executeUpdate();
			System.out.println(sql + "문 실행 완료!");
			System.out.println(n + " 개 레코드 저장");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
