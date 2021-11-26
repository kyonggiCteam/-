package tabledemo;

import rental.RentSystem;
import rental.UserManager;
import rental.Vehicle;

import java.util.ArrayList;

import javax.swing.JFrame;

import mgr.Factory;
import rental.RentSpotManager;
import rental.VehicleManager;
import rental.Ticket;
import rental.User;

public class GUIMain {
	static RentSystem rentSystem = RentSystem.getInstance();

	public static void main(String args[]) {
		rentSystem.run();
		startGUI();
	}
	
	public static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
    private static void createAndShowGUI() {
    	ArrayList<User> userList = new ArrayList<>(); // 임시 코드 나중에 지워야됨
    	new LoginJoin(userList);
    	// ---------------------- 로그인 처리까지 진행 ------------------------
    	
    	// JTable ==> 우선 로그인이랑 동시에 뜨는데 추후 고치기 
        //Create and set up the window.
        JFrame frame = new JFrame("TableSelectionDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TicketTableSelectionDemo newContentPane = new TicketTableSelectionDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}