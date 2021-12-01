package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import mgr.Manager;

public class TicketManager extends Manager<Ticket> { // 결제 기능 수행
//	Brand brand;
//	String brandName;
	Ticket ticket;
	
	// 티켓 결제( 콘솔 상의 것 수정)
	public void buyTicket(Ticket ticket,  User user) { // user에 티켓 넣어줌 + 시간계산 + 포인트 계산
		if(ticket.ticketType.equals("정기권")) { // 시간 계산 필요.
			startTicket(user);
		}
		// 포인트도 계산
		pointCount(ticket.price, user);
		user.ticket = ticket;
	}
	
	// 시간 세팅 
	void startTicket(User user) { // user 변수 시간 넣어줌
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH);
		user.startdate = now.get(Calendar.DATE);
		System.out.printf("시작 시간: %d년 %d 월 %d 일" , user.startyear, user.startmonth + 1, user.startdate);
	}
	
	// 포인트 계산 함수
	void pointCount(int price, User user) {
		user.point += (int) (price * 0.01); // 1% 적립
	}
	// 추가 결제 기능 수행 -> 1시간당 500원 추가
	public int morePay(int usemin) {
		return ( (usemin / 60 ) + 1 ) * 500;
	}
	
	// 정렬함수
	//가격 정렬
	public void sortByPrice(ArrayList <Ticket> ticketList) {

		Collections.sort(ticketList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.price > t2.price)
					return 1;
				if(t1.price < t2.price)
					return -1;
				return 0;
			}
		});
	}
	// 기간 정렬
	public void sortByPeriod(ArrayList <Ticket> ticketList) {
		Collections.sort(ticketList, new Comparator<Ticket>() {
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
//						return 1; // 양수
//					if(t1.brand.brandName.charAt(i) < t2.brand.brandName.charAt(i))
//						return -1; // 음수
//				}
//				return 0;  // 0
//			}
//		});
	// 브랜드 이름 따라
	public void sortByBrand(ArrayList <Ticket> ticketList) {
		Collections.sort(ticketList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				for(int i = 0; i < t1.brandName.length(); i++) {
					if(t1.brandName.charAt(i) > t2.brandName.charAt(i))
						return 1; // 양수
					if(t1.brandName.charAt(i) < t2.brandName.charAt(i))
						return -1; // 음수
				}
				return 0;  // 0
			}
		});
	}
	
	// ??
	public ArrayList <Ticket> extractticketList(String brandname) {
		
		ArrayList <Ticket> removeList = new ArrayList<>();
		ArrayList <Ticket> originalList = new ArrayList<>();
		
		for (Ticket t : RentSystem.ticketMgr.mList) {
			originalList.add(t);
			if (!t.brandName.equals(brandname)) {
				removeList.add(t);
			}
		}	
		originalList.removeAll(removeList);
		ArrayList <Ticket> searchList = originalList;
		return searchList;
	}
		
		
//	// 포인트 계산 함수 -> 결제시 불러줌.
//	void pointCount(User user, int payment) {
//		user.point += payment / 100;
//	}
//
//	boolean haveTicket(Ticket ticket) {
//		if (ticket == null) {
//			return false;
//		} else {
//			System.out.println("티켓을 이미 소유하고 있습니다");
//			return true;
//		}
//	}
//	
//	void buyTicket(String TicketId) {
//		ticket = find(TicketId);
//	}
//	
//	// 티켓 구입. => user의 ticket 값 변경.	
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
//		// 시간계산
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
//	// 시간 set
//	void setTime(User user) {
//		Calendar now = Calendar.getInstance();
//		user.startyear = now.get(Calendar.YEAR);
//		user.startmonth = now.get(Calendar.MONTH);
//		user.startdate = now.get(Calendar.DATE);
//	}	
}