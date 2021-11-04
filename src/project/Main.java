package project;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class Main {
	Scanner scan = new Scanner(System.in);

	static Manager<Vehicle> vehicleMgr = new Manager<>();
	static Manager<User> userMgr = new Manager<>();
	
	void run() {
		vehicleMgr.readAll("kickboard.txt", new Factory<Vehicle>() {
			public Vehicle create() {
				return new Vehicle();
			}
		});

		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.run();
	}
}
