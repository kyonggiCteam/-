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
		// 데이터 읽기
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
		
		System.out.println("--------------------------------User 모든 정보-----------------------------------");
		userMgr.printAll();
		System.out.println("--------------------------------Brand 모든 정보-----------------------------------");
		brandMgr.printAll();
		System.out.println("--------------------------------Vehicle 모든 정보-----------------------------------");
		vehicleMgr.printAll();
		System.out.println("--------------------------------RentSpot 모든 정보-----------------------------------");
		rentSpotMgr.printAll();
		System.out.println("--------------------------------Ticket 모든 정보-----------------------------------");
		ticketMgr.printAll();
		
		System.out.println("------------------------가격순 티켓 정렬------------------------");
		ticketMgr.sortByPrice(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		System.out.println("------------------------기간별 티켓 정렬------------------------");
		ticketMgr.sortByPeriod(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		System.out.println("------------------------브랜드별 티켓 정렬------------------------");
		ticketMgr.sortByBrand(RentSystem.ticketMgr.mList);
		ticketMgr.printAll();
		
//		start();
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
			// Step 2(메인화면). 대여 및 반납 / 불량/고장신고 / 마이페이지
			System.out.print("\n<메뉴>\n1.대여 및 반납  2.불량/고장신고  3.마이페이지  4.로그아웃 ");
			int num = scan.nextInt();
			switch(num) {
			case 1: //rentSpotMgr.rentVehicle(scan); 
				RentalAndReturn();
				break;
			case 2: 
				vehicleMgr.breakdownReport(scan);
				vehicleMgr.printAll(); // 확인용
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
				System.out.println(">> 다시 입력하세요.");
				break;
			}
		}
	}
	
	void RentalAndReturn() {
		// 대여 과정 : 1.장소 선택 -> 2.즐찾 -> 3.대여 선택 -> 4.티켓 정렬 -> 5.티켓 구매(결제) -> 6.장비 선택 -> 7.대여
		// 반납 과정 : 1.장소 선택 -> 2.즐찾 -> 3.반납 선택
		userMgr.selectSpot(scan); // 1.장소 선택
		userMgr.addFavoriteSpot(scan); // 2.즐찾
		
		System.out.print("1.대여  2.반납 "); // 3.대여/반납 선택
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
			System.out.println(">> 다시 입력하세요.");
			break;
		}
	}
	
	void sortTicket() {
		System.out.print("티켓 정렬 방식을 고르세요.\n (1)가격순  (2)기간별  (3)브랜드별 ");
		int num = scan.nextInt();
		switch(num) {
		case 1:
			System.out.println("----------------------가격순 티켓 정렬--------------------------");
			ticketMgr.sortByPrice(RentSystem.ticketMgr.mList);
			break;
		case 2:
			System.out.println("----------------------기간별 티켓 정렬--------------------------");
			ticketMgr.sortByPeriod(RentSystem.ticketMgr.mList);
			break;
		case 3:
			System.out.println("----------------------브랜드별 티켓 정렬--------------------------");
			ticketMgr.sortByBrand(RentSystem.ticketMgr.mList);
		default: 
			System.out.println(">> 다시 입력하세요.");
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