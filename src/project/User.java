package project;

import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	// �̸� ���̵� ��й�ȣ ����Ʈ ����Ǻ��� �̿���� ����������
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

	// ��й�ȣ ��ġ ���
	public boolean pwdMatch(String pwd) {
		return this.pwd.equals(pwd);
	}

	// �������� ���
	public void modify(Scanner scan) {
		String tmp = null;

		// ���ο� ��ȣ�� ����Ʈ�� ����
		System.out.print("����ȣ(������� ����): ");
		scan.nextLine();
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			pwd = tmp;
	}
}
