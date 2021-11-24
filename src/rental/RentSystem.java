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
		// 데이터 읽기
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
		//rentspot을 vehicle 뒤에 읡어야 함.
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
<<<<<<< Updated upstream
=======
	
//	void menu() {
//	// 내 정보
//	userMgr.myPage();
//	
//	while (true) {
//		// Step 2(메인화면). 대여 및 반납 / 불량/고장신고 / 마이페이지
//		System.out.print("\n<메뉴>\n1.대여 및 반납  2.불량/고장신고  3.마이페이지  4.로그아웃 ");
//		int num = scan.nextInt();
//		switch(num) {
//		case 1: //rentSpotMgr.rentVehicle(scan); 
//			RentalAndReturn();
//			break;
//		case 2: 
//			vehicleMgr.breakdownReport(scan);
//			vehicleMgr.printAll(); // 확인용
//			break;
//		case 3: 
//			userMgr.buyTicket(scan); 
//			break;
//		case 4: start(); break;
//		case 5: userMgr.modify(scan); break;
//		default: 
//			System.out.println(">> 다시 입력하세요.");
//			break;
//		}
//	}
//}
//
//
//void RentalAndReturn() {
//	// 대여 과정 : 1.장소 선택 -> 2.즐찾 -> 3.대여 선택 -> 4.티켓 구매(결제) -> 5.장비 선택 -> 6.대여
//	// 반납 과정 : 1.장소 선택 -> 2.즐찾 -> 3.반납 선택
//	userMgr.selectRentSpot(scan); // 1.장소 선택
//	userMgr.addFavoriteRentSpot(scan); // 2.즐찾
//	
//	System.out.print("1.대여  2.반납 "); // 3.대여/반납 선택
//	int num = scan.nextInt();
//	switch(num) {
//	case 1:
//		userMgr.rentalVehicle(scan);
//		break;
//	case 2:
//		userMgr.returnVehicle(scan);
//		break;
//	default: 
//		System.out.println(">> 다시 입력하세요.");
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
