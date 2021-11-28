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
    }
}
