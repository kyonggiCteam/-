package rental;

import java.util.ArrayList;
import java.util.Scanner;

public class RentSpot { // Vehicle �뿩, �ݳ�, ��ȸ ��� ����.

	String spotName; //
	// �ϴ��� �����Ŷ� ű���� ���� ����.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	// �뿩   ..�ϴ� ���� ����..
	void rentVehilce(User user, Scanner scan) {
		// code ã����
		Vehicle rent = null;
		String code = scan.next();
		for(Vehicle v : vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				rent = v;
			}
		}
		if(rent == null)
			System.out.println("�ش� ��ۼ����� �����ϴ�.");
	}
	
	// �ݳ�
	Vehicle returnVehicle(User user) {
		return user.rental;
	}
	
	// ��ȸ
	void checkStock() {

		System.out.println("<������ �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("B"))
				System.out.printf("[ %s ]\n", v.id);
		}
		System.out.println("<���� ű���� �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("S"))
				System.out.printf("[ %s ]\n", v.id);
		}
		
	}
}
