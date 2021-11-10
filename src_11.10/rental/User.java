package rental;

import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {

	String id;  
	String pw;
	String name;
	String phoneNumber;
	int point;
	Vehicle rental;
	boolean license; // ���� ����
	//����� ���ϱ� ����
	int ticket; // 0: �뿩�� ����   1: ���ϱ�(1�ð�) 2: ���ϱ�(2�ð�)
				// 3: �����(1����) 2: �����(6����) 3: �����(12���� = 1��)
	 
	int startyear;  
	int startmonth;
	int startdate;
	// ���� ��¥ -> �̰� ������ �ʿ��ѵ�.. �α��� �Ҷ����� �����ϴ� �� �?
	// �α��� �Ҷ� ����� ���� Ȯ����, ���� ��¥, ���� ��¥ ���ؼ� ���� ��¥ ���.
	int leftmonth;
	int leftdate;
	
	void vehicleprint() {
		if(rental != null) {
			System.out.printf("�뿩��\t\n %s", rental.id );
		}
	}

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pw = scan.next();
		int state = scan.nextInt();
		if(state == 1)
			license = true;
		else
			license = false;
		
	}
	@Override	
	public void print() {
		// GUI���� ���� �޴��� ���̴� �� ǥ��
		System.out.printf("%s  %s  %d\n", id, name, point);
		if(ticket != 0) {
			if(ticket == 1)
				System.out.println("[���ϱ�]: 1�ð� �̿�");
			else if(ticket == 2)
				System.out.println("[���ϱ�]: 2�ð� �̿�");
			else {
				System.out.printf("[�����]\t%d���� %d�� ����", leftmonth, leftdate);
				
			}
	
		}
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pw.equals(kwd))
			return true;
		if(name.equals(kwd))
			return true;
		return false;
	}
	
}
