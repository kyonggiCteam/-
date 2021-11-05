package rent;

import java.util.Scanner;

import mgr.Manager;
import mgr.RentManager;
import mgr.UserManager;

public class RentSystem {
	static Scanner scan = new Scanner(System.in);
	static UserManager UserMgr = new UserManager();
	public static Manager<Vehicle> vehicleMgr = new Manager<>();
	static RentManager rentDataMgr = new RentManager();
	
	void run() {
		start();
		menu();
}
	void start() {
		// Step 1. 로그인 / 회원가입
		System.out.print("1. 로그인 2. 회원가입 ");
		int num = scan.nextInt();
		switch(num) {
		case 1: UserMgr.login(scan); break;
		case 2: UserMgr.join(scan); break;
		default: break;
		}
	}
	
	void menu() {
		// 내 정보
		UserMgr.myInfo();
		
		// Step 2. 대여/반납위치 / 고장 신고 / 결제
		System.out.print("1. 대여/반납 위치 2. 고장 신고 3. 결제 ");
		int num = scan.nextInt();
		switch(num) {
		case 1: rentDataMgr.rent(scan); break;
		case 2: break;
		case 3: break;
		default: break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem s = new RentSystem();
		s.run();
	}

}
