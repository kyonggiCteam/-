package rental;

import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {

	String id;  
	String pw;
	String name;
	String phoneNumber;
	int point;
	Vehicle rental;
	boolean license; // 면허 유무
	//정기권 일일권 유무
	int ticket; // 0: 대여권 없음   1: 일일권(1시간) 2: 일일권(2시간)
				// 3: 정기권(1개월) 2: 정기권(6개월) 3: 정기권(12개월 = 1년)
	 
	int startyear;  
	int startmonth;
	int startdate;
	// 남은 날짜 -> 이거 갱신이 필요한데.. 로그인 할때마다 갱신하는 거 어떰?
	// 로그인 할때 정기권 유무 확인후, 현재 날짜, 시작 날짜 비교해서 남은 날짜 계산.
	int leftmonth;
	int leftdate;
	
	void vehicleprint() {
		if(rental != null) {
			System.out.printf("대여중\t\n %s", rental.id );
		}
	}

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pw = scan.next();
		int state = scan.nextInt();
		if(state == 1)
			license = true;
		else
			license = false;
		
	}
	@Override	
	public void print() {
		// GUI에서 메인 메뉴에 보이는 거 표현
		System.out.printf("%s  %s  %d\n", id, name, point);
		if(ticket != 0) {
			if(ticket == 1)
				System.out.println("[일일권]: 1시간 이용");
			else if(ticket == 2)
				System.out.println("[일일권]: 2시간 이용");
			else {
				System.out.printf("[정기권]\t%d개월 %d일 남음", leftmonth, leftdate);
				
			}
	
		}
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pw.equals(kwd))
			return true;
		if(name.equals(kwd))
			return true;
		return false;
	}
	
}
