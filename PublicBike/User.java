package PublicBike;

import java.util.*;


public class User implements Manageable {
	
	String name;
	String tel;
	String id;
	String pw;
	
	int pay;
	int point;
	
	boolean license(int i) {
		if (i == 1) return true;
		else return false;
	}
	
	ArrayList <RentData> useList = new ArrayList <>();
	
	@Override
	public void read(Scanner scan) {
		
	}
	@Override
	public void print() {
		
	}
	@Override
	public boolean matches(String str) {
		return true;
	}

	public boolean matches(String[] kwdArr) {
		for (String kwd: kwdArr) {
			if (!matches(kwd))
				return false;
		}
		return true;
	}
	
}
