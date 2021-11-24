package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user = new User();
	
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
	public void myInfo() {
		user.print();
	}
	
	// 회원 정보 수정
	public void modify(Scanner scan) {
		System.out.print("기존암호: ");
		String pwd = scan.next();
		if (user.passwordMatch(pwd)) {
			user.modifyInfo(scan);
		} else {
			System.out.println(">> 암호가 올바르지 않습니다.");
		}
	}
	
	// 아이디 중복 확인
	public boolean idDuplicate(String id) {
		return (find(id) != null);
	}
	
	// 회원 가입
	public void join(Scanner scan) {	
		User user = new User();
		System.out.println("회원 정보(이름, 전화번호, 아이디, 비밀번호, 면허증보유여부(0 or 1)를 입력해주세요.");
		user.read(scan);
		if (idDuplicate(user.id)) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		System.out.print("비밀번호 확인: ");
		String checkpw = scan.next();
		if(!user.passwordMatch(checkpw)) {
			System.out.println("비닐번호가 올바르지 않습니다.");
			return;
		}
		mList.add(user);
	}
	
<<<<<<< Updated upstream
//	// 회원 탈퇴
//	public void withdraw(Scanner scan) {
//		System.out.println("<탈퇴>");
//		System.out.println("비밀번호를 입력해주세요.");
//		if (user.passwordMatch(scan.next())) {
//			mList.remove(user);
//			logout(scan);
//		}
//	}
//	
=======
>>>>>>> Stashed changes
//	// 로그아웃
//	public void logout(Scanner scan) {
//		//login(scan);
//		//exit(0);
//	}
<<<<<<< Updated upstream
	
