import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BoardSVC {
	Connection con; 		

	public BoardVO getBoardVO(Scanner sc) {
		System.out.println("=== �Խù� ��� ===");
		System.out.println("�ۼ��� : ");  			String writer = sc.next();
		System.out.println("��й�ȣ : ");  		String passwd = sc.next();
		System.out.println("���� : ");  			String subject = sc.next();
		System.out.println("������ : ");  			String email = sc.next();
		BoardVO boardVO = new BoardVO(0, writer, passwd, subject, email);
		return boardVO;
	}
	public void writeArticle(Scanner sc) {
		BoardVO boardVO = getBoardVO(sc);
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board VALUES(board_seq.nextval, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getEmail());
			int count = pstmt.executeUpdate();
			if (count > 0) System.out.println("�� ��� �Ϸ�");
			else System.out.println("�� ��� ����");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt); 		close(con);
		}
	}
	public void showArticleList() {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("\t ���̵� \t �ۼ��� \t ���� \t �̸���");
			while (rs.next()) {
				System.out.println("\t" + rs.getInt("id") + "\t"
						+ rs.getString("writer") + "\t"
						+ rs.getString("subject") + "\t"
						+ rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs); 		close(pstmt); 		close(con);
		}
	}
	
	public void showArticle(Scanner sc) {
		System.out.println("�˻��� �� ��ȣ�� �Է��ϼ���.");
		System.out.println("�� ��ȣ: ");
		int id = sc.nextInt();
		BoardVO boardVO = getArticle(id);
		System.out.println(boardVO);
	}
	private BoardVO getArticle(int id) {
		BoardVO boardVO = null;
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int dbId = rs.getInt("id");
				String writer = rs.getString("writer");
				String passwd = rs.getString("passwd");
				String subject = rs.getString("subject");
				String email = rs.getString("email");
				boardVO = new BoardVO(dbId, writer, passwd, subject, email);
			}
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(rs); 		close(pstmt); 		close(con); }
		return boardVO;
	}
	public void deleteArticle(Scanner sc) {
		System.out.println("������ �� ��ȣ�� �Է��ϼ���.");
		System.out.println("�� ��ȣ : ");
		int id = sc.nextInt();
		int count = deleteArticle(id);
		if (count > 0) System.out.println("�� ���� �Ϸ�");
		else System.out.println("�� ���� ����");
	}
	private int deleteArticle(int id) {
		int deleteCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(pstmt); 	close(con); }
		return deleteCount;
	}
	public void updateArticle(Scanner sc) {
		System.out.println("������ �� ��ȣ�� �Է��ϼ���.");
		System.out.println("�� ��ȣ : ");
		int id = sc.nextInt();
		BoardVO boardVO = getArticle(id);
		System.out.println("������ �����͸� �Է��ϼ���.");
		System.out.println("���� �ۼ��� : " + boardVO.getWriter());
		System.out.println("������ �ۼ��� : ");
		String writer = sc.next();
		System.out.println("���� ��й�ȣ : " + boardVO.getPasswd());
		System.out.println("������ ��й�ȣ : ");
		String passwd = sc.next();
		System.out.println("���� ���� : " + boardVO.getSubject());
		System.out.println("������ ���� : ");
		String subject = sc.next();
		System.out.println("���� �̸��� : " + boardVO.getEmail());
		System.out.println("������ �̸��� : ");
		String email = sc.next();
		boardVO.setWriter(writer); 		boardVO.setPasswd(passwd);
		boardVO.setSubject(subject); 		boardVO.setEmail(email);
		int count = updateArticle(boardVO);
		if(count > 0) System.out.println("�� ���� �Ϸ�");
		else System.out.println("�� ���� ����");
	}
	private int updateArticle(BoardVO boardVO) {
		int updateCount = 0;
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET writer = ?, passwd = ?, subject = ?, "
				+ " email = ? WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setInt(5, boardVO.getId());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(pstmt);  	close(con); }
		return updateCount;
	}
}