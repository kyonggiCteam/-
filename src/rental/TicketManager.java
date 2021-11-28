package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import mgr.Manager;

public class TicketManager extends Manager<Ticket> { // ���� ��� ����
//	Brand brand;
//	String brandName;
	Ticket ticket;
	
	// �߰� ���� ��� ���� -> 1�ð��� 500�� �߰�
	static public void morePay(int diff) {
		int pay = diff * 500;
	}
	
	// �����Լ�
	public void sortByPrice() {

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
	
	public void sortByPeriod() {
		Collections.sort(RentSystem.ticketMgr.mList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.month > t2.month)
					return 1;
				if(t1.month < t2.month)
					return -1;
				if(t1.hour > t2.hour)
					return 1;
				if(t1.hour < t2.hour)
					return -1;
				return 0;
			}
		});
	}
	
//	void sortByBrand() {
//		Collections.sort(RentSystem.payMgr.mList, new Comparator<Ticket>() {
//			public int compare(Ticket t1, Ticket t2) {
//				for(int i = 0; i < t1.brand.brandName.length(); i++) {
//					if(t1.brand.brandName.charAt(i) > t2.brand.brandName.charAt(i))
//						return 1; // ���
//					if(t1.brand.brandName.charAt(i) < t2.brand.brandName.charAt(i))
//						return -1; // ����
//				}
//				return 0;  // 0
//			}
//		});
	public void sortByBrand() {
	Collections.sort(RentSystem.ticketMgr.mList, new Comparator<Ticket>() {
		public int compare(Ticket t1, Ticket t2) {
			for(int i = 0; i < t1.brandName.length(); i++) {
				if(t1.brandName.charAt(i) > t2.brandName.charAt(i))
					return 1; // ���
				if(t1.brandName.charAt(i) < t2.brandName.charAt(i))
					return -1; // ����
			}
			return 0;  // 0
		}
	});
	}
		
		
//	// ����Ʈ ��� �Լ� -> ������ �ҷ���.
//	void pointCount(User user, int payment) {
//		user.point += payment / 100;
//	}
//
//	boolean haveTicket(Ticket ticket) {
//		if (ticket == null) {
//			return false;
//		} else {
//			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
//			return true;
//		}
//	}
//	
//	void buyTicket(String TicketId) {
//		ticket = find(TicketId);
//	}
//	
//	// Ƽ�� ����. => user�� ticket �� ����.	
//	void buyOnehourTicket(User user) {
//		if (haveTicket(ticket)) return;
//	
//		user.ticket = 1;
//		pointCount(user, 1000);
//	}
//
//	void buytwohourTicket(User user) {
//		if (haveTicket(ticket)) return;
//		
//		user.ticket = 2;
//		pointCount(user, 1500);
//	}
//
//	void buyOneMonthTicket(User user) {
//		if (haveTicket(ticket)) return;
//		
//		user.ticket = 3;
//		pointCount(user, 5000);
//		// �ð����
//		setTime(user);
//		user.leftday = 30;
//	}
//	void buysixMonthTicket(User user) {
//		if (haveTicket(ticket)) return;
//		
//		user.ticket = 4;
//		pointCount(user, 20000);
//		setTime(user);
//		user.leftday = 30*6;
//	}
//	void buyOneYearTicket(User user) {
//		if (haveTicket(ticket)) return;
//		
//		user.ticket = 3;
//		pointCount(user, 30000);
//		setTime(user);
//		user.leftday = 30*12;
//	}
//	// �ð� set
//	void setTime(User user) {
//		Calendar now = Calendar.getInstance();
//		user.startyear = now.get(Calendar.YEAR);
//		user.startmonth = now.get(Calendar.MONTH);
//		user.startdate = now.get(Calendar.DATE);
//	}	
}
