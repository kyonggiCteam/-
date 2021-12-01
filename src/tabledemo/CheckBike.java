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
		
		// �������� ȭ�� ����� ��ġ
		setLocation(400,200);
				
        //Create and set up the content pane.
        BikeTable newContentPane = new BikeTable(user, spot);
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);
        //add(newContentPane);

        //Display the window.
        pack();
        setVisible(true);
	}
}
