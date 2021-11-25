package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	String id;
	String pwd;
	String name;
	String phoneNumber;
	Vehicle vehicle;
	int point;
	int burnedCalories;
	int license; // 0: ���� �̺���  1: ���� ����
	Ticket ticket;
	int startyear;  
	int startmonth;
	int startdate;
	// ���� ��¥ -> �̰� ������ �ʿ��ѵ� => �α��� �Ҷ����� �����ϴ� �� ���
	// �α��� �Ҷ� ����� ���� Ȯ����, ���� ��¥, ���� ��¥ ���ؼ� ���� ��¥ ���.
	int leftday;
	ArrayList<RentSpot> favoriteRentSpotList = new ArrayList<>(); //���ã�� �뿩��
	
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
		System.out.printf("[%s] ���̵�: %s, ��ȭ��ȣ: %s, ����Ʈ: %d��, ���㺸������: %d, ���� �̿��: ", 
				name, id, phoneNumber, point, license);
		// GUI���� ���� �޴��� ���̴� �� ǥ��
		if(ticket == null) {
			System.out.println("����");
		} else {
			if (ticket.ticketType == "���ϱ�")
				System.out.printf("[%s] %d�ð� �̿��", ticket.ticketType, ticket.hour);
			else
				System.out.printf("[%s] %d���� �̿��, %d�� ����", ticket.ticketType, ticket.month, leftday);
		}
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

}
