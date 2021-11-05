package PublicBike;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RentSystemFrame extends JFrame {

	void run() {
		RentSystemFrame frame = new RentSystemFrame();
    	frame.createAndShowGUI();
	}
	
	private void createAndShowGUI() {   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        addComponentsToPane();
        setPreferredSize(new Dimension(300, 300));
        pack();
        setVisible(true);
    }
	
	public void addComponentsToPane() {
		setTitle("RentPublicBikeSystem");
		Container pane = getContentPane();
		
	}
	
	class ButtonListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		String buttonName = e.getActionCommand();
    		
    		User user = new User();
    		
    		int licenseJudger = 0;

    		user.license(licenseJudger);
    		
           
    		if (buttonName.equals("로그인")) {

    		} else if (buttonName.equals("회원가입")) {

    		} else if (buttonName.equals("중복확인")) {

    		} else if (buttonName.equals("가입")) {

    		} else if (buttonName.equals("대여")) {
    			
    		}
    		//...
    		

    	}
	}
}
