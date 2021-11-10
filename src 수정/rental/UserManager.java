package rental;

import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {  // user ��������.
 
	//�α���
	User login(Scanner scan) {
		String id = scan.next();
		String pw = scan.next();
		User user1 = null;
		User user2 = null;
		user1 = find(id);
		user2 = find(pw);
		if( user1 != null && user2 != null && user1 == user2 ) {
			System.out.println("�α��� ����");
			return user1;
		}
		else {
			System.out.println("�α��� ����");
			return null;
		}
	}
	
	//ȸ������ -> id �ߺ� Ȯ�� ��� �߰� �ʿ�.
	void signup(Scanner scan) {
		User user = new User();
		user.name = scan.next();
		user.phoneNumber = scan.next();
		user.id = scan.next();
		user.pw = scan.next();
		String checkpw = scan.next();
		if(!user.pw.equals(checkpw)) {
			System.out.println("��ҹ�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
		int license = scan.nextInt();
		if(license == 1)
			user.license = true;
		else
			user.license = false;
		mList.add(user);	
	}
	
}
