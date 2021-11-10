package rental;

import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {  // user 관리해줌.
 
	//로그인
	User login(Scanner scan) {
		String id = scan.next();
		String pw = scan.next();
		User user1 = null;
		User user2 = null;
		user1 = find(id);
		user2 = find(pw);
		if( user1 != null && user2 != null && user1 == user2 ) {
			System.out.println("로그인 성공");
			return user1;
		}
		else {
			System.out.println("로그인 실패");
			return null;
		}
	}
	
	//회원가입 -> id 중복 확인 기능 추가 필요.
	void signup(Scanner scan) {
		User user = new User();
		user.name = scan.next();
		user.phoneNumber = scan.next();
		user.id = scan.next();
		user.pw = scan.next();
		String checkpw = scan.next();
		if(!user.pw.equals(checkpw)) {
			System.out.println("비닐번호가 올바르지 않습니다.");
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
