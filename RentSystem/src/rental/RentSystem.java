package rental;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class RentSystem {
	private static RentSystem rentSystem = null;
	private RentSystem() {}
	public static RentSystem getInstance() {
		if (rentSystem == null)
			rentSystem = new RentSystem();
		return rentSystem;
	}
	public static Scanner scan = new Scanner(System.in);
	public static VehicleManager vehicleMgr = new VehicleManager();
	public static UserManager userMgr = new UserManager();
	public static RentSpotManager rentSpotMgr = new RentSpotManager();
	public static Manager<Brand> brandMgr = new Manager<>();
	public static TicketManager ticketMgr = new TicketManager();
	
	public void run() {
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			public Vehicle create() {
				return new Vehicle();
			}
		});
		ticketMgr.readAll("ticket.txt", new Factory<Ticket>() {
			public Ticket create() {
				return new Ticket();
			}
		});
		rentSpotMgr.readAll("rentspot.txt", new Factory<RentSpot>() {
			public RentSpot create() {
				return new RentSpot();
			}
		});
		brandMgr.readAll("brand.txt", new Factory<Brand>() {
			public Brand create() {
				return new Brand();
			}
		});
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}
}