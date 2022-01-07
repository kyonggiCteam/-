package tabledemo;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import rental.User;

public class Map extends JFrame {
	private static Map map = null;
	private Map() {}
	public static Map getInstance() {
		if (map == null)
			map = new Map();
		return map;
	}
	Map1 map1 = null;
	Map2 map2 = null;
	Map3 map3 = null;
	Map4 map4 = null;
	MapOftenSpot mapoftenspot= null;
	
	Map(User user){
		map = this;
		setTitle("수원지도");
		setLocation(200,50);
		
		map1 = new Map1(user);
		map2 = new Map2(user);
		map3 = new Map3(user);
		map4 = new Map4(user);
		mapoftenspot = new MapOftenSpot(user, this);
		
		JTabbedPane jtab = new JTabbedPane();
		jtab.addTab("영통구", map1);
		jtab.addTab("장안구", map2);
		jtab.addTab("팔달구", map3);
		jtab.addTab("권선구", map4);
		jtab.addTab("즐겨찾기", mapoftenspot);
		
		add(jtab);
		
		setSize(800, 600);
		setVisible(true);
	}
}