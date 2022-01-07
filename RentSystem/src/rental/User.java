package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	public String name;
	public String phoneNumber;
	public String id;
	public String pwd;
	public int license; // 0:���� �̺���  1:���� ����
	public String ticketCode;
	public String vehicleCode;
	public Vehicle vehicle;
	public int point;
	int burnedCalories;
	public Ticket ticket;
	public int startyear;  
	public int startmonth;
	public int startdate;
	public int leftday;
	public ArrayList<String> oftenSpotList = new ArrayList<>();
	
	@Override
	public void read(Scanner scan) { // �̸� ��ȭ��ȣ ���̵� pw ���㿩�� ticket vehicle �� �� �� point ��ȣ������
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pwd = scan.next();
		license = scan.nextInt();	
		ticketCode = scan.next(); // 1201 ����
		// ticket ���� ���� ã���ֱ�
		if(!ticketCode.equals("null")) { // null�� �ƴϸ� ã�������.  => user�ȿ����� 
			ticket = RentSystem.ticketMgr.find(ticketCode);
			if(ticket == null)
				System.out.println("ticket ��");
			else
				System.out.println("�ξƴ�");
		}
		vehicleCode = scan.next(); // 1201 ����
		if(!vehicleCode.equals("null")) {  // ���� �ƴϸ�
			vehicle = RentSystem.vehicleMgr.find(vehicleCode);
		}
		point = scan.nextInt(); // 1201 ����
		startyear = scan.nextInt();
		startmonth = scan.nextInt();
		startdate = scan.nextInt();
		String spotName = null;
		while(true) {
			spotName = scan.next();
			if(spotName.contentEquals("end"))	
				break;  
			oftenSpotList.add(spotName);
		}
	}
	
	@Override	
	public void print() {
		System.out.printf("[%s] ���̵�: %s, ��ȭ��ȣ: %s, ����Ʈ: %d��, ���㺸������: %d, ���� �̿��: ", 
				name, id, phoneNumber, point, license);
		if (ticket == null) {
			System.out.println("����");
		} else {
			if (ticket.ticketType == "���ϱ�")
				System.out.printf("<%s> %d�ð� �̿��\n", ticket.ticketType, ticket.hour);
			else
				System.out.printf("<%s> %d���� �̿��, %d�� ����\n", ticket.ticketType, ticket.month, leftday);
		}
		System.out.println("<���ã�� �뿩��>");
		if (oftenSpotList.isEmpty()) {
			System.out.println("���ã�� �뿩�Ұ� �����ϴ�.");
			return;
		}
		for (String spot: oftenSpotList)
			System.out.println(spot);
	}
	
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(name.equals(kwd))
			return true;
		return false;
	}
	
	public boolean passwordMatch(String pwd) {
		return this.pwd.equals(pwd);
	}
}