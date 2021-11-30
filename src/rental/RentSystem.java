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
		// ������ �б�
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
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
		
		System.out.println("--------------------------------User ��� ����-----------------------------------");
		userMgr.printAll();
		System.out.println("--------------------------------Brand ��� ����-----------------------------------");
		brandMgr.printAll();
		System.out.println("--------------------------------Vehicle ��� ����-----------------------------------");
		vehicleMgr.printAll();
		System.out.println("--------------------------------RentSpot ��� ����-----------------------------------");
		rentSpotMgr.printAll();
		System.out.println("--------------------------------Ticket ��� ����-----------------------------------");
		ticketMgr.printAll();
		
		System.out.println("------------------------���ݼ� Ƽ�� ����------------------------");
		ticketMgr.sortByPrice(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		System.out.println("------------------------�Ⱓ�� Ƽ�� ����------------------------");
		ticketMgr.sortByPeriod(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		System.out.println("------------------------�귣�庰 Ƽ�� ����------------------------");
		ticketMgr.sortByBrand(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		
//		start();
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
			case 2: 
//				userMgr.join(scan);
				break;
			default: break;
			}
			System.out.println();
		}
	}
	
	void menu() {
		while (true) {
			// Step 2(����ȭ��). �뿩 �� �ݳ� / �ҷ�/����Ű� / ����������
			System.out.print("\n<�޴�>\n1.�뿩 �� �ݳ�  2.�ҷ�/����Ű�  3.����������  4.�α׾ƿ� ");
			int num = scan.nextInt();
			switch(num) {
			case 1: //rentSpotMgr.rentVehicle(scan); 
				RentalAndReturn();
				break;
			case 2: 
				vehicleMgr.breakdownReport(scan);
				vehicleMgr.printAll(); // Ȯ�ο�
				break;
			case 3: 
				userMgr.myPage(); 
				break;
			case 4: 
				start(); 
				break;
			case 5: 
				userMgr.modify(scan); 
				break;
			default: 
				System.out.println(">> �ٽ� �Է��ϼ���.");
				break;
			}
		}
	}
	
	void RentalAndReturn() {
		// �뿩 ���� : 1.��� ���� -> 2.��ã -> 3.�뿩 ���� -> 4.Ƽ�� ���� -> 5.Ƽ�� ����(����) -> 6.��� ���� -> 7.�뿩
		// �ݳ� ���� : 1.��� ���� -> 2.��ã -> 3.�ݳ� ����
		userMgr.selectSpot(scan); // 1.��� ����
		userMgr.addFavoriteSpot(scan); // 2.��ã
		
		System.out.print("1.�뿩  2.�ݳ� "); // 3.�뿩/�ݳ� ����
		int num = scan.nextInt();
		switch(num) {
		case 1:
			sortTicket();
			userMgr.rentalVehicle(scan);
			break;
		case 2:
			userMgr.returnVehicle(scan);
			break;
		default: 
			System.out.println(">> �ٽ� �Է��ϼ���.");
			break;
		}
	}
	
	void sortTicket() {
		System.out.print("Ƽ�� ���� ����� ������.\n (1)���ݼ�  (2)�Ⱓ��  (3)�귣�庰 ");
		int num = scan.nextInt();
		switch(num) {
		case 1:
			System.out.println("----------------------���ݼ� Ƽ�� ����--------------------------");
			ticketMgr.sortByPrice(RentSystem.ticketMgr.mList);
			break;
		case 2:
			System.out.println("----------------------�Ⱓ�� Ƽ�� ����--------------------------");
			ticketMgr.sortByPeriod(RentSystem.ticketMgr.mList);
			break;
		case 3:
			System.out.println("----------------------�귣�庰 Ƽ�� ����--------------------------");
			ticketMgr.sortByBrand(RentSystem.ticketMgr.mList);
		default: 
			System.out.println(">> �ٽ� �Է��ϼ���.");
			break;
		}
		ticketMgr.printAll();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}
}