package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		JButton btn_bck = new JButton("뒤로가기");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);
		
		String flaglcs = null;
		
		if (user.license==1)
			flaglcs = "있음";
		else if (user.license==0)
			flaglcs = "없음";
		
		String tick = null;
		if( user.ticket == null)
			tick = "없음";
		else
			tick = user.ticket.code;
		
		String str2 = " 이름: " + user.name;
		String str3 = " 아이디: " + user.id;
		String str4 = " 전화번호: " + user.phoneNumber;
		String str5 = " 면허: " + flaglcs;
		String str6 = " 보유 티켓번호: " + tick;
		String str7 = " 보유 포인트: " + user.point + " point";
		
		setTitle("MyPage");
	    	
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		bottom.setLayout(new GridLayout(1, 3));
		
		top.setBackground(new Color(240, 248, 239));
		bottom1.setBackground(new Color(240, 248, 239));
		bottom2.setBackground(new Color(240, 248, 239));
		bottom3.setBackground(new Color(240, 248, 239));
		
		btn_mdf.setBorderPainted(false);
		btn_mdf.setBackground(new Color(153, 231,35));
		btn_bck.setBorderPainted(false);
		btn_bck.setBackground(new Color(153, 231,35));
			
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(bottom, BorderLayout.PAGE_END);
		pane.add(myInfo, BorderLayout.CENTER);
		top.add(title);
		bottom.add(bottom1);
		bottom.add(bottom2);
		bottom.add(bottom3);
		
		bottom1.add(btn_bck);
		bottom2.add(btn_mdf);
		
		myInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		myInfo.setOpaque(true);
		myInfo.setBackground(new Color(240, 248, 239));
		
		myInfo.setHorizontalAlignment(JLabel.CENTER);
		myInfo.setText("<html>"+str2+"<p>"+str3+"<p>"+str4+"<p>"+str5+"<p>"+str6+"<p>"+str7+"</html>"); // 텍스트 줄바꿈
		
		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyMyInfo(user);
				dispose();
			}
		});
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainMenu(user);
				dispose();
			}
		});
	}
}