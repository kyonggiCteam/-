package rental;

import java.util.Scanner;

import mgr.Factory;
<<<<<<< Updated upstream
=======
import mgr.Manager;
>>>>>>> Stashed changes

public class RentSystem {
	static Scanner scan = new Scanner(System.in);
	static VehicleManager vehicleMgr = new VehicleManager();
	static UserManager userMgr = new UserManager();
	static RentSpotManager rentSpotMgr = new RentSpotManager();
<<<<<<< Updated upstream
=======
	static Manager<Brand> brandMgr = new Manager<>();
	static Manager<Ticket> ticketMgr = new Manager<>();
	
	static Payment payment = new Payment();
>>>>>>> Stashed changes
	
	void run() {
		// ������ �б�
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
<<<<<<< Updated upstream
		userMgr.printAll();
=======
		//userMgr.printAll();
		
>>>>>>> Stashed changes
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			public Vehicle create() {
				return new Vehicle();
			}
		});
<<<<<<< Updated upstream
		vehicleMgr.printAll();
=======
		//rentspot�� vehicle �ڿ� �ɾ�� ��.
		//vehicleMgr.printAll();
		
>>>>>>> Stashed changes
		rentSpotMgr.readAll("rentspot.txt", new Factory<RentSpot>() {
			public RentSpot create() {
				return new RentSpot();
			}
		});
<<<<<<< Updated upstream
		rentSpotMgr.printAll();
		start();
=======
		//rentSpotMgr.printAll();
		
		ticketMgr.readAll("ticket.txt", new Factory<Ticket>() {
			public Ticket create() {
				return new Ticket();
			}
		});
		//ticketMgr.printAll();
		
		brandMgr.readAll("brand.txt", new Factory<Brand>() {
			public Brand create() {
				return new Brand();
			}
		});
		//brandMgr.printAll();
		
		payment.sortbypirce();
		ticketMgr.printAll();
		System.out.println();
		payment.sortbyperiod();
		ticketMgr.printAll();
		System.out.println();
		payment.sortbybrand();
		ticketMgr.printAll();
		
		
		
		
		
		//start();
>>>>>>> Stashed changes
}
	void start() {
		// Step 1. �α��� / ȸ������
		while (true) {
			System.out.print("1.�α���  2.ȸ������ ");
			int num = scan.nextInt();
			switch(num) {
			case 1: 
				userMgr.login(scan); 
				menu();
				break;
			case 2: userMgr.join(scan); break;
			default: break;
			}
			System.out.println();
		}
	}
	
	void menu() {
		// �� ����
		userMgr.myInfo();
		
		while (true) {
			// Step 2. �뿩/�ݳ���ġ / ���� �Ű� / ����
			System.out.print("\n<�޴�>\n1.�뿩/�ݳ� ��ġ  2.���� �Ű�  3.����  4.�α׾ƿ�  5.ȸ������ ���� ");
			int num = scan.nextInt();
			switch(num) {
			case 1: //rentSpotMgr.rentVehicle(scan); 
				break;
			case 2: vehicleMgr.breakdownreport(scan);break;
			case 3: //userMgr.pay(scan); 
				break;
			case 4: start(); break;
			case 5: userMgr.modify(scan); break;
			default: break;
			}
		}
	}
<<<<<<< Updated upstream
=======
	
//	void menu() {
//	// �� ����
//	userMgr.myPage();
//	
//	while (true) {
//		// Step 2(����ȭ��). �뿩 �� �ݳ� / �ҷ�/����Ű� / ����������
//		System.out.print("\n<�޴�>\n1.�뿩 �� �ݳ�  2.�ҷ�/����Ű�  3.����������  4.�α׾ƿ� ");
//		int num = scan.nextInt();
//		switch(num) {
//		case 1: //rentSpotMgr.rentVehicle(scan); 
//			RentalAndReturn();
//			break;
//		case 2: 
//			vehicleMgr.breakdownReport(scan);
//			vehicleMgr.printAll(); // Ȯ�ο�
//			break;
//		case 3: 
//			userMgr.buyTicket(scan); 
//			break;
//		case 4: start(); break;
//		case 5: userMgr.modify(scan); break;
//		default: 
//			System.out.println(">> �ٽ� �Է��ϼ���.");
//			break;
//		}
//	}
//}
//
//
//void RentalAndReturn() {
//	// �뿩 ���� : 1.��� ���� -> 2.��ã -> 3.�뿩 ���� -> 4.Ƽ�� ����(����) -> 5.��� ���� -> 6.�뿩
//	// �ݳ� ���� : 1.��� ���� -> 2.��ã -> 3.�ݳ� ����
//	userMgr.selectRentSpot(scan); // 1.��� ����
//	userMgr.addFavoriteRentSpot(scan); // 2.��ã
//	
//	System.out.print("1.�뿩  2.�ݳ� "); // 3.�뿩/�ݳ� ����
//	int num = scan.nextInt();
//	switch(num) {
//	case 1:
//		userMgr.rentalVehicle(scan);
//		break;
//	case 2:
//		userMgr.returnVehicle(scan);
//		break;
//	default: 
//		System.out.println(">> �ٽ� �Է��ϼ���.");
//		break;
//	}
//	
//	
//}
>>>>>>> Stashed changes

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
