package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
	RentSpot rentSpot;
	
	void selectSpot(Scanner scan) {
		System.out.print("대여/반납할 장소를 고르세요.(spotCode 입력) "); //GUI에서는 장소 버튼 클릭 시 이름을 인식해서 처리하는 부분
		String spotCode = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		rentSpot.print();
	}
	
	void addFavoriteSpot(Scanner scan, User user) {
		System.out.print("즐겨찾는 대여소로 등록하시겠습니까?(등록을 원할 시 y 입력, 아니면 n 입력) ");
		String answer = scan.next();
		if (answer == "y") {
			user.favoriteSpotList.add(rentSpot);
		}
		for (RentSpot spot: user.favoriteSpotList) {
			System.out.println("즐겨찾는 대여소");
			spot.print();
		}
	}

	// 티켓 결제
	boolean buyTicket(Scanner scan, User user) {
		if (user.ticket != null) {
			System.out.printf(">> 현재 보유 중인 티켓은 %s입니다.\n", user.ticket.code);
			return true;
		}
		
		System.out.print("구매하고 싶은 티켓을 고르세요(ticketCode 입력) ");
		String ticketCode = scan.next();
		Ticket ticket = RentSystem.ticketMgr.find(ticketCode);
		
		if (ticket == null) {
			System.out.println("존재하지 않는 티켓입니다.");
			return false;
		}
		// 결제 화면
		System.out.printf("선택하신 티켓은 %s (%d원)입니다. 결제하시겠습니까?\n현재 보유 포인트는 %d입니다.\n"
									, ticket.code, ticket.price, user.point);
		// 결제 수행
		String answer = scan.next();
		if (answer.contentEquals("y")) {
			user.ticket = ticket;
//			startTicket();
			return true;
		} else {
			System.out.println("결제가 취소되었습니다.");
			return false;
		}
	}
	
	void rentalVehicle(Scanner scan, User user) { // 장비 대여
		if (!buyTicket(scan, user)) // 티켓 구매(결제) => 결제 취소 시 대여 종료
			return;
		if (user.vehicle != null) {
			System.out.println(">> 이미 장비를 대여중입니다.");
			return;
		}

		rentSpot.print();
		// 장비 보여주기 - 선택한 장소의, 선택한 티켓의 브랜드의 장비들만 보여주고 선택할 수 있게 해야 함
		ArrayList<Vehicle> available = new ArrayList<>();
		for(Vehicle v : rentSpot.vehicleList) {
			if (v.brandName == user.ticket.brandName) {
				available.add(v);
				v.print();
			}
		}
		if (available.isEmpty()) {
			System.out.println("대여 가능한 장비가 없습니다.");
			return;
		}
		
//		if (rentSpot.vehicleList == null) {
//			System.out.println("대여 가능한 장비가 없습니다.");
//			return;
//		}
//		for (Vehicle v : rentSpot.vehicleList) {
//			if (v.brandName == user.ticket.brandName && v.state == 0) {
//				v.print();
//				available.add(v);
//			}
//		}
		
		// 장비 선택
		// code 찾아줌 + 시간 저장하는 함수 호출. + vehicleList에서 요소 제거
		System.out.print(">> 대여할 장비를 고르세요.(vehicleCode 입력) ");
		String vehicleCode = scan.next();
		Vehicle vehicle = RentSystem.vehicleMgr.find(vehicleCode);
		if (vehicle == null) {
			System.out.println(">> 해당 장비는 존재하지 않습니다.");
			return;
		}
		// 전동킥보드이면 면허 여부 확인
		if(vehicleCode.charAt(0) == 'S') { // 혹은 vehicle.code.charAt(0) == 'S'
			if(user.license == 0) {
				System.out.println("면허를 가지고 있지 않습니다.");
				return;
			}
		}
		
		// 선택한 장소의, 선택한 티켓의 브랜드의 장비들만 보여주고 선택할 수 있게 해야 함
		for(Vehicle a : available) {
			if (a.equals(vehicle)) {
				user.vehicle = vehicle;
				user.vehicle.state = 1;
				System.out.println(">> 대여가 완료되었습니다.");
//				RentSystem.userMgr.startTicket();
				user.vehicle.setTime();
			} else {
				System.out.println(">> 해당 장비는 대여할 수 없습니다.");
				return;
			}
			
//			if(v.code.equals(vehicleCode)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
//				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
//				user.vehicle.setTime();
//			}
		}
	}
	
	// 반납
	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
	void returnVehicle(Scanner scan, User user) {
//		void returnVehicle(User user, RentSpot spot) {
		// 일일권일 경우 시간계산
		Vehicle tmp = user.vehicle;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		System.out.printf("현재 %d시 %d분\n", nowh, nowm);
		if(user.ticket.hour == 1) { // 1시간권
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
				// 추가 계산 함수 호출 -> 1시간당 500원 추가
				TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
				// 추가 계산 함수 호출
				TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
				// 추가 계산 함수 호출
				TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
				// 추가 계산 함수 호출
				TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // 반납
		RentSystem.userMgr.pointCount(user.ticket.price); // 맞나?
	}
	
	
//	RentSpot getRentSpot(String spotName) {
//		return find(spotName); // 장소 버튼 클릭 시 처리? 마찬가지로 장소 id가 있어야 좋을듯
//	}
	
	//user와 spot 중 하나는 입력 처리가 되어야 하는데, user를 입력 처리 하기엔 프로세스 상 이상함. 
	//대여 및 반납 함수롤 userManager에서 구현하고, 장소 버튼 누르면 그 이름으로 장소를 찾아서 함수의 인수로 넣어주는 게 맞는 것 같음
	
//	void rentVehicle(User user, Scanner scan) {
//		// user가 대여권 있는지 확인
//		if(user.ticket == null) {
//			System.out.println("대여권이 필요합니다.");
//			return;
//		}
//		// user가 대여중인지 확인
//		if(user.vehicle != null) {
//			System.out.println("현재 대여중입니다.");
//			return;
//		}
//		// code 찾아줌 + 시간 저장하는 함수 호출. + vehicleList에서 요소 제거
//		String code = scan.next();
//		// 면허 여부 확인.
//		if(code.contains("S")) {
//			if(user.license == 0) {
//				System.out.println("면허를 가지고 있지 않습니다.");
//				return;
//			}
//		}
//		for(Vehicle v : spot.vehicleList) {
//			if(v.id.equals(code)) {
//				user.vehicle = v;
//				spot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
//				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
//				user.vehicle.setTime();
//			}
//		}
//		if(user.vehicle == null)
//			System.out.println("해당 운송수단이 없습니다.");
//
//	}
//	// 반납
//	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
//	void returnVehicle(User user, RentSpot spot) {
//		// 일일권일 경우 시간계산
//		Vehicle tmp = user.vehicle;
//		Calendar now = Calendar.getInstance();
//		int nowh = now.get(Calendar.HOUR);
//		int nowm = now.get(Calendar.MINUTE);
//		
//		if(user.ticket == 1) { // 1시간권
//			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
//				// 추가 계산 함수 호출 -> 1시간당 500원 추가
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
//				// 추가 계산 함수 호출
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		if(user.ticket == 2) { // 2시간권
//			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
//				// 추가 계산 함수 호출
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
//				// 추가 계산 함수 호출
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		user.rental = null;
//		spot.vehicleList.add(tmp); // 반납.
//	}
}
