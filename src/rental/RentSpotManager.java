package rental;

import java.util.Calendar;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
<<<<<<< Updated upstream
=======
	
	int countKcal(int time) { // ������ ����.
		// 10 �д� 44kcal �Ҹ�
		return (time / 10) * 44;
	}
>>>>>>> Stashed changes

	// �뿩   ..�ϴ� ���� ����..
	void rentVehicle(User user, RentSpot spot, Scanner scan) {  // RentSpotManager���� �����ϸ� RentSpot�� ���� ������ �ʿ�..
		// user�� �뿩�� �ִ��� Ȯ��
<<<<<<< Updated upstream
		if(user.ticket == 0) {
=======
		if(user.ticket == null) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
		if(user.ticket.onedayTime == 1) { // 1�ð���
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
				RentSystem.payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				RentSystem.payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.onedayTime == 2) { // 2�ð���
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
				// �߰� ��� �Լ� ȣ��
				RentSystem.payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
				// �߰� ��� �Լ� ȣ��
				RentSystem.payment.morePay(nowh - tmp.starthour);
>>>>>>> Stashed changes
			}
		}
		user.rental = null;
		spot.vehicleList.add(tmp); // �ݳ�.
	}
<<<<<<< Updated upstream
}
=======
	
	// ���ã�� ���
	void favoiritprint() {
		
		for(RentSpot r : RentSystem.rentSpotMgr.mList) {
			if(r.favorite == 1) {
				System.out.println(r);
			}
		}
	}
	// ���ã�� �߰�
	void plusfavoirite(RentSpot r) {
		r.favorite = 1;
	}
}
>>>>>>> Stashed changes
