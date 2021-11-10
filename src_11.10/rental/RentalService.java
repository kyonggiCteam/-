package rental;

//import java.util.ArrayList;
import mgr.Factory;
import mgr.Manager;

public class RentalService {
	
	VehicleManager vehicleMgr = new VehicleManager();
	UserManager userMgr = new UserManager();
	Manager rentspotMgr = new Manager();
	//TicketManager ticketMgr = new TicketManager();
	
	// * ���̵� �������� Ȯ������� ��.
	
	
	// ���� �Ű� -> crush�� ���� ����
	// => �ϴ� VehicleManager���� ����.
	/*void breakdownreport() {
		
	}*/
	
	
	void run() {
		// Vehicle �����Ϳ� User ������ �б� +) ticket�� �����͵� �б�
		userMgr.readAll("User.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		userMgr.printAll();
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			@Override
			public Vehicle create() {
				return new Vehicle();
			}
		});
		vehicleMgr.printAll();
		// rentspotMgr�� ������ �б�.
		
		

		/*ticketMgr.readAll("ticket.txt", new Factory<Ticket>() {
			@Override
			public Ticket create() {
				return new Ticket();
			}
		});
		ticketMgr.printAll();*/
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentalService rental = new RentalService();
		rental.run();
	}

}
