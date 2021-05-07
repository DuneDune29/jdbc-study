import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class DBMain extends Frame implements ActionListener {
	Connection conn; 		Statement stmt; 		ResultSet rs;
	String sql; 			String id; 				String pass;
	int row; 				boolean flag = false;
	Login login; 			DBFrame submit;
	String data[][] = new String[0][7]; // 0�� addRow�� �� ������, 7�� colunm ����
	String title[] = { "ID", "�̸�", "����", "��", "��ȭ��ȣ", "�ּ�", "����" };
	
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable table = new JTable(model);
	JScrollPane pan= new JScrollPane(table);
	Panel south = new Panel();
	JButton show = new JButton("����"); 		JButton del = new JButton("����");
	JButton update = new JButton("����"); 	JButton select = new JButton("�˻�");
	MenuBar mb = new MenuBar();
	Menu menu = new Menu("������");
	MenuItem admin = new MenuItem("����������");
	MenuItem exit = new MenuItem("����");
	DBMain() {
		setLayout(new BorderLayout());
		south.setLayout(new GridLayout(1, 4, 10, 10));
		south.add(show); 			south.add(del);
		south.add(update); 			south.add(select);
		add("Center", pan); 		add("South", south);
		setSize(650, 300);
		addWindowListener(new WindowAdapter() {
			public void windowclosing(WindowEvent e) { System.exit(0); }
		});
		del.setEnabled(false); // �����ڸ� ���� �� �ֵ��� ����
		menu.add(admin); 			menu.add(exit);
		mb.add(menu); 				setMenuBar(mb);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					 "jdbc:oracle:thin:@localhost:1521:xe", "lion", "1234");
			stmt = conn.createStatement();
			System.out.println("DB ���� ����");
		} catch (Exception e) { System.out.println("DB ���� ����" + e); }
		login = new Login();  		submit = new DBFrame();
		eventUp();
	}
	public void loginCheck() {
		id = login.tf_id.getText().trim();
		pass = login.tf_pass.getText().trim();
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(login, "ID �Է�");
			login.tf_pass.setText(null);
			login.tf_id.requestFocus();
			return;
		} else if (pass.length() == 0) {
			JOptionPane.showMessageDialog(login, "��й�ȣ �Է�");
			login.tf_pass.requestFocus();
			return;
		}
		sql = "select pass from datadb where id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {
					JOptionPane.showMessageDialog(login, "ȯ���մϴ�.");
					login.setVisible(false);
					setVisible(true);
					rs.close();
					return;
				} else {
					JOptionPane.showMessageDialog(login,
							"��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					login.tf_pass.setText("");
					login.tf_pass.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(login, "�������� �ʴ� ���̵��Դϴ�.");
				login.tf_id.setText("");
				login.tf_pass.setText("");
				login.tf_id.requestFocus();
			}
		} catch (Exception e) { System.out.println("ID, pass �˻� ����" + e); }
	}
	public void insertProcess() {
		Data d = new Data();
		d.id = submit.tf_id.getText().trim();
		d.pass = submit.tf_pass1.getText().trim();
		d.name = submit.tf_name.getText().trim();
		d.jumin = submit.tf_jumin1.getText().trim()
				+ submit.tf_jumin2.getText().trim();
		d.tel = submit.tf_tel1.getText().trim()
				+ submit.tf_tel2.getText().trim()
				+ submit.tf_tel3.getText().trim();
		d.addr = submit.tf_addr.getText().trim();
		d.job = submit.job.getSelectedItem();
		if (!d.isFull()) {
			JOptionPane.showMessageDialog(submit, "������� Ȯ��");
			return;
		}
		if (!d.pass.equals(submit.tf_pass2.getText().trim())) {
			JOptionPane.showMessageDialog(submit, "PassWord �ٽ�Ȯ��");
			return;
		}
		try {
			sql = "insert into datadb values (' d ')";
			System.out.println("sql" + sql);
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(submit, "���� ���ԵǾ����ϴ�.");
			submit.setVisible(false);
			login.setVisible(true);
		} catch (Exception e) { System.out.println("DB �߰� ����" + e); }
	}
	public void showProcess() {
		try {
			sql = "select * from datadb"; 		rs = stmt.executeQuery(sql);
			String temp[] = new String[7]; 		String jumin;
			int gender, age, year;   			model.setRowCount(0);
			while (rs.next()) {
				temp[0] = rs.getString("id");
				temp[1] = rs.getString("name");
				jumin= rs.getString("jumin");
				year = Integer.parseInt(jumin.substring(0, 2));
				gender = jumin.charAt(7) - 48;
				age = gender >= 3 ? 2000 + year : 1900 + year;
				Calendar cal = new GregorianCalendar();
				age = cal.get(Calendar.YEAR) - age + 1;
				temp[2] = age + "";
				temp[3] = (gender % 2 == 1) ? "����" : "����";
				temp[4] = rs.getString("tel");
				temp[5] = rs.getString("addr");
				temp[6] = rs.getString("job");
				model.addRow(temp);
			}
		} catch (Exception e) { System.out.println("DB �˻� ����"); }
	}
	void deleteProcess() {
		String str = JOptionPane.showInputDialog(this, "������ ID �Է� : ");
		if (str == null) return;
		if (id.equals(str)) { // �α��ε� ID�� ������ �� ���� ����
			JOptionPane.showMessageDialog(this, "������ �� ���� ID�Դϴ�.");
			return;
		}
		if (checkID(str)) {
			JOptionPane.showMessageDialog(this, "�������� �ʴ� ID�Դϴ�.");
			return;
		} else {
			int t = JOptionPane.showConfirmDialog(this, "������ �����Ͻðڽ��ϱ�?",
					"���� ���", JOptionPane.YES_NO_OPTION);
			if (t == 1 || str.equals("")) return;
			sql = "delete from dataDB where id = '" + str + "'";
			try {
				stmt.executeUpdate(sql);
				for (int i = 0; i < model.getRowCount(); i++) {
					String temp = (String) model.getValueAt(i, 0);
					if (temp.equals(str)) model.removeRow(i);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "���� ����");
				return;
			}
			JOptionPane.showMessageDialog(this, "���� ����");
		}
	}
	public void eventUp() {
	login.newID.addActionListener(this);
	submit.submit.addActionListener(this);
	show.addActionListener(this);
	del.addActionListener(this);
	login.ok.addActionListener(this);
	submit.idCheck.addActionListener(this);
	update.addActionListener(this);
	admin.addActionListener(this);
	exit.addActionListener(this);
	
	}
	public boolean checkID(String id) {
		this.id = id;
		sql = "select id from datadb where id = '" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) 	return false;
		} catch (SQLException e) { System.out.println("ID �˻� ����!" + e); }
		return true;
	}
	public void updateShow() {
		String id = "";
		if (flag) { // ������
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "������ �����͸� �����ϼ���.");
				return;
			}
			id = (String) (model.getValueAt(row, 0));
		} else { // �Ϲ� �����
			id = login.tf_id.getText().trim();
		}
		sql = "select * from dataDB where id='" + id + "'";
		try {
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				JOptionPane.showMessageDialog(this, "�����Ͱ� �������� �ʽ��ϴ�.");
				return;
			}
			submit.tf_id.setText(rs.getString("id"));
			String pass = rs.getString("pass");
			submit.tf_pass1.setText(pass);
			submit.tf_pass2.setText(pass);
			submit.tf_name.setText(rs.getString("name"));
			String jumin = rs.getString("jumin");
			submit.tf_jumin1.setText(jumin.substring(0, 6));
			submit.tf_jumin2.setText(jumin.substring(7));
			String tel = rs.getString("tel");
			int index = tel.indexOf('-');
			if (index == -1) {
				submit.tf_tel1.setText(tel);
				submit.tf_tel2.setText(null);
				submit.tf_tel3.setText(null);
			} else {
				submit.tf_tel1.setText(tel.substring(0, index));
				tel = tel.substring(index + 1);
				index = tel.indexOf('-');
				if (index == -1) {
					submit.tf_tel2.setText(tel);
					submit.tf_tel3.setText(null);
				} else {
					submit.tf_tel2.setText(tel.substring(0, index));
					submit.tf_tel3.setText(tel.substring(index + 1));
				}
				submit.tf_addr.setText(rs.getString("addr"));
				submit.job.select(rs.getString("job"));
			}
		} catch (Exception e) { System.out.println("�˻�â ���� ����" + e); }
		submit.submit.setText("����"); 		submit.setVisible(true);
		submit.tf_id.setEnabled(false);
		submit.tf_name.setEnabled(false);
		submit.tf_jumin1.setEnabled(false);
		submit.tf_jumin2.setEnabled(false);
		submit.idCheck.setEnabled(false);
	}	
	public void updateProcess() {
		Data d = new Data();
		d.id = submit.tf_id.getText().trim();
		d.pass = submit.tf_pass1.getText().trim();
		d.tel = submit.tf_tel1.getText().trim() + "-"
				+ submit.tf_tel2.getText().trim() + "-"
				+ submit.tf_tel3.getText().trim();
		d.addr = submit.tf_addr.getText().trim();
		d.job = submit.job.getSelectedItem();
		if (!d.isFull2()) {
			JOptionPane.showMessageDialog(submit, "������� Ȯ��");
			return;
		}
		if (!d.pass.equals(submit.tf_pass2.getText().trim())) {
			JOptionPane.showMessageDialog(submit, "PassWord �ٽ� Ȯ��.");
			return;
		}
		try {
			sql = "update datadb set pass = '" + d.pass + "', tel='" + d.tel
					+ "', addr='" + d.addr + "', job='" + d.job
					+ "' where id='" + d.id + "'";
			System.out.println("sql" + sql);
			stmt.executeUpdate(sql);
			submit.setVisible(false);
			JOptionPane.showMessageDialog(this, "������ �����Ǿ����ϴ�.");
		} catch (SQLException ee) {
			System.out.println("���� ���� ���� : " + ee);
			ee.printStackTrace();
		}
		showProcess();
	}
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == login.newID) {
			login.setVisible(false); 			submit.setVisible(true);
		} else if (ob == submit.submit) {
			String la = submit.submit.getText();
			if (la.equals("   ��   ��   ")) {
				if (!checkID(id)) {
					JOptionPane.showMessageDialog(submit, "�̹� ��ϵ� ID�Դϴ�.");
					return;
				}
				insertProcess();					submit.tf_id.setText("");
				submit.tf_pass1.setText("");  		submit.tf_pass2.setText("");
				submit.tf_name.setText("");  		submit.tf_jumin1.setText("");
				submit.tf_jumin2.setText("");  		submit.tf_tel1.setText("");
				submit.tf_tel2.setText("");  		submit.tf_tel3.setText("");
				submit.tf_addr.setText("");
			} else {
				insertProcess();
			}
		} else if (ob == show) {
			showProcess();
		} else if (ob == del) {
			deleteProcess();
		} else if (ob == login.ok) {
			loginCheck();
		} else if (ob == submit.idCheck) {
			id = submit.tf_id.getText().trim();
			if (id.equals("")) {
				JOptionPane.showMessageDialog(submit, "���̵� �Է�");
				submit.tf_id.requestFocus();
				return;
			}
			if (checkID(id)) {
				JOptionPane.showMessageDialog(submit, "��밡���� ID�Դϴ�.");
				submit.tf_pass1.requestFocus();
			} else {
				JOptionPane.showMessageDialog(submit,
						"��ϵ� ID�Դϴ�. �ٽ� �Է��ϼ���.");
				submit.tf_id.setText(null);
				submit.tf_id.requestFocus();
				return;
			}
		} else if (ob == update) {
			updateShow();
		} else if (ob == exit) {
			System.exit(0);
		} else if (ob == admin) {
			if (!id.equals("admin") || !pass.equals("admin")) {
				JOptionPane.showMessageDialog(submit,
						"������ ������ �ʿ��մϴ�. \n�����ڷ� �α��� �ϼ���.");
				return;
			} else {
				JOptionPane.showMessageDialog(submit, "������ �α��� �Ǿ����ϴ�.");
				del.setEnabled(true);
				flag = true;
			}
		}
	}
	public static void main(String[] args) {
		DBMain dbm = new DBMain();
	}
}