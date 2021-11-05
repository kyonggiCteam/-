package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manager;

public class VehicleManager extends Manager<Vehicle>{
	
	ArrayList<Vehicle> breakdownList = new ArrayList<>();
	//고장 신고
	void breakdownreport(Scanner scan) {
		String code = scan.next();
		Vehicle breakdown = find(code);
		boolean success = mList.remove(breakdown);
		if(success == false)
			System.out.println("코드가 존재하지 않습니다");
		breakdownList.add(breakdown);
	}
}