//	// 결제
//	// 티켓에 따라 다르게 금액, 시간 세팅
//	public void pay(Scanner scan) {
//		System.out.println(" (1) [일일권] 1시간 - 1000원  (2) [일일권] 2시간 - 1500원\n"
//				+ " (3) [정기권] 1개월 - 5000원  (4) [정기권] 6개월 - 20000원  (5) [정기권] 1년 - 30000원");
//		
//		if (user.ticket != 0) {
//			System.out.println("이미 이용권을 보유하고 있습니다.");
//			return;
//		} 
//		
//		System.out.print("구매할 이용권을 선택하세요. ");
//		int num = scan.nextInt();
//		switch(num) {
//		case 1:
//			user.ticket = 1; 
//			startTicket();
//			pointCount(1000); 
//			break;
//		case 2:
//			user.ticket = 2;
//			startTicket();
//			pointCount(1500);
//			break;
//		case 3:
//			user.ticket = 3;
//			startTicket();
//			pointCount(5000);
//			break;
//		case 4:
//			user.ticket = 4;
//			startTicket();
//			pointCount(20000);
//			break;
//		case 5:
//			user.ticket = 5;
//			startTicket();
//			pointCount(30000);
//			break;
//		default: break;
//		}
//	}
//	
=======
>>>>>>> Stashed changes
//	// 포인트 계산 함수
//	void pointCount(int price) {
//		user.point += (int) (price * 0.01); // 1% 적립
//	}
//	
//	// 시간 세팅 --> RentManager?
//	void startTicket() {
//		System.out.println(">> 결제가 완료되었습니다! *물품 최초 대여 시 이용권 이용이 시작됩니다.*");
////		Calendar now = Calendar.getInstance();
////		user.startyear = now.get(Calendar.YEAR);
////		user.startmonth = now.get(Calendar.MONTH) + 1;
////		user.startdate = now.get(Calendar.DATE);
//	}
	
	//leftmonth, leftdate 갱신
	void updateDate(User user) { // 1달은 30일이라고 가정.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int nowY = now.get(Calendar.YEAR);
		int nowM = now.get(Calendar.MONTH);
		int nowD = now.get(Calendar.DATE);
		Date nowday = null;
		Date endday = null;
		
		int endY = 0, endM = 0 , endD = 0;
<<<<<<< Updated upstream
		if(user.ticket == 3) { // 1개월
			endM = ( user.startmonth + 1 ) % 12;
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
		if(user.ticket == 4) { // 6개월
			endM = ( user.startmonth + 6 ) % 12;
=======
		// 3개월 추가
//		if(user.ticket.seasonMonth == 3) { //  3개월
//			endM = ( user.startmonth + 3 ) % 12;
//			if(endM < user.startmonth)
//				endY = user.startyear + 1;
//			endD = user.startdate;
//		}
//		if(user.ticket.seasonMonth == 6) { // 6개월
//			endM = ( user.startmonth + 6 ) % 12;
//			if(endM < user.startmonth)
//				endY = user.startyear + 1;
//			endD = user.startdate;
//		}
		if(user.ticket.seasonMonth == 12) { // 12개월
			endM = user.startmonth;
			endY = user.startyear + 1;
			endD = user.startdate;
		}
		else{ // 1개월, 3, 6 개월
			endM = ( user.startmonth + user.ticket.seasonMonth ) % 12;
>>>>>>> Stashed changes
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
<<<<<<< Updated upstream
		if(user.ticket == 5) { // 12개월
			endM = user.startmonth;
			endY = user.startyear + 1;
			endD = user.startdate;
		}
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
			user.ticket = 0;

	}
}
=======
			user.ticket = null;
	}
	
	
//	// 티켓 결제
//	void buyTicket(Scanner scan) {
//		System.out.println("구매하고 싶은 티켓을 고르세요(ticketId 입력)");
//		String ticketId = scan.next();
//		Ticket ticket = RentSystem.PayMgr.find(ticketId);
//		if (ticket == null || user.ticket != null) {
//			System.out.println("티켓이 존재하지 않거나 이미 보유하고 있습니다.");
//			return;
//		}
//		if (user.vehicle != null) {
//			System.out.println("이미 장비를 대여중입니다.");
//			return;
//		}
//		user.ticket = ticket;
//		startTicket();
////		pointCount(ticket.ticketPrice); //반납 시에 적립되도록?
//	}
//	
//	void addFavoriteRentSpot(Scanner scan) {
//		System.out.println("즐겨찾는 대여소로 등록할 대여소의 이름을 입력하세요."); //대여소 id를 따로 만드는 게 좋을 수도? 이름 잘못 입력시 확인 어렵
//		String rentSpotName = scan.next();
//		RentSpot rentSpot = RentSystem.rentSpotMgr.find(rentSpotName);
//		user.favoriteRentSpotList.add(rentSpot);
//		// + 이미 있으면 add 하지 말도록 처리 !!!
//	}
//	
//	void selectRentSpot(Scanner scan) {
//		System.out.println("선택한 장소 이름을 입력하세요. "); //테스트를 위해 존재. GUI에서는 장소 버튼 클릭 시 이름을 인식해서 처리해야 할 듯. id가 있으면 좀 더 편할 수도
//		String spotName = scan.next();
//		rentSpot = RentSystem.rentSpotMgr.find(spotName);
//	}
//	
//	// 대여 과정 : 1.장소 선택 -> 2.즐찾 -> 3.대여/반납 선택 -> 4.티켓 구매(결제) -> 5.장비 선택 -> 6.대여
//	void rentalVehicle(Scanner scan) {
//		// 티켓 결제
//		buyTicket(scan); 
//		
//		// 장비 선택
//		// code 찾아줌 + 시간 저장하는 함수 호출. + vehicleList에서 요소 제거
//		String code = scan.next();
//		// 면허 여부 확인.
//		if(code.contains("S")) {
//			if(user.license == 0) {
//				System.out.println("면허를 가지고 있지 않습니다.");
//				return;
//			}
//		}
//		
//		// 여기까지 수정 //
//		
//		for(Vehicle v : rentSpot.vehicleList) {
//			if(v.id.equals(code)) {
//				user.vehicle = v;
//				rentSpot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
//				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
//				user.vehicle.setTime();
//			}
//		}
//		if(user.vehicle == null)
//			System.out.println("해당 운송수단이 없습니다.");
//
//	}
//	
//	// 반납
//	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
//	void returnVehicle(Scanner scan) {
////	void returnVehicle(User user, RentSpot spot) {
//		// 일일권일 경우 시간계산
//		Vehicle tmp = user.vehicle;
//		Calendar now = Calendar.getInstance();
//		int nowh = now.get(Calendar.HOUR);
//		int nowm = now.get(Calendar.MINUTE);
//		
//		if(user.ticket.hour == 1) { // 1시간권
//			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
//				// 추가 계산 함수 호출 -> 1시간당 500원 추가
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
//				// 추가 계산 함수 호출
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		if(user.ticket.hour == 2) { // 2시간권
//			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
//				// 추가 계산 함수 호출
//				Payment.morePay(1);
//			}
//			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
//				// 추가 계산 함수 호출
//				Payment.morePay(nowh - tmp.starthour);
//			}
//		}
//		user.vehicle = null;
//		rentSpot.vehicleList.add(tmp); // 반납.
//	}
}
>>>>>>> Stashed changes
