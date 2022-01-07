package tabledemo;

import javax.swing.JFrame;

import rental.RentSpot;
import rental.User;

public class CheckBike extends JFrame{
	private static CheckBike checkBike = null;
	private CheckBike() {}
	public static CheckBike getInstance() {
		if (checkBike == null)
			checkBike = new CheckBike();
		return checkBike;
	}

	CheckBike(User user, RentSpot spot){
		checkBike = this;
		setTitle("CheckBike");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400,200);
				
        BikeTable newContentPane = new BikeTable(user, spot);
        newContentPane.setOpaque(true);
        setContentPane(newContentPane);

        pack();
        setVisible(true);
	}
}
