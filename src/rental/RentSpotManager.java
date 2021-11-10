package rental;

import java.util.Calendar;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {

	// 대여   ..일단 대충 구현..
	void rentVehicle(User user, RentSpot spot, Scanner scan) {  // Manager에서 구현하면 RentSpot의 참조 변수가 필요..
		// user가 대여권 있는지 확인
		if(user.ticket == 0) {
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
		if(user.ticket == 1) { // 1시간권
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) {
				// 추가 계산 함수 호출
			}
			if((nowh - tmp.starthour) > 1) {
				// 추가 계산 함수 호출
			}
		}
		if(user.ticket == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) {
				// 추가 계산 함수 호출
			}
			if((nowh - tmp.starthour) > 2) {
				// 추가 계산 함수 호출
			}
		}
		user.rental = null;
		spot.vehicleList.add(tmp); // 반납.
	}
}
