package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
	UserManager userMgr = new UserManager();
	User user;
	public Login() {
		JPanel p = new JPanel();
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
		add(j1);
		setSize(350, 300);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1.setBounds(40, 50, 40, 30);
		l2.setBounds(40, 90, 50, 30);
		t1.setBounds(120, 50, 150, 30);
		t2.setBounds(120, 90, 150, 30);
		j1.setBounds(125, 200, 80, 30);
		add(p);
		setVisible(true);
	
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {// 로그인 할때
				String id = t1.getText();
				String pw = t2.getText();
				user = RentSystem.userMgr.find(id);
				
<<<<<<< Updated upstream
				if (user != null && user.passwordMatch(pw))
					JOptionPane.showMessageDialog(null, "로그인 성공");
				else
=======
				if (user != null && user.passwordMatch(pw)) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					Table t = new Table();
	                setVisible(false);
				} else
>>>>>>> Stashed changes
					JOptionPane.showMessageDialog(null, "올바른 회원 정보가 아닙니다. 다시 입력해주세요.");
			}
		});
	}
}
