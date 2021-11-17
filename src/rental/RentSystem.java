package rental;

import java.util.Scanner;

import mgr.Factory;

public class RentSystem {
	static Scanner scan = new Scanner(System.in);
	static VehicleManager vehicleMgr = new VehicleManager();
	static UserManager userMgr = new UserManager();
	static RentSpotManager rentSpotMgr = new RentSpotManager();
	
	void run() {
		// 데이터 읽기
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
		// Step 1. 로그인 / 회원가입
		while (true) {
			System.out.print("1.로그인  2.회원가입 ");
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
		// 내 정보
		userMgr.myInfo();
		
		while (true) {
			// Step 2. 대여/반납위치 / 고장 신고 / 결제
			System.out.print("\n<메뉴>\n1.대여/반납 위치  2.고장 신고  3.결제  4.로그아웃  5.회원정보 수정 ");
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
