
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
			System.out.println("���� �Է� ����");
			System.exit(0);
		}
		return filein;
	}

	User user = new User();

	// �α��� ���
	public void login(Scanner scan) {
		while (true) {
			System.out.print("user id: ");
			String userId = scan.next();
			System.out.print("password: ");
			String pwd = scan.next();

			user = (User) find(userId);

			if (user != null && user.pwdMatch(pwd)) { // ��ĵ�� ���̵�� ��й�ȣ�� ȸ�������� ������
				System.out.printf("%s�� ȯ���մϴ�.", userId);
				break;
			} else if ((User) find(userId) == null) {
				System.out.println("���̵� Ȯ���ϼ���. ");
				continue;
			} else {
				System.out.println("��й�ȣ�� Ȯ���ϼ���. ");
				continue;
			}
		}
		System.out.println();
	}

	// Ż��
	public void withdrawl(Scanner scan) {
		String answ = null;
		System.out.println("Ż���Ͻðڽ��ϱ�? ");
		answ = scan.next();
		if (answ.equals("y"))
			mList.remove(user); // ����Ʈ���� ���� ����
		logout(scan);
	}

	// �������� ���
	public void infoModify(Scanner scan) {
		System.out.print("������ȣ: ");
		String oldpwd = scan.next();
		if (user.pwdMatch(oldpwd)) // ������ȣ�� �Է¹��� ��ȣ�� ���ٸ�
			user.modify(scan);
		else
			System.out.println("��ȣ�� �ùٸ��� �ʽ��ϴ�. ");
	}

	// �α׾ƿ�
	public void logout(Scanner scan) {
		login(scan);
	}
}
