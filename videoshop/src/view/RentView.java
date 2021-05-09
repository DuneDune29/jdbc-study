package  view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.RentModel;
import model.rec.RentVO;

public class RentView extends JPanel 
{
//   member field
   JTextField   tfRentTel, tfRentCustName,tfRentVideoNum,tfReturnVideoNum;

   JButton      bRent,bRetun;

   JTable      tableRecentList;
   RentTabelModel tmRent;
   RentModel model;
   //==============================================
   //    생성자 함수
   public RentView(){
     
      newObject(); //객체생성메소드
      addLayour(); //화면구성 메소드
      //setStyle(); //화면 꾸미는 메소드
      eventProc(); //이벤트 등록 메소드
      try{
         model = new RentModel();
         System.out.println("대여 디비 연결 성공");
         selectTable();
         
      }
      catch(Exception ex){ //실패 했을시
         JOptionPane.showMessageDialog(null, "대여 DB연결 실패"+ex.getMessage());
      }
   }
   
   public void newObject(){//객체생성
      tfRentTel = new JTextField();
      tfRentCustName = new JTextField();
      tfRentVideoNum = new JTextField();
      tfReturnVideoNum = new JTextField(10);
      bRent = new JButton("대여");
      bRetun = new JButton("반납");
      
      tmRent = new RentTabelModel();
      tableRecentList = new JTable(tmRent);
      }
   public void addLayour(){
      
      setLayout(new BorderLayout());
      //위쪽 영역
      JPanel pUP = new JPanel();
         //위쪽의 왼쪽
         JPanel pLeft = new JPanel();
         pLeft.setBorder(new TitledBorder("대여"));
         pLeft.setLayout(new GridLayout(4,2));
         pLeft.add(new JLabel("전화번호"));
         pLeft.add(tfRentTel);
         pLeft.add(new JLabel("고 객 명"));
         pLeft.add(tfRentCustName);
         pLeft.add(new JLabel("비디오 번호"));
         pLeft.add(tfRentVideoNum);
         pLeft.add(bRent);
         
         
         
         // 위쪽 오른쪽
         JPanel pRight = new JPanel();
         pRight.setBorder(new TitledBorder("반납")); //타이틀
         pRight.add(new JLabel ("비디오 번호"));
         pRight.add(tfReturnVideoNum);
         pRight.add(bRetun);
         
         
         //오른쪽 왼쪽 붙이기 
         pUP.setLayout(new GridLayout(1,2));
         pUP.add(pLeft);
         pUP.add(pRight);
         
         add(pUP, BorderLayout.NORTH); // pUP
         
      add(new JScrollPane(tableRecentList), BorderLayout.CENTER);

 
      
      //eventProc();//이벤트보여주기
   }

   public void eventProc(){ //이벤트 붙이기 
      BtnEvent evt = new BtnEvent();
      

      //이벤트 대상과 이벤트 핸들러 객체와 연결
      bRent.addActionListener(evt);
      bRetun.addActionListener(evt);
      tfRentTel.addActionListener(evt);
      //테이블 레코드 클릭하며 해당 레코드의 비디오 번호가 반납 비디오 번호로 출력
      tableRecentList.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e) {
           int row = tableRecentList.getSelectedRow();
           int col = 0;
           String videono = String.valueOf(tableRecentList.getValueAt(row, col));
           tfReturnVideoNum.setText(videono);
        }
      });
      
   }

   class BtnEvent implements ActionListener{
      public void actionPerformed(ActionEvent e){
         Object evt = e.getSource();
         // 대여 버튼이 눌렸을 때
         if( evt == bRent) {
            // 1. 화면의 입력값들을 얻어오기
            String tel = tfRentTel.getText();
            String name = tfRentCustName.getText();
            int num = Integer.parseInt(tfRentVideoNum.getText());
            
            RentVO vo = new RentVO();
            
            vo.setRentCustName(tel);
            vo.setRentCustName(name);
            vo.setRentNo(num);
            
            // 2. 모델단 메소드 호출
            //tfRentTel, tfRentCustName,tfRentVideoNum
            try {
            model.videoRent(tel,name, num);
            selectTable();
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
            
         // 반납 버튼이 눌렸을 때   
         } else if( evt == bRetun) {
            int num = Integer.parseInt(tfReturnVideoNum.getText());
            try {
               boolean returnposs = model.isRentPossible(num);
               //이미 반납 되어 있는 경우는 반납 불가
            if(returnposs) {
               try {
               model.videoReturn(num);
               System.out.println("반납성공");
               selectTable();
               //clearLayout();
               } catch (Exception e2) {
                  // TODO: handle exception
                  JOptionPane.showMessageDialog(null, "대여중이 아니거나 이미 반납되었습니다."+e2.getMessage());
               }
            }
            System.out.println("반납완료");
         } catch (Exception e2) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "반납실패"+e2.getMessage());
         }
            
         // 전화번호 텍스트필드에서 엔터쳤을 때   
         } else if( evt == tfRentTel) {
            String tel = tfRentTel.getText();

            String name = null;
            try {
           
               name = model.searchByTel(tel);
               selectTable();
               
               tfRentCustName.setText(name);
              
              
            }catch (Exception e1) {
            // TODO: handle exception
               JOptionPane.showMessageDialog(null, "이름 검색 실패 : "+ e1.getMessage());
         }
            
         }
      }
   }
    class RentTabelModel extends AbstractTableModel 
    //AbstractTableModel 추상클래스 여서 오버라이딩 해야함 오버라이딩은 이클립스의 힘으로 
    {
       ArrayList data = new ArrayList();
      String [] title={"비디오 번호","비디오제목", "고객명", "전화번호","반납예정일", "반납여부"};
      
      public int getColumnCount() {
         
         return title.length;
      }
      
      public int getRowCount() {
         
         return data.size();
      }

      public Object getValueAt(int row, int col) {
         //값 하나씩 얻어노는 메소드
         ArrayList temp = (ArrayList)data.get(row);
         //get은 오브젝트형으로 변환해줘서 강제 형변화 필요
         
         return temp.get(col);
      
      }
      public String getColumnName(int col){
         return title[col];
      }
      void clearLayout() {//화면 초기화
         
      }
      
    }  void selectTable() {
        try {
         ArrayList list = model.recentList();
         tmRent.data=list;
         //tableRecentList.setModel(tmRent);
         tmRent.fireTableDataChanged();
      } catch (Exception ex) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "데이블 추력 실패 : "+ ex.getMessage());
         
      }
     }
}