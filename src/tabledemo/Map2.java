package tabledemo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rental.User;
//import tabledemo.Map1.MyActionListener;

public class Map2 extends JPanel {
	JButton j1 = new JButton("A 경기대후문");
	JButton j2 = new JButton("B 세븐일레븐광교점");
	JButton j3 = new JButton("C 시은소교회");
	JButton j4 = new JButton("D 동물병원동행");
	JButton j5 = new JButton("E 꿈의교회");
	JButton j6 = new JButton("F 광교E편한세상아파트");
	JButton j7 = new JButton("G 뚜레쥬르광교로점");
	JButton j8 = new JButton("H 광교푸르지오");
	JButton back = new JButton("뒤로가기");
	User user;
	Map map;

	Image img = new ImageIcon("image/background.png").getImage();

	Map2(Map m, User user) {
		this.user = user;
		map = m;
		setLayout(null);
		setSize(700, 500);
		setVisible(false);
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
		
		MyActionListener rentListener = new MyActionListener();
		j1.addActionListener(rentListener);	
		j2.addActionListener(rentListener);
		j3.addActionListener(rentListener);
		j4.addActionListener(rentListener);		
		j5.addActionListener(rentListener);
		j6.addActionListener(rentListener);
		j7.addActionListener(rentListener);
		j8.addActionListener(rentListener);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 뒤로가기
				// TODO Auto-generated method stub
				new Table(user);
				m.setVisible(false); // 창 안보이게 하기 
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			RentReturn rr = new RentReturn(user);
			map.setVisible(false);
			
		}
	}	
	
	
}