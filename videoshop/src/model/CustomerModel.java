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

	// ȸ������ �޼ҵ�
	public void regist(CustomerVO cust) throws Exception{
		String name = cust.getCustName();
		String tel = cust.getCustTel();
		String addtel = cust.getCustTelAid();
		String addr = cust.getCustAddr();
		String email = cust.getCustEmail();
		
		String sql = "insert into customertab(custname, custtel, custtelaid, custaddr, custemail) values(?,?,?,?,?)";
		
		// sql ���۰�ü ������
		PreparedStatement ps = con.prepareStatement(sql);
		
		// �̿ϼ��� �κп� ���� ���� -> sql ����
		ps.setString(1, name);
		ps.setString(2,tel);
		ps.setString(3, addtel);
		ps.setString(4, addr);
		ps.setString(5,email);
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	// ȸ������ �޼ҵ�
	public void modify(CustomerVO cust) throws Exception{
		
		//1.  sql �ۼ��ϱ�		( update �� �ۼ� : CustomerVO�� ��������� ���� �����Ͽ� )
		String sql = "UPDATE customer SET custname = ?, custaddtel = ?, custaddr = ?, email = ? " +
					 " WHERE custtel = ?";
				
		//2.  sql  ���۰�ü ������	( PreparedStatement�� �� ������ �� )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3.  sql  ����			( �������� ?�� ���� )
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustTel());
		ps.setString(3, cust.getCustAddr());
		ps.setString(4, cust.getCustEmail());
		ps.setString(5, cust.getCustTel());
		
		ps.executeUpdate();
		
		//4.  �ݱ�				( Connection�� ������ �ȵ� )
		ps.close();
	}
	
	// �̸����� �˻��ϴ� �޼ҵ�
	public ArrayList searchName(String name) throws Exception{
		//1. sql �ۼ��ϱ�	 ( select �� : �Լ��� ���ڷ� �Ѿ�� �̸��� ���� ������ ���ڵ� �˻� )
		String sql = "SELECT * FROM customertab WHERE custname = ?";	
		
		//2. sql  ���۰�ü ������	( Statement / PreparedStatement ��� ���� )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3. sql  ����		 ( ResultSet �� ����Ÿ�� �� ��� �ʵ忡 ���� )
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
		
		//4.  �ݱ�			 ( Connection�� ������ �ȵ� )
		
		rs.close();
		ps.close();
		return list;
	}
	
	// ��ȭ��ȣ�� �˻��ϴ� �޼ҵ�
	public CustomerVO searchTel(String tel) throws Exception{
		//1. sql �ۼ��ϱ�	 ( select �� : �Լ��� ���ڷ� �Ѿ�� �̸��� ���� ������ ���ڵ� �˻� )
		String sql = "SELECT * FROM customertab WHERE custTel = ?";	
		
		//2. sql  ���۰�ü ������	( Statement / PreparedStatement ��� ���� )
		PreparedStatement ps = con.prepareStatement(sql);
		
		//3. sql  ����		 ( ResultSet �� ����Ÿ�� �� ��� �ʵ忡 ���� )
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
		
		//4.  �ݱ�			 ( Connection�� ������ �ȵ� )
		
		rs.close();
		ps.close();
		return vo;
	}	

}
