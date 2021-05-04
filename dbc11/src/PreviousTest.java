import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreviousTest {
	Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) { cne.printStackTrace(); }
	}
	public void connect() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "lion", "1234");
		} catch (SQLException se) { se.printStackTrace(); }
	}
	public void select() {
		Statement stmt = null; 			ResultSet rs = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM member";
			rs = stmt.executeQuery(sql);
			System.out.println("다음: 앞으로 이동하면서 출력");
			while (rs.next()) {
				for (int i = 1; i <= 6; i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			System.out.println("이전: 뒤로 이동하면서 출력");
			while (rs.previous()) {
				for (int i = 1; i <= 6; i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			System.out.println("첫 번째 레코드");
			if (rs.first()) {
				for (int i = 1; i <= 6; i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			System.out.println("마지막 레코드");
			if (rs.last()) {
				for (int i = 1; i <= 6; i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
			System.out.println("세 번째 레코드");
			if (rs.absolute(3)) {
				for (int i = 1; i <= 6; i++)
					System.out.print("\t" + rs.getString(i));
				System.out.println();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try { rs.close(); 		stmt.close(); 		con.close(); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
	public static void main(String[] args) {
		PreviousTest pt = new PreviousTest();
		pt.connect();  		pt.select();
	}
}