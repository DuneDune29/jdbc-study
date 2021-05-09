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

	//생성자
	public VideoModel() throws Exception
	{
		connectDB();
		
	}

	//DB 연결
	void connectDB() throws Exception
	{
		con = ConnectionPool.getConnection();
	}
	
	//비디오 테이블에 입력하는 메소드
	public void insert(VideoVO vo, int count) throws Exception
	{
		// 3. sql 문장 만들기
		String sql = "INSERT INTO videotab (VIDEONUM, VIDEOtitle, VIDEOjanre, VIDEOdirector, VIDEOactor, VIDEOContent, videoregdate) " +
					" VALUES(videonumseq.nextval, ?, ?, ?, ?, ?, sysdate)";
				
		// 4. 전송객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getVideoTitle());
		ps.setString(2, vo.getVideoJanre());
		ps.setString(3, vo.getVideoDirector());
		ps.setString(4, vo.getVideoActor());
		ps.setString(5, vo.getVideoContent());
		
		// 5. 전송하기
		for(int i = 0 ; i < count ; i++)
		{
			ps.executeUpdate(); //입고 갯수만큼 비디오 입력,,
		}
		
		// 6. 닫기 (con 빼고)
		ps.close();

	}

	//PK값으로 비디오 검색하는 메소드
	public VideoVO selectByPk(int vNum) throws Exception{
		//3. sql문장 만들기
		VideoVO vo = new VideoVO();
		
		//3. sql문장 만들기
		String sql = "SELECT * FROM videotab WHERE VIDEONUM = " + vNum;
		
		//4. 전송객체 얻어오기
		Statement stmt = con.createStatement();
		
		//5. 전송하기
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
		
		//6. 닫기
		rs.close();
		stmt.close();
		
		return vo;
	}
	

	//비디오 검색하는 메소드
	public ArrayList selectVideo(int sel, String text) throws Exception
	{
		// 3. sql 문장 만들기
		String[] selCol = {"VideoTitle", "VideoDirector", "VideoActor"};
		String sql = "select videonum, videotitle, videodirector, videojanre, videoactor, videoregdate  from videotab " +
					"where " + selCol[sel] + " like '%" + text +"%'";
		
		// 4. 전송객체 얻어오기
		Statement ps = con.createStatement();
		
		// 5. 전송하기
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
		// 6. 닫기
		rs.close();
		ps.close();
		
		return list;
	}

	
	//비디오 수정하는 메소드
	public void modifyVideo(VideoVO vo) throws Exception
	{
		//3. sql 문장 만들기

		
		//4. 전송객체 얻어오기

		//5. 전송하기
		
		//6. 닫기

	}
	
	//비디오 삭제하는 메소드
	public void deleteVideo(int videono) throws Exception
	{
		//3. sql 문장 만들기
				
		//4. 전송객체 얻어오기
				
		//5. 전송하기
				
		//6.닫기
	}


}
