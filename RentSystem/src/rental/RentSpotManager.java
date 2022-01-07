package rental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import mgr.Manager;

public class RentSpotManager extends Manager<RentSpot> {
	
	//대여(GUI)
	public void rentalVehicle(User user, Vehicle vehicle, RentSpot spot) { // ( vehicle : 대여할 vehicle
		Vehicle remove = vehicle;
		// 정류소에서 삭제
		spot.vehicleList.remove(remove);
		// 칼로리 계산하기 위해서 시작시간 다 재야함.
		vehicle.setTime();
		user.vehicle = vehicle;
		System.out.printf("\n%d시 %d분\n", vehicle.starthour, vehicle.startmin);
	}
	
	// 반납 -> 전체 이용 시간 계산 ( 현재시간 - 시작시간)
	public int returnVehicle(User user, RentSpot spot) {
		Vehicle tmp = user.vehicle;
		int useh = 0, usem = 0;
		Calendar now = Calendar.getInstance();
		int nowh = now.get(Calendar.HOUR_OF_DAY);
		int nowm = now.get(Calendar.MINUTE);
		System.out.printf("현재 %d시 %d분\n", nowh, nowm);
		
		if(nowh == tmp.starthour)
			usem = nowm - tmp.startmin;
		else if( nowh > tmp.starthour && nowm < tmp.startmin) {
			useh = nowh - tmp.starthour - 1;
			usem = 60 + nowm - tmp.startmin;
		}
		else {
			useh = nowh = tmp.starthour;
			usem = nowm - tmp.startmin;
		}
		user.vehicle = null;
		spot.vehicleList.add(tmp);
		
		if(user.ticket.ticketType.equals("일일권"))
			user.ticket = null;

		return (useh*60 + usem);
	}
	
	// 즐겨찾기
	public void oftenSpot(User user, String spotname) {
		try {
			BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
			BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
			
			ArrayList <String[]> arrays = new ArrayList<String[]>(); 
			String str = null;
			int add = 8;
			
			if(user.oftenSpotList.contains(spotname)) {
				int option =
				JOptionPane.showConfirmDialog(null, "이 정류장을 즐겨찾기 목록에서 삭제하시겠습니까?","즐겨찾기",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
			
				if (option == 0) {
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);
						
						if (strarr[2].matches(user.id)) {
							for (int i=8;!strarr[i].equals("end");i++) {
								if (strarr[i].equals(spotname)) {
									for (int j=i;!strarr[j].equals("end");j++) {
										strarr[j]=strarr[j+1];
									}
								}
							}
						}
					}
					user.oftenSpotList.remove(spotname);
					JOptionPane.showMessageDialog(null, "즐겨찾기 목록에서 제외됩니다.");
					
					new FileOutputStream("user.txt").close();
				
					for (int i=0; i < arrays.size(); i++) {
						String[] outputarr = arrays.get(i);
						for (int k=1; !outputarr[k].equals("end"); k++) {
								outputarr[0] = outputarr[0] + " " + outputarr[k];
						}
						String oneLine = outputarr[0]+" end";
						if (i==arrays.size()-1) {
							bos.write(oneLine);
						}
						else {
							bos.write(oneLine + "\n");
						}
					}

					bur.close();
					bos.close();
				}
			}
			
			else {
				int option =
				JOptionPane.showConfirmDialog(null, "이 정류장을 즐겨찾기 정류장으로 지정하시겠습니까?","즐겨찾기",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
				if (option == 0) {
					
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);
						
						if (strarr[2].matches(user.id)) {
							for (int i=8;!strarr[i].equals("end");i++) {
								add = i+1;
							}
							if (add<=10) {
								strarr[add] = spotname + " end";
								user.oftenSpotList.add(spotname);
							}
						}
					}
					if (add>=11) 
						JOptionPane.showMessageDialog(null, "즐겨찾기는 최대 3개의 정류소까지만 선택하실 수 있습니다.");
					
					new FileOutputStream("user.txt").close();
				
					for (int i=0; i < arrays.size(); i++) {
						String[] outputarr = arrays.get(i);
						for (int k=1; k < outputarr.length; k++) {
							outputarr[0] = outputarr[0] + " " + outputarr[k];
						}
						String oneLine = outputarr[0];
						if (i==arrays.size()-1) {
							bos.write(oneLine);
						}
						else {
							bos.write(oneLine + "\n");
						}
					}
				
					bur.close();
					bos.close();
				}
			} 
		} catch (Exception ex) {
			 ex.printStackTrace();
		}
	}
}