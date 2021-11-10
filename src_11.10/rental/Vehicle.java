package rental;

import java.util.Calendar;
import java.util.Scanner;


import mgr.Manageable;

public class Vehicle implements Manageable {

	String id;  // scooter : S1234 (�տ� �� ���ڴ� ������, �ڿ� 2���ڴ� ����)
				// Bicycle: B1234
	String spotname;  // ***** �̰� �ݳ��� ������ �޶��� �� ����. => �ݳ��Ҷ����� ��������� �� ��
	boolean crush;
	// �ð� ����� ������ϹǷ� ���� ���۽ð�, ���� �ð� �߰�...
	//���ϱǿ��� �̿�.
	int starthour;
	int startmin;
	
	// �ð� ���� �Լ�
	void setTime() {
		Calendar now = Calendar.getInstance();
		starthour = now.get(Calendar.HOUR_OF_DAY);
		startmin = now.get(Calendar.MINUTE);		
	}
	
	
	@Override
	public void read(Scanner scan) {
		id = scan.next();
		spotname = scan.next();
	}
	@Override
	public void print() {
		if (id.contains("B"))
			System.out.printf("������/ �ڵ�: %s, ������ ��ġ: %s\n", id, spotname);
		else
			System.out.printf("����ű����/ �ڵ�: %s, ������ ��ġ: %s\n", id, spotname);
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(spotname.equals(kwd))
			return true;
		return false;
	}
}
