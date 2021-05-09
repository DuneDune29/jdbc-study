package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.VideoVO;

public class VideoModel {


	Connection				con;

	//������
	public VideoModel() throws Exception
	{
		connectDB();
		
	}

	//DB ����
	void connectDB() throws Exception
	{
		con = ConnectionPool.getConnection();
	}
	
	//���� ���̺� �Է��ϴ� �޼ҵ�
	public void insert(VideoVO vo, int count) throws Exception
	{
		// 3. sql ���� �����
		String sql = "INSERT INTO videotab (VIDEONUM, VIDEOtitle, VIDEOjanre, VIDEOdirector, VIDEOactor, VIDEOContent, videoregdate) " +
					" VALUES(videonumseq.nextval, ?, ?, ?, ?, ?, sysdate)";
				
		// 4. ���۰�ü ������
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getVideoTitle());
		ps.setString(2, vo.getVideoJanre());
		ps.setString(3, vo.getVideoDirector());
		ps.setString(4, vo.getVideoActor());
		ps.setString(5, vo.getVideoContent());
		
		// 5. �����ϱ�
		for(int i = 0 ; i < count ; i++)
		{
			ps.executeUpdate(); //�԰� ������ŭ ���� �Է�,,
		}
		
		// 6. �ݱ� (con ����)
		ps.close();

	}

	//PK������ ���� �˻��ϴ� �޼ҵ�
	public VideoVO selectByPk(int vNum) throws Exception{
		//3. sql���� �����
		VideoVO vo = new VideoVO();
		
		//3. sql���� �����
		String sql = "SELECT * FROM videotab WHERE VIDEONUM = " + vNum;
		
		//4. ���۰�ü ������
		Statement stmt = con.createStatement();
		
		//5. �����ϱ�
		ResultSet rs = stmt.executeQuery(sql);
		if( rs.next() )
		{
			vo.setVideoNum(rs.getInt("VIDEONUM"));
			vo.setVideoTitle(rs.getString("VIDEOtitle"));
			vo.setVideoJanre(rs.getString("VIDEOjanre"));
			vo.setVideoActor(rs.getString("VIDEOactor"));
			vo.setVideoDirector(rs.getString("VIDEOdirector"));
			vo.setVideoContent(rs.getString("VIDEOContent"));
		}
		
		//6. �ݱ�
		rs.close();
		stmt.close();
		
		return vo;
	}
	

	//���� �˻��ϴ� �޼ҵ�
	public ArrayList selectVideo(int sel, String text) throws Exception
	{
		// 3. sql ���� �����
		String[] selCol = {"VideoTitle", "VideoDirector", "VideoActor"};
		String sql = "select videonum, videotitle, videodirector, videojanre, videoactor, videoregdate  from videotab " +
					"where " + selCol[sel] + " like '%" + text +"%'";
		
		// 4. ���۰�ü ������
		Statement ps = con.createStatement();
		
		// 5. �����ϱ�
		ResultSet rs = ps.executeQuery(sql);
		ArrayList list = new ArrayList();
				
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("videonum"));
			temp.add(rs.getString("videotitle"));
			temp.add(rs.getString("videojanre"));
			temp.add(rs.getString("videodirector"));
			temp.add(rs.getString("videoactor"));
			temp.add(rs.getString("videoregdate"));
			list.add(temp);
		}
		// 6. �ݱ�
		rs.close();
		ps.close();
		
		return list;
	}

	
	//���� �����ϴ� �޼ҵ�
	public void modifyVideo(VideoVO vo) throws Exception
	{
		//3. sql ���� �����

		
		//4. ���۰�ü ������

		//5. �����ϱ�
		
		//6. �ݱ�

	}
	
	//���� �����ϴ� �޼ҵ�
	public void deleteVideo(int videono) throws Exception
	{
		//3. sql ���� �����
				
		//4. ���۰�ü ������
				
		//5. �����ϱ�
				
		//6.�ݱ�
	}


}
