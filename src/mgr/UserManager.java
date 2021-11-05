package mgr;

import java.util.Scanner;

import rent.User;

public class UserManager extends Manager<User> {
	User user = new User();
	
	// 로그인
	public void login(Scanner scan) {
		System.out.println("[로그인]");
		while(true) {
			System.out.print("아이디: ");
			String id = scan.next();
			System.out.print("비밀번호: ");
			String pwd = scan.next();

			user = find(id);
			
			if (user != null && user.passwordMatch(pwd)) {
				System.out.println("로그인 성공!");
				break;
			} else {
				System.out.println("올바른 회원 정보가 아닙니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
	
	// 내 정보
	public void myInfo() {
		user.print();
	}
	
	// 회원 정보 수정
	public void modify(Scanner scan) {
		System.out.print("기존암호: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println("암호가 올바르지 않습니다.");
		}
	}
	
	// 회원 가입
	public void join(Scanner scan) {
			System.out.println("회원 정보(아이디, 비밀번호, 이름, 전화번호)를 입력해주세요.");
			user.read(scan);
			mList.add(user);
			login(scan);
	}
	
	// 회원 탈퇴
	public void withdraw(Scanner scan) {
		System.out.println("[탈퇴]");
		System.out.println("비밀번호를 입력해주세요.");
		if (user.passwordMatch(scan.next())) {
			mList.remove(user);
			logout(scan);
		}
	}
	
	// 로그아웃
	public void logout(Scanner scan) {
		//login(scan);
		//exit(0);
	}
}
