package tabledemo;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import rental.User;

public class Map extends JFrame {
	Map1 map1 = null;
	Map2 map2 = null;
	Map3 map3 = null;
	Map4 map4 = null;
	
	Map(User user){
		setTitle("����ȭ��");
		map1 = new Map1(this, user);
		map2 = new Map2(this, user);
		map3 = new Map3(this, user);
		map4 = new Map4(this, user);
		
		JTabbedPane jtab = new JTabbedPane();
		
		jtab.addTab("��ȱ�", map1);
		jtab.addTab("���뱸", map2);
		jtab.addTab("�ȴޱ�", map3);
		jtab.addTab("�Ǽ���", map4);
		
		add(jtab);
		
		setSize(800, 600);
		setVisible(true);
	}
	/*
	 * public static void main(String[] args) { Map map = new Map();
	 * map.setTitle("����ȭ��"); map.map1 = new Map1(); map.map2 = new Map2(); map.map3
	 * = new Map3(); map.map4 = new Map4();
	 * 
	 * JTabbedPane jtab = new JTabbedPane();
	 * 
	 * jtab.addTab("��ȱ�", map.map1); jtab.addTab("���뱸", map.map2);
	 * jtab.addTab("�ȴޱ�", map.map3); jtab.addTab("�Ǽ���", map.map4);
	 * 
	 * map.add(jtab);
	 * 
	 * map.setSize(800, 600); map.setVisible(true); }
	 */
}
