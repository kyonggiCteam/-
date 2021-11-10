package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle �뿩, �ݳ�, ��ȸ ��� ����.

	String spotName; //
	// �ϴ��� �����Ŷ� ű���� ���� ����.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	// �뿩   ..�ϴ� ���� ����..
	void rentVehicle(User user, Scanner scan) {
		// user�� �뿩�� �ִ��� Ȯ��
		if(user.ticket == 0) {
			System.out.println("�뿩���� �ʿ��մϴ�.");
			return;
		}
		// user�� �뿩������ Ȯ��
		if(user.rental != null) {
			System.out.println("���� �뿩���Դϴ�.");
			return;
		}
		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
		String code = scan.next();
		for(Vehicle v : vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ������ �̷��� �ۼ��ұ�?
				user.rental.setTime();
			}
		}
		if(user.rental == null)
			System.out.println("�ش� ��ۼ����� �����ϴ�.");

	}
	
	// �ݳ�
	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
	void returnVehicle(User user) {
		// ���ϱ��� ��� �ð����
		Vehicle tmp = user.rental;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		if(user.ticket == 1) { // 1�ð���
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) {
				// �߰� ��� �Լ� ȣ��
			}
			if((nowh - tmp.starthour) > 1) {
				// �߰� ��� �Լ� ȣ��
			}
		}
		if(user.ticket == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) {
				// �߰� ��� �Լ� ȣ��
			}
			if((nowh - tmp.starthour) > 2) {
				// �߰� ��� �Լ� ȣ��
			}
		}
		user.rental = null;
		vehicleList.add(tmp); // �ݳ�.
	}
	
	// ��ȸ
	void checkStock() {

		System.out.println("<������ �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("B"))
				System.out.printf("[ %s ]\n", v.id);
		}
		System.out.println("<���� ű���� �ڵ�>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("S"))
				System.out.printf("[ %s ]\n", v.id);
		}
		
	}
	@Override
	public void read(Scanner scan) {
		
	}
	@Override
	public void print() {
		
	}
	@Override
	public boolean matches(String kwd) {
		return false;
	}
}
