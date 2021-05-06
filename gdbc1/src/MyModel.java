import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel {
	Object[][] data;
	String[] columnName;
	int rows, cols; // 레코드의 행과열의 개수를 저장할 변수 선언
	
	public int getColumnCount() {
		return columnName.length;
	}
	public int getRowCount() {
		return data.length;
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	public void getRowCount(ResultSet rsScroll) {
		try {
			rsScroll.last();
			rows = rsScroll.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// DB의 저장된 데이터를 채움
	public void setData(ResultSet rs) {
		try {
			ResultSetMetaData rsmd;
			rsmd = rs.getMetaData();
			cols = rsmd.getColumnCount();
			columnName = new String[cols];
			for (int i = 0; i < cols; i++)
				columnName[i] = rsmd.getColumnName(i + 1);
			data = new Object[rows][cols];
			int r = 0;
			while (rs.next()) {
				for (int c = 0; c < cols; c++)
					if (c != 1)
						data[r][c] = rs.getObject(c + 1);
					else
						data[r][c] = (String) rs.getObject(c + 1);
				r++;
			}
			rs.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}