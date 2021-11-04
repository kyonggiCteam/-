package project;

import java.util.Scanner;

import mgr.Manageable;

public class Vehicle implements Manageable{
	//위치 사용여부 고장여부
	String location;
	String use;
	String breakdown;
	String code;//자전거코드(전기자전거(BE)냐 일반자전거(BR)), 킥보드코드(K)
	
	@Override
	public void read(Scanner scan) {
		location = scan.next();
		use = scan.next();
		breakdown = scan.next();
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
	
	//고장여부
	void Break() {
		if(breakdown.contains("y"))
			System.out.print("고장");
		else
			System.out.print("사용가능");
	}

}
