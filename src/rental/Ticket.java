package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	//L01 Lime 1200 일일권 1 0
	public String code;
//	Brand brand;
	String brandName; //임시
	int price;
	String ticketType;
	int hour;
	int month;
	
	@Override
	public void read(Scanner scan) {
		 code = scan.next();
//		 String brandName = scan.next(); 
//		 brand = RentSystem.brandMgr.find(brandName); //brandMgr 임시로 만들긴 했는데,,
		 brandName = scan.next();
		 price = scan.nextInt();
		 ticketType = scan.next();
		 hour = scan.nextInt();
		 month = scan.nextInt();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	public String[] getTexts() {
		return new String[] {brandName, "킥보드/자전거", ticketType, ""+price, "${month}개월 ${hour}시간"};
	}

	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
//		if(brandName.equals(kwd)) // 브랜드별 티켓 탐색 시 필요 예쌍
//			return true;
		if(ticketType.equals(kwd))
			return true;
		return false;
	}

}