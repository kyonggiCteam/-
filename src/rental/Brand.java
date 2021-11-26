package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Brand implements Manageable {
	//beam 90 창용초등학교 경기대한의원 꿈마루어린이집 CU편의점목화아파트지점 세븐일레븐광교점 카페거리동물병원동행 뚜레쥬르광교로점 에이스빌딩 경기간호학원 CU편의점깃매산로점 스마일약국 수원역2번출구 0 SV0201 SV0202 SV0203 SV0301 SV0302 SV0303 SV0501 SV0502 SV0503 SV0801 SV0802 SV0803 SV1001 SV1002 SV1003 SV1201 SV1202 SV1203 SV1501 SV1502 SV1503 SV1901 SV1902 SV1903 SV2101 SV2102 SV2103 SV2401 SV2402 SV2403 SV2801 SV2802 SV2803 SV3001 SV3002 SV3003 0 V01 V02 V03 0
	String brandName;
	int plusPrice;
//	ArrayList<RentSpot> rentSpotList = new ArrayList<>();
	ArrayList<String> spotNameList = new ArrayList<>();
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	ArrayList<Ticket> ticketList = new ArrayList<>();
	
	@Override
	public void read(Scanner scan) {
		brandName = scan.next();
		plusPrice = scan.nextInt();

//		String spotName = null;
//		RentSpot rentSpot = null;
//		while(true) {
//			spotName = scan.next();
//			if(spotName.contentEquals("0"))	
//				break;  
//			rentSpot = RentSystem.rentSpotMgr.find(spotName); //brandMgr 임시로 만들긴 했는데,,
//			rentSpotList.add(rentSpot);
//		}
		String spotName = null;
		while (true) {
			spotName = scan.next();
			if (spotName.contentEquals("0"))
				break;
			spotNameList.add(spotName);
		}

		String vehicleCode = null;
		Vehicle vehicle = null;
		while (true) {
			vehicleCode = scan.next();
			if (vehicleCode.contentEquals("0"))
				break;
			vehicle = RentSystem.vehicleMgr.find(vehicleCode);
			vehicleList.add(vehicle);
		}

		String ticketCode = null;
		Ticket ticket = null;
		while (true) {
			ticketCode = scan.next();
			if (ticketCode.contentEquals("0"))
				break;
			ticket = RentSystem.payMgr.find(ticketCode);
			ticketList.add(ticket);
		}
	}

	@Override
	public void print() {
		print(true);
	}
	
	public void print(boolean bDetail) {
		System.out.printf("%s\n", brandName);
		if (!bDetail)
			return;
		
//		System.out.println(">> 대여소 리스트");
//		for (String spot: spotNameList)
//			System.out.printf("%s\n", spot);
//		
//		System.out.println(">> 장비 리스트");
//		for (Vehicle vehicle: vehicleList)
//			vehicle.print();
	}

	@Override
	public boolean matches(String kwd) {
		if(brandName.equals(kwd))
			return true;
		return false;
	}

}