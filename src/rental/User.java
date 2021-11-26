package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	public String id;
	public String pwd;
	public String name;
	public String phoneNumber;
	public Vehicle vehicle;
	public int point;
	int burnedCalories;
	public int license; // 0: 면허 미보유  1: 면허 보유
	public Ticket ticket;
	int startyear;  
	int startmonth;
	int startdate;
	// 남은 날짜 -> 이거 갱신이 필요한데 => 로그인 할때마다 갱신하는 거 어떨까
	// 로그인 할때 정기권 유무 확인후, 현재 날짜, 시작 날짜 비교해서 남은 날짜 계산.
	int leftday;
	ArrayList<RentSpot> favoriteRentSpotList = new ArrayList<>(); //즐겨찾는 대여소
	
	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pwd = scan.next();
		license = scan.nextInt();		
	}
	
	@Override	
	public void print() {
		System.out.printf("[%s] 아이디: %s, 전화번호: %s, 포인트: %d점, 면허보유여부: %d, 보유 이용권: ", 
				name, id, phoneNumber, point, license);
		// GUI에서 메인 메뉴에 보이는 거 표현
		if(ticket == null) {
			System.out.println("없음");
		} else {
			if (ticket.ticketType == "일일권")
				System.out.printf("[%s] %d시간 이용권", ticket.ticketType, ticket.hour);
			else
				System.out.printf("[%s] %d개월 이용권, %d일 남음", ticket.ticketType, ticket.month, leftday);
		}
	}
	
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pwd.equals(kwd))
			return true;
//		if(name.equals(kwd))
//			return true;
		return false;
	}
	
	// 비밀번호 일치 확인
	public boolean passwordMatch(String pwd) {
		return this.pwd.equals(pwd);
	}
	
	// 정보 수정
	public void modifyInfo(Scanner scan) {	
		String tmp = null;
		
		System.out.print("새암호(변경없음 엔터): ");
		scan.nextLine();  // 추가해주어야 tmp에 값이 잘 들어감
		tmp = scan.nextLine();
		if (!tmp.equals(""))  // 엔터가 아니면
			pwd = tmp;
		
		System.out.printf("이름(%s): ", name);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			name = tmp;
		
		System.out.printf("전화번호(%s): ", phoneNumber);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			phoneNumber = tmp;
		
		System.out.printf("면허보유여부(%d): ", license);
		tmp = scan.nextLine();
		if (!tmp.equals(""))
			license = (int) Integer.parseInt(tmp);
	}

}