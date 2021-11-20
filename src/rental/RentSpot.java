package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle 대여, 반납, 조회 기능 수행.

	String spotName; 
	// 일단은 자전거랑 킥보드 구분 안함.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot 데이터 읽을 때, 저장.
	

	// 조회
	void checkStock() {

		System.out.println("<자전거 코드>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("B"))
				System.out.printf("[ %s ]\n", v.id);
		}
		System.out.println("<전동 킥보드 코드>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("S"))
				System.out.printf("[ %s ]\n", v.id);
		}
		
	}
	@Override
	public void read(Scanner scan) {
		spotName = scan.next();
		String vehicleName = null;
		Vehicle vehicle = null;
		while(true) {
			vehicleName = scan.next();
			if(vehicleName.contentEquals("0"))	
				break;
			// 코드에 맞는 vehicle을 찾아줘야 함.  
			vehicle = RentSystem.vehicleMgr.find(vehicleName);
			vehicleList.add(vehicle);
		}
		
	}
	@Override
	public void print() {
		System.out.printf("정류장 이름: %s\n", spotName);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
}
