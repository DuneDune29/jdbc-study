package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.rec.CustomerVO;

public class CustomerModel {
	Connection con;
	
	//  constructor
	public	CustomerModel() throws Exception{
		connectDB();
	}

	void  connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	// 회원가입 메소드
	public void regist(CustomerVO cust) throws Exception{
		String name = cust.getCustName();
		String tel = cust.getCustTel();
		String addtel = cust.getCustTelAid();
		String addr = cust.getCustAddr();
		String email = cust.getCustEmail();
		
		String sql = "insert into customertab(custname, custtel, custtelaid, custaddr, custemail) values(?,?,?,?,?)";
		
		// sql 전송객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		
		// 미완성된 부분에 값을 대입 -> sql 전송
		ps.setString(1, name);
		ps.setString(2,tel);
		ps.setString(3, addtel);
		ps.setString(4, addr);
		ps.setString(5,email);
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	// 회원수정 메소드
	public void modify(CustomerVO cust) throws Exception{
		
		//1.  sql 작성하기		( update 문 작성 : CustomerVO의 멤버변수로 각각 지정하여 )
		String sql = "UPDATE customer SET custname = ?, custaddtel = ?, custaddr = ?, email = ? " +
					 " WHERE custtel = ?";
				
		//2.  sql  전송객체 얻어오기	( PreparedStatement가 더 적합할 듯 )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3.  sql  전송			( 전송전에 ?로 지정 )
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustTel());
		ps.setString(3, cust.getCustAddr());
		ps.setString(4, cust.getCustEmail());
		ps.setString(5, cust.getCustTel());
		
		ps.executeUpdate();
		
		//4.  닫기				( Connection은 닫으면 안됨 )
		ps.close();
	}
	
	// 이름으로 검색하는 메소드
	public ArrayList searchName(String name) throws Exception{
		//1. sql 작성하기	 ( select 문 : 함수의 인자로 넘어온 이름과 같은 조건의 레코드 검색 )
		String sql = "SELECT * FROM customertab WHERE custname = ?";	
		
		//2. sql  전송객체 얻어오기	( Statement / PreparedStatement 모두 적합 )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3. sql  전송		 ( ResultSet 의 데이타를 얻어서 멤버 필드에 지정 )
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		ArrayList list = new ArrayList();
		while(rs.next()) {
			CustomerVO vo = new CustomerVO();
			vo.setCustTel(rs.getString("CUSTTEL"));
			vo.setCustName(rs.getString("CUSTNAME"));
			vo.setCustTelAid(rs.getString("CUSTTELAID"));
			vo.setCustAddr(rs.getString("CUSTADDR"));
			vo.setCustEmail(rs.getString("CUSTEMAIL"));
			list.add(vo);	//
		}
		
		//4.  닫기			 ( Connection은 닫으면 안됨 )
		
		rs.close();
		ps.close();
		return list;
	}
	
	// 전화번호로 검색하는 메소드
	public CustomerVO searchTel(String tel) throws Exception{
		//1. sql 작성하기	 ( select 문 : 함수의 인자로 넘어온 이름과 같은 조건의 레코드 검색 )
		String sql = "SELECT * FROM customertab WHERE custTel = ?";	
		
		//2. sql  전송객체 얻어오기	( Statement / PreparedStatement 모두 적합 )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3. sql  전송		 ( ResultSet 의 데이타를 얻어서 멤버 필드에 지정 )
		ps.setString(1, tel);
		
		ResultSet rs = ps.executeQuery();
		CustomerVO vo = new CustomerVO();
		if(rs.next()) {
			
			vo.setCustTel(rs.getString("CUSTTEL"));
			vo.setCustName(rs.getString("CUSTNAME"));
			vo.setCustTelAid(rs.getString("CUSTTELAID"));
			vo.setCustAddr(rs.getString("CUSTADDR"));
			vo.setCustEmail(rs.getString("CUSTEMAIL"));
		}
		
		//4.  닫기			 ( Connection은 닫으면 안됨 )
		
		rs.close();
		ps.close();
		return vo;
	}	

}
