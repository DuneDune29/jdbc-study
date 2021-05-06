import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserJDialogGUI extends JDialog implements ActionListener {
	JPanel pw = new JPanel(new GridLayout(4, 1));
	JPanel pc = new JPanel(new GridLayout(4, 1));
	JPanel ps = new JPanel();
	JLabel lable_Id = new JLabel("ID");
	JLabel lable_Name = new JLabel("�̸�");
	JLabel lable_Age = new JLabel("����");
	JLabel lable_Addr = new JLabel("�ּ�");
	JTextField id = new JTextField();
	JTextField name = new JTextField();
	JTextField age = new JTextField();
	JTextField addr = new JTextField();
	JButton confirm;
	JButton reset = new JButton("���");
	MenuJTableExam me;
	JPanel idCkP = new JPanel(new BorderLayout());
	JButton idCkBtn = new JButton("IDCheck");
	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	
	public UserJDialogGUI(MenuJTableExam me, String index) {
		super(me, "���̾�α�"); 		this.me = me;
		if (index.equals("����")) {
			confirm = new JButton(index);
		} else {
			confirm = new JButton("����");
			// text �ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = me.jt.getSelectedRow();
			id.setText(me.jt.getValueAt(row, 0).toString());
			name.setText(me.jt.getValueAt(row, 1).toString());
			age.setText(me.jt.getValueAt(row, 2).toString());
			addr.setText(me.jt.getValueAt(row, 3).toString());
			id.setEditable(false); 			idCkBtn.setEnabled(false);
		}
		pw.add(lable_Id); 				pw.add(lable_Name);  
		pw.add(lable_Age); 				pw.add(lable_Addr);		
		idCkP.add(id, "Center"); 		idCkP.add(idCkBtn, "East");
		pc.add(idCkP); 				pc.add(name); 					pc.add(age);
		pc.add(addr); 				pc.add(confirm); 				pc.add(reset);
		add(pw, "West"); 			add(pc, "Center"); 				add(ps, "South");
		setSize(300, 250); 			setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		confirm.addActionListener(this);
		reset.addActionListener(this);
		idCkBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		if (btnLabel.equals("����")) {
			if (dao.userListInsert(this) > 0) {
				messageBox(this, name.getText() + "�� ���ԵǾ����ϴ�.");
				dispose();
				// ��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0); // ù ��° �� ����
			} else {
				messageBox(this, "���Ե��� �ʾҽ��ϴ�.");
			}
		} else if (btnLabel.equals("����")) {
			if (dao.userUpdate(this) > 0) {
				messageBox(this, "���� �Ϸ�");
				dispose();
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "���� Fail!");
			}
		} else if (btnLabel.equals("���")) {
			dispose();
		} else if (btnLabel.equals("IDCheck")) {
			if (id.getText().trim().equals("")) {
				messageBox(this, "ID �Է�");
				id.requestFocus();
			} else {
				if (dao.getIdByCheck(id.getText())) {
					messageBox(this, id.getText() + "�� ��밡��");
				} else {
					messageBox(this, id.getText() + "�� �ߺ�!");
					id.setText("");
					id.requestFocus();
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}