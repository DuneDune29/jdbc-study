import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSVC {
	Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) { 
			cne.printStackTrace(); 
		}
	}
	public void connect() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "lion", "1234");
			System.out.println("Connection Success!");
		} catch (SQLException se) {
			se.printStackTrace(); 
		}
	}
	public User login(String id, String passwd) {
		User user = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect();
			stmt = con.createStatement();
			String sql = "SELECT * FROM member WHERE id = '" + id
					+ "' AND " + "passwd = '" + passwd + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getString("id");
				passwd = rs.getString("passwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				user = new User(id, passwd, name, age, addr, email);
			}
		} catch  (SQLException se) {
			se.printStackTrace(); 
		} finally {
			try {
			
			rs.close(); 	stmt.close(); 		con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		return user;	
	}
}