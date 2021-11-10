package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle �뿩, �ݳ�, ��ȸ ��� ����.

	String spotName; 
	// �ϴ��� �����Ŷ� ű���� ���� ����.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot ������ ���� ��, ����.
	

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
	@Override
	public void read(Scanner scan) {
		spotName = scan.next();
	}
	@Override
	public void print() {
		System.out.printf("������ �̸�: %s\n", spotName);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
}
