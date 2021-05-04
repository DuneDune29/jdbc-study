import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		BoardSVC boardSVC = new BoardSVC();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("=== �Խ��� ===");
			System.out.println("1. �� ���");
			System.out.println("2. �� ��Ϻ���");
			System.out.println("3. �� �˻��ϱ�");
			System.out.println("4. �� �����ϱ�");
			System.out.println("5. �� �����ϱ�");
			System.out.println("6. �����ϱ�");
			System.out.print("�޴��� �����ϼ��� : ");
			int menu = Integer.parseInt(sc.next());
			switch (menu) {
			case 1: boardSVC.writeArticle(sc); 		break;
			case 2: boardSVC.showArticleList(); 	break;
			case 3: boardSVC.showArticle(sc); 		break;
			case 4: boardSVC.deleteArticle(sc); 	break;
			case 5: boardSVC.updateArticle(sc); 	break;
			case 6: return;
			default: System.out.println("��� �Է��� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
}