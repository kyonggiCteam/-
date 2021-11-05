package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable { //정기권 일일권으로 나누기에는 데이터가 너무 부족

	int price;
	String name;
	int month;
	int hour;
	
	@Override
	public void read(Scanner scan) {
		name = scan.next();
		price = scan.nextInt();
		if(name.equals("일일권"))
			hour = scan.nextInt();
		else
			month = scan.nextInt();	
	}
	@Override	
	public void print() {
		System.out.printf("[%s] 기간:  가격: %d원\n", name, price);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
	
}
