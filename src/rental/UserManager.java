package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user;
	RentSpot rentSpot;
	RentSpot returnSpot;
	

	// ȸ������
	public void join(String[] userInfo) {	
		User user = new User();
		user.name = userInfo[0];
		user.phoneNumber = userInfo[1];
		user.id = userInfo[2];
		user.pwd = userInfo[3];
		user.license = Integer.parseInt(userInfo[4]);		
		mList.add(user);
	}
	
	// ���̵� �ߺ� Ȯ��
	public boolean idDuplicate(String id) {
		return (find(id) != null);
	}
	
	// �α���
	public void login(Scanner scan) {
		System.out.println("<�α���>");
		while(true) {
			System.out.print("���̵�: ");
			String id = scan.next();
			System.out.print("��й�ȣ: ");
			String pwd = scan.next();
			
			user = find(id);
			
			if (user != null && user.passwordMatch(pwd)) {
				System.out.println(">> �α��� ����!\n");
				//updateDate(); // �ʱ⿡ null ���� �߻�
				//return true;
				break;
			} else {
				System.out.println(">> �ùٸ� ȸ�� ������ �ƴմϴ�. �ٽ� �Է����ּ���.\n");
				//return false;
				continue;
			}
		}
	}
	
	// �� ����
	public void myPage() {
		System.out.println("<My Page>");
		user.print();
	}
	
	// ȸ�� ���� ����
	public void modify(Scanner scan) {
		System.out.print("���� ��ȣ: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println(">> ��ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
	}
	
	void selectSpot(Scanner scan) {
		System.out.print("�뿩/�ݳ��� ��Ҹ� ������.(spotCode �Է�) "); //GUI������ ��� ��ư Ŭ�� �� �̸��� �ν��ؼ� ó���ϴ� �κ�
		String spotCode = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		rentSpot.print();
	}
	
	void addFavoriteSpot(Scanner scan) {
		System.out.print("���ã�� �뿩�ҷ� ����Ͻðڽ��ϱ�?(����� ���� �� y �Է�, �ƴϸ� n �Է�) ");
		String answer = scan.next();
		if (answer.equals('y')) {
			user.favoriteSpotList.add(rentSpot);
		}
		for (RentSpot spot: user.favoriteSpotList) {
			System.out.println("���ã�� �뿩��");
			spot.print();
		}
	}

	//- --------------------------------------
	// Ƽ�� ����( �ܼ� ���� �� ����)
//	public void buyTicket(Ticket ticket,  User user) { // user�� Ƽ�� �־��� + �ð���� + ����Ʈ ���
//		if(ticket.ticketType.equals("�����")) { // �ð� ��� �ʿ�.
//			startTicket(user);
//		}
//		// ����Ʈ�� ���
//		pointCount(ticket.price, user);
//		user.ticket = ticket;
//	}
//	
//	// �ð� ���� 
//	void startTicket(User user) { // user ���� �ð� �־���
//		Calendar now = Calendar.getInstance();
//		user.startyear = now.get(Calendar.YEAR);
//		user.startmonth = now.get(Calendar.MONTH);
//		user.startdate = now.get(Calendar.DATE);
//		System.out.printf("���� �ð�: %d�� %d �� %d ��" , user.startyear, user.startmonth + 1, user.startdate);
//	}
//	
//	// ����Ʈ ��� �Լ�
//	void pointCount(int price, User user) {
//		user.point += (int) (price * 0.01); // 1% ����
//	}
	
	// ---------------------------------------------
	
	void rentalVehicle(Scanner scan) { // ��� �뿩
//		if (!buyTicket(scan)) // Ƽ�� ����(����) => ���� ��� �� �뿩 ����
//			return;
		if (user.vehicle != null) {
			System.out.println(">> �̹� ��� �뿩���Դϴ�.");
			return;
		}
		// ��� �����ֱ� - ������ �����, ������ Ƽ���� �귣����, ��밡���� ������ ��� �����ְ� ������ �� �ְ� �ؾ� ��
		ArrayList<Vehicle> available = new ArrayList<>();
		for(Vehicle v : rentSpot.vehicleList) {
			if (v.brandName.equals(user.ticket.brandName) && v.state == 0) {
				available.add(v);
				v.print();
			}
		}
		if (available.isEmpty()) {
			System.out.println("�뿩 ������ ��� �����ϴ�.");
			return;
		}
		
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
		
		// �뿩 ����
		user.vehicle = vehicle;
		user.vehicle.state = 1;
		System.out.println(">> �뿩�� �Ϸ�Ǿ����ϴ�.");
		user.vehicle.setTime(); //���� �ð� ī��Ʈ�ϴ� ����?
			
//			if(v.code.equals(vehicleCode)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
//				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
//				user.vehicle.setTime();
//			}
	}
	
	// �ݳ�
	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
	void returnVehicle(Scanner scan) {
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
				//TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				//TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
				// �߰� ��� �Լ� ȣ��
				//TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				//.morePay(nowh - tmp.starthour);
			}
		}
		
		// �ݳ� ����
		user.vehicle.state = 0;  // �̿� ������ ���·� ��ȯ
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // �ݳ� ==> state ���� �ʿ� ���� ��..?
		//pointCount(user.ticket.price); // �³�?
	}
	

	
	//leftmonth, leftdate ����
	public void updateDate(User user) { // 1���� 30���̶�� ����.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int nowY = now.get(Calendar.YEAR);
		int nowM = now.get(Calendar.MONTH);
		int nowD = now.get(Calendar.DATE);
		Date nowday = null;
		Date endday = null;
		
		int endY = 0, endM = 0 , endD = 0;
		endM = ( user.startmonth + user.ticket.month ) % 12;
		if(endM < user.startmonth)
			endY = user.startyear + 1;
		endD = user.startdate;
		
		try {
			if (nowM > 9) {
				nowday = sdf.parse(nowY + "-" + nowM + "-" + nowD);
			}
			else {
				nowday = sdf.parse(nowY + "-0" + nowM + "-" + nowD);
			}
			if (endM > 9) {
				endday = sdf.parse(endY + "-" + endM + "-" + endD);
			}
			else {
				endday = sdf.parse(endY + "-0" + endM + "-" + endD);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cmpDate = Calendar.getInstance();
		cmpDate.setTime(endday);
		
		int compare = endday.compareTo(nowday);
		if(compare > 0) { // endday > nowday : �Ⱓ ����.
			long diffSec = (cmpDate.getTimeInMillis() - now.getTimeInMillis() ) / 1000;
			long diffDays = diffSec / ( 24*60*60);
			user.leftday = (int)diffDays;
		}
		else // �Ⱓ ����
			user.ticket = null;

	}
}