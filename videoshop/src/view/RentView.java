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
   //    ������ �Լ�
   public RentView(){
     
      newObject(); //��ü�����޼ҵ�
      addLayour(); //ȭ�鱸�� �޼ҵ�
      //setStyle(); //ȭ�� �ٹ̴� �޼ҵ�
      eventProc(); //�̺�Ʈ ��� �޼ҵ�
      try{
         model = new RentModel();
         System.out.println("�뿩 ��� ���� ����");
         selectTable();
         
      }
      catch(Exception ex){ //���� ������
         JOptionPane.showMessageDialog(null, "�뿩 DB���� ����"+ex.getMessage());
      }
   }
   
   public void newObject(){//��ü����
      tfRentTel = new JTextField();
      tfRentCustName = new JTextField();
      tfRentVideoNum = new JTextField();
      tfReturnVideoNum = new JTextField(10);
      bRent = new JButton("�뿩");
      bRetun = new JButton("�ݳ�");
      
      tmRent = new RentTabelModel();
      tableRecentList = new JTable(tmRent);
      }
   public void addLayour(){
      
      setLayout(new BorderLayout());
      //���� ����
      JPanel pUP = new JPanel();
         //������ ����
         JPanel pLeft = new JPanel();
         pLeft.setBorder(new TitledBorder("�뿩"));
         pLeft.setLayout(new GridLayout(4,2));
         pLeft.add(new JLabel("��ȭ��ȣ"));
         pLeft.add(tfRentTel);
         pLeft.add(new JLabel("�� �� ��"));
         pLeft.add(tfRentCustName);
         pLeft.add(new JLabel("���� ��ȣ"));
         pLeft.add(tfRentVideoNum);
         pLeft.add(bRent);
         
         
         
         // ���� ������
         JPanel pRight = new JPanel();
         pRight.setBorder(new TitledBorder("�ݳ�")); //Ÿ��Ʋ
         pRight.add(new JLabel ("���� ��ȣ"));
         pRight.add(tfReturnVideoNum);
         pRight.add(bRetun);
         
         
         //������ ���� ���̱� 
         pUP.setLayout(new GridLayout(1,2));
         pUP.add(pLeft);
         pUP.add(pRight);
         
         add(pUP, BorderLayout.NORTH); // pUP
         
      add(new JScrollPane(tableRecentList), BorderLayout.CENTER);

 
      
      //eventProc();//�̺�Ʈ�����ֱ�
   }

   public void eventProc(){ //�̺�Ʈ ���̱� 
      BtnEvent evt = new BtnEvent();
      

      //�̺�Ʈ ���� �̺�Ʈ �ڵ鷯 ��ü�� ����
      bRent.addActionListener(evt);
      bRetun.addActionListener(evt);
      tfRentTel.addActionListener(evt);
      //���̺� ���ڵ� Ŭ���ϸ� �ش� ���ڵ��� ���� ��ȣ�� �ݳ� ���� ��ȣ�� ���
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
         // �뿩 ��ư�� ������ ��
         if( evt == bRent) {
            // 1. ȭ���� �Է°����� ������
            String tel = tfRentTel.getText();
            String name = tfRentCustName.getText();
            int num = Integer.parseInt(tfRentVideoNum.getText());
            
            RentVO vo = new RentVO();
            
            vo.setRentCustName(tel);
            vo.setRentCustName(name);
            vo.setRentNo(num);
            
            // 2. �𵨴� �޼ҵ� ȣ��
            //tfRentTel, tfRentCustName,tfRentVideoNum
            try {
            model.videoRent(tel,name, num);
            selectTable();
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
            
         // �ݳ� ��ư�� ������ ��   
         } else if( evt == bRetun) {
            int num = Integer.parseInt(tfReturnVideoNum.getText());
            try {
               boolean returnposs = model.isRentPossible(num);
               //�̹� �ݳ� �Ǿ� �ִ� ���� �ݳ� �Ұ�
            if(returnposs) {
               try {
               model.videoReturn(num);
               System.out.println("�ݳ�����");
               selectTable();
               //clearLayout();
               } catch (Exception e2) {
                  // TODO: handle exception
                  JOptionPane.showMessageDialog(null, "�뿩���� �ƴϰų� �̹� �ݳ��Ǿ����ϴ�."+e2.getMessage());
               }
            }
            System.out.println("�ݳ��Ϸ�");
         } catch (Exception e2) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "�ݳ�����"+e2.getMessage());
         }
            
         // ��ȭ��ȣ �ؽ�Ʈ�ʵ忡�� �������� ��   
         } else if( evt == tfRentTel) {
            String tel = tfRentTel.getText();

            String name = null;
            try {
           
               name = model.searchByTel(tel);
               selectTable();
               
               tfRentCustName.setText(name);
              
              
            }catch (Exception e1) {
            // TODO: handle exception
               JOptionPane.showMessageDialog(null, "�̸� �˻� ���� : "+ e1.getMessage());
         }
            
         }
      }
   }
    class RentTabelModel extends AbstractTableModel 
    //AbstractTableModel �߻�Ŭ���� ���� �������̵� �ؾ��� �������̵��� ��Ŭ������ ������ 
    {
       ArrayList data = new ArrayList();
      String [] title={"���� ��ȣ","��������", "����", "��ȭ��ȣ","�ݳ�������", "�ݳ�����"};
      
      public int getColumnCount() {
         
         return title.length;
      }
      
      public int getRowCount() {
         
         return data.size();
      }

      public Object getValueAt(int row, int col) {
         //�� �ϳ��� ����� �޼ҵ�
         ArrayList temp = (ArrayList)data.get(row);
         //get�� ������Ʈ������ ��ȯ���༭ ���� ����ȭ �ʿ�
         
         return temp.get(col);
      
      }
      public String getColumnName(int col){
         return title[col];
      }
      void clearLayout() {//ȭ�� �ʱ�ȭ
         
      }
      
    }  void selectTable() {
        try {
         ArrayList list = model.recentList();
         tmRent.data=list;
         //tableRecentList.setModel(tmRent);
         tmRent.fireTableDataChanged();
      } catch (Exception ex) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "���̺� �߷� ���� : "+ ex.getMessage());
         
      }
     }
}