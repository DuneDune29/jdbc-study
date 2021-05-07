import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;

public class DBFrame extends Frame{
	Label lid, lpass1, lpass2, lname, ljumin, ltel, laddr, ljob, lbar1, lbar2, lbar3, empty1, empty2, empty3, empty4;
	TextField tf_id, tf_pass1, tf_pass2, tf_name, tf_jumin1, tf_jumin2, tf_tel1, tf_tel2, tf_tel3, tf_addr;
	JButton idCheck, submit;
	Choice job; 			String name;
	Connection conn; 		Statement stmt; 			ResultSet rs;
	
	public static void main(String[] args) {
		DBFrame dbf = new DBFrame();
	}
	DBFrame() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setResizable(false);
		setFont(new Font("���ü", Font.BOLD, 15));
		setLayout(new FlowLayout()); 		setBackground(Color.yellow);
		setBounds(250, 250, 350, 320);
		lid = new Label("�� �� �� : "); 		lpass1 = new Label("��й�ȣ : ");
		lpass2 = new Label("���Ȯ�� : "); 	lname = new Label("��   �� : ");
		ljumin = new Label("�ֹι�ȣ : "); 	ltel = new Label("��ȭ��ȣ : ");
		laddr = new Label("��   �� : "); 		ljob = new Label("��   �� : ");
		lbar1 = new Label("-");				lbar2 = new Label("-"); 
		lbar3 = new Label("-");
		empty1 = new Label(" 				");
		empty2 = new Label(" 				");
		empty3 = new Label(" 				");
		empty4 = new Label(" 				");
		tf_id = new TextField(10); 			tf_pass1 = new TextField(10);
		tf_pass2 = new TextField(10); 		tf_name = new TextField(10);
		tf_jumin1 = new TextField(6); 		tf_jumin2 = new TextField(7);
		tf_tel1 = new TextField(3); 		tf_tel2 = new TextField(4);
		tf_tel3 = new TextField(4); 		tf_addr = new TextField(25);
		tf_pass1.setEchoChar('*');
		tf_jumin2.setEchoChar('*');
		idCheck = new JButton("�ߺ� Ȯ��"); 	submit = new JButton("��   ��");
		job = new Choice();
		add(lid); 			add(tf_id);			add(idCheck);		add(lpass1);
		add(tf_pass1); 		add(empty1);		add(lpass2);		add(tf_pass2);
		add(empty2); 		add(lname);			add(tf_name);		add(empty3);
		add(ljumin); 		add(tf_jumin1);		add(lbar1);			add(tf_jumin2);
		add(ltel); 			add(tf_tel1);		add(lbar2);			add(tf_tel2);
		add(lbar3); 		add(tf_tel3);		add(laddr);			add(tf_addr);
		add(ljob); 			add(job);			
		job.add("�� ��");			job.add("������");			job.add("�� ��"); 
		job.add("�� ��");			job.add("�����");			job.add("�� Ÿ"); 
		add(empty4); 			add(submit);			
		//setVisible(true);
	}
}