package rental;

import java.util.Calendar;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
<<<<<<< Updated upstream
=======
	
	int countKcal(int time) { // 분으로 들어옴.
		// 10 분당 44kcal 소모
		return (time / 10) * 44;
	}
>>>>>>> Stashed changes

	// 대여   ..일단 대충 구현..
	void rentVehicle(User user, RentSpot spot, Scanner scan) {  // RentSpotManager에서 구현하면 RentSpot의 참조 변수가 필요..
		// user가 대여권 있는지 확인
<<<<<<< Updated upstream
		if(user.ticket == 0) {
=======
		if(user.ticket == null) {
>>>>>>> Stashed changes
			System.out.println("대여권이 필요합니다.");
			return;
		}
		// user가 대여중인지 확인
		if(user.rental != null) {
			System.out.println("현재 대여중입니다.");
			return;
		}
		// code 찾아줌 + 시간 저장하는 함수 호출. + vehicleList에서 요소 제거
		String code = scan.next();
		// 면허 여부 확인.
		if(code.contains("S")) {
			if(user.license == 0) {
				System.out.println("면허를 가지고 있지 않습니다.");
				return;
			}
		}
		for(Vehicle v : spot.vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				spot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
				user.rental.setTime();
			}
		}
		if(user.rental == null)
			System.out.println("해당 운송수단이 없습니다.");

	}
	// 반납
	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
	void returnVehicle(User user, RentSpot spot) {
		// 일일권일 경우 시간계산
		Vehicle tmp = user.rental;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
<<<<<<< Updated upstream
		if(user.ticket == 1) { // 1시간권
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
				// 추가 계산 함수 호출 -> 1시간당 500원 추가
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
				// 추가 계산 함수 호출
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
				// 추가 계산 함수 호출
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
				// 추가 계산 함수 호출
				Payment.morePay(nowh - tmp.starthour);
=======
		if(user.ticket.onedayTime == 1) { // 1시간권
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
				// 추가 계산 함수 호출 -> 1시간당 500원 추가
				RentSystem.payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
				// 추가 계산 함수 호출
				RentSystem.payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.onedayTime == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
				// 추가 계산 함수 호출
				RentSystem.payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
				// 추가 계산 함수 호출
				RentSystem.payment.morePay(nowh - tmp.starthour);
>>>>>>> Stashed changes
			}
		}
		user.rental = null;
		spot.vehicleList.add(tmp); // 반납.
	}
<<<<<<< Updated upstream
}
=======
	
	// 즐겨찾기 출력
	void favoiritprint() {
		
		for(RentSpot r : RentSystem.rentSpotMgr.mList) {
			if(r.favorite == 1) {
				System.out.println(r);
			}
		}
	}
	// 즐겨찾기 추가
	void plusfavoirite(RentSpot r) {
		r.favorite = 1;
	}
}
>>>>>>> Stashed changes
