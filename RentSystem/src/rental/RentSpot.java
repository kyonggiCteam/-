package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class RentSpot implements Manageable {
	public String spotCode;
	public String areaName;
	public String spotName;
	public ArrayList<String> brandNameList = new ArrayList<>();
	public ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	@Override
	public void read(Scanner scan) {
		spotCode = scan.next();
		areaName = scan.next();
		spotName = scan.next();
		
		String brandName = null;
		while(true) {
			brandName = scan.next();
			if(brandName.contentEquals("0"))	
				break;
			brandNameList.add(brandName);
		}
		
		String vehicleCode = null;
		Vehicle vehicle = null;
		while(true) {
			vehicleCode = scan.next();
			if(vehicleCode.contentEquals("0"))	
				break;  
			vehicle = RentSystem.vehicleMgr.find(vehicleCode);
			vehicleList.add(vehicle);
		}
		
	}
	
	@Override
	public void print() {
		print(false);
	}

	public void print(boolean bDetail) {
		System.out.printf("[%s] 이름: (%s) %s\n", spotCode, areaName, spotName);
		if (!bDetail)
			return;
  
		System.out.println("<브랜드 리스트>");
		for (String brandName: brandNameList)
			System.out.println(brandName);
   
		System.out.println("<장비 리스트>");
		for (Vehicle vehicle: vehicleList)
			vehicle.print();
	}

	void checkStock() {
		System.out.printf("[현재 대여소(%s)의 장비 재고 조회]");
		System.out.println("<자전거>");
		for(Vehicle v : vehicleList) {
			if(v.code.charAt(0) == 'B')
				v.print();
		}
		System.out.println("<전동 킥보드>");
		for(Vehicle v : vehicleList) {
			if(v.code.charAt(0) == 'S')
				v.print();
		}	
	}
	
	@Override
	public boolean matches(String kwd) {
		if (spotCode.equals(kwd))
			return true;
		if(areaName.equals(kwd))
			return true;
		if(spotName.equals(kwd))
			return true;
		return false;
	}
}