package tabledemo;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import rental.RentSpot;
import rental.User;

public class Rent extends JFrame {
   JTabbedPane tab;

   int selectedIndex = -1;

	private static Rent rent = null;
	private Rent() {}
	public static Rent getInstance() {
		if (rent == null)
			rent = new Rent();
		return rent;
	}

	Rent(User user, RentSpot spot) {
		rent = this;
		setTitle("�뿩ȭ��");
		
	    setLocation(300,200);
		
		tab = new JTabbedPane(JTabbedPane.TOP);
		tab.addTab("���ݼ� ����", new TicketTableSelectionDemo(1, user, spot));
		tab.addTab("�Ⱓ�� ����", new TicketTableSelectionDemo(2, user, spot));
		tab.addTab("�귣�庰 ����", new TicketTableSelectionDemo(3, user, spot));

		add(tab);
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
}