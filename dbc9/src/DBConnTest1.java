import java.sql.Connection;
import java.sql.Statement;

public class DBConnTest1 {
	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		if (conn == null) {
			System.out.println("���� ����");  	 	System.exit(0);
		}
		System.out.println("���� ����");
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into dept values (90, '����', '����')";
			int result = stmt.executeUpdate(sql);
			System.out.println(result + "�� ���ڵ� ����");
		} catch (Exception e) { System.out.println(e.toString()); }
		DBConn.close();
	}
}
