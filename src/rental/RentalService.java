package rental;

//import java.util.ArrayList;
import mgr.Factory;

public class RentalService {
	
	VehicleManager vehicleMgr = new VehicleManager();
	UserManager userMgr = new UserManager();
	TicketManager ticketMgr = new TicketManager();
	
	// * 아이디 고유한지 확인해줘야 함.
	
	
	// 고장 신고 -> crush의 상태 변경
	// => 일단 VehicleManager에서 구현.
	/*void breakdownreport() {
		
	}*/
	
	
	void run() {
		// Vehicle 데이터와 User 데이터 읽기 +) ticket의 데이터도 읽기
		userMgr.readAll("User.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		//userMgr.printAll();
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			@Override
			public Vehicle create() {
				return new Vehicle();
			}
		});
		//vehicleMgr.printAll();
		ticketMgr.readAll("ticket.txt", new Factory<Ticket>() {
			@Override
			public Ticket create() {
				return new Ticket();
			}
		});
		ticketMgr.printAll();
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentalService rental = new RentalService();
		rental.run();
	}

}
