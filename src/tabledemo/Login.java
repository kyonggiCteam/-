package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.*;
import mgr.*;

public class Login extends JFrame {
	
	
	public Login(ArrayList<User> userList) {
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
		JButton btn_bck = new JButton("back");
		add(btn_bck);
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
		btn_bck.setBounds(250, 220, 70, 30);
		add(p);
		setVisible(true);
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin(userList);
				setVisible(false); // 창 안보이게 하기 
			}
		});;
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {// 로그인 할때
				String id = t1.getText();
				String pw = t2.getText();
				
				/*
				for (User finduser: userList) {
					if (id.equals(finduser.id) && pw.equals(finduser.pwd))
						member = finduser;
					else
						member = null;
				}
				
				if (member != null) {
					JOptionPane.showMessageDialog(null, member.name+"님 반갑습니다.");
					
				}
				else
					JOptionPane.showMessageDialog(null, "올바른 회원 정보가 아닙니다. 다시 입력해주세요.");
				*/
				
				User member = new User();
				Vehicle userbike = new Vehicle();
				Ticket userticket = new Ticket();
				
				member.id = "moon442";
				member.pwd = "4321";
				member.name = "문석호";
				member.license = 1;
				member.phoneNumber = "01027271010";
				member.point = 0;
				member.vehicle = userbike;
				member.ticket = userticket;
				
				userbike.code = "BI0101";
				userticket.code = "V01";
				
				JOptionPane.showMessageDialog(null, member.name+"님 반갑습니다.");
				Table t = new Table(userList, member);
				setVisible(false);
				}
			
		});

	}
}