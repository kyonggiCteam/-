package rent;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class RentSpot implements Manageable {
	// Data : 위치 정보, 자전거 및 보드 재고 현황, 대여자 목록 정보
	String location; // 도로명 주소
	int stock;
	ArrayList<User> lender = new ArrayList<User>(); // 대여자 리스트
	
	// Function : 재고 현황 출력(조회 기능), 대여소 즐겨찾기 기능
	public void printStock(Vehicle v) {
		System.out.printf("현재 %d개 대여 가능합니다.", stock);
	}

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
