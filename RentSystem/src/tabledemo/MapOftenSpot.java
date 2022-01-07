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

public class MapOftenSpot extends JPanel implements ActionListener{
	JButton j1 = new JButton();
	JButton j2 = new JButton();
	JButton j3 = new JButton();

	JButton back = new JButton("뒤로가기");
	User user;
	RentSpot spot;
	Map map;
	
	Image img = new ImageIcon("즐겨찾기.png").getImage();
	
	MapOftenSpot(User user, Map m) {
		this.user = user;
		map = m;
		setLayout(null);
		setSize(700, 500);
		setVisible(false);
		
		j1.setBorderPainted(false);
		j1.setBackground(new Color(41, 175,76));
		j2.setBorderPainted(false);
		j2.setBackground(new Color(41, 175,76));
		j3.setBorderPainted(false);
		j3.setBackground(new Color(41, 175,76));
		back.setBorderPainted(false);
		back.setBackground(new Color(153, 231,35));
		
		back.setBounds(10, 505, 100, 25);
		add(back);
		
		int n = this.user.oftenSpotList.size();
		
		if (n>0) {
			String spot1 = this.user.oftenSpotList.get(0); 
			j1.setBounds(610, 10, 165, 30);
			add(j1);
			j1.setText(spot1);
			if (n>1) {
				String spot2 = this.user.oftenSpotList.get(1); 
				j2.setBounds(610, 45, 165, 30);
				add(j2);
				j2.setText(spot2);
				if(n>2) {
					String spot3 = this.user.oftenSpotList.get(2); 
					j3.setBounds(610, 80, 165, 30);
					add(j3);
					j3.setText(spot3);
				}
					
			}
				
		}
		
		j1.addActionListener(this);	
		j2.addActionListener(this);
		j3.addActionListener(this);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainMenu(user);
				m.setVisible(false);
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String spotname = e.getActionCommand();
		spot = RentSystem.rentSpotMgr.find(spotname);
		new RentReturn(user,spot);
		map.setVisible(false);
	}	
}