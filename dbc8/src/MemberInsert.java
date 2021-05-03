import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberInsert {
	public static void main(String[] args) {
		try {
			Connection conn = DBConnec.getConnection();
			String userid = "tiger"; 				String pwd = "tiger";
			String email = "tiger@java.com"; 		String hp = "010-1111-3333";
			// String 클래스의 format 메서드를 사용하여 보기좋게 작성.
			String sql = String.format("INSERT INTO TB_MEMBER (m_seq,"
					+ " m_userid, m_pwd, m_email, m_hp) "
					+ " values(seq_tb_member.nextval, '%s','%s','%s','%s')",
					userid, pwd, email, hp);
			Statement stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			System.out.println(count + "개 행 입력");
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try { DBConnec.close(); } catch (SQLException e) { }
		}
	}
}