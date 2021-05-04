import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		BoardSVC boardSVC = new BoardSVC();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("=== 게시판 ===");
			System.out.println("1. 글 등록");
			System.out.println("2. 글 목록보기");
			System.out.println("3. 글 검색하기");
			System.out.println("4. 글 삭제하기");
			System.out.println("5. 글 수정하기");
			System.out.println("6. 종료하기");
			System.out.print("메뉴를 선택하세요 : ");
			int menu = Integer.parseInt(sc.next());
			switch (menu) {
			case 1: boardSVC.writeArticle(sc); 		break;
			case 2: boardSVC.showArticleList(); 	break;
			case 3: boardSVC.showArticle(sc); 		break;
			case 4: boardSVC.deleteArticle(sc); 	break;
			case 5: boardSVC.updateArticle(sc); 	break;
			case 6: return;
			default: System.out.println("명령 입력이 잘못되었습니다. 다시 입력해주세요.");
			}
		}
	}
}