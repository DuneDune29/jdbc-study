
public class ScoreMain {
	public static void main(String[] args) {
		char ch;
		Score ob = new Score();
		try {
			while (true) {
				do {
					System.out.print("1.입력 2.수정 3.삭제 4.전체출력 "
							+ " 5.이름검색 6.종료 =>");
					ch = (char) System.in.read();
					System.in.skip(2);
				} while (ch < '1' || ch > '6');
				switch (ch) {
				case '1':
					if (ob.insertDate() != 0) System.out.println("입력 성공!");
					break;
				case '2':
					if (ob.updateDate() != 0) System.out.println("수정 성공!");
					else System.out.println("수정 실패!");
					break;
				case '3':
					if (ob.deleteDate() != 0) System.out.println("삭제 성공!");
					else System.out.println("삭제 실패!");
					break;
				case '4':
					ob.selectAll(); 		break;
				case '5':
					ob.selectName(); 		break;
				case '6':
					DBConn.close(); 		System.exit(0);
				}
			}
		} catch (Exception e) { System.out.println(e.toString()); }
	}
}