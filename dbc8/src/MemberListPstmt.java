import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberListPstmt {
	public static void main(String[] args) {
		try {
			Connection conn = DBConnec.getConnection();
			String sql = "SELECT m_seq, m_userid, m_pwd, m_email, "
					+ " m_hp, m_registdate, m_point "
					+ " FROM tb_member ORDER BY m_seq desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				for (int i = 1; i<=7; i++)
					System.out.print(rs.getString(i) + "\t");
				System.out.println();
			}
			rs.close(); 	pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try { DBConnec.close(); } catch (SQLException e) { }
		}
	}
}