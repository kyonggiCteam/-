package rent;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class RentSpot implements Manageable {
	// Data : ��ġ ����, ������ �� ���� ��� ��Ȳ, �뿩�� ��� ����
	String location; // ���θ� �ּ�
	int stock;
	ArrayList<User> lender = new ArrayList<User>(); // �뿩�� ����Ʈ
	
	// Function : ��� ��Ȳ ���(��ȸ ���), �뿩�� ���ã�� ���
	public void printStock(Vehicle v) {
		System.out.printf("���� %d�� �뿩 �����մϴ�.", stock);
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
