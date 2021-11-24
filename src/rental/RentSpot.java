package rental;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle 대여, 반납, 조회 기능 수행.
<<<<<<< Updated upstream

	String spotName; 
	// 일단은 자전거랑 킥보드 구분 안함.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot 데이터 읽을 때, 저장.
	

=======
	int spotCode;
	String areaName;
	String spotName; 
	// 일단은 자전거랑 킥보드 구분 안함.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();  // RentSpot 데이터 읽을 때, 저장.
	// 고장 vehicle을 얘가 가져도 괜찮을 듯. 그래서 검색할 때, 고장신고된 vehicle 보여주기

	// 업체명 -> 여러 업체를 가지고 있으므로 List로 가져야할듯
	ArrayList<Brand> brandList	= new ArrayList<>();
	//즐겨찾기 기능
	int favorite;  // 1: 즐겨찾기
	//rentspot code 추가해도 될 듯.
	
	
>>>>>>> Stashed changes
	// 조회
	void checkStock() {

		System.out.println("<자전거 코드>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("B"))
				System.out.printf("[ %s ]\n", v.id);
		}
		System.out.println("<전동 킥보드 코드>");
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
			// 코드에 맞는 vehicle을 찾아줘야 함.  
=======
			// 코드에 맞는 vehicle을 찾아줘야 함. -> so, vehicle을 읽고 spot을 읽어야함.
>>>>>>> Stashed changes
			vehicle = RentSystem.vehicleMgr.find(vehicleName);
			vehicleList.add(vehicle);
		}
		
	}
	@Override
	public void print() {
<<<<<<< Updated upstream
		System.out.printf("정류장 이름: %s\n", spotName);
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
}
=======
		System.out.printf("지역 이름: %s, 정류장 이름: %s	\n", areaName, spotName);
	}
	
//	@Override
//	public void print() {
//		print(true);
//	}
//
//   public void print(boolean bDetail) {
//	   System.out.printf("대여소 이름: (%s) %s\n", Gu, spotName);
//	   if (!bDetail)
//		   return;
//	   
//	   System.out.println("[브랜드 리스트]");
//	   for (Brand brand: brandList)
//		   brand.print();
//	   
//	   System.out.println("[장비 리스트]");
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
