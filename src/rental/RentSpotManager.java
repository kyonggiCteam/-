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
	
	void selectSpot(Scanner scan) {
		System.out.print("�뿩/�ݳ��� ��Ҹ� ������.(spotCode �Է�) "); //GUI������ ��� ��ư Ŭ�� �� �̸��� �ν��ؼ� ó���ϴ� �κ�
		String spotCode = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		rentSpot.print();
	}
	
	void addFavoriteSpot(Scanner scan, User user) {
		System.out.print("���ã�� �뿩�ҷ� ����Ͻðڽ��ϱ�?(����� ���� �� y �Է�, �ƴϸ� n �Է�) ");
		String answer = scan.next();
		if (answer == "y") {
			user.favoriteSpotList.add(rentSpot);
		}
		for (RentSpot spot: user.favoriteSpotList) {
			System.out.println("���ã�� �뿩��");
			spot.print();
		}
	}

	// Ƽ�� ����
	boolean buyTicket(Scanner scan, User user) {
		if (user.ticket != null) {
			System.out.printf(">> ���� ���� ���� Ƽ���� %s�Դϴ�.\n", user.ticket.code);
			return true;
		}
		
		System.out.print("�����ϰ� ���� Ƽ���� ������(ticketCode �Է�) ");
		String ticketCode = scan.next();
		Ticket ticket = RentSystem.ticketMgr.find(ticketCode);
		
		if (ticket == null) {
			System.out.println("�������� �ʴ� Ƽ���Դϴ�.");
			return false;
		}
		// ���� ȭ��
		System.out.printf("�����Ͻ� Ƽ���� %s (%d��)�Դϴ�. �����Ͻðڽ��ϱ�?\n���� ���� ����Ʈ�� %d�Դϴ�.\n"
									, ticket.code, ticket.price, user.point);
		// ���� ����
		String answer = scan.next();
		if (answer.contentEquals("y")) {
			user.ticket = ticket;
//			startTicket();
			return true;
		} else {
			System.out.println("������ ��ҵǾ����ϴ�.");
			return false;
		}
	}
	
	void rentalVehicle(Scanner scan, User user) { // ��� �뿩
		if (!buyTicket(scan, user)) // Ƽ�� ����(����) => ���� ��� �� �뿩 ����
			return;
		if (user.vehicle != null) {
			System.out.println(">> �̹� ��� �뿩���Դϴ�.");
			return;
		}

		rentSpot.print();
		// ��� �����ֱ� - ������ �����, ������ Ƽ���� �귣���� ���鸸 �����ְ� ������ �� �ְ� �ؾ� ��
		ArrayList<Vehicle> available = new ArrayList<>();
		for(Vehicle v : rentSpot.vehicleList) {
			if (v.brandName == user.ticket.brandName) {
				available.add(v);
				v.print();
			}
		}
		if (available.isEmpty()) {
			System.out.println("�뿩 ������ ��� �����ϴ�.");
			return;
		}
		
//		if (rentSpot.vehicleList == null) {
//			System.out.println("�뿩 ������ ��� �����ϴ�.");
//			return;
//		}
//		for (Vehicle v : rentSpot.vehicleList) {
//			if (v.brandName == user.ticket.brandName && v.state == 0) {
//				v.print();
//				available.add(v);
//			}
//		}
		
		// ��� ����
		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
		System.out.print(">> �뿩�� ��� ������.(vehicleCode �Է�) ");
		String vehicleCode = scan.next();
		Vehicle vehicle = RentSystem.vehicleMgr.find(vehicleCode);
		if (vehicle == null) {
			System.out.println(">> �ش� ���� �������� �ʽ��ϴ�.");
			return;
		}
		// ����ű�����̸� ���� ���� Ȯ��
		if(vehicleCode.charAt(0) == 'S') { // Ȥ�� vehicle.code.charAt(0) == 'S'
			if(user.license == 0) {
				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
				return;
			}
		}
		
		// ������ �����, ������ Ƽ���� �귣���� ���鸸 �����ְ� ������ �� �ְ� �ؾ� ��
		for(Vehicle a : available) {
			if (a.equals(vehicle)) {
				user.vehicle = vehicle;
				user.vehicle.state = 1;
				System.out.println(">> �뿩�� �Ϸ�Ǿ����ϴ�.");
//				RentSystem.userMgr.startTicket();
				user.vehicle.setTime();
			} else {
				System.out.println(">> �ش� ���� �뿩�� �� �����ϴ�.");
				return;
			}
			
//			if(v.code.equals(vehicleCode)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
//				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
//				user.vehicle.setTime();
//			}
		}
	}
	
	// �ݳ�
	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
	void returnVehicle(Scanner scan, User user) {
//		void returnVehicle(User user, RentSpot spot) {
		// ���ϱ��� ��� �ð����
		Vehicle tmp = user.vehicle;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		System.out.printf("���� %d�� %d��\n", nowh, nowm);
		if(user.ticket.hour == 1) { // 1�ð���
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
				TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
				// �߰� ��� �Լ� ȣ��
				TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // �ݳ�
		RentSystem.userMgr.pointCount(user.ticket.price); // �³�?
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
