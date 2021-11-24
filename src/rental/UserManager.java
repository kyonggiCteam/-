package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user = new User();
	
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
	public void myInfo() {
		user.print();
	}
	
	// ȸ�� ���� ����
	public void modify(Scanner scan) {
		System.out.print("������ȣ: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println(">> ��ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
	}
	
	// ���̵� �ߺ� Ȯ��
	public boolean idDuplicate(String id) {
		return (find(id) != null);
	}
	
	// ȸ�� ����
	public void join(Scanner scan) {	
		User user = new User();
		System.out.println("ȸ�� ����(�̸�, ��ȭ��ȣ, ���̵�, ��й�ȣ, ��������������(0 or 1)�� �Է����ּ���.");
		user.read(scan);
		if (idDuplicate(user.id)) {
			System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
			return;
		}
		System.out.print("��й�ȣ Ȯ��: ");
		String checkpw = scan.next();
		if(!user.passwordMatch(checkpw)) {
			System.out.println("��ҹ�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
		mList.add(user);
	}
	
<<<<<<< Updated upstream
//	// ȸ�� Ż��
//	public void withdraw(Scanner scan) {
//		System.out.println("<Ż��>");
//		System.out.println("��й�ȣ�� �Է����ּ���.");
//		if (user.passwordMatch(scan.next())) {
//			mList.remove(user);
//			logout(scan);
//		}
//	}
//	
=======
>>>>>>> Stashed changes
//	// �α׾ƿ�
//	public void logout(Scanner scan) {
//		//login(scan);
//		//exit(0);
//	}
<<<<<<< Updated upstream
	
//	// ����
//	// Ƽ�Ͽ� ���� �ٸ��� �ݾ�, �ð� ����
//	public void pay(Scanner scan) {
//		System.out.println(" (1) [���ϱ�] 1�ð� - 1000��  (2) [���ϱ�] 2�ð� - 1500��\n"
//				+ " (3) [�����] 1���� - 5000��  (4) [�����] 6���� - 20000��  (5) [�����] 1�� - 30000��");
//		
//		if (user.ticket != 0) {
//			System.out.println("�̹� �̿���� �����ϰ� �ֽ��ϴ�.");
//			return;
//		} 
//		
//		System.out.print("������ �̿���� �����ϼ���. ");
//		int num = scan.nextInt();
//		switch(num) {
//		case 1:
//			user.ticket = 1; 
//			startTicket();
//			pointCount(1000); 
//			break;
//		case 2:
//			user.ticket = 2;
//			startTicket();
//			pointCount(1500);
//			break;
//		case 3:
//			user.ticket = 3;
//			startTicket();
//			pointCount(5000);
//			break;
//		case 4:
//			user.ticket = 4;
//			startTicket();
//			pointCount(20000);
//			break;
//		case 5:
//			user.ticket = 5;
//			startTicket();
//			pointCount(30000);
//			break;
//		default: break;
//		}
//	}
//	
=======
>>>>>>> Stashed changes
//	// ����Ʈ ��� �Լ�
//	void pointCount(int price) {
//		user.point += (int) (price * 0.01); // 1% ����
//	}
//	
//	// �ð� ���� --> RentManager?
//	void startTicket() {
//		System.out.println(">> ������ �Ϸ�Ǿ����ϴ�! *��ǰ ���� �뿩 �� �̿�� �̿��� ���۵˴ϴ�.*");
////		Calendar now = Calendar.getInstance();
////		user.startyear = now.get(Calendar.YEAR);
////		user.startmonth = now.get(Calendar.MONTH) + 1;
////		user.startdate = now.get(Calendar.DATE);
//	}
	
	//leftmonth, leftdate ����
	void updateDate(User user) { // 1���� 30���̶�� ����.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int nowY = now.get(Calendar.YEAR);
		int nowM = now.get(Calendar.MONTH);
		int nowD = now.get(Calendar.DATE);
		Date nowday = null;
		Date endday = null;
		
		int endY = 0, endM = 0 , endD = 0;
<<<<<<< Updated upstream
		if(user.ticket == 3) { // 1����
			endM = ( user.startmonth + 1 ) % 12;
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
		if(user.ticket == 4) { // 6����
			endM = ( user.startmonth + 6 ) % 12;
=======
		// 3���� �߰�
//		if(user.ticket.seasonMonth == 3) { //  3����
//			endM = ( user.startmonth + 3 ) % 12;
//			if(endM < user.startmonth)
//				endY = user.startyear + 1;
//			endD = user.startdate;
//		}
//		if(user.ticket.seasonMonth == 6) { // 6����
//			endM = ( user.startmonth + 6 ) % 12;
//			if(endM < user.startmonth)
//				endY = user.startyear + 1;
//			endD = user.startdate;
//		}
		if(user.ticket.seasonMonth == 12) { // 12����
			endM = user.startmonth;
			endY = user.startyear + 1;
			endD = user.startdate;
		}
		else{ // 1����, 3, 6 ����
			endM = ( user.startmonth + user.ticket.seasonMonth ) % 12;
>>>>>>> Stashed changes
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
<<<<<<< Updated upstream
		if(user.ticket == 5) { // 12����
			endM = user.startmonth;
			endY = user.startyear + 1;
			endD = user.startdate;
		}
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
			user.ticket = 0;

	}
}
=======
			user.ticket = null;
	}
	
	
//	// Ƽ�� ����
//	void buyTicket(Scanner scan) {
//		System.out.println("�����ϰ� ���� Ƽ���� ������(ticketId �Է�)");
//		String ticketId = scan.next();
//		Ticket ticket = RentSystem.PayMgr.find(ticketId);
//		if (ticket == null || user.ticket != null) {
//			System.out.println("Ƽ���� �������� �ʰų� �̹� �����ϰ� �ֽ��ϴ�.");
//			return;
//		}
//		if (user.vehicle != null) {
//			System.out.println("�̹� ��� �뿩���Դϴ�.");
//			return;
//		}
//		user.ticket = ticket;
//		startTicket();
////		pointCount(ticket.ticketPrice); //�ݳ� �ÿ� �����ǵ���?
//	}
//	
//	void addFavoriteRentSpot(Scanner scan) {
//		System.out.println("���ã�� �뿩�ҷ� ����� �뿩���� �̸��� �Է��ϼ���."); //�뿩�� id�� ���� ����� �� ���� ����? �̸� �߸� �Է½� Ȯ�� ���
//		String rentSpotName = scan.next();
//		RentSpot rentSpot = RentSystem.rentSpotMgr.find(rentSpotName);
//		user.favoriteRentSpotList.add(rentSpot);
//		// + �̹� ������ add ���� ������ ó�� !!!
//	}
//	
//	void selectRentSpot(Scanner scan) {
//		System.out.println("������ ��� �̸��� �Է��ϼ���. "); //�׽�Ʈ�� ���� ����. GUI������ ��� ��ư Ŭ�� �� �̸��� �ν��ؼ� ó���ؾ� �� ��. id�� ������ �� �� ���� ����
//		String spotName = scan.next();
//		rentSpot = RentSystem.rentSpotMgr.find(spotName);
//	}
//	
//	// �뿩 ���� : 1.��� ���� -> 2.��ã -> 3.�뿩/�ݳ� ���� -> 4.Ƽ�� ����(����) -> 5.��� ���� -> 6.�뿩
//	void rentalVehicle(Scanner scan) {
//		// Ƽ�� ����
//		buyTicket(scan); 
//		
//		// ��� ����
//		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
//		String code = scan.next();
//		// ���� ���� Ȯ��.
//		if(code.contains("S")) {
//			if(user.license == 0) {
//				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
//				return;
//			}
//		}
//		
//		// ������� ���� //
//		
//		for(Vehicle v : rentSpot.vehicleList) {
//			if(v.id.equals(code)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
//				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
//				user.vehicle.setTime();
//			}
//		}
//		if(user.vehicle == null)
//			System.out.println("�ش� ��ۼ����� �����ϴ�.");
//
//	}
//	
//	// �ݳ�
//	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
//	void returnVehicle(Scanner scan) {
////	void returnVehicle(User user, RentSpot spot) {
//		// ���ϱ��� ��� �ð����
//		Vehicle tmp = user.vehicle;
//		Calendar now = Calendar.getInstance();
//		int nowh = now.get(Calendar.HOUR);
//		int nowm = now.get(Calendar.MINUTE);
//		
//		if(user.ticket.hour == 1) { // 1�ð���
//			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
//				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		if(user.ticket.hour == 2) { // 2�ð���
//			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		user.vehicle = null;
//		rentSpot.vehicleList.add(tmp); // �ݳ�.
//	}
}
>>>>>>> Stashed changes
