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
			
			System.out.println("�������� �����ϰ� ��ø� y�� �Է����ּ���.");
			if (scan.next().equals("y")) {
				license = true;
			} else {
				license = false;
			}
		}

		@Override
		public void print() {
			System.out.printf("[�� ����]\n %s���� �̿��: %s | ����Ʈ: %d�� | �뿩 ����: %b\n", 
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

		// ��й�ȣ ��ġ Ȯ��
		public boolean passwordMatch(String pwd) {
			return this.pwd.equals(pwd);
		}
		
		// ���� ����
		public void modifyInfo(Scanner scan) {	
			String tmp = null;
			
			System.out.print("����ȣ(������� ����): ");
			scan.nextLine();  // �߰����־�� tmp�� ���� �� ��
			tmp = scan.nextLine();
			if (!tmp.equals(""))  // ���Ͱ� �ƴϸ�
				pwd = tmp;
			
//			System.out.printf("���(%s): ", grade);
//			tmp = scan.nextLine();
//			if (!tmp.equals(""))
//				grade = tmp;
			
			System.out.printf("����Ʈ(%d��): ", point);
			tmp = scan.nextLine();
			if (!tmp.equals(""))
				point = Integer.parseInt(tmp);
		}
}
