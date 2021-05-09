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
			System.out.println("비디오 디비 연결 성공");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "DB연결 실패 : " + ex.getMessage());
		}
	}

	//##############################################
	// 멤버필드 객체 생성
	void	newObject(){
		tfVideoNum			= new JTextField(15);
		tfVideoTitle		= new JTextField(15);
		tfVideoDirector		= new JTextField(15);
		tfVideoActor		= new JTextField(15);
		String janreText[]	= {"에로","코믹","드라마","공포","애니메이션" };
		comVideoJanre		= new JComboBox(janreText);
		taVideoContent		= new JTextArea( 15, 3 );

		cbMultiInsert		= new JCheckBox(" 다중입고 ");
		tfInsertCount		= new JTextField("1", 15);
		bVideoInsert		= new JButton("	입	고	");
		bVideoModify		= new JButton("	수	정	");
		bVideoDelete		= new JButton("	삭	제	");

		String	searchText[]= {"제목", "감독" , "배우" };
		comVideoSearch		= new JComboBox(searchText );
		tfVideoSearch		= new JTextField(15);

		tmVideo				= new VideoTableModel();
		tableVideo			= new JTable( tmVideo );
	}
	
	//##############################################
	//	GUI 구성하기 위해 Layout에 붙이기
	void	addLayout(){
		//-----------------------------------------
		// west 영역 : 비디오 입력 및 수정
		JPanel	pWest	= new JPanel();
			// 비디오 정보입력하는 부분
			JPanel	pWestNorth		= new JPanel();
					JPanel	pWestNorthUp	= new JPanel();
					pWestNorthUp.setLayout( new GridLayout( 5, 2 ));
					pWestNorthUp.add(	new JLabel(" 비디오 번호 " ));
					pWestNorthUp.add( tfVideoNum );
					pWestNorthUp.add(	new JLabel(" 장		르 " ));
					pWestNorthUp.add( comVideoJanre );
					pWestNorthUp.add(	new JLabel(" 제		목 " ));
					pWestNorthUp.add( tfVideoTitle );
					pWestNorthUp.add(	new JLabel(" 감		독 " ));
					pWestNorthUp.add( tfVideoDirector );
					pWestNorthUp.add(	new JLabel(" 배		우 " ));
					pWestNorthUp.add( tfVideoActor );

					JPanel	pWestNorthDown	= new JPanel();
					pWestNorthDown.setLayout( new BorderLayout() );
					pWestNorthDown.add("West",	new JLabel("  설   명 " ) );
					pWestNorthDown.add("Center", taVideoContent );

					pWestNorth.setBorder( new TitledBorder(" 비디오 정보 입력 " ));

			pWestNorth.setLayout( new BorderLayout() );
			pWestNorth.add("Center", pWestNorthUp );
			pWestNorth.add("South",  pWestNorthDown );

			// 비디오 정보 입력/수정 버튼 부분
			JPanel	pWestSouth	= new JPanel();
					JPanel	pWestSouthUp	= new JPanel();
					pWestSouthUp.add( cbMultiInsert );
					pWestSouthUp.add( tfInsertCount);
					pWestSouthUp.add( new JLabel("	개	" ));
					pWestSouthUp.setBorder( new TitledBorder(" 다중입력시 선택하시오 " ));

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
		// east 영역 : 비디오 검색부분
		JPanel	pEast		= new JPanel();
			JPanel	pEastNorth	= new JPanel();
			pEastNorth.add( comVideoSearch );
			pEastNorth.add( tfVideoSearch );
			pEastNorth.setBorder( new TitledBorder(" 비디오 검색 " ) );

			JPanel	pEastCenter = new JPanel();
			pEastCenter.setLayout( new BorderLayout() );
			pEastCenter.add("Center", new JScrollPane( tableVideo) );

		pEast.setLayout( new BorderLayout() );
		pEast.add("North",  pEastNorth);
		pEast.add("Center", pEastCenter);


		//-----------------------------------------
		//	전체 붙이기
		setLayout( new GridLayout( 1, 2 ) );
		add( pWest );
		add( pEast );
	}

	//####################################################
	//	화면에 필요한 스타일 지정
	void setStyle(){

		tfInsertCount.setText("1");
		// 텍스트필드 편집가능하지 않도록 지정
		tfVideoNum.setEditable( false );
		tfInsertCount.setEditable( false );
		
		// 입고량 지정하는 텍스트필드의 글자 정렬 ( 오른쪽으로 )
		tfInsertCount.setHorizontalAlignment(JTextField.RIGHT);


	}

	//####################################################
	//	이벤트 등록 및 관련
	void eventProc(){
		// 버튼과 텍스트필드 이벤트 등록
		bVideoInsert.addActionListener( this );
		bVideoModify.addActionListener( this );
		bVideoDelete.addActionListener( this );
		tfVideoSearch.addActionListener( this );
		cbMultiInsert.addActionListener(this);
		
		
		//JTable에 마우스 클릭 이벤트
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
					JOptionPane.showMessageDialog(null, "비디오 검색 실패 : " + ex.getMessage());
				}
				
				// VO의 각각의 값을 왼쪽 화면에 출력
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
		// 입고
		if( o == bVideoInsert )
		{
			/*
			 1. 화면에서 입력값 얻어오기
				( JTextField - getText() / JComboBox - getSelectedItem() )
			 2. 입력값을 VideoRecord의 멤버필드에 지정 ( setter 이용 )
				( janre / title / director / actor / content )
			 3. VideoRecord의 insertVideo() 호출
				( 입고량을 인자로 )
			 4. 화면 초기화
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
				int count = Integer.parseInt(tfInsertCount.getText());   // 입고 개수 받기
				model.insert(vo, count);
				System.out.println("입고 성공");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "입고 실패 : " + e.getMessage());
			}
			clearScreen();
		// 다중입고 체크박스
		}else if(o == cbMultiInsert) {
			if(cbMultiInsert.isSelected()){
				tfInsertCount.setEditable(true);  // 텍스트 필드 입력가능하도록!!
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
			 1. 콤보박스에 선택한 값과 텍스트필드에서 입력한 값 읽어오기
				( comVideoSearch , tfVideoSearch )
			 2. VideoRecord의 videoSelect() 호출시 위의 값을 인자로 전송
			 3. 2의 결과(Vector)을 받아 TableModel의 data로 지정
			 4. Table에 TableModel을 다시 지정
			 5. TableModel에서 Table로 데이타 변경알리기
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
	comVideoJanre.setSelectedItem("에로");
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
		JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
	}
}


class VideoTableModel extends AbstractTableModel { 
  
ArrayList data = new ArrayList();
String [] columnNames = {"비디오번호", "비디오제목","장	르","감	 독", "배	우", "등록일" };

//=============================================================
// 1. 기본적인 TabelModel  만들기
// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
// AbstractTabelModel에서 구현되지 않았기에...
// 반드시 사용자 구현 필수!!!!

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
// 2. 지정된 컬럼명으로 변환하기
//
//		기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	 public String getColumnName(int col) { 
        return columnNames[col]; 
    } 
}
}


