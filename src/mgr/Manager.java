
package mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.User;

public class Manager<T extends Manageable> {
	ArrayList<T> mList = new ArrayList<>();

	public T find(String kwd) {
		for (T m : mList)
			if (m.matches(kwd))
				return m;
		return null;
	}

	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	public void printAll() {
		for (T m : mList) {
			m.print();
		}
	}

	public List<Manageable> findAll(String kwd) {
		java.util.List<Manageable> results = new ArrayList<>();
		for (T m : mList)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}

	public void search(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (T m : mList) {
				if (m.matches(kwd))
					m.print();
			}
		}
	}

	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
		return filein;
	}

	User user = new User();

	// 로그인 기능
	public void login(Scanner scan) {
		while (true) {
			System.out.print("user id: ");
			String userId = scan.next();
			System.out.print("password: ");
			String pwd = scan.next();

			user = (User) find(userId);

			if (user != null && user.pwdMatch(pwd)) { // 스캔한 아이디와 비밀번호가 회원내역에 있으면
				System.out.printf("%s님 환영합니다.", userId);
				break;
			} else if ((User) find(userId) == null) {
				System.out.println("아이디를 확인하세요. ");
				continue;
			} else {
				System.out.println("비밀번호를 확인하세요. ");
				continue;
			}
		}
		System.out.println();
	}

	// 탈퇴
	public void withdrawl(Scanner scan) {
		String answ = null;
		System.out.println("탈퇴하시겠습니까? ");
		answ = scan.next();
		if (answ.equals("y"))
			mList.remove(user); // 리스트에서 정보 삭제
		logout(scan);
	}

	// 정보수정 기능
	public void infoModify(Scanner scan) {
		System.out.print("기존암호: ");
		String oldpwd = scan.next();
		if (user.pwdMatch(oldpwd)) // 기존암호가 입력받은 암호와 같다면
			user.modify(scan);
		else
			System.out.println("암호가 올바르지 않습니다. ");
	}

	// 로그아웃
	public void logout(Scanner scan) {
		login(scan);
	}
}
