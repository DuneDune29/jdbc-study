import java.sql.Connection;
import java.sql.Statement;

public class DBConnTest1 {
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		if (conn == null) {
			System.out.println("연결 실패");  	 	System.exit(0);
		}
		System.out.println("연결 성공");
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into dept values (90, '개발', '강남')";
			int result = stmt.executeUpdate(sql);
			System.out.println(result + "개 레코드 실행");
		} catch (Exception e) { System.out.println(e.toString()); }
		DBConn.close();
	}
}
