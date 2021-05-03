import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex22 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "lion";
		String passwd = "1234";
		Connection con = null; 		Statement stmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			String sql = "INSERT INTO dept (deptno, dname, loc) "
					+ " VALUES (50, '개발', '서울')";
			int n = stmt.executeUpdate(sql);
			System.out.println(sql + "문 실행 완료!");
			System.out.println(n + " 개 레코드 저장");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { stmt.close(); 		con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}

