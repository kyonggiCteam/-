package rental;

import java.util.Calendar;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {

	// �뿩   ..�ϴ� ���� ����..
	void rentVehicle(User user, RentSpot spot, Scanner scan) {  // RentSpotManager���� �����ϸ� RentSpot�� ���� ������ �ʿ�..
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
		// ���� ���� Ȯ��.
		if(code.contains("S")) {
			if(user.license == 0) {
				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
				return;
			}
		}
		for(Vehicle v : spot.vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				spot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
				user.rental.setTime();
			}
		}
		if(user.rental == null)
			System.out.println("�ش� ��ۼ����� �����ϴ�.");

	}
	// �ݳ�
	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
	void returnVehicle(User user, RentSpot spot) {
		// ���ϱ��� ��� �ð����
		Vehicle tmp = user.rental;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		if(user.ticket == 1) { // 1�ð���
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		user.rental = null;
		spot.vehicleList.add(tmp); // �ݳ�.
	}
}
