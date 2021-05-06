import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JTableTest extends JFrame implements ActionListener{
	JPanel panWest;
	JPanel panSouth;
	JPanel p1, p2, p3, p4, p5;
	JTextField txtNo, txtName, txtEmail, txtTel;
	JButton btnTotal, btnAdd, btnDel, btnSearch, btnCancel;
	JTable table; // 검색, 전체보기를 위한 테이블 객체 생성
	private static final int NONE = 0;
	private static final int ADD = 1;
	private static final int DELETE = 2;
	private static final int SEARCH = 3;
	private static final int TOTAL = 4;
	int cmd = NONE; 	
	Connection con;
	Statement stmt;
	PreparedStatement pstmtInsert;
	PreparedStatement pstmtDelete;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "lion";
	private String pwd = "1234";
	private String sqlInsert = "insert into customer values(?,?,?,?)";
	private String sqlDelete = "delete from customer where name=?";
	MyModel model;
	PreparedStatement pstmtTotal, pstmtTotalScroll;
	PreparedStatement pstmtSearch, pstmtSearchScroll;
	private String sqlTotal = "select * from customer";
	private String sqlSearch = "select * from customer where name=?";
	
	public JTableTest() {
		panWest = new JPanel(new GridLayout(5, 0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("번 호"));
		p1.add(txtNo = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("이 름"));
		p2.add(txtName = new JTextField(12));
		panWest.add(p2);
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("이 메 일"));
		p3.add(txtEmail = new JTextField(12));
		panWest.add(p3);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(new JLabel("전화번호"));
		p4.add(txtTel = new JTextField(12));
		panWest.add(p4);
		p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5.add(new JLabel(""));
		panWest.add(p5);
		add(panWest, "West");
		panSouth = new JPanel();
		panSouth.add(btnTotal = new JButton("전체보기"));
		panSouth.add(btnAdd = new JButton("추   가"));
		panSouth.add(btnDel = new JButton("삭   제"));
		panSouth.add(btnSearch = new JButton("검   색"));
		panSouth.add(btnCancel = new JButton("취   소"));
		add(panSouth, "South");
		btnTotal.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnSearch.addActionListener(this);
		btnCancel.addActionListener(this);
		// 빈 테이블 객체 생성
		add(new JScrollPane(table = new JTable()), "Center");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setVisible(true);
		dbConnect();
	}
	private void dbConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			stmt = con.createStatement();
			pstmtInsert = con.prepareStatement(sqlInsert);
			pstmtDelete = con.prepareStatement(sqlDelete);
			pstmtTotalScroll = con.prepareStatement(sqlTotal,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmtSearchScroll = con.prepareStatement(sqlSearch,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmtTotal = con.prepareStatement(sqlTotal);
			pstmtSearch = con.prepareStatement(sqlSearch);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == btnAdd) {
			if (cmd != ADD) {
				setText(ADD);
				return;
			}
			setTitle(ae.getActionCommand());
			add();
		} else if (obj == btnDel) {
			if (cmd != DELETE) {
				setText(DELETE);
				return;
			}
			setTitle(ae.getActionCommand());
			del();
		} else if (obj == btnSearch) {
			if (cmd != SEARCH) {
				setText(SEARCH);
				return;
			}
			setTitle(ae.getActionCommand());
			search();
		} else if (obj == btnTotal) {
			setTitle(ae.getActionCommand());
			total();
		}
		setText(NONE);
		init();
	}
	private void init() {
		txtNo.setText("");
		txtNo.setEditable(false);
		txtName.setText("");
		txtName.setEditable(false);
		txtEmail.setText("");
		txtEmail.setEditable(false);
		txtTel.setText("");
		txtTel.setEditable(false);
	}
	private void setText(int command) {
		switch (command) {
		case ADD:
			txtNo.setEditable(true);
			txtName.setEditable(true);
			txtEmail.setEditable(true);
			txtTel.setEditable(true);
			break;
		case DELETE: 	case SEARCH:	
			txtName.setEditable(true);
			break;
		}
		setButton(command);
	}
	private void setButton(int command) {
		// 취소버튼을 제외하고 어떤 버튼이 눌리더라도 모든 버튼 비활성화
		btnTotal.setEnabled(false);
		btnAdd.setEnabled(false);
		btnDel.setEnabled(false);
		btnSearch.setEnabled(false);
		switch (command) {
		case ADD:
			btnAdd.setEnabled(true);
			cmd = ADD;
			break;
		case DELETE:
			btnDel.setEnabled(true);
			cmd = DELETE;
			break;
		case SEARCH:
			btnSearch.setEnabled(true);
			cmd = SEARCH;
			break;
		case TOTAL:
			btnTotal.setEnabled(true);
			cmd = TOTAL;
			break;
		case NONE:
			btnAdd.setEnabled(true);
			btnDel.setEnabled(true);
			btnSearch.setEnabled(true);
			btnTotal.setEnabled(true);
			btnCancel.setEnabled(true);
			cmd = NONE;
		}
	}		
	public static void main(String[] args) {
		new JTableTest();
	}
	private void add() {
		try {
			String strNo = txtNo.getText();
			String strName = txtName.getText();
			String strEmail = txtEmail.getText();
			String strTel = txtTel.getText();
			if (strNo.length() < 1 || strName.length() < 1) {
				JOptionPane.showMessageDialog(null,
						"번호와 이름은 필수 입력란입니다.");
				return;
			}
			pstmtInsert.setInt(1, Integer.parseInt(strNo));
			pstmtInsert.setString(2, strName);
			pstmtInsert.setString(3, strEmail);
			pstmtInsert.setString(4, strTel);
			pstmtInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "추가 성공");
	}
	private void del() {
		try {
			String strName = txtName.getText();
			if (strName.length() < 1) {
				JOptionPane.showMessageDialog(null, "이름은 필수 입력란입니다.");
				return;
			}
			pstmtDelete.setString(1, strName);
			pstmtDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "삭제 성공");
	}
	private void total() {
		try {
			ResultSet rsScroll = pstmtTotalScroll.executeQuery();
			ResultSet rs = pstmtTotal.executeQuery();
			if (model == null)
				model = new MyModel();
			model.getRowCount(rsScroll);
			model.setData(rs);
			table.setModel(model);
			table.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void search() {
		String strName = txtName.getText();
		if (strName.length() < 1) {
			JOptionPane.showMessageDialog(null, "이름은 필수 입력란입니다.");
			return;
		}
		try { 
			pstmtSearchScroll.setString(1, strName);
			ResultSet rsScroll = pstmtSearchScroll.executeQuery();
			pstmtSearch.setString(1, strName);
			ResultSet rs = pstmtSearch.executeQuery();
			if (model == null)
				model = new MyModel();
			model.getRowCount(rsScroll);
			model.setData(rs);
			table.setModel(model);
			table.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}