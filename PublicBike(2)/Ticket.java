package PublicBike;

import java.util.*;

public class Ticket implements Manageable, Factory <Ticket> {
	Scanner sc = new Scanner(System.in);
	int price;
	Date startDate = new Date();
	Date endDate = new Date();
	
	public Ticket create(int flag) {
		Ticket ticket = new Ticket();
		switch(flag) {
		case 1: ticket = new oneHourTicket(); break;
		case 2: ticket = new twoHoursTicket(); break; 
		case 3: ticket = new oneMonthTicket(); break;
		case 4: ticket = new halfYearTicket(); break;
		case 5: ticket = new oneYearTicket(); break;
		}
		return ticket;
	}
	
	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean matches(String str) {
		// TODO Auto-generated method stub
		
		return false;
	}
}


class oneHourTicket extends Ticket {
	
}
class twoHoursTicket extends Ticket {

}
class oneMonthTicket extends Ticket {

}
class halfYearTicket extends Ticket {

}
class oneYearTicket extends Ticket {

}