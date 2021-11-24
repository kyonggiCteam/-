package rental;

<<<<<<< Updated upstream
=======
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

<<<<<<< Updated upstream
	String id;  // scooter : S1234 (앞에 두 글자는 정류장, 뒤에 2글자는 개수)
				// Bicycle: B1234
	String spotname;  // 이 변수가 굳이 필요할까? -> 이 필드를 만들면 반납할 때마다 바꿔줘야 함.
	boolean crush;
=======
	String id; 
	String brandName;
	boolean crush;
	// 장비 리스트
	ArrayList<String> optionList = new ArrayList<>();
	
>>>>>>> Stashed changes
	//일일권에서 이용.
	int starthour;
	int startmin;
	// state 고려 !!
<<<<<<< Updated upstream
	
=======
//	int state; // 0: 이용가능 1: 이용중 -1: 고장
>>>>>>> Stashed changes
	// 시간 저장 함수
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
	@Override
	public void read(Scanner scan) {
		id = scan.next();
<<<<<<< Updated upstream
		spotname = scan.next();
	}
	@Override
	public void print() {
		if (id.contains("B"))
			System.out.printf("자전거/ 코드: %s, 자전거 위치: %s\n", id, spotname);
		else
			System.out.printf("전동킥보드/ 코드: %s, 자전거 위치: %s\n", id, spotname);
=======
		brandName = scan.next();
		String option = scan.next();
		while(!option.contentEquals("0")) {
			optionList.add(option);
			option = scan.next();
		}
	}
	@Override
	public void print() {
		if (id.contains("B")) {
			System.out.printf("자전거/ 코드: %s, 브랜드 이름: %s\n장비들:", id, brandName);
			for(String e : optionList)
				System.out.printf("%s ", e);
			System.out.println();
		}
		else {
			System.out.printf("킥보드/ 코드: %s, 브랜드 이름: %s\n장비들:", id, brandName);
			for(String e : optionList)
				System.out.printf("%s ", e);
			System.out.println();
		}
>>>>>>> Stashed changes
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
<<<<<<< Updated upstream
		if(spotname.equals(kwd))
			return true;
		return false;
	}
}
=======
//		if(spotname.equals(kwd))
//			return true;
		return false;
	}
}
>>>>>>> Stashed changes
