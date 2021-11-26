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
	static VehicleManager vehicleMgr = new VehicleManager();
//	public static UserManager userMgr = new UserManager();
	public static UserManager userMgr = UserManager.getInstance();
	static RentSpotManager rentSpotMgr = new RentSpotManager();
//	static Manager<Ticket> payMgr = new Manager<>();
	static Manager<Brand> brandMgr = new Manager<>();
	public static Payment payMgr = new Payment();
	
	public void run() {
		// ������ �б�
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		brandMgr.readAll("brand.txt", new Factory<Brand>() {
			public Brand create() {
				return new Brand();
			}
		});
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			public Vehicle create() {
				return new Vehicle();
			}
		});
		rentSpotMgr.readAll("rentspot.txt", new Factory<RentSpot>() {
			public RentSpot create() {
				return new RentSpot();
			}
		});
		payMgr.readAll("ticket.txt", new Factory<Ticket>() {
			public Ticket create() {
				return new Ticket();
			}
		});
		
		userMgr.printAll();
		brandMgr.printAll();
		vehicleMgr.printAll();
		rentSpotMgr.printAll();
		payMgr.printAll();
		
		//start();
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
				//userMgr.join(scan); 
				userMgr.printAll(); // Ȯ�ο�
				break;
			default: break;
			}
			System.out.println();
		}
	}
	
	void menu() {
		// �� ����
		userMgr.myPage();
		
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
				userMgr.buyTicket(scan); 
				break;
			case 4: start(); break;
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
		// �뿩 ���� : 1.��� ���� -> 2.��ã -> 3.�뿩 ���� -> 4.Ƽ�� ���� -> 5..Ƽ�� ����(����) -> 6.��� ���� -> 7.�뿩
		// �ݳ� ���� : 1.��� ���� -> 2.��ã -> 3.�ݳ� ����
		userMgr.selectRentSpot(scan); // 1.��� ����
		userMgr.addFavoriteRentSpot(scan); // 2.��ã
		
		System.out.print("1.�뿩  2.�ݳ� "); // 3.�뿩/�ݳ� ����
		int num = scan.nextInt();
		switch(num) {
		case 1:
			System.out.println("----------------------���ݼ� Ƽ�� ����--------------------------");
			payMgr.sortByPrice();
			System.out.println("----------------------�Ⱓ�� Ƽ�� ����--------------------------");
			payMgr.sortByPeriod();
			System.out.println("----------------------�귣�庰 Ƽ�� ����--------------------------");
			payMgr.sortByBrand();
			
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}
}