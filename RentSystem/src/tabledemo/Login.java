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

import rental.RentSystem;
import rental.User;

public class Login extends JFrame {
	User user;
	
	public Login() {
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

		JButton back = new JButton("뒤로가기");

		j1.setBorderPainted(false);
		back.setBorderPainted(false);
		j1.setBackground(new Color(153, 231,35));
		back.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));
		setBackground(new Color(240, 248, 239));
		
		add(back);
		add(j1);
		setSize(350, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1.setBounds(30, 80, 40, 30);
		l2.setBounds(30, 120, 50, 30);
		t1.setBounds(120, 80, 100, 30);
		t2.setBounds(120, 120, 100, 30);
		j1.setBounds(240, 80, 80, 65);
		back.setBounds(15, 225, 100, 30);
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
					if(user.ticket != null)
						RentSystem.userMgr.updateDate(user);
					new MainMenu(user);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(c, "올바른 회원 정보가 아닙니다. 다시 입력해주세요.");
			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginJoin();
				dispose();
			}
		});
	}
}