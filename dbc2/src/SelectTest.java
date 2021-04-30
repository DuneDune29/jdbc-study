import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public SelectTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "lion";
			String pwd = "1234";
			Connection con = DriverManager.getConnection(url, user, pwd);
			System.out.println("DB 연결 성공");

			String sql = "select * from emp";
			st = con.createStatement();
			rs = st.executeQuery(sql); // Resultset(결과테이블) 반환
			//
			
			
			while (rs.next()) {
				for(int i = 1; i <= 7; i++)
					System.out.printf("%10s \t", rs.getString(i));
				System.out.println(rs.getString(8));
			}
			rs.close();			st.close(); 		con.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println(e + "=> Sql 예외");
		} catch (Exception e) {
			System.out.println(e + "=> 일반 예외");
		}
	}
	public static void main(String[] args) { new SelectTest(); }
}