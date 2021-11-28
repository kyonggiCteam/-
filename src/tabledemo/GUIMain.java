package tabledemo;

import rental.RentSystem;
import rental.UserManager;
import rental.Vehicle;

import javax.swing.JFrame;

import mgr.Factory;
import rental.RentSpotManager;
import rental.VehicleManager;
import rental.Ticket;

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
    	new LoginJoin();
    	// ---------------------- �α��� ó������ ���� ------------------------
    	
//    	// JTable ==> �켱 �α����̶� ���ÿ� �ߴµ� ���� ��ġ�� 
//        //Create and set up the window.
//        JFrame frame = new JFrame("TableSelectionDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        TicketTableSelectionDemo newContentPane = new TicketTableSelectionDemo(1);
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
    }
}