package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle �뿩, �ݳ�, ��ȸ ��� ����.
<<<<<<< Updated upstream

	String spotName; 
	// �ϴ��� �����Ŷ� ű���� ���� ����.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot ������ ���� ��, ����.
	

=======
	int spotCode;
	String areaName;
	String spotName; 
	// �ϴ��� �����Ŷ� ű���� ���� ����.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot ������ ���� ��, ����.
	// ���� vehicle�� �갡 ������ ������ ��. �׷��� �˻��� ��, ����Ű�� vehicle �����ֱ�

	// ��ü�� -> ���� ��ü�� ������ �����Ƿ� List�� �������ҵ�
	ArrayList<Brand> brandList	= new ArrayList<>();
	//���ã�� ���
	int favorite;  // 1: ���ã��
	//rentspot code �߰��ص� �� ��.
	
	
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		spotName = scan.next();
=======
		spotCode = scan.nextInt();
		areaName = scan.next();
		spotName = scan.next();
		String brandName = null;
		Brand brand = null;
		while(true) {
			brandName = scan.next();
			if(brandName.contentEquals("0"))	
				break;
			brand = RentSystem.brandMgr.find(brandName);
			brandList.add(brand);
		}
>>>>>>> Stashed changes
		String vehicleName = null;
		Vehicle vehicle = null;
		while(true) {
			vehicleName = scan.next();
			if(vehicleName.contentEquals("0"))	
				break;
<<<<<<< Updated upstream
			// �ڵ忡 �´� vehicle�� ã����� ��.  
=======
			// �ڵ忡 �´� vehicle�� ã����� ��. -> so, vehicle�� �а� spot�� �о����.
>>>>>>> Stashed changes
			vehicle = RentSystem.vehicleMgr.find(vehicleName);
			vehicleList.add(vehicle);
		}
		
	}
	@Override
	public void print() {
<<<<<<< Updated upstream
		System.out.printf("������ �̸�: %s\n", spotName);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
}
=======
		System.out.printf("���� �̸�: %s, ������ �̸�: %s	\n", areaName, spotName);
	}
	
//	@Override
//	public void print() {
//		print(true);
//	}
//
//   public void print(boolean bDetail) {
//	   System.out.printf("�뿩�� �̸�: (%s) %s\n", Gu, spotName);
//	   if (!bDetail)
//		   return;
//	   
//	   System.out.println("[�귣�� ����Ʈ]");
//	   for (Brand brand: brandList)
//		   brand.print();
//	   
//	   System.out.println("[��� ����Ʈ]");
//	   for (Vehicle vehicle: vehicleList)
//		   vehicle.print();
//   }
	
	@Override
	public boolean matches(String kwd) {
		if(spotName.contentEquals(kwd)) {
			return true;
		}
		return false;
	}
}
>>>>>>> Stashed changes
