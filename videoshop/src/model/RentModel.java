package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RentModel
{
	Connection con;

	public RentModel() throws Exception
	{
		//=========================================
		// 1. 드라이버를 메모리에 로딩
		// 2. Connection 얻어오기
		con = ConnectionPool.getConnection();
	}

	public String searchByTel( String custTel ) throws Exception
	{


		String custName = null;

		//=========================================
		// 1. sql 쿼리 만들기
		//		` 입력받은 전화번호의 고객명을 검색하라
		//		` select custName from customerTab where custTel=넘겨받은인자값
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 4. 결과값을 받아 custName 변수에 지정
		// 5. sql 닫기
		String sql = "SELECT custname FROM customertab WHERE custtel = '" + custTel +"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if ( rs.next() )
		{
			custName = rs.getString("CUSTNAME");
		}
		return custName;

	}

	public void videoRent( String custTel, String name, int videoNum ) throws Exception	{
		String sql = "INSERT INTO renttab (RENTNUM, RENTCUSTTEL, RENTCUSTNAME, RENTVIDEONUM, RENTDATE,  RETURNSCHEDULED, RETURNFLAG, RENTCHARGE) " +
				" VALUES(rentnumseq.nextval, ?, ?, ?,sysdate, sysdate+5, 'N', 5000)";
		//System.out.println(sql);
		
		//4. 전송객체 생성
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, custTel);
		ps.setString(2, name);
		ps.setInt(3, videoNum);
		
		//5. 전송
		ps.executeUpdate();
		
		//6. 닫기
		ps.close();	
	}

	//이미 대여 중인지 확인하는 메소드
	public boolean isPossible(int videono) throws Exception	{
		String sql = "SELECT RENTVIDEONUM FROM renttab WHERE RETURNFLAG = 'N' and RENTVIDEONUM = " + videono;
		
		//4. 전송객체 불러오기
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if( rs.next() ) //대여 중인 비디오면,,  false를 반환
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	// 반납 했는지의 여부를 확인하는 메소드
	public boolean isRentPossible(int videoNum) throws Exception{
		String sql = "select RETURNFLAG from renttab where RETURNFLAG = 'N' and RENTVIDEONUM = " + videoNum;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			rs.close();
			stmt.close();
			return true;
		}else{
			rs.close();
			
			stmt.close();
			return false;
		}
		
	}
	public void videoReturn(int videoNum ) throws Exception
	{
		//=========================================
		// 1. sql 쿼리 만들기
		//		` 입력받은 비디오번호로 rentTab 테이블의 returnFlag 컬럼을 true로 변경하라
		//		` update rentTab set returnFlag='false', returndate=sysdate where videoNum=넘겨받은인자값 AND	 returnFlag='Y'
		//
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 4. sql 닫기
		String sql = "UPDATE renttab SET returnflag = 'Y' WHERE RENTVIDEONUM = " + videoNum + " and RETURNFLAG = 'N'";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public ArrayList recentList() throws Exception
	{
	      String sql = "select v.videonum, v.videotitle,r.rentcustname,r.rentcusttel,r.returndate,r.returnflag "
	              + "from videotab v,renttab r where v.videonum = r.rentvideonum";

	        Statement st;

	        ArrayList list = new ArrayList();

	        st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	           ArrayList temp = new ArrayList();
	           temp.add(rs.getString("videonum"));
	           temp.add(rs.getString("videotitle"));
	           temp.add(rs.getString("rentcustname"));
	           temp.add(rs.getString("rentcusttel"));
	           temp.add(rs.getString("returndate"));
	           temp.add(rs.getString("returnflag"));
	           list.add(temp);
	        }
	        rs.close();
	        st.close();

	        return list;

	}
	


};