package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	//L01 Lime 1200 ���ϱ� 1 0
	public String code;
	public String brandName;
	public int price;
	String ticketType;
	int hour;
	int month;
	
	@Override
	public void read(Scanner scan) {
		 code = scan.next();
		 brandName = scan.next();
		 price = scan.nextInt();
		 ticketType = scan.next();
		 hour = scan.nextInt();
		 month = scan.nextInt();
	}

	@Override
	public void print() {
		if (hour == 0)
			System.out.printf("[%s] %s %d�� %s %d����\n", code, brandName, price, ticketType, month);
		else if (month == 0)
			System.out.printf("[%s] %s %d�� %s %d�ð�\n", code, brandName, price, ticketType, hour);
		else
			System.out.printf("[%s] %s %d�� %s %d���� %d�ð�\n", code, brandName, price, ticketType, month, hour);	
	}
	
	public String[] getTexts() {
		if (hour == 0)
			return new String[] {brandName, "������/ű����", ticketType, price+"��", month+"����", code};
		else if (month == 0)
			return new String[] {brandName, "������/ű����", ticketType, price+"��", hour+"�ð�", code};
		else
			return new String[] {brandName, "������/ű����", ticketType, price+"��", month+"���� "+hour+"�ð�", code};
	}

	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
		if(ticketType.equals(kwd))
			return true;
		return false;
	}

}