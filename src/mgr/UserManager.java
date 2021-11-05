package mgr;

import java.util.Scanner;

import rent.User;

public class UserManager extends Manager<User> {
	User user = new User();
	
	// �α���
	public void login(Scanner scan) {
		System.out.println("[�α���]");
		while(true) {
			System.out.print("���̵�: ");
			String id = scan.next();
			System.out.print("��й�ȣ: ");
			String pwd = scan.next();

			user = find(id);
			
			if (user != null && user.passwordMatch(pwd)) {
				System.out.println("�α��� ����!");
				break;
			} else {
				System.out.println("�ùٸ� ȸ�� ������ �ƴմϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
		}
	}
	
	// �� ����
	public void myInfo() {
		user.print();
	}
	
	// ȸ�� ���� ����
	public void modify(Scanner scan) {
		System.out.print("������ȣ: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println("��ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
	}
	
	// ȸ�� ����
	public void join(Scanner scan) {
			System.out.println("ȸ�� ����(���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ)�� �Է����ּ���.");
			user.read(scan);
			mList.add(user);
			login(scan);
	}
	
	// ȸ�� Ż��
	public void withdraw(Scanner scan) {
		System.out.println("[Ż��]");
		System.out.println("��й�ȣ�� �Է����ּ���.");
		if (user.passwordMatch(scan.next())) {
			mList.remove(user);
			logout(scan);
		}
	}
	
	// �α׾ƿ�
	public void logout(Scanner scan) {
		//login(scan);
		//exit(0);
	}
}
