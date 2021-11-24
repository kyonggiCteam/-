package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Brand implements Manageable {

	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	ArrayList<RentSpot> rentspotList = new ArrayList<>();
	ArrayList<Ticket> ticketList = new ArrayList<>();
	String brandName;  //  �귣�� ���� : 10��  ( �����Һ�  4���� �귣�� ����)
	int plusPrice;
	// brandcode �߰��ص� ��������
	
	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		brandName = scan.next();
		plusPrice = scan.nextInt();
		String spotname = null;
		RentSpot rentspot = null;
		while(true) {
			spotname = scan.next();
			if(spotname.contentEquals("0"))	
				break;
			rentspot = RentSystem.rentSpotMgr.find(spotname);
			rentspotList.add(rentspot);
		}
		String vehiclecode = null;
		Vehicle v = null;
		while(true) {
			vehiclecode = scan.next();
			if(vehiclecode.contentEquals("0"))	
				break;
			v = RentSystem.vehicleMgr.find(vehiclecode);
			vehicleList.add(v);
		}
		String ticketcode = null;
		Ticket t = null;
		while(true) {
			ticketcode = scan.next();
			if(ticketcode.contentEquals("0"))	
				break;
			t = RentSystem.ticketMgr.find(ticketcode);
			ticketList.add(t);
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("�̸�:%s ����:%d\n", brandName, plusPrice );
//		for(RentSpot r : rentspotList)
//			System.out.printf("%s ", r.spotName);
//		System.out.println();
		for(Vehicle v : vehicleList)
			System.out.printf("%s ", v.id);
		System.out.println();
		
		
	}
	
//	@Override
//	public void print() {
//		print(true);
//	}
//	
//	public void print(boolean bDetail) {
//		System.out.printf("%s", brandName);
//		if (!bDetail)
//			return;
//		
//		System.out.println("[�뿩�� ����Ʈ]");
//		for (RentSpot rentSpot: rentSpotList)
//			rentSpot.print();
//		
//		System.out.println("[��� ����Ʈ]");
//		for (Vehicle vehicle: vehicleList)
//			vehicle.print();
//	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		if(brandName.contentEquals(kwd))
			return true;
		return false;
	}
	
}
