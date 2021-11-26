package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user = new User();
	RentSpot rentSpot;
	RentSpot returnSpot;
	
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
				updateDate();
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
			System.out.println("�̹� ��� ���� ���̵��Դϴ�.");
			return;
		}
		System.out.println(">> ��� ������ ���̵��Դϴ�.");
		System.out.print("��й�ȣ Ȯ��: ");
		String checkpw = scan.next();
		if(!user.passwordMatch(checkpw)) {
			System.out.println(">> ��ҹ�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
		mList.add(user);
	}
	
//	// ȸ�� Ż��
//	public void withdraw(Scanner scan) {
//		System.out.println("<Ż��>");
//		System.out.println("��й�ȣ�� �Է����ּ���.");
//		if (user.passwordMatch(scan.next())) {
//			mList.remove(user);
//			logout(scan);
//		}
//	}
	
	// �α׾ƿ�
	public void logout(Scanner scan) {
		login(scan);
	}
	
	// Ƽ�� ����
	void buyTicket(Scanner scan) {
		System.out.println("�����ϰ� ���� Ƽ���� ������(ticketCode �Է�)");
		String ticketCode = scan.next();
		Ticket ticket = RentSystem.payMgr.find(ticketCode);
		if (user.ticket != null) {
			System.out.printf(">> ���� ���� ���� Ƽ���� %s�Դϴ�.\n", user.ticket);
			return;
		}
		if (user.vehicle != null) {
			System.out.println(">> �̹� ��� �뿩���Դϴ�.");
			return;
		}
		
		// ���� ȭ��
		System.out.printf("�����Ͻ� Ƽ���� %s�Դϴ�. ������ %d���Դϴ�. �����Ͻðڽ��ϱ�?\n���� ���� ����Ʈ�� %d�Դϴ�."
									, ticket, ticket.price, user.point);
		// ���� ����
		user.ticket = ticket;
		startTicket();
//		pointCount(ticket.ticketPrice); //�ݳ� �ÿ� �����ǵ���?
	}
	
	// ����Ʈ ��� �Լ�
	void pointCount(int price) {
		user.point += (int) (price * 0.01); // 1% ����
	}
	
	// �ð� ���� --> RentManager?
	void startTicket() {
		System.out.println(">> ������ �Ϸ�Ǿ����ϴ�! *��ǰ ���� �뿩 �� �̿�� �̿��� ���۵˴ϴ�.*");
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH) + 1;
		user.startdate = now.get(Calendar.DATE);
	}
	
	//leftmonth, leftdate ����
	void updateDate() { // 1���� 30���̶�� ����.
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
	
	void addFavoriteRentSpot(Scanner scan) {
		System.out.println(">> ���ã�� �뿩�ҷ� ����� �뿩���� �ڵ带 �Է��ϼ���."); //�뿩�� id�� ���� ����� �� ���� ����? �̸� �߸� �Է½� Ȯ�� ���
		String spotCode = scan.next();
		RentSpot rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		user.favoriteRentSpotList.add(rentSpot);
		// + �̹� ������ add ���� ������ ó�� !!!
	}
	
	void selectRentSpot(Scanner scan) {
		System.out.println("������ ��� �̸��� �Է��ϼ���. "); //�׽�Ʈ�� ���� ����. GUI������ ��� ��ư Ŭ�� �� �̸��� �ν��ؼ� ó���ؾ� �� ��. id�� ������ �� �� ���� ����
		String spotName = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotName);
	}
	
	// �뿩 ���� : 1.��� ���� -> 2.��ã -> 3.�뿩/�ݳ� ���� -> 4.Ƽ�� ����(����) -> 5.��� ���� -> 6.�뿩
	void rentalVehicle(Scanner scan) {
		// Ƽ�� ����
		buyTicket(scan); 
		
		// ��� ����
		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
		String vehicleCode = scan.next();
		// ���� ���� Ȯ��.
		if(vehicleCode.contains("S")) {
			if(user.license == 0) {
				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
				return;
			}
		}
		
		// ������� ���� //
		// ������ �����, ������ Ƽ���� �귣���� ���鸸 ������� �� or �׷��� ��� ������ �� �ְ� �ؾ� ��
		for(Vehicle v : rentSpot.vehicleList) {
			
			if(v.code.equals(vehicleCode)) {
				user.vehicle = v;
				rentSpot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
				user.vehicle.setTime();
			}
		}
		if(user.vehicle == null)
			System.out.println("�ش� ��ۼ����� �����ϴ�.");

	}
	
	// �ݳ�
	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
	void returnVehicle(Scanner scan) {
//	void returnVehicle(User user, RentSpot spot) {
		// ���ϱ��� ��� �ð����
		Vehicle tmp = user.vehicle;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		
		if(user.ticket.hour == 1) { // 1�ð���
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // �ݳ�.
	}
}