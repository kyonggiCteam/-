package tabledemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import rental.RentSpot;
import rental.RentSystem;
import rental.User;

public class Map2 extends JPanel implements ActionListener{
	// 장안구
	JButton j1 = new JButton("A 수원보훈원우체국");
	JButton j2 = new JButton("B 꿈마루어린이집");
	JButton j3 = new JButton("C 효준아파트");
	JButton j4 = new JButton("D 팔복빌딩");
	JButton j5 = new JButton("E 경기대한의원");
	JButton j6 = new JButton("F 신미주아파트");
	JButton j7 = new JButton("G 창용초등학교");
	JButton j8 = new JButton("H CU편의점목화아파트");
	JButton back = new JButton("뒤로가기");
	User user;
	RentSpot spot;

	Image img = new ImageIcon("장안구.png").getImage();

	Map2(User user) {
		this.user = user;
		setLayout(null);
		setSize(700, 500);
		setVisible(false);
		
		j1.setBorderPainted(false);
		j1.setBackground(new Color(41, 175,76));
		j2.setBorderPainted(false);
		j2.setBackground(new Color(41, 175,76));
		j3.setBorderPainted(false);
		j3.setBackground(new Color(41, 175,76));
		j4.setBorderPainted(false);
		j4.setBackground(new Color(41, 175,76));
		j5.setBorderPainted(false);
		j5.setBackground(new Color(41, 175,76));
		j6.setBorderPainted(false);
		j6.setBackground(new Color(41, 175,76));
		j7.setBorderPainted(false);
		j7.setBackground(new Color(41, 175,76));
		j8.setBorderPainted(false);
		j8.setBackground(new Color(41, 175,76));
		back.setBorderPainted(false);
		back.setBackground(new Color(153, 231,35));

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
		j1.addActionListener(this);	
		j2.addActionListener(this);
		j3.addActionListener(this);
		j4.addActionListener(this);		
		j5.addActionListener(this);
		j6.addActionListener(this);
		j7.addActionListener(this);
		j8.addActionListener(this);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainMenu(user);
				Map.getInstance().dispose();
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonName = e.getActionCommand();
		String[] spotnamearr = buttonName.split(" ");
		String spotname = spotnamearr[1];
		spot = RentSystem.rentSpotMgr.find(spotname);
		RentSystem.rentSpotMgr.oftenSpot(user, spotname);
		
		new RentReturn(user,spot);
		Map.getInstance().dispose();
	}	
	
}