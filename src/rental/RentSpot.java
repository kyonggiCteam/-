package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle 대여, 반납, 조회 기능 수행.
	int spotCode;
	String areaName;
	String spotName;
	ArrayList<Brand> brandList = new ArrayList<>();
	public static ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot 데이터 읽을 때, 저장.

	// 조회
	void checkStock() {
		System.out.println("<자전거 코드>");
		for(Vehicle v : vehicleList) {
			if(v.code.contains("B"))
				System.out.printf("[ %s ]\n", v.code);
		}
		System.out.println("<전동 킥보드 코드>");
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
			brand = RentSystem.brandMgr.find(brandName); //brandMgr 임시로 만들긴 했는데,,
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
	   System.out.printf("대여소 코드: %d 이름: (%s) %s\n", spotCode, areaName, spotName);
	   if (!bDetail)
		   return;
	   
	   System.out.println("[브랜드 리스트]");
	   for (Brand brand: brandList)
		   brand.print();
	   
	   System.out.println("[장비 리스트]");
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