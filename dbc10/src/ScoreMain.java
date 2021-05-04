
public class ScoreMain {
	public static void main(String[] args) {
		char ch;
		Score ob = new Score();
		try {
			while (true) {
				do {
					System.out.print("1.�Է� 2.���� 3.���� 4.��ü��� "
							+ " 5.�̸��˻� 6.���� =>");
					ch = (char) System.in.read();
					System.in.skip(2);
				} while (ch < '1' || ch > '6');
				switch (ch) {
				case '1':
					if (ob.insertDate() != 0) System.out.println("�Է� ����!");
					break;
				case '2':
					if (ob.updateDate() != 0) System.out.println("���� ����!");
					else System.out.println("���� ����!");
					break;
				case '3':
					if (ob.deleteDate() != 0) System.out.println("���� ����!");
					else System.out.println("���� ����!");
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