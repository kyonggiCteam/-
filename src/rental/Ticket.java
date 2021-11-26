package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	//L01 Lime 1200 ���ϱ� 1 0
	public String code;
//	Brand brand;
	String brandName; //�ӽ�
	int price;
	String ticketType;
	int hour;
	int month;
	
	@Override
	public void read(Scanner scan) {
		 code = scan.next();
//		 String brandName = scan.next(); 
//		 brand = RentSystem.brandMgr.find(brandName); //brandMgr �ӽ÷� ����� �ߴµ�,,
		 brandName = scan.next();
		 price = scan.nextInt();
		 ticketType = scan.next();
		 hour = scan.nextInt();
		 month = scan.nextInt();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	public String[] getTexts() {
		return new String[] {brandName, "ű����/������", ticketType, ""+price, "${month}���� ${hour}�ð�"};
	}

	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
//		if(brandName.equals(kwd)) // �귣�庰 Ƽ�� Ž�� �� �ʿ� ����
//			return true;
		if(ticketType.equals(kwd))
			return true;
		return false;
	}

}