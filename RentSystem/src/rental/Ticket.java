package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	public String code;
	public String brandName;
	public int price;
	public String ticketType;
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
		// ticket�� �귣�� �̸����� ã����
		String vehiclekind = null;
		if(brandName.contentEquals("Lime") || brandName.contentEquals("beam") || brandName.contentEquals("SWING") ||
				brandName.contentEquals("������")) { // ű������ ���
			vehiclekind = "ű����";
		} else if (brandName.contentEquals("������")) { // ������/ű����
			vehiclekind = "������/ű����";
		} else {
			vehiclekind = "������";
		}
		if (hour == 0)
			return new String[] {brandName, vehiclekind, ticketType, price+"��", month+"����", code};
		else if (month == 0)
			return new String[] {brandName, vehiclekind, ticketType, price+"��", hour+"�ð�", code};
		else
			return new String[] {brandName, vehiclekind, ticketType, price+"��", month+"���� "+hour+"�ð�", code};
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