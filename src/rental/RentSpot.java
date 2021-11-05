package rental;

import java.util.ArrayList;
import java.util.Scanner;

public class RentSpot { // Vehicle 대여, 반납, 조회 기능 수행.

	String spotName; //
	// 일단은 자전거랑 킥보드 구분 안함.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	// 대여   ..일단 대충 구현..
	void rentVehilce(User user, Scanner scan) {
		// code 찾아줌
		Vehicle rent = null;
		String code = scan.next();
		for(Vehicle v : vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				rent = v;
			}
		}
		if(rent == null)
			System.out.println("해당 운송수단이 없습니다.");
	}
	
	// 반납
	Vehicle returnVehicle(User user) {
		return user.rental;
	}
	
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
}
