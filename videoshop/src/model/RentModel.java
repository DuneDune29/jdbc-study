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
		// 1. ����̹��� �޸𸮿� �ε�
		// 2. Connection ������
		con = ConnectionPool.getConnection();
	}

	public String searchByTel( String custTel ) throws Exception
	{


		String custName = null;

		//=========================================
		// 1. sql ���� �����
		//		` �Է¹��� ��ȭ��ȣ�� ������ �˻��϶�
		//		` select custName from customerTab where custTel=�Ѱܹ������ڰ�
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 4. ������� �޾� custName ������ ����
		// 5. sql �ݱ�
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
		
		//4. ���۰�ü ����
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, custTel);
		ps.setString(2, name);
		ps.setInt(3, videoNum);
		
		//5. ����
		ps.executeUpdate();
		
		//6. �ݱ�
		ps.close();	
	}

	//�̹� �뿩 ������ Ȯ���ϴ� �޼ҵ�
	public boolean isPossible(int videono) throws Exception	{
		String sql = "SELECT RENTVIDEONUM FROM renttab WHERE RETURNFLAG = 'N' and RENTVIDEONUM = " + videono;
		
		//4. ���۰�ü �ҷ�����
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if( rs.next() ) //�뿩 ���� ������,,  false�� ��ȯ
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	// �ݳ� �ߴ����� ���θ� Ȯ���ϴ� �޼ҵ�
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
		// 1. sql ���� �����
		//		` �Է¹��� ������ȣ�� rentTab ���̺��� returnFlag �÷��� true�� �����϶�
		//		` update rentTab set returnFlag='false', returndate=sysdate where videoNum=�Ѱܹ������ڰ� AND	 returnFlag='Y'
		//
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 4. sql �ݱ�
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