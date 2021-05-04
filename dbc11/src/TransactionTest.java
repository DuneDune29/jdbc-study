import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {
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
			con.setAutoCommit(false);
			System.out.println("Connection Success!");
		} catch (SQLException se) { se.printStackTrace(); }
	}
	public void insert() {
		connect();
		Statement stmt = null;
		boolean isOrderSuccess = false;
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO pay VALUES ('aaa', '001', 1000)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO orderList VALUES ('001', 'p2014001')";
			stmt.executeUpdate(sql);
			isOrderSuccess = true;
		} catch (SQLException se) {
			System.out.println("insert fail");
			se.printStackTrace();
		} finally {
			if (isOrderSuccess) {
				try { con.commit(); }
				catch (Exception e) { e.printStackTrace(); }
			} else {
				try { con.rollback(); } catch (Exception e) { }
			}
			try { stmt.close();  	con.close(); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
	public static void main(String[] args) {
		TransactionTest tt = new TransactionTest();
		tt.insert();
	}
}