package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import mgr.Manager;

public class TicketManager extends Manager<Ticket> {

	public void buyTicket(Ticket ticket, User user) {
		if(ticket.ticketType.equals("정기권")) {
			startTicket(user);
		}
		pointCount(ticket.price, user);
		user.ticket = ticket;
	}

	void startTicket(User user) {
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH);
		user.startdate = now.get(Calendar.DATE);
		System.out.printf("시작 시간: %d년 %d 월 %d 일" , user.startyear, user.startmonth + 1, user.startdate);
	}
	
	void pointCount(int price, User user) {
		user.point += (int) (price * 0.01);
	}
	
	public void sortByPrice(ArrayList <Ticket> ticketList) {

		Collections.sort(ticketList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.price > t2.price)
					return 1;
				if(t1.price < t2.price)
					return -1;
				return 0;
			}
		});
	}

	public void sortByPeriod(ArrayList <Ticket> ticketList) {
		Collections.sort(ticketList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				if(t1.month > t2.month)
					return 1;
				if(t1.month < t2.month)
					return -1;
				if(t1.hour > t2.hour)
					return 1;
				if(t1.hour < t2.hour)
					return -1;
				return 0;
			}
		});
	}
	
	public void sortByBrand(ArrayList <Ticket> ticketList) {
		Collections.sort(ticketList, new Comparator<Ticket>() {
			public int compare(Ticket t1, Ticket t2) {
				for(int i = 0; i < t1.brandName.length(); i++) {
					if(t1.brandName.charAt(i) > t2.brandName.charAt(i))
						return 1;
					if(t1.brandName.charAt(i) < t2.brandName.charAt(i))
						return -1;
				}
				return 0;
			}
		});
	}		
}