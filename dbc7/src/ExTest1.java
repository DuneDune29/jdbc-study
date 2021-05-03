import java.util.ArrayList;

public class ExTest1 {
	public static void main(String[] args) { 
		ExDAO dao = new ExDAO();
		ArrayList<ExDTO> list = dao.select();
		
		for (ExDTO dto : list) {
			int deptno = dto.getDeptno();
			String dname = dto.getDname();
			String loc = dto.getLoc();
			System.out.println(deptno + " " + dname + " " + loc);
		}
	}
}
