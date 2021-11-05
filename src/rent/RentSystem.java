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
		// Step 1. �α��� / ȸ������
		System.out.print("1. �α��� 2. ȸ������ ");
		int num = scan.nextInt();
		switch(num) {
		case 1: UserMgr.login(scan); break;
		case 2: UserMgr.join(scan); break;
		default: break;
		}
	}
	
	void menu() {
		// �� ����
		UserMgr.myInfo();
		
		// Step 2. �뿩/�ݳ���ġ / ���� �Ű� / ����
		System.out.print("1. �뿩/�ݳ� ��ġ 2. ���� �Ű� 3. ���� ");
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
