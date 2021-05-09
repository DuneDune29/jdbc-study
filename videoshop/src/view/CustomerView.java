package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CustomerModel;
import model.rec.CustomerVO;

public class CustomerView extends JPanel implements ActionListener{ 
		JTextField	tfCustName, tfCustTel,  tfCustTelAid, tfCustAddr, tfCustEmail;
		JButton		bCustRegist, bCustModify;
		
		JTextField  tfCustNameSearch,  tfCustTelSearch;
		JButton		bCustNameSearch,  bCustTelSearch;
		
		CustomerModel model = null;

		public CustomerView(){
			addLayout();
			
			// DB ����
			try {
				model = new CustomerModel();
				System.out.println("����� ���� ����!!");
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "DB���� ����"+e.getMessage());
			}
			
		}
		
		// ȭ�� ����
		public void addLayout() {
				tfCustName			= new JTextField(20);
				tfCustTel			= new JTextField(20);
				tfCustTelAid		= new JTextField(20);
				tfCustAddr			= new JTextField(20);
				tfCustEmail			= new JTextField(20);


				tfCustNameSearch	= new JTextField(20);
				tfCustTelSearch		= new JTextField(20);

				bCustRegist			= new JButton("ȸ������");
				bCustModify			= new JButton("ȸ������");
				bCustNameSearch		= new JButton("�̸��˻�");
				bCustTelSearch		= new JButton("��ȣ�˻�");

				// ȸ������ �κ� ���̱� 
				//		( �� �����ϴٴ� GridBagLayout�� ����ؼ� ������ ����..�ٸ� ���������...��ġ ���� )
				JPanel			pRegist		= new JPanel();
				pRegist.setLayout( new GridBagLayout() );
					GridBagConstraints	cbc = new GridBagConstraints();
					cbc.weightx	= 1.0;
					cbc.weighty	 = 1.0;
					cbc.fill				= GridBagConstraints.BOTH;
				cbc.gridx	=	0;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( new JLabel("	��	��	") ,	cbc );
				cbc.gridx	=	1;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( tfCustName ,	cbc );
				cbc.gridx	=	2;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( bCustModify,	cbc );
				cbc.gridx	=	3;	 			cbc.gridy	=  0;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( bCustRegist,	cbc );

				cbc.gridx	=	0;	 			cbc.gridy	=  1;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( new JLabel("	��	ȭ	") ,	cbc );
				cbc.gridx	=	1;	 			cbc.gridy	=  1;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add(  tfCustTel ,	cbc );
				cbc.gridx	=	2;	 			cbc.gridy	=  1;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( new JLabel(" �߰���ȭ  ") ,	cbc );
				cbc.gridx	=	3;	 			cbc.gridy	=  1;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( tfCustTelAid ,	cbc );

				cbc.gridx	=	0;	 			cbc.gridy	=  2;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( new JLabel("	��	��	") ,	cbc );
				cbc.gridx	=	1;	 			cbc.gridy	=  2;			cbc.gridwidth	=	3;			cbc.gridheight= 1;
				pRegist.add(  tfCustAddr  ,	cbc );

				cbc.gridx	=	0;	 			cbc.gridy	=  3;			cbc.gridwidth	=	1;			cbc.gridheight= 1;
				pRegist.add( new JLabel("	�̸���	") ,	cbc );
				cbc.gridx	=	1;	 			cbc.gridy	=  3;			cbc.gridwidth	=	3;			cbc.gridheight= 1;
				pRegist.add( tfCustEmail ,	cbc );




				// ȸ���˻� �κ� ���̱�
				JPanel	pSearch		= new JPanel();
				pSearch.setLayout( new GridLayout(2, 1) );
				JPanel	pSearchName	= new JPanel();
				pSearchName.add(	new JLabel("		�� 	��	"));
				pSearchName.add(	tfCustNameSearch );
				pSearchName.add(	bCustNameSearch );
				JPanel	pSearchTel	= new JPanel();
				pSearchTel.add(		new JLabel("	��ȭ��ȣ	"));
				pSearchTel.add(	tfCustTelSearch );
				pSearchTel.add(	bCustTelSearch );
				pSearch.add(	 pSearchName );
				pSearch.add( pSearchTel );

				// ��ü �гο� ���̱�
				setLayout( new BorderLayout() );
				add("Center",		pRegist );
				add("South",		pSearch );
				
				// ��ư�� �̺�Ʈ ���
				bCustRegist.addActionListener( this );     // ȸ������ �̺�Ʈ
				bCustModify.addActionListener( this );     // ȸ������ �̺�Ʈ
				bCustNameSearch.addActionListener( this ); // �̸��˻� �̺�Ʈ
				bCustTelSearch.addActionListener( this );  // ��ȭ��ȣ�˻� �̺�Ʈ
		}

		//---------------------------------------------
		//	ActionEvent �߻��� ȣ��Ǵ� �޼ҵ�
		public void actionPerformed( ActionEvent ev ){
			Object o = ev.getSource();
			// ȸ������ 
			if( o == bCustRegist ) {
				/*
					1. �� TextField ���� �Է°� ������
					2. CustoemrVO Ŭ������ setter �޼ҵ带 �̿��Ͽ�
						����ʵ忡 1������ ����
					3. CustoemrModel Ŭ������ regist() ȣ��  // insert ����� ����ִ� �޼ҵ�
					4. �� TextField �ʱ�ȭ
				*/
				String name = tfCustName.getText();
				String tel = tfCustTel.getText();
				String addtel = tfCustTelAid.getText();
				String addr = tfCustAddr.getText();
				String email = tfCustEmail.getText();
				
				CustomerVO vo = new CustomerVO(tel, name, addtel, addr, email);
								
				try {
					model.regist(vo);
					System.out.println("ȸ������ ����!");
					layoutClear();
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�Է½��� : " + e.getMessage());
				}
			// ȸ�� ����	
			}else if ( o == bCustModify)	{
				/*
					1. �� TextField ���� �Է°� ������
					2. CustoemrRecord Ŭ������ setter �޼ҵ带 �̿��Ͽ�
						����ʵ忡 1������ ����
					3. CustoemrRecord Ŭ������ modify() ȣ��
					4. �� TextField �ʱ�ȭ
				*/
				String name = tfCustName.getText();
				String tel = tfCustTel.getText();
				String addtel = tfCustTelAid.getText();
				String addr = tfCustAddr.getText();
				String email = tfCustEmail.getText();
				
				CustomerVO vo = new CustomerVO(tel, name,addtel, addr, email);
				
				try{
					model.modify(vo);
					layoutClear();
					System.out.println("ȸ������ ����");
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "�������� : " + ex.getMessage());
					}
			// �̸� �˻�		
			}else if ( o == bCustNameSearch){
				/*
					1. �˻��κ��� �̸� �Է��ϴ� TextField���� �Է°� ������
					2. CustoemrRecord Ŭ������ searchName() ȣ��
					3. CustoemrRecord Ŭ������ getter �޼ҵ带 �̿��Ͽ� 
						DB���� �˻��� ����Ÿ�� �� �ؽ�Ʈ �ʵ忡 �����Ѵ�
					
				*/
				// # ���� ������ �ִ� ��� ��� ( ���߿� )
				ArrayList list = new ArrayList();
				CustomerView parent = new CustomerView();
				try {
					String name = tfCustNameSearch.getText();
					list = model.searchName(name);
					SameName na =new SameName(list, this);	//
					System.out.println(name);
					
/*					
					// ���
					tfCustName.setText(vo.getCustName());
					tfCustTel.setText(vo.getCustTel());
					tfCustTelAid.setText(vo.getCustTelAid());
					tfCustAddr.setText(vetCustAddr());
					tfCustEmail.setText(vo.getCustEmail());
*/
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�̸��˻� ���� : " + e.getMessage());
				}
				
			// ��ȭ��ȣ �˻�
			}else if ( o == bCustTelSearch){
				/*
					1. �˻��κ��� �̸� �Է��ϴ� TextField���� �Է°� ������
					2. CustoemrRecord Ŭ������ searchTel() ȣ��
					3. CustoemrRecord Ŭ������ getter �޼ҵ带 �̿��Ͽ� 
						DB���� �˻��� ����Ÿ�� �� �ؽ�Ʈ �ʵ忡 �����Ѵ�
				*/
				try {
					String tel = tfCustTelSearch.getText();
					CustomerVO vo = model.searchTel(tel);
					
					tfCustName.setText(vo.getCustName());
					tfCustTel.setText(vo.getCustTel());
					tfCustTelAid.setText(vo.getCustTelAid());
					tfCustAddr.setText(vo.getCustAddr());
					tfCustEmail.setText(vo.getCustEmail());
					
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�̸��˻� ���� : " + e.getMessage());
				}
	
			}
					 		
	};		
	
	public void layoutClear() {
		tfCustName.setText("");
		tfCustTel.setText("");
		tfCustTelAid.setText("");
		tfCustAddr.setText("");
		tfCustEmail.setText("");
	}
	
	void searchTel(String tel)	{
		try
		{
			CustomerVO vo = model.searchTel(tel);
			
			//���
			tfCustName.setText(vo.getCustName());
			tfCustTel.setText(vo.getCustTel());
			tfCustTelAid.setText(vo.getCustTelAid());
			tfCustAddr.setText(vo.getCustAddr());
			tfCustEmail.setText(vo.getCustEmail());
			
			System.out.println("�˻� ����!");
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "�˻� ���� : " + ex.getMessage());
		}
	}
}
					 	


