package rental;

import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	String id;  // scooter : S1234 (앞에 두 글자는 정류장, 뒤에 2글자는 개수)
				// Bicycle: B1234
	String spotname;  // 이 변수가 굳이 필요할까? -> 이 필드를 만들면 반납할 때마다 바꿔줘야 함.
	boolean crush;
	//일일권에서 이용.
	int starthour;
	int startmin;
	
	// 시간 저장 함수
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
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
