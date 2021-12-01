package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user;
	RentSpot rentSpot;
	RentSpot returnSpot;
	

	// 회원가입
	public void join(String[] userInfo) {	
		User user = new User();
		user.name = userInfo[0];
		user.phoneNumber = userInfo[1];
		user.id = userInfo[2];
		user.pwd = userInfo[3];
		user.license = Integer.parseInt(userInfo[4]);		
		mList.add(user);
	}
	
	// 아이디 중복 확인
	public boolean idDuplicate(String id) {
		return (find(id) != null);
	}
	
	// 로그인
	public void login(Scanner scan) {
		System.out.println("<로그인>");
		while(true) {
			System.out.print("아이디: ");
			String id = scan.next();
			System.out.print("비밀번호: ");
			String pwd = scan.next();
			
			user = find(id);
			
			if (user != null && user.passwordMatch(pwd)) {
				System.out.println(">> 로그인 성공!\n");
				//updateDate(); // 초기에 null 예외 발생
				//return true;
				break;
			} else {
				System.out.println(">> 올바른 회원 정보가 아닙니다. 다시 입력해주세요.\n");
				//return false;
				continue;
			}
		}
	}
	
	// 내 정보
	public void myPage() {
		System.out.println("<My Page>");
		user.print();
	}
	
	// 회원 정보 수정
	public void modify(Scanner scan) {
		System.out.print("기존 암호: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println(">> 암호가 올바르지 않습니다.");
		}
	}
	
	void selectSpot(Scanner scan) {
		System.out.print("대여/반납할 장소를 고르세요.(spotCode 입력) "); //GUI에서는 장소 버튼 클릭 시 이름을 인식해서 처리하는 부분
		String spotCode = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		rentSpot.print();
	}
	
	void addFavoriteSpot(Scanner scan) {
		System.out.print("즐겨찾는 대여소로 등록하시겠습니까?(등록을 원할 시 y 입력, 아니면 n 입력) ");
		String answer = scan.next();
		if (answer.equals('y')) {
			user.favoriteSpotList.add(rentSpot);
		}
		for (RentSpot spot: user.favoriteSpotList) {
			System.out.println("즐겨찾는 대여소");
			spot.print();
		}
	}

	//- --------------------------------------
	// 티켓 결제( 콘솔 상의 것 수정)
//	public void buyTicket(Ticket ticket,  User user) { // user에 티켓 넣어줌 + 시간계산 + 포인트 계산
//		if(ticket.ticketType.equals("정기권")) { // 시간 계산 필요.
//			startTicket(user);
//		}
//		// 포인트도 계산
//		pointCount(ticket.price, user);
//		user.ticket = ticket;
//	}
//	
//	// 시간 세팅 
//	void startTicket(User user) { // user 변수 시간 넣어줌
//		Calendar now = Calendar.getInstance();
//		user.startyear = now.get(Calendar.YEAR);
//		user.startmonth = now.get(Calendar.MONTH);
//		user.startdate = now.get(Calendar.DATE);
//		System.out.printf("시작 시간: %d년 %d 월 %d 일" , user.startyear, user.startmonth + 1, user.startdate);
//	}
//	
//	// 포인트 계산 함수
//	void pointCount(int price, User user) {
//		user.point += (int) (price * 0.01); // 1% 적립
//	}
	
	// ---------------------------------------------
	
	void rentalVehicle(Scanner scan) { // 장비 대여
//		if (!buyTicket(scan)) // 티켓 구매(결제) => 결제 취소 시 대여 종료
//			return;
		if (user.vehicle != null) {
			System.out.println(">> 이미 장비를 대여중입니다.");
			return;
		}
		// 장비 보여주기 - 선택한 장소의, 선택한 티켓의 브랜드의, 사용가능한 상태의 장비만 보여주고 선택할 수 있게 해야 함
		ArrayList<Vehicle> available = new ArrayList<>();
		for(Vehicle v : rentSpot.vehicleList) {
			if (v.brandName.equals(user.ticket.brandName) && v.state == 0) {
				available.add(v);
				v.print();
			}
		}
		if (available.isEmpty()) {
			System.out.println("대여 가능한 장비가 없습니다.");
			return;
		}
		
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
		
		// 대여 진행
		user.vehicle = vehicle;
		user.vehicle.state = 1;
		System.out.println(">> 대여가 완료되었습니다.");
		user.vehicle.setTime(); //따로 시간 카운트하는 이유?
			
//			if(v.code.equals(vehicleCode)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
//				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
//				user.vehicle.setTime();
//			}
	}
	
	// 반납
	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
	void returnVehicle(Scanner scan) {
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
				//TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
				// 추가 계산 함수 호출
				//TicketManager.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
				// 추가 계산 함수 호출
				//TicketManager.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
				// 추가 계산 함수 호출
				//.morePay(nowh - tmp.starthour);
			}
		}
		
		// 반납 진행
		user.vehicle.state = 0;  // 이용 가능한 상태로 전환
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // 반납 ==> state 쓰면 필요 없을 듯..?
		//pointCount(user.ticket.price); // 맞나?
	}
	

	
	//leftmonth, leftdate 갱신
	public void updateDate(User user) { // 1달은 30일이라고 가정.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int nowY = now.get(Calendar.YEAR);
		int nowM = now.get(Calendar.MONTH);
		int nowD = now.get(Calendar.DATE);
		Date nowday = null;
		Date endday = null;
		
		int endY = 0, endM = 0 , endD = 0;
		endM = ( user.startmonth + user.ticket.month ) % 12;
		if(endM < user.startmonth)
			endY = user.startyear + 1;
		endD = user.startdate;
		
		try {
			if (nowM > 9) {
				nowday = sdf.parse(nowY + "-" + nowM + "-" + nowD);
			}
			else {
				nowday = sdf.parse(nowY + "-0" + nowM + "-" + nowD);
			}
			if (endM > 9) {
				endday = sdf.parse(endY + "-" + endM + "-" + endD);
			}
			else {
				endday = sdf.parse(endY + "-0" + endM + "-" + endD);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cmpDate = Calendar.getInstance();
		cmpDate.setTime(endday);
		
		int compare = endday.compareTo(nowday);
		if(compare > 0) { // endday > nowday : 기간 남음.
			long diffSec = (cmpDate.getTimeInMillis() - now.getTimeInMillis() ) / 1000;
			long diffDays = diffSec / ( 24*60*60);
			user.leftday = (int)diffDays;
		}
		else // 기간 종료
			user.ticket = null;

	}
}