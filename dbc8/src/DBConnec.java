import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnec {
		private static Connection connec;
		public static Connection getConnection()
				throws ClassNotFoundException, SQLException {
			if (connec == null) { // connec�� null�̸� Connection ��������
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "lion"; 		String pw = "1234";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connec = DriverManager.getConnection(url, user, pw);
			}
			return connec;
		}
		public static void close() throws SQLException {
			if (connec != null) {
				if (!connec.isClosed()) connec.close();
			}
			connec = null;
		}
	}