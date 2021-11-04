package project;

import java.util.Scanner;

import mgr.Manageable;

public class RentSpot implements Manageable{
	//위치정보, 대여자 목록 정보(아이디 비밀번호)
	String location;
	
	
	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		location = scan.next();
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
