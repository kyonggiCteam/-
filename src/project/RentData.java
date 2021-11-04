package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import mgr.Manageable;

public class RentData implements Manageable {
	// 대여시간 발권종류 대여소 대여물품(여러개 대여가능)
	int rentSort; //대여권종류(정기권/대여권)
	int rentTime; //대여권시간(정기권-1,6,12개월/대여권-1,2시간)
	String rentspot; //대여소
	String rentProducts[]; //대여물품
	static public ArrayList<RentData> rentList=new ArrayList<>();
	// 대여시간 선택
	// 대여(결제) 및 반납 기능

	void rent() {
		chooseRentSort();
		rentTime();
	}

	void rentTime() {
			//현재 시간 표시
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
			Date time = new Date();
			String time1 = format.format(time);
			System.out.println(time1);
			
		}

	void chooseRentSort() {
		
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}
}
