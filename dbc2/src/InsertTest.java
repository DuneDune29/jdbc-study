import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	private Connection con;
	private Statement st;

	public InsertTest(int empno, String ename, double sal) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "lion";
			String pwd = "1234";
			Connection con = DriverManager.getConnection(url, user, pwd);
			System.out.println("DB 연결 성공");

			String sql = "insert into emp(empno, ename, sal) values(" + empno + ",'" + ename + "'," + sal + ")";
			st = con.createStatement();
			int result = st.executeUpdate(sql);
			// 처리된 레코드의 개수를 int형으로 반환
			// update, insert, delete 문은 executeUpdate()를 사용
			System.out.println("처리된 레코드 개수: " + result);
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println(e + "=> Sql 예외");
		} catch (Exception e) {
			System.out.println(e + "=> 일반 예외");
		}
	}

	public static void main(String[] args) {
		new InsertTest(1, "Tom", 100);
	}
}