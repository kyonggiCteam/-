package tabledemo;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import rental.RentSpot;
import rental.RentSystem;
import rental.User;

public class Rent extends JFrame {
   JTabbedPane tab;
//   JTable table01, table02, table03;
//   JScrollPane sp01, sp02, sp03;
   int selectedIndex = -1;
//    DataEngineInterface dataMgr;
	User user;
//	private static Rent rent = null;
//	private Rent() {}
//	public static Rent getInstance() {
//		if (rent == null)
//			rent = new Rent();
//		return rent;
//	}
//   
   Rent(User user, RentSpot spot) {
	   this.user = user;
      setTitle("대여화면");

      tab = new JTabbedPane(JTabbedPane.TOP);

//      table01 = new JTable();
//      sp01 = new JScrollPane(table01);
//      table02 = new JTable();
//      sp02 = new JScrollPane(table02);
//      table03 = new JTable();
//      sp03 = new JScrollPane(table03);

      tab.addTab("가격순 정렬", new TicketTableSelectionDemo(1, user, this, spot));
      tab.addTab("기간별 정렬", new TicketTableSelectionDemo(2, user, this, spot));
      tab.addTab("브랜드별 정렬", new TicketTableSelectionDemo(3, user, this, spot));

      add(tab);
      setSize(600, 500);
      setVisible(true);
      //rent.setVisible(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
}