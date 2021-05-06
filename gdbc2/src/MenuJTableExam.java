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
	JMenu m = new JMenu("����");
	JMenuItem insert = new JMenuItem("����");
	JMenuItem update = new JMenuItem("����");
	JMenuItem delete = new JMenuItem("����");
	JMenuItem quit = new JMenuItem("����");
	JMenuBar mb = new JMenuBar();
	String[] name = { "id", "name", "age", "addr" };
	
	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel();
	String[] comboName = { "ALL", "ID", "name", "addr" };

	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton search = new JButton("�˻�");
	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	
	public MenuJTableExam() {
		super("GUI ȸ������");
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
		// ��� ���ڵ带 �˻��Ͽ� DefaultTableModel�� �ø���
		dao.userSelectAll(dt);
		// ù��° �� ����
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}
	public static void main(String[] args) {
		new MenuJTableExam();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new UserJDialogGUI(this, "����");
		} else if (e.getSource() == update) {
			new UserJDialogGUI(this, "����");
		} else if (e.getSource() == delete) {
			// ���� Jtable�� ���õ� ��� ���� ���� ������
			int row = jt.getSelectedRow();
			System.out.println("���� �� : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�� : " + obj);
			if (dao.userDelete(obj.toString()) > 0) {
				UserJDialogGUI.messageBox(this, "���ڵ� ����");
				// ����Ʈ ����
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDialogGUI.messageBox(this, "���ڵ� �������� ����");
			}
		} else if (e.getSource() == quit) {
			System.exit(0);
		} else if (e.getSource() == search) {
			// JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("�ʵ�� : " + fieldName);
			if (fieldName.trim().equals("ALL")) {
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					UserJDialogGUI.messageBox(this, "�˻��� �Է�!");
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