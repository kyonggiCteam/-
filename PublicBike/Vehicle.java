package PublicBike;

import java.util.Scanner;

public class Vehicle implements Manageable {
	
	int code;
	String name;
	int i;
	
	boolean using(int i) {
		return true;
	}
	
	boolean broken(int i) {
		return true;
	}
	
	boolean canRide() {
		if (using(i)==false && broken(i)==false)
			return true;
		return false;
	}

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
