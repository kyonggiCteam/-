package rental;

import java.util.Calendar;

public class Payment { // 결재 기능 수행

	// 추가 결재 기능 수행 -> 1시간당 500원 추가
	static public void morePay(int diff) {
		int pay = diff * 500;
	}
	// 포인트 계산 함수 -> 결제시 불러줌.
	void pointCount(User user, int payment) {
		user.point += payment / 100;
	}

	// 티켓 구입. => user의 ticket 값 변경.	
	void buyOnehourTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 1;
		pointCount(user, 1000);
	}

	void buytwohourTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 2;
		pointCount(user, 1500);
	}

	void buyOneMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 3;
		pointCount(user, 5000);
		// 시간계산
		setTime(user);
		user.leftday = 30;
	}
	void buysixMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 4;
		pointCount(user, 20000);
		setTime(user);
		user.leftday = 30*6;
	}
	void buyOneYearTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 3;
		pointCount(user, 30000);
		setTime(user);
		user.leftday = 30*12;
	}
	// 시간 set
	void setTime(User user) {
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH);
		user.startdate = now.get(Calendar.DATE);
	}
	
	

	
}
