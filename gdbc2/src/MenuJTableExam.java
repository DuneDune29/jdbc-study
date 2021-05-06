import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MenuJTableExam extends JFrame implements ActionListener {
	JMenu m = new JMenu("관리");
	JMenuItem insert = new JMenuItem("가입");
	JMenuItem update = new JMenuItem("수정");
	JMenuItem delete = new JMenuItem("삭제");
	JMenuItem quit = new JMenuItem("종료");
	JMenuBar mb = new JMenuBar();
	String[] name = { "id", "name", "age", "addr" };
	
	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel();
	String[] comboName = { "ALL", "ID", "name", "addr" };

	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("검색");
	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	
	public MenuJTableExam() {
		super("GUI 회원관리");
		m.add(insert); 			m.add(update); 			m.add(delete);
		m.add(quit); 			mb.add(m); 				setJMenuBar(mb);
		p.setBackground(Color.yellow);
		p.add(combo); 			p.add(jtf);		 		p.add(search);
		add(jsp, "Center"); 	add(p, "South");
		setSize(500, 400); 		setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		search.addActionListener(this);
		// 모든 레코드를 검색하여 DefaultTableModel에 올리기
		dao.userSelectAll(dt);
		// 첫번째 행 선택
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}
	public static void main(String[] args) {
		new MenuJTableExam();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new UserJDialogGUI(this, "가입");
		} else if (e.getSource() == update) {
			new UserJDialogGUI(this, "수정");
		} else if (e.getSource() == delete) {
			// 현재 Jtable의 선택된 행과 열의 값을 얻어오기
			int row = jt.getSelectedRow();
			System.out.println("선택 행 : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : " + obj);
			if (dao.userDelete(obj.toString()) > 0) {
				UserJDialogGUI.messageBox(this, "레코드 삭제");
				// 리스트 갱신
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDialogGUI.messageBox(this, "레코드 삭제되지 않음");
			}
		} else if (e.getSource() == quit) {
			System.exit(0);
		} else if (e.getSource() == search) {
			// JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 : " + fieldName);
			if (fieldName.trim().equals("ALL")) {
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					UserJDialogGUI.messageBox(this, "검색어 입력!");
					jtf.requestFocus();
				} else {
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
				}
			}
		}
	}	
}