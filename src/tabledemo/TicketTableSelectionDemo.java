package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mgr.Factory;
import rental.TicketManager;
import rental.Brand;
import rental.RentSpot;
import rental.RentSpotManager;
import rental.RentSystem;
import rental.Ticket;
import rental.User;
import rental.Vehicle;
import rental.VehicleManager;

public class TicketTableSelectionDemo extends JPanel implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table = null;
	JTextField edits[] = new JTextField[5];
	
    DefaultTableModel tableModel;
    User user;
    Rent rent;
	String[] rowTexts = new String[6];
	RentSpot spot;
//    Payment payMgr = new Payment();

	public TicketTableSelectionDemo(int sortOpt, User user, RentSpot spot) { //���� ���, ���� ����, ������ ����
		super(new BorderLayout());
		this.user = user;
		this.spot = spot;
		ticketTableInit(sortOpt);
		JPanel pane = makeBottomPane();
		add(pane, BorderLayout.PAGE_END);
	}

	void ticketTableInit(int sortOpt) {
//       payMgr.readAll("ticket.txt", new Factory<Ticket>(){
//         public Ticket create() {
//            return new Ticket();
//         }
//      });
		
		final String[] columnNames = { "�귣��", "���", "����", "����", "��ȿ�Ⱓ", "�ڵ�"};
		tableModel = new DefaultTableModel(columnNames, 0){ // �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		/*
		// �̷��� �ϸ� �ȵǰ�, RentSpot�� ������ �ִ� �������� Ticket �鸸 ����ؾ���.
		
		switch (sortOpt) {
		case 1:
			RentSystem.ticketMgr.sortByPrice(RentSystem.ticketMgr.mList);
			break;
		case 2:
			RentSystem.ticketMgr.sortByPeriod(RentSystem.ticketMgr.mList);
			break;
		case 3:
			RentSystem.ticketMgr.sortByBrand(RentSystem.ticketMgr.mList);
			break;
		}
		for (Ticket t : RentSystem.ticketMgr.mList)
			tableModel.addRow(t.getTexts());
		
		*/
		
		// ������ ���

		ArrayList <Ticket> searchList = new ArrayList<>(); // �������� ������ �귣����� Ƽ�ϵ� ����Ʈ
		ArrayList <Brand> brandList = new ArrayList<>();
		
		for (String str: spot.brandNameList) {
			brandList.add(RentSystem.brandMgr.find(str));
		}
		
		for (Brand b: brandList) {
			for (int i=0;i<b.ticketList.size();i++) {
				searchList.add(b.ticketList.get(i));
			}
		}
		
		switch (sortOpt) {
		case 1:
			RentSystem.ticketMgr.sortByPrice(searchList);
			break;
		case 2:
			RentSystem.ticketMgr.sortByPeriod(searchList);
			break;
		case 3:
			RentSystem.ticketMgr.sortByBrand(searchList);
			break;
		}
		
		for (Ticket t: searchList) {
			tableModel.addRow(t.getTexts());
		}
		
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	JPanel makeBottomPane() {
		JPanel pane = new JPanel();

		JPanel bottom = new JPanel();
		
		bottom.setLayout(new FlowLayout());
		JButton buttons1 = new JButton("���ư���");
		buttons1.setBorderPainted(false);
		buttons1.setBackground(new Color(153, 231,35));
		buttons1.addActionListener(this);
		bottom.add(buttons1);
		JButton buttons2 = new JButton("Ƽ�Ϻ�����");
		buttons2.setBorderPainted(false);
		buttons2.setBackground(new Color(153, 231,35));
		buttons2.addActionListener(this);
		bottom.add(buttons2);
		JButton buttons3 = new JButton("Ƽ�ϰ���");
		buttons3.setBorderPainted(false);
		buttons3.setBackground(new Color(153, 231,35));
		buttons3.addActionListener(this);
		bottom.add(buttons3);
		
		pane.add(bottom);
		return pane;
	}

	int selectedIndex = -1;

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			for (int i = 0; i < rowTexts.length; i++)
				rowTexts[i] = (String)tableModel.getValueAt(selectedIndex, i);
			
			//moveSelectedToEdits();
		}
//        if (!lsm.isSelectionEmpty()) {
//        	selectedIndex = lsm.getMinSelectionIndex();
//        	//String[] rowTexts = new String[tableModel.getColumnCount()];
//        	for (int i = 0; i < rowTexts.length; i++)
//        		rowTexts[i] = (String)tableModel.getValueAt(selectedIndex, i);
////        	TableSelectionDemo.bottom.moveSelectedToEdits(rowTexts);
//        	moveSelectedToEdits();
//        }
	}

	private void moveSelectedToEdits() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
//		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		// Ƽ�� ã���ֱ�
		Ticket ticket = RentSystem.ticketMgr.find(rowTexts[5]);
    	
		if (e.getActionCommand().equals("���ư���")) {
			new RentReturn(user,spot);
			Rent.getInstance().dispose();

		} else if (e.getActionCommand().equals("Ƽ�Ϻ�����")) {
			if (user.ticket != null) {
				JOptionPane.showMessageDialog(null, "���� ���� ���� Ƽ���� " + user.ticket.code + " �Դϴ�."); // +�ڿ� �������� Ƽ�� �ߵ���
				// GUI_Payment â�� �ƴ϶� CheckBike â�� �ҷ��� ��. Ƽ���� �����Ƿ� ���� �ʿ� ����
				new CheckBike(user, spot);
				Rent.getInstance().dispose();
			} else {
				JOptionPane.showMessageDialog(null, "�������� Ƽ���� �����ϴ�.");
			}

		} else if (e.getActionCommand().equals("Ƽ�ϰ���")) {
			//Ŭ���ߴ��� Ȯ��.
			if(rowTexts[0] == null) {
				JOptionPane.showMessageDialog(null, "Ƽ���� �������ּ���.");
			}			
			else if (user.ticket != null) { // Ƽ�� ���� ������ Ȯ��.
				JOptionPane.showMessageDialog(null, "Ƽ���� ������ �ֽ��ϴ�.");
			}
			else {
				new GUI_Payment(user, ticket, spot);  // user ���� / Ŭ���� ���� / ������ ����
				Rent.getInstance().dispose();
			}
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		DefaultTableModel data = (DefaultTableModel) (table.getModel());
//		if (e.getActionCommand().equals("���ư���")) {
//
//		} else if (e.getActionCommand().equals("Ƽ�Ϻ�����")) {
//			JOptionPane.showMessageDialog(null, "�������� Ƽ���� �����ϴ�.");
//
//		} else if (e.getActionCommand().equals("Ƽ�ϰ���")) {
//			new GUI_Payment();
//		}
//	}
}