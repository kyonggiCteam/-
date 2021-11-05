package mgr;

import java.util.Scanner;

import rent.RentData;
import rent.RentSystem;
import rent.Vehicle;

public class RentManager extends Manager<RentData>{
	
	private Vehicle vehicle;
	
	// �뿩
	public void rent(Scanner scan) {
		System.out.println("�뿩�� ��ǰ�� �ڵ带 �Է��Ͻÿ�.");
		String code = scan.next();
		vehicle = RentSystem.vehicleMgr.find(code);
		if (vehicle.state == 0) {  // �뿩 ������ ���¶��
			vehicle.state = 1; // �����
		} else {
			System.out.println("�ش� ��ǰ�� �뿩�� �� �����ϴ�.");
		}
	}
	
	// �ݳ�
	public void returnVehicle() {
		if (vehicle.state == 1) {
			vehicle.state = 0; // �뿩 ����
		} else {
			System.out.println("�̹� �ݳ� �����Դϴ�.");
		}
	}
}
