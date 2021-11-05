package rent;

import java.util.Scanner;

import mgr.Manageable;

public class Vehicle implements Manageable {
	// Data : 현재 자전거(킥보드) 위치, 사용 중 여부 데이터, 고장 여부 데이터
	String code;
	String location;
	public int state; // 0:대여가능 / 1:사용중 / -1:고장
	
	// Function : 고장 신고 및 고장 여부 출력
	public void breakDown() {
		state = -1; // 고장
	}
//	public int getState() {
//		return this.state;
//	}
//	
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
