import java.util.Scanner;

public class LoginMain {
	public static void main(String[] args) {
		boolean isStop = false;
		Scanner sc = new Scanner(System.in);
		LoginSVC loginSVC = new LoginSVC();
		do {
			System.out.println("�α��� ȭ��");
			System.out.println("���̵�� ��й�ȣ�� �Է��ϼ���.");
			System.out.println("���̵� : ");
			String id = sc.next();
			System.out.println("��й�ȣ : ");
			String passwd = sc.next();
			User user = loginSVC.login(id, passwd);
			if (user == null) {
				System.out.println("���̵� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			} else {
				System.out.println("�α����� ����� ����");
				System.out.println(user);
				isStop = true;
			}
		} while (!isStop);
		sc.close();
	}
}