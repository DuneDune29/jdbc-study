import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Score {
	public int insertDate() {
		int result = 0;  		Connection conn = DBConn.getConnection();
		Statement stmt = null; 	String sql, hak, name;
		int kor, eng, mat;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("학번?");  		hak = br.readLine();
			System.out.print("이름?");  		name = br.readLine();
			System.out.print("국어?");
			kor = Integer.parseInt(br.readLine());
			System.out.print("영어?");
			eng = Integer.parseInt(br.readLine());
			System.out.print("수학?");
			mat = Integer.parseInt(br.readLine());
			sql = "INSERT INTO score (hak, name, kor, eng, mat) VALUES (";
			sql += "'" + hak + "'," + "'" + name + "',";
			sql += "" + kor + "," + "" + eng + "," + "" + mat + ")";
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) { System.out.println(e.toString()); }
		return result;
	}
	public void selectAll() {
		Statement stmt = null; 			ResultSet rs = null;
		String sql, hak, name, str;  	int kor, eng, mat, tot, ave;
		Connection conn = DBConn.getConnection();
		try {
			sql = "SELECT hak, name, kor, eng, mat,";
			sql += "(kor+eng+mat) AS tot,";
			sql += "(kor+eng+mat)/3 AS ave ";
			sql += " FROM score ORDER BY hak";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				hak = rs.getString(1); 		name = rs.getString("name");
				kor = rs.getInt(3); 		eng = rs.getInt(4);
				mat = rs.getInt(5); 		tot = rs.getInt(6);
				ave = rs.getInt(7);
				str = String.format("%-10s %-15s %5d %5d %5d %5d %5d",
						hak, name, kor, eng, mat, tot, ave);
				System.out.println(str);
			}
			rs.close(); 		stmt.close();
		} catch (Exception e) { System.out.println(e.toString()); }
	}
	public void selectName() {
		Statement stmt = null; 			ResultSet rs = null;
		Connection conn = DBConn.getConnection();
		String sql, str, searchName, hak, name;
		int kor, eng, mat, tot, ave;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("검색할 이름?"); 		searchName = br.readLine();
			sql = "SELECT hak, name, kor, eng, mat, ";
			sql += "(kor+eng+mat) AS tot, ";
			sql += "(kor+eng+mat)/3 AS ave FROM score ";
			sql += " WHERE upper(name) LIKE upper('" + searchName + "%')";
			stmt = conn.createStatement(); 		rs = stmt.executeQuery(sql);
			while (rs.next()) {
				hak = rs.getString(1); 		name = rs.getString("name");
				kor = rs.getInt(3); 		eng = rs.getInt(4);
				mat = rs.getInt(5); 		tot = rs.getInt(6);
				ave = rs.getInt(7);
				str = String.format("%-10s %-15s %5d %5d %5d %5d %5d",
						hak, name, kor, eng, mat, tot, ave);
				System.out.println(str);
			}
			rs.close(); 		stmt.close();
		} catch (Exception e) { }
	}
	public int deleteDate() {
		int result = 0; 		Connection conn = DBConn.getConnection();
		Statement stmt = null; 			String sql, str;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("삭제할 학번?"); 		str = br.readLine();
			sql = "DELETE FROM score WHERE hak='" + str + "'";
			stmt = conn.createStatement(); 		
			result = stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) { System.out.println(e.toString()); }
		return result;
	}
	public int updateDate() {
		int result = 0; 		Connection conn = DBConn.getConnection();
		Statement stmt = null; 			String sql, hak, name;
		int kor, eng, mat;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		System.out.print("수정할 학번?");  		hak = br.readLine();
		System.out.print("이름?");  			name = br.readLine();
		System.out.print("국어?");
		kor = Integer.parseInt(br.readLine());
		System.out.print("영어?");
		eng = Integer.parseInt(br.readLine());
		System.out.print("수학?");
		mat = Integer.parseInt(br.readLine());
		sql = "UPDATE score SET ";
		sql += "name='" + name + "',";
		sql += "kor=" + kor + ",";
		sql += "eng=" + eng + ",";
		sql += "mat=" + mat + "";
		sql += " WHERE hak='" + hak + "'";
		System.out.println(sql);
		stmt = conn.createStatement(); 		
		result = stmt.executeUpdate(sql);
		stmt.close();
		} catch (Exception e) { System.out.println(e.toString()); }
		return result;
	}
}