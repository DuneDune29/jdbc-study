import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBC {
	public static void main(String[] args) {
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!");
			
			// 2. DB와 커넥션(연결)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "lion";  			String pwd = "1234";
			Connection con = DriverManager.getConnection(url, user, pwd);
			System.out.println("DB Connected~~!");
			
			// 3. DB에 SQL문을 전송하기 위한 Statement 객체 할당
			Statement st = con.createStatement();
			String sql = "SELECT * FROM dept"; // 모든 테이블 목록 조회
			
			// 4. DB에 쿼리문 전송
			ResultSet rs = st.executeQuery(sql);
			// select문일 경우는 executeQuery(sql)를 이용, 이때 반환타입은 ResultSet
			
			// 5. ResultSet의 커서를 이동하면서 데이터를 읽어와 출력
			while (rs.next()) {
				int col1 = rs.getInt(1);
				String col2 = rs.getString(2);
			String col3 = rs.getString(3);
				System.out.println(col1 + "\t" + col2 + "\t" + col3);
			}
			
			// 6. DB와 연결된 자원 반납(해제)
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (con != null) con.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 드라이버 로딩 오류");
		} catch (SQLException e) {
			System.out.println(e + "=> DB 연결 오류");
		}
		// 보통 XX.Close()는 finally 블록에 작성
	}
}