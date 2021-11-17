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
//	// 로그아웃
//	public void logout(Scanner scan) {
//		//login(scan);
//		//exit(0);
//	}
	
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
		if(user.ticket == 3) { // 1개월
			endM = ( user.startmonth + 1 ) % 12;
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
		if(user.ticket == 4) { // 6개월
			endM = ( user.startmonth + 6 ) % 12;
			if(endM < user.startmonth)
				endY = user.startyear + 1;
			endD = user.startdate;
		}
		if(user.ticket == 5) { // 12개월
			endM = user.startmonth;
			endY = user.startyear + 1;
			endD = user.startdate;
		}
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
			user.ticket = 0;

	}
}
