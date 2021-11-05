package PublicBike;

import java.util.*;

public class RentSpot implements Manageable {
	
	String name;
	int code;

	
	ArrayList <Vehicle> parkingList = new ArrayList <> ();
	
	@Override
	public void read(Scanner sc) {
		
	}
	
	@Override
	public void print() {
		
	}
	
	@Override
	public boolean matches(String kwd) {
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
