package project;

import java.util.Scanner;

import mgr.Manageable;

public class Vehicle implements Manageable{
	//��ġ ��뿩�� ���忩��
	String location;
	String use;
	String breakdown;
	String code;//�������ڵ�(����������(BE)�� �Ϲ�������(BR)), ű�����ڵ�(K)
	
	@Override
	public void read(Scanner scan) {
		location = scan.next();
		use = scan.next();
		breakdown = scan.next();
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
	
	//���忩��
	void Break() {
		if(breakdown.contains("y"))
			System.out.print("����");
		else
			System.out.print("��밡��");
	}

}
