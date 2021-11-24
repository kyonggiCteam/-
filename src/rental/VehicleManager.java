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
<<<<<<< Updated upstream
}
=======
	
//	//고장 신고
//	void breakdownReport(Scanner scan) {
//		System.out.print("고장난 물품의 코드 : ");
//		String code = scan.next();
//		Vehicle breakdown = find(code);
//		if (breakdown == null) {
//			System.out.println("코드가 존재하지 않습니다");
//			return;
//		}
//		breakdown.state = -1;
//	}
}
>>>>>>> Stashed changes
