package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import model.rec.CustomerVO;

public class SameName extends JFrame{
	//������� ����
	CustomerView cv;
	//JButton btn;
	JRadioButton[] rbt;
	ArrayList list;
	String tel;
	CustomerView Child;


	
	//�⺻ ������
	public SameName()
	{
		
	}
	
	//�����ִ� ������
	public SameName(ArrayList list, CustomerView parent) throws Exception
	{
		Child = new CustomerView();
		Child = parent;
		this.list = list;
		int i = 0;
		cv = new CustomerView();
		//System.out.println(list.get(0));
	
		setLayout(new FlowLayout());
		
		rbt = new JRadioButton[list.size()];
		
		//�̺�Ʈ ��ü ����
		RbtEvent evt = new RbtEvent();
		
		while( i < rbt.length )
		{
			CustomerVO vo = new CustomerVO();
			vo = (CustomerVO)list.get(i);
			rbt[i] = new JRadioButton(vo.getCustTel());
			
			//�ڵ鷯 ��ü ����
			rbt[i].addActionListener(evt);
			
			add(rbt[i]);
			i++;
		}
		
		setTitle("�̸� �˻�");
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public CustomerView eventProc(CustomerView turn)
	{
		
		return turn;
	}
	
	
	public class RbtEvent implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			
			JRadioButton temp = (JRadioButton)e.getSource();
			Child.searchTel(temp.getText()) ;
			System.out.println(temp.getText());
			setVisible(false);
		
			
		}
		
	}

}
