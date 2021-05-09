package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.VideoModel;
import model.rec.VideoVO;


public class VideoView extends JPanel implements ActionListener
{	
	//	member field
	JTextField	tfVideoNum, tfVideoTitle, tfVideoDirector, tfVideoActor;
	JComboBox	comVideoJanre;
	JTextArea	taVideoContent;

	JCheckBox	cbMultiInsert;
	JTextField	tfInsertCount;

	JButton		bVideoInsert, bVideoModify, bVideoDelete;

	JComboBox	comVideoSearch;
	JTextField	tfVideoSearch;
	JTable		tableVideo;
	VideoModel	model;
	VideoTableModel tmVideo;

	//##############################################
	//	constructor method
	public VideoView(){
		newObject();
		addLayout();
		setStyle();
		eventProc();
		try
		{
			model = new VideoModel();
			System.out.println("���� ��� ���� ����");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "DB���� ���� : " + ex.getMessage());
		}
	}

	//##############################################
	// ����ʵ� ��ü ����
	void	newObject(){
		tfVideoNum			= new JTextField(15);
		tfVideoTitle		= new JTextField(15);
		tfVideoDirector		= new JTextField(15);
		tfVideoActor		= new JTextField(15);
		String janreText[]	= {"����","�ڹ�","���","����","�ִϸ��̼�" };
		comVideoJanre		= new JComboBox(janreText);
		taVideoContent		= new JTextArea( 15, 3 );

		cbMultiInsert		= new JCheckBox(" �����԰� ");
		tfInsertCount		= new JTextField("1", 15);
		bVideoInsert		= new JButton("	��	��	");
		bVideoModify		= new JButton("	��	��	");
		bVideoDelete		= new JButton("	��	��	");

		String	searchText[]= {"����", "����" , "���" };
		comVideoSearch		= new JComboBox(searchText );
		tfVideoSearch		= new JTextField(15);

		tmVideo				= new VideoTableModel();
		tableVideo			= new JTable( tmVideo );
	}
	
	//##############################################
	//	GUI �����ϱ� ���� Layout�� ���̱�
	void	addLayout(){
		//-----------------------------------------
		// west ���� : ���� �Է� �� ����
		JPanel	pWest	= new JPanel();
			// ���� �����Է��ϴ� �κ�
			JPanel	pWestNorth		= new JPanel();
					JPanel	pWestNorthUp	= new JPanel();
					pWestNorthUp.setLayout( new GridLayout( 5, 2 ));
					pWestNorthUp.add(	new JLabel(" ���� ��ȣ " ));
					pWestNorthUp.add( tfVideoNum );
					pWestNorthUp.add(	new JLabel(" ��		�� " ));
					pWestNorthUp.add( comVideoJanre );
					pWestNorthUp.add(	new JLabel(" ��		�� " ));
					pWestNorthUp.add( tfVideoTitle );
					pWestNorthUp.add(	new JLabel(" ��		�� " ));
					pWestNorthUp.add( tfVideoDirector );
					pWestNorthUp.add(	new JLabel(" ��		�� " ));
					pWestNorthUp.add( tfVideoActor );

					JPanel	pWestNorthDown	= new JPanel();
					pWestNorthDown.setLayout( new BorderLayout() );
					pWestNorthDown.add("West",	new JLabel("  ��   �� " ) );
					pWestNorthDown.add("Center", taVideoContent );

					pWestNorth.setBorder( new TitledBorder(" ���� ���� �Է� " ));

			pWestNorth.setLayout( new BorderLayout() );
			pWestNorth.add("Center", pWestNorthUp );
			pWestNorth.add("South",  pWestNorthDown );

			// ���� ���� �Է�/���� ��ư �κ�
			JPanel	pWestSouth	= new JPanel();
					JPanel	pWestSouthUp	= new JPanel();
					pWestSouthUp.add( cbMultiInsert );
					pWestSouthUp.add( tfInsertCount);
					pWestSouthUp.add( new JLabel("	��	" ));
					pWestSouthUp.setBorder( new TitledBorder(" �����Է½� �����Ͻÿ� " ));

					JPanel	pWestSouthDown	= new JPanel();
					pWestSouthDown.setLayout( new GridLayout( 1, 3 ) );
					pWestSouthDown.add( bVideoInsert );
					pWestSouthDown.add( bVideoModify );
					pWestSouthDown.add( bVideoDelete );

			pWestSouth.setLayout( new GridLayout( 2, 1 ) );
			pWestSouth.add(pWestSouthUp);
			pWestSouth.add(pWestSouthDown);

		pWest.setLayout( new BorderLayout() );
		pWest.add("Center", pWestNorth );
		pWest.add("South",  pWestSouth );


		//-----------------------------------------
		// east ���� : ���� �˻��κ�
		JPanel	pEast		= new JPanel();
			JPanel	pEastNorth	= new JPanel();
			pEastNorth.add( comVideoSearch );
			pEastNorth.add( tfVideoSearch );
			pEastNorth.setBorder( new TitledBorder(" ���� �˻� " ) );

			JPanel	pEastCenter = new JPanel();
			pEastCenter.setLayout( new BorderLayout() );
			pEastCenter.add("Center", new JScrollPane( tableVideo) );

		pEast.setLayout( new BorderLayout() );
		pEast.add("North",  pEastNorth);
		pEast.add("Center", pEastCenter);


		//-----------------------------------------
		//	��ü ���̱�
		setLayout( new GridLayout( 1, 2 ) );
		add( pWest );
		add( pEast );
	}

	//####################################################
	//	ȭ�鿡 �ʿ��� ��Ÿ�� ����
	void setStyle(){

		tfInsertCount.setText("1");
		// �ؽ�Ʈ�ʵ� ������������ �ʵ��� ����
		tfVideoNum.setEditable( false );
		tfInsertCount.setEditable( false );
		
		// �԰� �����ϴ� �ؽ�Ʈ�ʵ��� ���� ���� ( ���������� )
		tfInsertCount.setHorizontalAlignment(JTextField.RIGHT);


	}

	//####################################################
	//	�̺�Ʈ ��� �� ����
	void eventProc(){
		// ��ư�� �ؽ�Ʈ�ʵ� �̺�Ʈ ���
		bVideoInsert.addActionListener( this );
		bVideoModify.addActionListener( this );
		bVideoDelete.addActionListener( this );
		tfVideoSearch.addActionListener( this );
		cbMultiInsert.addActionListener(this);
		
		
		//JTable�� ���콺 Ŭ�� �̺�Ʈ
		tableVideo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableVideo.getSelectedRow();
				int col = 0;
				int vNum = (Integer)tableVideo.getValueAt(row, col);
				VideoVO vo = new VideoVO();
				try {
					vo = model.selectByPk(vNum);
				}catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "���� �˻� ���� : " + ex.getMessage());
				}
				
				// VO�� ������ ���� ���� ȭ�鿡 ���
				tfVideoNum.setText(String.valueOf(vo.getVideoNum()));
				comVideoJanre.setSelectedItem(vo.getVideoJanre());
				tfVideoTitle.setText(vo.getVideoTitle());
				tfVideoDirector.setText(vo.getVideoDirector());
				tfVideoActor.setText(vo.getVideoActor());
				taVideoContent.setText(vo.getVideoContent());
				
			}
		} );
	}

	public void actionPerformed( ActionEvent ev){
		Object o = ev.getSource();
		// �԰�
		if( o == bVideoInsert )
		{
			/*
			 1. ȭ�鿡�� �Է°� ������
				( JTextField - getText() / JComboBox - getSelectedItem() )
			 2. �Է°��� VideoRecord�� ����ʵ忡 ���� ( setter �̿� )
				( janre / title / director / actor / content )
			 3. VideoRecord�� insertVideo() ȣ��
				( �԰��� ���ڷ� )
			 4. ȭ�� �ʱ�ȭ
			*/
			String janre = (String)comVideoJanre.getSelectedItem();
			String title = tfVideoTitle.getText();
			String director = tfVideoDirector.getText();
			String actor = tfVideoActor.getText();
			String exp = taVideoContent.getText();
			
			VideoVO vo = new VideoVO();
			vo.setVideoJanre(janre);
			vo.setVideoTitle(title);;
			vo.setVideoActor(actor);
			vo.setVideoDirector(director);
			vo.setVideoContent(exp);
						
			try {
				int count = Integer.parseInt(tfInsertCount.getText());   // �԰� ���� �ޱ�
				model.insert(vo, count);
				System.out.println("�԰� ����");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "�԰� ���� : " + e.getMessage());
			}
			clearScreen();
		// �����԰� üũ�ڽ�
		}else if(o == cbMultiInsert) {
			if(cbMultiInsert.isSelected()){
				tfInsertCount.setEditable(true);  // �ؽ�Ʈ �ʵ� �Է°����ϵ���!!
			}else {
				tfInsertCount.setEditable(false);
			}

		}else if ( o == bVideoModify )
		{
			
		}else if ( o == bVideoDelete )
		{

		}else if ( o == tfVideoSearch )
		{
			/*
			 1. �޺��ڽ��� ������ ���� �ؽ�Ʈ�ʵ忡�� �Է��� �� �о����
				( comVideoSearch , tfVideoSearch )
			 2. VideoRecord�� videoSelect() ȣ��� ���� ���� ���ڷ� ����
			 3. 2�� ���(Vector)�� �޾� TableModel�� data�� ����
			 4. Table�� TableModel�� �ٽ� ����
			 5. TableModel���� Table�� ����Ÿ ����˸���
			*/
			selectTable();
		}
	};

