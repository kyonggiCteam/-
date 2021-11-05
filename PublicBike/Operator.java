package PublicBike;

import java.util.ArrayList;

public class Operator extends Manager {
	
	public static void main(String[] args) {

		Operator o = new Operator();
		o.run();
	}
	
	void run() {
		
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

}
