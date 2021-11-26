package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.User;

public class MyPage extends JFrame {

	
	public MyPage(User user) {
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JLabel myInfo = new JLabel();
		
		JLabel title = new JLabel("My Page");
		JPanel bottom1 = new JPanel();
		JPanel bottom2 = new JPanel();	
		JPanel bottom3 = new JPanel();
		
		JButton btn_mdf = new JButton("회원정보 수정");
		JButton btn_bck = new JButton("돌아가기");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		String flaglcs = null;
		
		if (user.license == 1)
			flaglcs = "있음";
		else if (user.license == 0)
			flaglcs = "없음";
			
		
		String str1 = "현재 회원정보";
		String str2 = "- 이름: " + user.name;
		String str3 = "- 아이디: " + user.id;
		String str4 = "- 전화번호: " + user.phoneNumber;
		String str5 = "- 면허: " + flaglcs;
		String str6 = "- 보유한 티켓번호: " + user.ticket.code;
		String str7 = "- 보유 포인트: " + user.point;
		
		setTitle("MyPage");
	    	
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		bottom.setLayout(new GridLayout(1, 3));
			
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(bottom, BorderLayout.PAGE_END);
		pane.add(myInfo, BorderLayout.CENTER);
		top.add(title);
		bottom.add(bottom1);
		bottom.add(bottom2);
		bottom.add(bottom3);
		
		bottom1.add(btn_bck);
		bottom2.add(btn_mdf);
		
		myInfo.setOpaque(true);
		myInfo.setBackground(new Color(0, 250, 0));
		
		myInfo.setHorizontalAlignment(JLabel.CENTER);
		myInfo.setText("<html>"+str1+"<br>"+str2+"<p>"+str3+"<p>"+str4+"<p>"+str5+"<p>"+str6+"<p>"+str7+"</html>"); // 텍스트 줄바꿈
		
		
		
		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//정보수정 창으로 이동.
				// TODO Auto-generated method stub
				ModifyMyInfo m = new ModifyMyInfo(user);
				setVisible(false); // 창 안보이게 하기 
			}
		});;
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//메뉴로 이동
				// TODO Auto-generated method stub
				Table t = new Table(user);
				setVisible(false); // 창 안보이게 하기
			}
		});;
		    
		/*class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String buttonName = e.getActionCommand();
		           
				if (buttonName.equals("회원정보 수정")) {
					ModifyMyInfo m = new ModifyMyInfo();
					m.run();
					setVisible(false);
				} else if (buttonName.equals("돌아가기")) {
					Table t = new Table();
					setVisible(false);
				} 
			}
		}*/
	}
	
	
    
	
   
    
}