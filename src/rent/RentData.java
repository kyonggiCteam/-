package rent;

import java.util.Scanner;

import mgr.Manageable;

public class RentData implements Manageable {
	// Data : ���� ���� ���� (ȸ��, �뿩�ð�, �߱� ����, �뿩��, �뿩��ǰ ��)
	User user;
	Vehicle vehicle;
	int startTime;
	int endTime;
	String seasonTicket;
	String rentLocation;
	
	// Function : �뿩(����) �� �ݳ� ���
//	public void rent(Scanner scan) {
//		System.out.println("�뿩�� ��ǰ�� �ڵ带 �Է��Ͻÿ�.");
//		String code = scan.next();
//		vehicle = RentSystem.vehicleMgr.find(code);
//		if (vehicle.state == 0) {  // �뿩 ������ ���¶��
//			vehicle.state = 1; // �����
//		} else {
//			System.out.println("�ش� ��ǰ�� �뿩�� �� �����ϴ�.");
//		}
//	}
//	
//	public void returnVehicle() {
//		if (vehicle.state == 1) {
//			vehicle.state = 0; // �뿩 ����
//		} else {
//			System.out.println("�̹� �ݳ� �����Դϴ�.");
//		}
//	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
