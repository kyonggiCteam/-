package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable { //����� ���ϱ����� �����⿡�� �����Ͱ� �ʹ� ����

	int price;
	String name;
	int month;
	int hour;
	
	@Override
	public void read(Scanner scan) {
		name = scan.next();
		price = scan.nextInt();
		if(name.equals("���ϱ�"))
			hour = scan.nextInt();
		else
			month = scan.nextInt();	
	}
	@Override	
	public void print() {
		System.out.printf("[%s] �Ⱓ:  ����: %d��\n", name, price);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
	
}
