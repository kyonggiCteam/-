package project;

import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	// 이름 아이디 비밀번호 포인트 정기권보유 이용상태 면허증여부
	String name;
	String id;
	String pwd;
	int point;
	int HaveTicket;
	String UsageStatus;
	String license;

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		name = scan.next();
		id = scan.next();
		pwd = scan.next();
		point = scan.nextInt();
		HaveTicket = scan.nextInt();
		UsageStatus = scan.next();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}

	// 비밀번호 일치 기능
	public boolean pwdMatch(String pwd) {
		return this.pwd.equals(pwd);
	}

	// 정보수정 기능
	public void modify(Scanner scan) {
		String tmp = null;

		// 새로운 암호를 리스트에 저장
		System.out.print("새암호(변경없음 엔터): ");
		scan.nextLine();
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			pwd = tmp;
	}
}
