package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mgr.Manager;

public class UserManager extends Manager<User> {
	User user;
	RentSpot rentSpot;
	RentSpot returnSpot;
	
	//point 사용 계산
	public void usePoint(User user, int used) {
		user.point -= used;
	}
	
	public void join(String[] userInfo) {	
		User user = new User();
		user.name = userInfo[0];
		user.phoneNumber = userInfo[1];
		user.id = userInfo[2];
		user.pwd = userInfo[3];
		user.license = Integer.parseInt(userInfo[4]);		
		mList.add(user);
	}
	
	public boolean idDuplicate(String id) {
		return (find(id) != null);
	}
	
	public void updateDate(User user) {
		if (user.ticket.ticketType.contentEquals("일일권"))
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int nowY = now.get(Calendar.YEAR);
		int nowM = now.get(Calendar.MONTH);
		int nowD = now.get(Calendar.DATE);
		Date nowday = null;
		Date endday = null;
		
		int endY = 0, endM = 0 , endD = 0;
		endM = ( user.startmonth + user.ticket.month ) % 12 + 1;
		if(endM < user.startmonth)
			endY = user.startyear + 1;
		endD = user.startdate;
		
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
		if(compare > 0) {
			long diffSec = (cmpDate.getTimeInMillis() - now.getTimeInMillis() ) / 1000;
			long diffDays = diffSec / ( 24*60*60);
			user.leftday = (int)diffDays;
		}
		else if(user.ticket.month == 12) { // ticket이 12개얼 경우.
			user.leftday = 365;
		}
		else
			user.ticket = null;
	}
}