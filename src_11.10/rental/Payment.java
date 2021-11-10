package rental;

import java.util.Calendar;

public class Payment { // ���� ��� ����

	// �߰� ���� ��� ����
	static public void morepay() {
		
	}
	// ����Ʈ ��� �Լ� -> ������ �ҷ���.
	void pointCount(User user, int payment) {
		user.point += payment / 100;
	}

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
	}
	void buysixMonthTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 4;
		pointCount(user, 20000);
		setTime(user);
	}
	void buyOneYearTicket(User user) {
		if (user.ticket != 0) {
			System.out.println("Ƽ���� �̹� �����ϰ� �ֽ��ϴ�");
			return;
		}
		user.ticket = 3;
		pointCount(user, 30000);
		setTime(user);
	}
	// �ð� set
	void setTime(User user) {
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH) + 1;
		user.startdate = now.get(Calendar.DATE);
	}
	
	

	
}
