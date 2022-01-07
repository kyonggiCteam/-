package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	public String code;  // ����ű���� : ù ���� S, ������: ù ���� B
	public String brandName;
	public int state; 			 // 0:�̿밡��  1:�̿���  -1:����
	public ArrayList<String> optionList = new ArrayList<>();
	int starthour;
	int startmin;
		
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	@Override
	public void read(Scanner scan) {
		code = scan.next();
		brandName = scan.next();
		state = scan.nextInt();
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
		if (code.charAt(0) == 'B')
			System.out.printf("[%s] ���: ������, ���� : %d, �귣��: %s\n", code, state, brandName);
		else if (code.charAt(0) == 'S')
			System.out.printf("[%s] ���: ����ű����, ���� : %d, �귣��: %s\n", code, state, brandName);
		else
			System.out.printf("[%s] ����: %d, �귣��: %s\n", code, state, brandName);
	}
	
	public String[] getTexts() {
		String options= "";
		String tmp = "";
		for (int i=0; i<optionList.size(); i++) {
			tmp = tmp + optionList.get(i) + ", ";
			options = tmp.substring(0, tmp.length()-2);
		}
		if (code.charAt(0) == 'B')
			return new String[] {code, brandName, "������", options};
		else if (code.charAt(0) == 'S')
			return new String[] {code, brandName, "����ű����", options};
		else
			return new String[] {code, brandName, "���", options};
	}
	
	@Override
	public boolean matches(String kwd) {
		if(code.equals(kwd))
			return true;
		if(brandName.equals(kwd))
			return true;
		return false;
	}
}