void clearScreen()
{
	tfVideoActor.setText("");
	tfVideoDirector.setText("");
	tfInsertCount.setText("1");
	tfVideoTitle.setText("");
	taVideoContent.setText("");
	comVideoJanre.setSelectedItem("����");
	tfVideoNum.setText("1");
}

void selectTable() {
	int sel = comVideoSearch.getSelectedIndex();
	String text = tfVideoSearch.getText();
	
	try {
		ArrayList list = model.selectVideo(sel, text);
		tmVideo.data = list;
		tableVideo.setModel(tmVideo);
		tmVideo.fireTableDataChanged();
		
		
	}catch (Exception e) {
		// TODO: handle exception
		JOptionPane.showMessageDialog(null, "�˻� ���� : " + e.getMessage());
	}
}


class VideoTableModel extends AbstractTableModel { 
  
ArrayList data = new ArrayList();
String [] columnNames = {"������ȣ", "��������","��	��","��	 ��", "��	��", "�����" };

//=============================================================
// 1. �⺻���� TabelModel  �����
// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
// AbstractTabelModel���� �������� �ʾұ⿡...
// �ݵ�� ����� ���� �ʼ�!!!!

    public int getColumnCount() { 
        return columnNames.length; 
    } 
     
    public int getRowCount() { 
        return data.size(); 
    } 

    public Object getValueAt(int row, int col) { 
    	ArrayList temp = (ArrayList)data.get( row );
        return temp.get( col ); 
    }

//===============================================================
// 2. ������ �÷������� ��ȯ�ϱ�
//
//		�⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
	 public String getColumnName(int col) { 
        return columnNames[col]; 
    } 
}
}


