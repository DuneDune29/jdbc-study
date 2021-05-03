import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnTest2 {
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		if (conn == null) {
			System.out.println("연결 실패");  	 	System.exit(0);
		}
		System.out.println("연결 성공");
		try {
			String sql = "select * from dept";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("deptno") + ", "
						+ rs.getString("dname") + ", " + rs.getString(3));
			}
			rs.close(); 	pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		DBConn.close();
	}
}