package rental;

import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	String id;  // scooter : S1234 (앞에 두 글자는 정류장, 뒤에 2글자는 개수)
				// Bicycle: B1234
	String spotname;
	boolean crush;
	// 시간 계산을 해줘야하므로 추후 시작시간, 도착 시간 추가...
	
	
	@Override
	public void read(Scanner scan) {
		id = scan.next();
		spotname = scan.next();
	}
	@Override
	public void print() {
		if (id.contains("B"))
			System.out.printf("자전거/ 코드: %s, 자전거 위치: %s\n", id, spotname);
		else
			System.out.printf("전동킥보드/ 코드: %s, 자전거 위치: %s\n", id, spotname);
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(spotname.equals(kwd))
			return true;
		return false;
	}
}
