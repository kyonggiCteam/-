package rent;

import java.util.Scanner;

import mgr.Manageable;

public class Vehicle implements Manageable {
	// Data : ���� ������(ű����) ��ġ, ��� �� ���� ������, ���� ���� ������
	String code;
	String location;
	public int state; // 0:�뿩���� / 1:����� / -1:����
	
	// Function : ���� �Ű� �� ���� ���� ���
	public void breakDown() {
		state = -1; // ����
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
