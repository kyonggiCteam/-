package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	public String name;
	public String phoneNumber;
	public String id;
	public String pwd;
	public int license; // 0:면허 미보유  1:면허 보유
	public String ticketCode;
	public String vehicleCode;
	public Vehicle vehicle;
	public int point;
	int burnedCalories;
	public Ticket ticket;
	public int startyear;  
	public int startmonth;
	public int startdate;
	public int leftday;
	public ArrayList<String> oftenSpotList = new ArrayList<>();
	
	@Override
	public void read(Scanner scan) { // 이름 전화번호 아이디 pw 면허여부 ticket vehicle 년 월 일 point 선호정류장
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pwd = scan.next();
		license = scan.nextInt();	
		ticketCode = scan.next(); // 1201 수정
		// ticket 참조 변수 찾아주기
		if(!ticketCode.equals("null")) { // null이 아니면 찾아줘야함.  => user안에서는 
			ticket = RentSystem.ticketMgr.find(ticketCode);
			if(ticket == null)
				System.out.println("ticket 널");
			else
				System.out.println("널아님");
		}
		vehicleCode = scan.next(); // 1201 수정
		if(!vehicleCode.equals("null")) {  // 널이 아니면
			vehicle = RentSystem.vehicleMgr.find(vehicleCode);
		}
		point = scan.nextInt(); // 1201 수정
		startyear = scan.nextInt();
		startmonth = scan.nextInt();
		startdate = scan.nextInt();
		String spotName = null;
		while(true) {
			spotName = scan.next();
			if(spotName.contentEquals("end"))	
				break;  
			oftenSpotList.add(spotName);
		}
	}
	
	@Override	
	public void print() {
		System.out.printf("[%s] 아이디: %s, 전화번호: %s, 포인트: %d점, 면허보유여부: %d, 보유 이용권: ", 
				name, id, phoneNumber, point, license);
		if (ticket == null) {
			System.out.println("없음");
		} else {
			if (ticket.ticketType == "일일권")
				System.out.printf("<%s> %d시간 이용권\n", ticket.ticketType, ticket.hour);
			else
				System.out.printf("<%s> %d개월 이용권, %d일 남음\n", ticket.ticketType, ticket.month, leftday);
		}
		System.out.println("<즐겨찾는 대여소>");
		if (oftenSpotList.isEmpty()) {
			System.out.println("즐겨찾는 대여소가 없습니다.");
			return;
		}
		for (String spot: oftenSpotList)
			System.out.println(spot);
	}
	
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(name.equals(kwd))
			return true;
		return false;
	}
	
	public boolean passwordMatch(String pwd) {
		return this.pwd.equals(pwd);
	}
}