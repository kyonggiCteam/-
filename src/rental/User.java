package rental;

import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {

	String id;  
	String pw;
	String name;
	String phoneNumber;
	int point;
	Vehicle rental;
	boolean license; // 면허 유무
	
	void pointCount(int payment) {
		id += payment / 100;
	}

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phoneNumber = scan.next();
		id = scan.next();
		pw = scan.next();
		int state = scan.nextInt();
		if(state == 1)
			license = true;
		else
			license = false;
		
	}
	@Override	
	public void print() {
		//일단 간단하게 출력
		System.out.printf("%s %s %d \n", id, name, point);
	}
	@Override
	public boolean matches(String kwd) {
		if(id.equals(kwd))
			return true;
		if(pw.equals(kwd))
			return true;
		if(name.equals(kwd))
			return true;
		return false;
	}
	
}
