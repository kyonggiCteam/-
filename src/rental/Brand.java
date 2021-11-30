package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Brand implements Manageable {
	//beam 90 â���ʵ��б� �������ǿ� �޸������� CU��������ȭ����Ʈ���� �����Ϸ��챤����  0 SV0201 SV0202 SV0203 SV0301 0 V01 V02 V03 0
	String brandName;
	int plusPrice;
	ArrayList<RentSpot> rentSpotList = new ArrayList<>();
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	public ArrayList<Ticket> ticketList = new ArrayList<>();
	
	@Override
	public void read(Scanner scan) {
		brandName = scan.next();
		plusPrice = scan.nextInt();
		
		String spotName = null;
		RentSpot rentSpot = null;
		while(true) {
			spotName = scan.next();
			if(spotName.contentEquals("0"))	
				break;  
			rentSpot = RentSystem.rentSpotMgr.find(spotName);
			rentSpotList.add(rentSpot);
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
		
		String ticketCode = null;
		Ticket ticket = null;
		while(true) {
			ticketCode = scan.next();
			if(ticketCode.contentEquals("0"))	
				break;
			ticket = RentSystem.ticketMgr.find(ticketCode);
			ticketList.add(ticket);
		}
	}

	@Override
	public void print() {
		print(false);
	}
	
	public void print(boolean bDetail) {
		System.out.printf("[%s] �д� �߰����: %d\n", brandName, plusPrice);
		if (!bDetail)
			return;
		
		System.out.println("<�뿩�� ����Ʈ>");
		for (RentSpot spot: rentSpotList)
			System.out.printf("%s\n", spot);
		
		System.out.println("<��� ����Ʈ>");
		for (Vehicle vehicle: vehicleList)
			vehicle.print();
		
		System.out.println("<Ƽ�� ����Ʈ>");
		for (Ticket ticket: ticketList)
			ticket.print();
	}

	@Override
	public boolean matches(String kwd) {
		if(brandName.equals(kwd))
			return true;
		return false;
	}

}