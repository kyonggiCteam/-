package rental;

<<<<<<< Updated upstream
=======
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

<<<<<<< Updated upstream
	String id;  // scooter : S1234 (�տ� �� ���ڴ� ������, �ڿ� 2���ڴ� ����)
				// Bicycle: B1234
	String spotname;  // �� ������ ���� �ʿ��ұ�? -> �� �ʵ带 ����� �ݳ��� ������ �ٲ���� ��.
	boolean crush;
=======
	String id; 
	String brandName;
	boolean crush;
	// ��� ����Ʈ
	ArrayList<String> optionList = new ArrayList<>();
	
>>>>>>> Stashed changes
	//���ϱǿ��� �̿�.
	int starthour;
	int startmin;
	// state ��� !!
<<<<<<< Updated upstream
	
=======
//	int state; // 0: �̿밡�� 1: �̿��� -1: ����
>>>>>>> Stashed changes
	// �ð� ���� �Լ�
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
	@Override
	public void read(Scanner scan) {
		id = scan.next();
<<<<<<< Updated upstream
		spotname = scan.next();
	}
	@Override
	public void print() {
		if (id.contains("B"))
			System.out.printf("������/ �ڵ�: %s, ������ ��ġ: %s\n", id, spotname);
		else
			System.out.printf("����ű����/ �ڵ�: %s, ������ ��ġ: %s\n", id, spotname);
=======
		brandName = scan.next();
		String option = scan.next();
		while(!option.contentEquals("0")) {
			optionList.add(option);
			option = scan.next();
		}
	}
	@Override
	public void print() {
		if (id.contains("B")) {
			System.out.printf("������/ �ڵ�: %s, �귣�� �̸�: %s\n����:", id, brandName);
			for(String e : optionList)
				System.out.printf("%s ", e);
			System.out.println();
		}
		else {
			System.out.printf("ű����/ �ڵ�: %s, �귣�� �̸�: %s\n����:", id, brandName);
			for(String e : optionList)
				System.out.printf("%s ", e);
			System.out.println();
		}
>>>>>>> Stashed changes
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
<<<<<<< Updated upstream
		if(spotname.equals(kwd))
			return true;
		return false;
	}
}
=======
//		if(spotname.equals(kwd))
//			return true;
		return false;
	}
}
>>>>>>> Stashed changes
