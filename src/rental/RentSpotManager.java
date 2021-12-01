package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
	RentSpot rentSpot;
	
//	void selectSpot(Scanner scan) {
//		System.out.print("�뿩/�ݳ��� ��Ҹ� ������.(spotCode �Է�) "); //GUI������ ��� ��ư Ŭ�� �� �̸��� �ν��ؼ� ó���ϴ� �κ�
//		String spotCode = scan.next();
//		rentSpot = RentSystem.rentSpotMgr.find(spotCode);
//		rentSpot.print();
//	}
	
//	void addFavoriteSpot(Scanner scan, User user) {
//		System.out.print("���ã�� �뿩�ҷ� ����Ͻðڽ��ϱ�?(����� ���� �� y �Է�, �ƴϸ� n �Է�) ");
//		String answer = scan.next();
//		if (answer == "y") {
//			user.favoriteSpotList.add(rentSpot);
//		}
//		for (RentSpot spot: user.favoriteSpotList) {
//			System.out.println("���ã�� �뿩��");
//			spot.print();
//		}
//	}

//	// Ƽ�� ����
//	boolean buyTicket(Scanner scan, User user) {
//		if (user.ticket != null) {
//			System.out.printf(">> ���� ���� ���� Ƽ���� %s�Դϴ�.\n", user.ticket.code);
//			return true;
//		}
//		
//		System.out.print("�����ϰ� ���� Ƽ���� ������(ticketCode �Է�) ");
//		String ticketCode = scan.next();
//		Ticket ticket = RentSystem.ticketMgr.find(ticketCode);
//		
//		if (ticket == null) {
//			System.out.println("�������� �ʴ� Ƽ���Դϴ�.");
//			return false;
//		}
//		// ���� ȭ��
//		System.out.printf("�����Ͻ� Ƽ���� %s (%d��)�Դϴ�. �����Ͻðڽ��ϱ�?\n���� ���� ����Ʈ�� %d�Դϴ�.\n"
//									, ticket.code, ticket.price, user.point);
//		// ���� ����
//		String answer = scan.next();
//		if (answer.contentEquals("y")) {
//			user.ticket = ticket;
////			startTicket();
//			return true;
//		} else {
//			System.out.println("������ ��ҵǾ����ϴ�.");
//			return false;
//		}
//	}
	
	//�뿩(GUI)
	public void rentalVehicle(User user, Vehicle vehicle, RentSpot spot) {
		Vehicle remove = null;
		for(Vehicle v : spot.vehicleList) { // �����ҿ� Vehicle ����
			if(v.code.equals(vehicle.code))
				remove = v;
		}
		spot.vehicleList.remove(remove);
		// Į�θ� ����ϱ� ���ؼ� ���۽ð� �� �����.
		vehicle.setTime();
		user.vehicle = vehicle;
		System.out.printf("\n%d�� %d��\n", vehicle.starthour, vehicle.startmin);
	}
	
	// �ݳ� -> ��ü �̿� �ð� ��� ( ����ð� - ���۽ð�)
	public int returnVehicle(User user, RentSpot spot) {
		Vehicle tmp = user.vehicle;
		int useh = 0, usem = 0;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		System.out.printf("���� %d�� %d��\n", nowh, nowm);
		// �̿� �ð� ��� 
		if(nowh == tmp.starthour)
			usem = nowm - tmp.startmin;
		else if( nowh > tmp.starthour && nowm < tmp.startmin) {
			useh = nowh - tmp.starthour - 1;
			usem = 60 + nowm - tmp.startmin;
		}
		else {
			useh = nowh = tmp.starthour;
			usem = nowm - tmp.startmin;
		}
		user.vehicle = null;
		spot.vehicleList.add(tmp); // �ݳ�
		
		//�̿� �� ��ȯ
		return (useh*60 + usem);
	}
	
	
//	RentSpot getRentSpot(String spotName) {
//		return find(spotName); // ��� ��ư Ŭ�� �� ó��? ���������� ��� id�� �־�� ������
//	}
	
	//user�� spot �� �ϳ��� �Է� ó���� �Ǿ�� �ϴµ�, user�� �Է� ó�� �ϱ⿣ ���μ��� �� �̻���. 
	//�뿩 �� �ݳ� �Լ��� userManager���� �����ϰ�, ��� ��ư ������ �� �̸����� ��Ҹ� ã�Ƽ� �Լ��� �μ��� �־��ִ� �� �´� �� ����
	
//	void rentVehicle(User user, Scanner scan) {
//		// user�� �뿩�� �ִ��� Ȯ��
//		if(user.ticket == null) {
//			System.out.println("�뿩���� �ʿ��մϴ�.");
//			return;
//		}
//		// user�� �뿩������ Ȯ��
//		if(user.vehicle != null) {
//			System.out.println("���� �뿩���Դϴ�.");
//			return;
//		}
//		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
//		String code = scan.next();
//		// ���� ���� Ȯ��.
//		if(code.contains("S")) {
//			if(user.license == 0) {
//				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
//				return;
//			}
//		}
//		for(Vehicle v : spot.vehicleList) {
//			if(v.id.equals(code)) {
//				user.vehicle = v;
//				spot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
//				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
//				user.vehicle.setTime();
//			}
//		}
//		if(user.vehicle == null)
//			System.out.println("�ش� ��ۼ����� �����ϴ�.");
//
//	}
//	// �ݳ�
//	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
//	void returnVehicle(User user, RentSpot spot) {
//		// ���ϱ��� ��� �ð����
//		Vehicle tmp = user.vehicle;
//		Calendar now = Calendar.getInstance();
//		int nowh = now.get(Calendar.HOUR);
//		int nowm = now.get(Calendar.MINUTE);
//		
//		if(user.ticket == 1) { // 1�ð���
//			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
//				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		if(user.ticket == 2) { // 2�ð���
//			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		user.rental = null;
//		spot.vehicleList.add(tmp); // �ݳ�.
//	}
}