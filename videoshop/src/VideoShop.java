import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerView;
import view.RentView;
import view.VideoView;
public class VideoShop extends JFrame 
{
	CustomerView	customer;
	VideoView		video;
	RentView		rent;

	public VideoShop(){
		//각각의 화면을 관리하는 클래스 객체 생성
			customer = new CustomerView();
			video	 = new VideoView();
			rent	 = new RentView();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("고객관리", customer );
			pane.addTab("비디오관리", video);
			pane.addTab("대여관리", rent );

			pane.setSelectedIndex(2);

			getContentPane().add("Center", pane );
			//setSize(600,400);
			pack();
			setVisible( true );

			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	public static void main(String[] args) 
	{
			new VideoShop();
	}
}
