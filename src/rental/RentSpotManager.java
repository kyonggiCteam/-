package rental;

import java.util.Calendar;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
	RentSpot rentSpot;
	
//	RentSpot getRentSpot(String spotName) {
//		return find(spotName); // ��� ��ư Ŭ�� �� ó��? ���������� ��� id�� �־�� ������
//	}
	
	//user�� spot �� �ϳ��� �Է� ó���� �Ǿ�� �ϴµ�, user�� �Է� ó�� �ϱ⿣ ���μ��� �� �̻���. 
	//�뿩 �� �ݳ� �Լ��� userManager���� �����ϰ�, ��� ��ư ������ �� �̸����� ��Ҹ� ã�Ƽ� �Լ��� �μ��� �־��ִ� �� �´� �� ����
	
//	void rentVehicle(User user, Scanner scan) {
//		// user�� �뿩�� �ִ��� Ȯ��
//		if(user.ticket == null) {
//			System.out.println("�뿩���� �ʿ��մϴ�.");
//			return;
//		}
//		// user�� �뿩������ Ȯ��
//		if(user.vehicle != null) {
//			System.out.println("���� �뿩���Դϴ�.");
//			return;
//		}
//		// code ã���� + �ð� �����ϴ� �Լ� ȣ��. + vehicleList���� ��� ����
//		String code = scan.next();
//		// ���� ���� Ȯ��.
//		if(code.contains("S")) {
//			if(user.license == 0) {
//				System.out.println("���㸦 ������ ���� �ʽ��ϴ�.");
//				return;
//			}
//		}
//		for(Vehicle v : spot.vehicleList) {
//			if(v.id.equals(code)) {
//				user.vehicle = v;
//				spot.vehicleList.remove(v); // ��۸���Ʈ���� �����ؾ��� �ٸ� ��� �̿� �Ұ�.
//				// ������� ������ �ִ� ����� �ð� ������ �ʿ����� ����. => �ٸ� ����� �� ������
//				user.vehicle.setTime();
//			}
//		}
//		if(user.vehicle == null)
//			System.out.println("�ش� ��ۼ����� �����ϴ�.");
//
//	}
//	// �ݳ�
//	// �ݳ� �ð���� -> ������� ��� �ð���� �ʿ� ���� / ���ϱ��� ��� -> �ð���� �ʿ�.
//	void returnVehicle(User user, RentSpot spot) {
//		// ���ϱ��� ��� �ð����
//		Vehicle tmp = user.vehicle;
//		Calendar now = Calendar.getInstance();
//		int nowh = now.get(Calendar.HOUR);
//		int nowm = now.get(Calendar.MINUTE);
//		
//		if(user.ticket == 1) { // 1�ð���
//			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1�ð� �߰� ���
//				// �߰� ��� �Լ� ȣ�� -> 1�ð��� 500�� �߰�
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour ���� ��ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		if(user.ticket == 2) { // 2�ð���
//			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1�ð� �߰�
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 2) { // ���̸�ŭ �߰� ���
//				// �߰� ��� �Լ� ȣ��
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		user.rental = null;
//		spot.vehicleList.add(tmp); // �ݳ�.
//	}
}