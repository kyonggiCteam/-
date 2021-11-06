package PublicBike;

import java.util.Scanner;

public class Vehicle implements Manageable {
	
	int code;
	String name;
	int plag;
	
	boolean using(int plag) {
		return true;
	}
	
	boolean broken(int plag) {
		return true;
	}
	
	boolean canRide() {
		if (using(plag)==false && broken(plag)==false)
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

class Bike extends Vehicle {
	
	Operator op = new Operator();
	String spotFinder;
	 
	RentSpot currentSpot = op.findSpot(spotFinder);
	
}

class KickBoard extends Vehicle {
	
}
