package rental;

import java.util.Calendar;

public class Payment { // 결재 기능 수행

	// 추가 결재 기능 수행
	static public void morepay() {
		
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
	}
	void buysixMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 4;
		pointCount(user, 20000);
		setTime(user);
	}
	void buyOneYearTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("티켓을 이미 소유하고 있습니다");
			return;
		}
		user.ticket = 3;
		pointCount(user, 30000);
		setTime(user);
	}
	// 시간 set
	void setTime(User user) {
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH) + 1;
		user.startdate = now.get(Calendar.DATE);
	}
	
	

	
}
