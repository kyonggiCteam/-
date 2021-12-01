package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSystem;
import rental.User;
import rental.UserManager;

public class Login extends JFrame {
	
	//UserManager userMgr = new UserManager();
	// -> 이렇게 생성하면 기존의 데이터가 저장되어 있는 mList를 사용하지 못하지 않나?
	
	//UserManager 일단 사용 안됨.
//	UserManager userMgr = UserManager.getInstance();
	// 로그인 하면 User 찾아줌.
	User user;
	public Login() {
		//JPanel p = new JPanel();
		Container c = getContentPane();
		c.setLayout(null);
		
		Label l1 = new Label("아이디");
		Label l2 = new Label("비밀번호");
		add(l1);
		add(l2);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		add(t1);
		add(t2);
		t2.setEchoChar('*');
		JButton j1 = new JButton("로그인");
		//뒤로가기 버튼 추가
		JButton back = new JButton("뒤로가기");
		
		//back.setFont(new Font("맑은고딕", Font.BOLD, 9));
		
		j1.setBorderPainted(false); // 버튼 테두리 설정해제
		back.setBorderPainted(false); // 버튼 테두리 설정해제
		j1.setBackground(new Color(153, 231,35));
		back.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));
		setBackground(new Color(240, 248, 239));
		
		add(back);
		add(j1);
		setSize(350, 300);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		l1.setBounds(30, 80, 40, 30);
		l2.setBounds(30, 120, 50, 30);
		t1.setBounds(120, 80, 100, 30);
		t2.setBounds(120, 120, 100, 30);
		j1.setBounds(240, 80, 80, 65);
		back.setBounds(15, 225, 100, 30);
		//add(p);
		setVisible(true);
	
		// 로그인
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				String id = t1.getText();
				String pw = t2.getText();
				user = RentSystem.userMgr.find(id);
				
				if (user != null && user.passwordMatch(pw)) {
					JOptionPane.showMessageDialog(c, "로그인 성공");
					// user가 티켓 가지고 있으면 
					if(user.ticket != null)
						RentSystem.userMgr.updateDate(user);
					new MainMenu(user);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(c, "올바른 회원 정보가 아닙니다. 다시 입력해주세요.");
			}
		});
		// 뒤로가기
		back.addActionListener(new ActionListener() { // 뒤로가기
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginJoin();
				dispose();
			}
		});
	}
}