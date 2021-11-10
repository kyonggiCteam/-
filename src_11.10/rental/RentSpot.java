package rental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import mgr.Manageable;

public class RentSpot implements Manageable { // Vehicle 대여, 반납, 조회 기능 수행.

	String spotName; //
	// 일단은 자전거랑 킥보드 구분 안함.
	ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	// 대여   ..일단 대충 구현..
	void rentVehicle(User user, Scanner scan) {
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
		for(Vehicle v : vehicleList) {
			if(v.id.equals(code)) {
				user.rental = v;
				vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않은데 이렇게 작성할까?
				user.rental.setTime();
			}
		}
		if(user.rental == null)
			System.out.println("해당 운송수단이 없습니다.");

	}
	
	// 반납
	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
	void returnVehicle(User user) {
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
		vehicleList.add(tmp); // 반납.
	}
	
	// 조회
	void checkStock() {

		System.out.println("<자전거 코드>");
		for(Vehicle v : vehicleList) {
			if(v.id.contains("B"))
				System.out.printf("[ %s ]\n", v.id);
		}
		System.out.println("<전동 킥보드 코드>");
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
