package rental;

<<<<<<< Updated upstream
=======
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	String id;
	String pwd;
	String name;
	String phoneNumber;
	Vehicle rental;
	int point;
	int license; // 0: ���� �̺���  1: ���� ����
<<<<<<< Updated upstream
	int ticket; // 0: �뿩�� ����   1: ���ϱ�(1�ð�) 2: ���ϱ�(2�ð�)
				// 3: �����(1����) 4: �����(6����) 5: �����(12���� = 1��)
	
=======
	Ticket ticket;
	// String���� ����
>>>>>>> Stashed changes
	int startyear;  
	int startmonth;
	int startdate;
	// ���� ��¥ -> �̰� ������ �ʿ��ѵ� => �α��� �Ҷ����� �����ϴ� �� ���
	// �α��� �Ҷ� ����� ���� Ȯ����, ���� ��¥, ���� ��¥ ���ؼ� ���� ��¥ ���.
	int leftday;
<<<<<<< Updated upstream
=======
//	ArrayList<RentSpot> favoriteRentSpotList = new ArrayList<>(); //���ã�� �뿩��
>>>>>>> Stashed changes
	
	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pwd = scan.next();
		license = scan.nextInt();
	}
	
	@Override	
	public void print() {
		System.out.printf("[%s] ���̵�: %s, ����Ʈ: %d��, ���㺸������: %d\n", 
				name, id, point, license);
		// GUI���� ���� �޴��� ���̴� �� ǥ��
<<<<<<< Updated upstream
		if(ticket != 0) {
			if(ticket == 1)
				System.out.println("[���ϱ�]: 1�ð� �̿�");
			else if(ticket == 2)
				System.out.println("[���ϱ�]: 2�ð� �̿�");
			else {
				System.out.printf("[�����]\t%d�� ����", leftday);
				
			}
	
		}
=======
//		if(ticket == null) {
//		System.out.println("����");
//	} else {
//		if (ticket.name == "���ϱ�")
//			System.out.printf("[%s] %d�ð� �̿��", ticket.name, ticket.hour);
//		else
//			System.out.printf("[%s] %d���� �̿��, %d�� ����", ticket.name, ticket.month, leftday);
//	}
>>>>>>> Stashed changes
	}
	
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pwd.equals(kwd))
			return true;
//		if(name.equals(kwd))
//			return true;
		return false;
	}
	
	// ��й�ȣ ��ġ Ȯ��
	public boolean passwordMatch(String pwd) {
		return this.pwd.equals(pwd);
	}
	
	// ���� ����
	public void modifyInfo(Scanner scan) {	
		String tmp = null;
		
		System.out.print("����ȣ(������� ����): ");
		scan.nextLine();  // �߰����־�� tmp�� ���� �� ��
		tmp = scan.nextLine();
		if (!tmp.equals(""))  // ���Ͱ� �ƴϸ�
			pwd = tmp;
		
		System.out.printf("�̸�(%s): ", name);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			name = tmp;
		
		System.out.printf("��ȭ��ȣ(%s): ", phoneNumber);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			phoneNumber = tmp;
		
		System.out.printf("���㺸������(%d): ", license);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			license = (int) Integer.parseInt(tmp);
	}

<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
