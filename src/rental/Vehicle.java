package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	public String code;  // scooter : S1234 (�տ� �� ���ڴ� ������, �ڿ� 2���ڴ� ����)
				// Bicycle: B1234
	Brand brand;
//	boolean crush;
	int state; // 0: �̿밡�� 1: �̿��� -1: ����
	//���ϱǿ��� �̿�.
	
	ArrayList<String> optionList = new ArrayList<>();
	
	int starthour;
	int startmin;
	// state ��� !!
	
	// �ð� ���� �Լ�
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
	@Override
	public void read(Scanner scan) {
		code = scan.next();
		String brandName = scan.next();
		brand = RentSystem.brandMgr.find(brandName);
		
		String optionName = null;
		while(true) {
			optionName = scan.next();
			if(optionName.contentEquals("0"))	
				break;  
			optionList.add(optionName);
		}
	}
	
	@Override
	public void print() {
//		System.out.printf("��� �ڵ�: %s, �귣��: %s\n", code, brand.brandName);
		if (code.contains("B"))
			System.out.printf("������/ �ڵ�: %s, �귣��: %s\n", code, brand.brandName);
		else
			System.out.printf("����ű����/ �ڵ�: %s, �귣��: %s\n", code, brand.brandName);
	}
	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
		if(brand.brandName.equals(kwd))
			return true;
		return false;
	}
}