package rental;

import java.util.Scanner;

import mgr.Factory;

public class RentSystem {
	static Scanner scan = new Scanner(System.in);
	static VehicleManager vehicleMgr = new VehicleManager();
	static UserManager userMgr = new UserManager();
	static RentSpotManager rentSpotMgr = new RentSpotManager();
	
	void run() {
		// ������ �б�
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		userMgr.printAll();
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			public Vehicle create() {
				return new Vehicle();
			}
		});
		vehicleMgr.printAll();
		rentSpotMgr.readAll("rentspot.txt", new Factory<RentSpot>() {
			public RentSpot create() {
				return new RentSpot();
			}
		});
		rentSpotMgr.printAll();
		start();
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}
}
