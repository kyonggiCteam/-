package rental;

import mgr.Factory;

public class RentSystem {
	
	VehicleManager vehicleMgr = new VehicleManager();
	UserManager userMgr = new UserManager();
	RentSpotManager rentspotMgr = new RentSpotManager();
	// RentSpot 데이터에 정류소에 위치한 vehicleList에 vehicle도 넣어줘야함..
	

	void run() {
		// 데이터 읽기
		userMgr.readAll("user.txt", new Factory<User>() {
			@Override
			public User create() {
				return new User();
			}
		});
		userMgr.printAll();
		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>(){
			@Override
			public Vehicle create() {
				return new Vehicle();
			}
		});
		vehicleMgr.printAll();
		rentspotMgr.readAll("rentspot.txt", new Factory<RentSpot>() {
			@Override
			public RentSpot create() {
				return new RentSpot();
			}
		});
		rentspotMgr.printAll();
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentSystem rental = new RentSystem();
		rental.run();
	}

}
