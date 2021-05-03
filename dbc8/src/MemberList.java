import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberList {
	public static void main(String[] args) {
		try {
			Connection conn = DBConnec.getConnection();
			String sql = "SELECT m_seq, m_userid, m_pwd, m_email, "
					+ " m_hp, m_registdate, m_point "
					+ " FROM tb_member ORDER BY m_seq desc";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				for (int i = 1; i<=7; i++)
					System.out.print(rs.getString(i) + "\t");
				System.out.println();
			}
			rs.close(); 	stmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try { DBConnec.close(); } catch (SQLException e) { }
		}
	}
}
