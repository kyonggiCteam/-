package rental;

import java.util.Calendar;
<<<<<<< Updated upstream

public class Payment { // ���� ��� ����

	// �߰� ���� ��� ���� -> 1�ð��� 500�� �߰�
	static public void morePay(int diff) {
=======
import java.util.Collections;
import java.util.Comparator;


public class Payment { // ���� ��� ����
	//������ Payment�� ���� UserManager�� ���� ���� ����
	
	// �߰� ���� ��� ���� -> 1�ð��� 500�� �߰�
	public void morePay(int diff) {
>>>>>>> Stashed changes
		int pay = diff * 500;
	}
	// ����Ʈ ��� �Լ� -> ������ �ҷ���.
	void pointCount(User user, int payment) {
		user.point += payment / 100;
	}
<<<<<<< Updated upstream

	// Ƽ�� ����. => user�� ticket �� ����.	
	void buyOnehourTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 1;
		pointCount(user, 1000);
	}

	void buytwohourTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 2;
		pointCount(user, 1500);
	}

	void buyOneMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 3;
		pointCount(user, 5000);
		// �ð����
		setTime(user);
		user.leftday = 30;
	}
	void buysixMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 4;
		pointCount(user, 20000);
		setTime(user);
		user.leftday = 30*6;
	}
	void buyOneYearTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 3;
		pointCount(user, 30000);
		setTime(user);
		user.leftday = 30*12;
	}
=======
	// �����Լ�
	void sortbypirce() {

		Collections.sort(RentSystem.ticketMgr.mList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.price > t2.price)
					return 1;
				if(t1.price < t2.price)
					return -1;
				return 0;
			}
		});
	}
	void sortbyperiod() {
		Collections.sort(RentSystem.ticketMgr.mList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.seasonMonth > t2.seasonMonth)
					return 1;
				if(t1.seasonMonth < t2.seasonMonth)
					return -1;
				if(t1.onedayTime > t2.onedayTime)
					return 1;
				if(t1.onedayTime < t2.onedayTime)
					return -1;
				return 0;
			}
		});
	}
	void sortbybrand() {
		Collections.sort(RentSystem.ticketMgr.mList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				for(int i = 0; i < t1.brand.brandName.length(); i++) {
					if(t1.brand.brandName.charAt(i) > t2.brand.brandName.charAt(i))
						return 1; // ���
					if(t1.brand.brandName.charAt(i) < t2.brand.brandName.charAt(i))
						return -1; // ����
				}
				return 0;  // 0
			}
		});
	}
	void buyTicket(User user, Ticket buyticket) {
		if(user.ticket != null) {
			if(buyticket.seasonMonth != 0) // ������̸� �ð� set
				setTime(user);
			user.ticket = buyticket;
		}
	}
	int usePoint(int point, User user, Ticket ticket) {
		user.point -= point;
		return ( ticket.price - point );
	}

	// Ƽ�� ����. => user�� ticket �� ����.	
//	void buyOnehourTicket(User user) {
//		if (user.ticket != 0) {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return;
//		}
//		user.ticket = 1;
//		pointCount(user, 1000);
//	}
//
//	void buytwohourTicket(User user) {
//		if (user.ticket != 0) {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return;
//		}
//		user.ticket = 2;
//		pointCount(user, 1500);
//	}
//
//	void buyOneMonthTicket(User user) {
//		if (user.ticket != 0) {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return;
//		}
//		user.ticket = 3;
//		pointCount(user, 5000);
//		// �ð����
//		setTime(user);
//		user.leftday = 30;
//	}
//	void buysixMonthTicket(User user) {
//		if (user.ticket != 0) {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return;
//		}
//		user.ticket = 4;
//		pointCount(user, 20000);
//		setTime(user);
//		user.leftday = 30*6;
//	}
//	void buyOneYearTicket(User user) {
//		if (user.ticket != 0) {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return;
//		}
//		user.ticket = 3;
//		pointCount(user, 30000);
//		setTime(user);
//		user.leftday = 30*12;
//	}
>>>>>>> Stashed changes
	// �ð� set
	void setTime(User user) {
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH);
		user.startdate = now.get(Calendar.DATE);
	}
	
	

	
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
