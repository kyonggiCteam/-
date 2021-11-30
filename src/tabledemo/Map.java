package tabledemo;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import rental.RentSystem;
import rental.User;
//import tabledemo.Map1.MyActionListener;

public class Map extends JFrame {
	Map1 map1 = null;
	Map2 map2 = null;
	Map3 map3 = null;
	Map4 map4 = null;
	MapOftenSpot mapoftenspot= null;
	
	
	Map(User user){
		setTitle("瘤档拳搁");
		//setLocationRelativeTo(null);
		// 창 이치가 이상..
		
		map1 = new Map1(user, this);
		map2 = new Map2(user, this);
		map3 = new Map3(user, this);
		map4 = new Map4(user, this);
		mapoftenspot = new MapOftenSpot(user, this);
		
		JTabbedPane jtab = new JTabbedPane();
		
		
		
		jtab.addTab("厘救备", map1);
		jtab.addTab("康烹备", map2);
		jtab.addTab("迫崔备", map3);
		jtab.addTab("鼻急备", map4);
		jtab.addTab("Often Spot", mapoftenspot);
		
		add(jtab);
		
		setSize(800, 600);
		setVisible(true);
	}
	/*
	 * public static void main(String[] args) { Map map = new Map();
	 * map.setTitle("瘤档拳搁"); map.map1 = new Map1(); map.map2 = new Map2(); map.map3
	 * = new Map3(); map.map4 = new Map4();
	 * 
	 * JTabbedPane jtab = new JTabbedPane();
	 * 
	 * jtab.addTab("厘救备", map.map1); jtab.addTab("康烹备", map.map2);
	 * jtab.addTab("迫崔备", map.map3); jtab.addTab("鼻急备", map.map4);
	 * 
	 * map.add(jtab);
	 * 
	 * map.setSize(800, 600); map.setVisible(true); }
	 *
	 */
	

	
}