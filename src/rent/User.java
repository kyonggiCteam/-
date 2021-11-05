package rent;

import java.util.Scanner;

import mgr.Manageable;

//import store.Order;

public class User implements Manageable {
		String id;
		String pwd;
		String name;
		String phoneNumber;
		boolean license;
		String ticket;
		int point;
		boolean useState;
//		ArrayList<RentData> myRentDataList = new ArrayList<>(); 
		
		@Override
		public void read(Scanner scan) {
			id = scan.next();
			pwd = scan.next();
			name = scan.next();
			phoneNumber = scan.next();
//			ticket = scan.next();
//			point = scan.nextInt();
//			useState = scan.nextBoolean();
			
			System.out.println("면허증을 보유하고 계시면 y를 입력해주세요.");
			if (scan.next().equals("y")) {
				license = true;
			} else {
				license = false;
			}
		}

		@Override
		public void print() {
			System.out.printf("[내 정보]\n %s님의 이용권: %s | 포인트: %d점 | 대여 여부: %b\n", 
										name, ticket, point, useState);
		}

		@Override
		public boolean matches(String kwd) {
			if (id.equals(kwd))
				return true;
			if (name.contains(kwd))
				return true;
			if (phoneNumber.contains(kwd))
				return true;
			if (ticket.equals(kwd))
				return true;
//			for (Order od: myOrderList)
//				if (od.matches(kwd))
//					return true;
			return false;
		}

		// 비밀번호 일치 확인
		public boolean passwordMatch(String pwd) {
			return this.pwd.equals(pwd);
		}
		
		// 정보 수정
		public void modifyInfo(Scanner scan) {	
			String tmp = null;
			
			System.out.print("새암호(변경없음 엔터): ");
			scan.nextLine();  // 추가해주어야 tmp에 값이 잘 들어감
			tmp = scan.nextLine();
			if (!tmp.equals(""))  // 엔터가 아니면
				pwd = tmp;
			
//			System.out.printf("등급(%s): ", grade);
//			tmp = scan.nextLine();
//			if (!tmp.equals(""))
//				grade = tmp;
			
			System.out.printf("포인트(%d점): ", point);
			tmp = scan.nextLine();
			if (!tmp.equals(""))
				point = Integer.parseInt(tmp);
		}
}
