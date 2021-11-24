package rental;

import java.util.Scanner;

import mgr.Manageable;

public class Ticket implements Manageable {
	
	
	String code;
	Brand brand;
	int price;
	// ���ϱ�, ����� ���� ���� int�� or String ������ ������
	String ticketType;
	int onedayTime;
	int seasonMonth;
	

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		code = scan.next();
		String brandName = scan.next(); 
		brand = RentSystem.brandMgr.find(brandName);
		price = scan.nextInt();
		ticketType = scan.next();
		onedayTime = scan.nextInt();
		seasonMonth = scan.nextInt();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("[%s] �ڵ�: %s  �귣��: %s ����: %d ", ticketType, code, brand.brandName, price);
		if(onedayTime == 0) {
			System.out.printf("�Ⱓ: %d ����\n", seasonMonth);
		}
		else
			System.out.printf("�Ⱓ: %d �ð�\n", onedayTime);
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if(code.contentEquals(kwd))
			return true;
		return false;
	}

}
