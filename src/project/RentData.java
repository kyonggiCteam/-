package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import mgr.Manageable;

public class RentData implements Manageable {
	// �뿩�ð� �߱����� �뿩�� �뿩��ǰ(������ �뿩����)
	int rentSort; //�뿩������(�����/�뿩��)
	int rentTime; //�뿩�ǽð�(�����-1,6,12����/�뿩��-1,2�ð�)
	String rentspot; //�뿩��
	String rentProducts[]; //�뿩��ǰ
	static public ArrayList<RentData> rentList=new ArrayList<>();
	// �뿩�ð� ����
	// �뿩(����) �� �ݳ� ���

	void rent() {
		chooseRentSort();
		rentTime();
	}

	void rentTime() {
			//���� �ð� ǥ��
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy�� MM��dd�� HH��mm��ss��");
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
