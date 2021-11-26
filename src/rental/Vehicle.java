package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	public String code;  // scooter : S1234 (앞에 두 글자는 정류장, 뒤에 2글자는 개수)
				// Bicycle: B1234
	Brand brand;
//	boolean crush;
	int state; // 0: 이용가능 1: 이용중 -1: 고장
	//일일권에서 이용.
	
	ArrayList<String> optionList = new ArrayList<>();
	
	int starthour;
	int startmin;
	// state 고려 !!
	
	// 시간 저장 함수
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
	@Override
	public void read(Scanner scan) {
		code = scan.next();
		String brandName = scan.next();
		brand = RentSystem.brandMgr.find(brandName);
		
		String optionName = null;
		while(true) {
			optionName = scan.next();
			if(optionName.contentEquals("0"))	
				break;  
			optionList.add(optionName);
		}
	}
	
	@Override
	public void print() {
//		System.out.printf("장비 코드: %s, 브랜드: %s\n", code, brand.brandName);
		if (code.contains("B"))
			System.out.printf("자전거/ 코드: %s, 브랜드: %s\n", code, brand.brandName);
		else
			System.out.printf("전동킥보드/ 코드: %s, 브랜드: %s\n", code, brand.brandName);
	}
	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
		if(brand.brandName.equals(kwd))
			return true;
		return false;
	}
}