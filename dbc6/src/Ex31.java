import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex31 {
	public static void main(String[] args) { 
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "lion";
			String pass = "1234";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, id, pass);
			Statement stmt = conn.createStatement();
			String sql = "create table tb_test ("
				+ " test1 varchar2(10), test2 number)";
			stmt.executeUpdate(sql);
			System.out.println(sql + "문 실행 완료!");
			System.out.println("테이블 생성 OK");
			stmt.close(); 		conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
}