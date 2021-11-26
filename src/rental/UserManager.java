package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user = new User();
	RentSpot rentSpot;
	RentSpot returnSpot;
	
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
				updateDate();
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
			System.out.println("이미 사용 중인 아이디입니다.");
			return;
		}
		System.out.println(">> 사용 가능한 아이디입니다.");
		System.out.print("비밀번호 확인: ");
		String checkpw = scan.next();
		if(!user.passwordMatch(checkpw)) {
			System.out.println(">> 비닐번호가 올바르지 않습니다.");
			return;
		}
		mList.add(user);
	}
	
//	// 회원 탈퇴
//	public void withdraw(Scanner scan) {
//		System.out.println("<탈퇴>");
//		System.out.println("비밀번호를 입력해주세요.");
//		if (user.passwordMatch(scan.next())) {
//			mList.remove(user);
//			logout(scan);
//		}
//	}
	
	// 로그아웃
	public void logout(Scanner scan) {
		login(scan);
	}
	
	// 티켓 결제
	void buyTicket(Scanner scan) {
		System.out.println("구매하고 싶은 티켓을 고르세요(ticketCode 입력)");
		String ticketCode = scan.next();
		Ticket ticket = RentSystem.payMgr.find(ticketCode);
		if (user.ticket != null) {
			System.out.printf(">> 현재 보유 중인 티켓은 %s입니다.\n", user.ticket);
			return;
		}
		if (user.vehicle != null) {
			System.out.println(">> 이미 장비를 대여중입니다.");
			return;
		}
		
		// 결제 화면
		System.out.printf("선택하신 티켓은 %s입니다. 가격은 %d원입니다. 결제하시겠습니까?\n현재 보유 포인트는 %d입니다."
									, ticket, ticket.price, user.point);
		// 결제 수행
		user.ticket = ticket;
		startTicket();
//		pointCount(ticket.ticketPrice); //반납 시에 적립되도록?
	}
	
	// 포인트 계산 함수
	void pointCount(int price) {
		user.point += (int) (price * 0.01); // 1% 적립
	}
	
	// 시간 세팅 --> RentManager?
	void startTicket() {
		System.out.println(">> 결제가 완료되었습니다! *물품 최초 대여 시 이용권 이용이 시작됩니다.*");
		Calendar now = Calendar.getInstance();
		user.startyear = now.get(Calendar.YEAR);
		user.startmonth = now.get(Calendar.MONTH) + 1;
		user.startdate = now.get(Calendar.DATE);
	}
	
	//leftmonth, leftdate 갱신
	void updateDate() { // 1달은 30일이라고 가정.
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
	
	void addFavoriteRentSpot(Scanner scan) {
		System.out.println(">> 즐겨찾는 대여소로 등록할 대여소의 코드를 입력하세요."); //대여소 id를 따로 만드는 게 좋을 수도? 이름 잘못 입력시 확인 어렵
		String spotCode = scan.next();
		RentSpot rentSpot = RentSystem.rentSpotMgr.find(spotCode);
		user.favoriteRentSpotList.add(rentSpot);
		// + 이미 있으면 add 하지 말도록 처리 !!!
	}
	
	void selectRentSpot(Scanner scan) {
		System.out.println("선택한 장소 이름을 입력하세요. "); //테스트를 위해 존재. GUI에서는 장소 버튼 클릭 시 이름을 인식해서 처리해야 할 듯. id가 있으면 좀 더 편할 수도
		String spotName = scan.next();
		rentSpot = RentSystem.rentSpotMgr.find(spotName);
	}
	
	// 대여 과정 : 1.장소 선택 -> 2.즐찾 -> 3.대여/반납 선택 -> 4.티켓 구매(결제) -> 5.장비 선택 -> 6.대여
	void rentalVehicle(Scanner scan) {
		// 티켓 결제
		buyTicket(scan); 
		
		// 장비 선택
		// code 찾아줌 + 시간 저장하는 함수 호출. + vehicleList에서 요소 제거
		String vehicleCode = scan.next();
		// 면허 여부 확인.
		if(vehicleCode.contains("S")) {
			if(user.license == 0) {
				System.out.println("면허를 가지고 있지 않습니다.");
				return;
			}
		}
		
		// 여기까지 수정 //
		// 선택한 장소의, 선택한 티켓의 브랜드의 장비들만 보여줘야 함 or 그러한 장비만 선택할 수 있게 해야 함
		for(Vehicle v : rentSpot.vehicleList) {
			
			if(v.code.equals(vehicleCode)) {
				user.vehicle = v;
				rentSpot.vehicleList.remove(v); // 운송리스트에서 제거해야지 다른 사람 이용 불가.
				// 정기권을 가지고 있는 사람은 시간 저장이 필요하지 않음. => 다른 방법이 더 좋을듯
				user.vehicle.setTime();
			}
		}
		if(user.vehicle == null)
			System.out.println("해당 운송수단이 없습니다.");

	}
	
	// 반납
	// 반납 시간계산 -> 정기권인 경우 시간계산 필요 없음 / 일일권인 경우 -> 시간계산 필요.
	void returnVehicle(Scanner scan) {
//	void returnVehicle(User user, RentSpot spot) {
		// 일일권일 경우 시간계산
		Vehicle tmp = user.vehicle;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR);
		int nowm = now.get(Calendar.MINUTE);
		
		if(user.ticket.hour == 1) { // 1시간권
			if( (nowh - tmp.starthour) == 1 && nowm > tmp.startmin ) { // 1시간 추가 계산
				// 추가 계산 함수 호출 -> 1시간당 500원 추가
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 1) {  // nowh - tmp.starthour 차이 만큼 추가 계산
				// 추가 계산 함수 호출
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		if(user.ticket.hour == 2) { // 2시간권
			if( (nowh - tmp.starthour) == 2 && nowm > tmp.startmin) { // 1시간 추가
				// 추가 계산 함수 호출
				Payment.morePay(1);
			}
			if((nowh - tmp.starthour) > 2) { // 차이만큼 추가 계산
				// 추가 계산 함수 호출
				Payment.morePay(nowh - tmp.starthour);
			}
		}
		user.vehicle = null;
		rentSpot.vehicleList.add(tmp); // 반납.
	}
}