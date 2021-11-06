package PublicBike;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AFrame1 extends JFrame {

	// 각 화면의 상태를 나타냄, true가 현재 띄워진 창을 의미
	boolean[] flagArr = {true, false, false, false, false, false, false, false, false, false, false, false, false};
	// 화면 전환 시 띄울 창을 선택
	void framechanger(int i, int j) {
		flagArr[i+1]=false;
		flagArr[j+1]=true;
	}
	
	int cv, nv; // currentView, nextView;
	
	void run() {
		AFrame1 frame = new AFrame1();
    	frame.createAndShowGUI();
	}
	
	private void createAndShowGUI() {   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        addComponentsToPane();
        setPreferredSize(new Dimension(300, 300));
        pack();
        setVisible(flagArr[0]);
    }
	
	public void addComponentsToPane() {
		setTitle("RentPublicBikeSystem");
		Container pane = getContentPane();
	}
	
	class ButtonListener implements ActionListener {
		
		@Override
    	public void actionPerformed(ActionEvent e) {
    		String buttonName = e.getActionCommand();
    		
    		if (buttonName.equals("로그인")) {
    			cv = 1;
    			nv = 3;
    			framechanger(cv,nv);
    			setVisible(flagArr[cv-1]);
    			setVisible(flagArr[nv-1]);
    		} else if (buttonName.equals("회원가입")) {
    			cv = 1; nv = 2;
    			framechanger(cv,nv);
    			setVisible(flagArr[cv-1]);
    			setVisible(flagArr[nv-1]);
    		}
    	}
	}
}
