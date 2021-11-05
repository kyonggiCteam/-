package rent;

import java.util.Scanner;

import mgr.Manageable;

public class RentData implements Manageable {
	// Data : 결제 내역 정보 (회원, 대여시간, 발권 종류, 대여소, 대여물품 등)
	User user;
	Vehicle vehicle;
	int startTime;
	int endTime;
	String seasonTicket;
	String rentLocation;
	
	// Function : 대여(결제) 및 반납 기능
//	public void rent(Scanner scan) {
//		System.out.println("대여할 물품의 코드를 입력하시오.");
//		String code = scan.next();
//		vehicle = RentSystem.vehicleMgr.find(code);
//		if (vehicle.state == 0) {  // 대여 가능한 상태라면
//			vehicle.state = 1; // 사용중
//		} else {
//			System.out.println("해당 물품은 대여할 수 없습니다.");
//		}
//	}
//	
//	public void returnVehicle() {
//		if (vehicle.state == 1) {
//			vehicle.state = 0; // 대여 가능
//		} else {
//			System.out.println("이미 반납 상태입니다.");
//		}
//	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
