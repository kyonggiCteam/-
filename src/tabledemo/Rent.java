package tabledemo;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Rent extends JFrame {
	JTabbedPane tab;
	JTable table01, table02, table03;
	JScrollPane sp01, sp02, sp03;
	int selectedIndex = -1;
//    DataEngineInterface dataMgr;
	Rent() {
		setTitle("�뿩ȭ��");

		tab = new JTabbedPane(JTabbedPane.TOP);

		table01 = new JTable();
		sp01 = new JScrollPane(table01);
		table02 = new JTable();
		sp02 = new JScrollPane(table02);
		table03 = new JTable();
		sp03 = new JScrollPane(table03);

		tab.addTab("���ݼ� ����", sp01);
		tab.addTab("�Ⱓ�� ����", sp02);
		tab.addTab("�귣�庰 ����", sp03);

		add(tab);
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
