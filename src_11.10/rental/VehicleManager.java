package rental;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manager;

public class VehicleManager extends Manager<Vehicle>{
	
	ArrayList<Vehicle> breakdownList = new ArrayList<>();
	//���� �Ű�
	void breakdownreport(Scanner scan) {
		String code = scan.next();
		Vehicle breakdown = find(code);
		boolean success = mList.remove(breakdown);
		if(success == false)
			System.out.println("�ڵ尡 �������� �ʽ��ϴ�");
		breakdownList.add(breakdown);
	}
}
