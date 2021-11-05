package mgr;

import java.util.Scanner;

import rent.RentData;
import rent.RentSystem;
import rent.Vehicle;

public class RentManager extends Manager<RentData>{
	
	private Vehicle vehicle;
	
	// 대여
	public void rent(Scanner scan) {
		System.out.println("대여할 물품의 코드를 입력하시오.");
		String code = scan.next();
		vehicle = RentSystem.vehicleMgr.find(code);
		if (vehicle.state == 0) {  // 대여 가능한 상태라면
			vehicle.state = 1; // 사용중
		} else {
			System.out.println("해당 물품은 대여할 수 없습니다.");
		}
	}
	
	// 반납
	public void returnVehicle() {
		if (vehicle.state == 1) {
			vehicle.state = 0; // 대여 가능
		} else {
			System.out.println("이미 반납 상태입니다.");
		}
	}
}
