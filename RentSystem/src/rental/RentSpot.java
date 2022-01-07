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
		System.out.printf("[%s] �̸�: (%s) %s\n", spotCode, areaName, spotName);
		if (!bDetail)
			return;
  
		System.out.println("<�귣�� ����Ʈ>");
		for (String brandName: brandNameList)
			System.out.println(brandName);
   
		System.out.println("<��� ����Ʈ>");
		for (Vehicle vehicle: vehicleList)
			vehicle.print();
	}

	void checkStock() {
		System.out.printf("[���� �뿩��(%s)�� ��� ��� ��ȸ]");
		System.out.println("<������>");
		for(Vehicle v : vehicleList) {
			if(v.code.charAt(0) == 'B')
				v.print();
		}
		System.out.println("<���� ű����>");
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