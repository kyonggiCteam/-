package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	public String code;  // 전동킥보드 : 첫 글자 S, 자전거: 첫 글자 B
	public String brandName;
	public int state; 			 // 0:이용가능  1:이용중  -1:고장
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
			System.out.printf("[%s] 장비: 자전거, 상태 : %d, 브랜드: %s\n", code, state, brandName);
		else if (code.charAt(0) == 'S')
			System.out.printf("[%s] 장비: 전동킥보드, 상태 : %d, 브랜드: %s\n", code, state, brandName);
		else
			System.out.printf("[%s] 상태: %d, 브랜드: %s\n", code, state, brandName);
	}
	
	public String[] getTexts() {
		String options= "";
		String tmp = "";
		for (int i=0; i<optionList.size(); i++) {
			tmp = tmp + optionList.get(i) + ", ";
			options = tmp.substring(0, tmp.length()-2);
		}
		if (code.charAt(0) == 'B')
			return new String[] {code, brandName, "자전거", options};
		else if (code.charAt(0) == 'S')
			return new String[] {code, brandName, "전동킥보드", options};
		else
			return new String[] {code, brandName, "장비", options};
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