package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle �뿩, �ݳ�, ��ȸ ��� ����.
	int spotCode;
	String areaName;
	String spotName;
	ArrayList<Brand> brandList = new ArrayList<>();
	public static ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot ������ ���� ��, ����.

	// ��ȸ
	void checkStock() {
		System.out.println("<������ �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.code.contains("B"))
				System.out.printf("[ %s ]\n", v.code);
		}
		System.out.println("<���� ű���� �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.code.contains("S"))
				System.out.printf("[ %s ]\n", v.code);
		}	
	}
	@Override
	public void read(Scanner scan) {
		spotCode = scan.nextInt();
		areaName = scan.next();
		spotName = scan.next();
		
		String brandName = null;
		Brand brand = null;
		while(true) {
			brandName = scan.next();
			if(brandName.contentEquals("0"))	
				break;  
			brand = RentSystem.brandMgr.find(brandName); //brandMgr �ӽ÷� ����� �ߴµ�,,
			brandList.add(brand);
		}
		
		String vehicleName = null;
		Vehicle vehicle = null;
		while(true) {
			vehicleName = scan.next();
			if(vehicleName.contentEquals("0"))	
				break;  
			vehicle = RentSystem.vehicleMgr.find(vehicleName);
			vehicleList.add(vehicle);
		}
		
	}
	@Override
	public void print() {
		print(true);
	}

   public void print(boolean bDetail) {
	   System.out.printf("�뿩�� �ڵ�: %d �̸�: (%s) %s\n", spotCode, areaName, spotName);
	   if (!bDetail)
		   return;
	   
	   System.out.println("[�귣�� ����Ʈ]");
	   for (Brand brand: brandList)
		   brand.print();
	   
	   System.out.println("[��� ����Ʈ]");
	   for (Vehicle vehicle: vehicleList)
		   vehicle.print();
   }
	
	
	@Override
	public boolean matches(String kwd) {
		if(areaName.equals(kwd))
			return true;
		if(spotName.equals(kwd))
			return true;
		return false;
	}
}