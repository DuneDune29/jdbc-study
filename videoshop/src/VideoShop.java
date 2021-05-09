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
		//������ ȭ���� �����ϴ� Ŭ���� ��ü ����
			customer = new CustomerView();
			video	 = new VideoView();
			rent	 = new RentView();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("������", customer );
			pane.addTab("��������", video);
			pane.addTab("�뿩����", rent );

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
