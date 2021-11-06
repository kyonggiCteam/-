package PublicBike;

import java.util.ArrayList;

public class Operator extends Manager implements Factory <Manageable> {
	
	public static void main(String[] args) {
		Operator o = new Operator();
		AFrame1 myFrame = new AFrame1();
		o.run();
		myFrame.run();
	}
	int num;
	
	void run() {
		readAllFile("bike.txt", mList, 1, this);
		readAllFile("kickboard.txt", mList, 2, this);
		readAllFile("rentspot.txt", mList, 3, this);
	}

	static Vehicle findVehicle(String kwd) {
		return (Vehicle)find(kwd, mList);
	}
	static User findUser(String kwd) {
		return (User)find(kwd, mList);
	}
	static RentSpot findSpot(String kwd) {
		return (RentSpot)find(kwd, mList);
	}
	static Ticket findTicket(String kwd) {
		return (Ticket)find(kwd, mList);
	}

	static Manageable find(String kwd, ArrayList<Manageable> mList) {
		Manageable selectM = null;
		for (Manageable m: mList) {
			if (m.matches(kwd)) {
				selectM = m;
			}
		}
		return selectM;
	}

	@Override
	public Manageable create(int i) {
		Manageable m = null;
		switch(num) {
		case 1: m = new Bike(); break;
		case 2: m = new KickBoard(); break;
		case 3: m = new RentSpot(); break;	
		}
		return m;
	}

}
