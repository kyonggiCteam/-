package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	
	
	String code;
	Brand brand;
	int price;
	// 일일권, 정기권 구별 변수 int형 or String 형으로 받을까
	String ticketType;
	int onedayTime;
	int seasonMonth;
	

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		code = scan.next();
		String brandName = scan.next(); 
		brand = RentSystem.brandMgr.find(brandName);
		price = scan.nextInt();
		ticketType = scan.next();
		onedayTime = scan.nextInt();
		seasonMonth = scan.nextInt();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("[%s] 코드: %s  브랜드: %s 가격: %d ", ticketType, code, brand.brandName, price);
		if(onedayTime == 0) {
			System.out.printf("기간: %d 개월\n", seasonMonth);
		}
		else
			System.out.printf("기간: %d 시간\n", onedayTime);
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if(code.contentEquals(kwd))
			return true;
		return false;
	}

}
