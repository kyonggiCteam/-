package project;

import java.util.Scanner;

import mgr.Manageable;

public class RentSpot implements Manageable{
	//��ġ����, �뿩�� ��� ����(���̵� ��й�ȣ)
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
