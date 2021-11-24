package tabledemo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Map4 extends JPanel {
	JButton j1 = new JButton("A 참플란트치과의원");
	JButton j2 = new JButton("B 롯데백화점수원점");
	JButton j3 = new JButton("C 타이어타운수원역점");
	JButton j4 = new JButton("D 스마일약국");
	JButton j5 = new JButton("E 성심내과의원");
	JButton j6 = new JButton("F 수원역2번출구");
	JButton j7 = new JButton("G 서평초등학교");
	JButton j8 = new JButton("H 세화로168번길");
	JButton back = new JButton("뒤로가기");

	Image img = new ImageIcon(Main.class.getResource("../image/background.png")).getImage();

	Map4() {
		setLayout(null);
		setSize(700, 500);
		setVisible(true);
		// setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		j1.setBounds(610, 10, 165, 30);
		add(j1);
		j2.setBounds(610, 45, 165, 30);
		add(j2);
		j3.setBounds(610, 80, 165, 30);
		add(j3);
		j4.setBounds(610, 115, 165, 30);
		add(j4);
		j5.setBounds(610, 150, 165, 30);
		add(j5);
		j6.setBounds(610, 185, 165, 30);
		add(j6);
		j7.setBounds(610, 220, 165, 30);
		add(j7);
		j8.setBounds(610, 255, 165, 30);
		add(j8);
		back.setBounds(10, 505, 100, 25);
		add(back);

		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		j8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				Main m = new Main();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
