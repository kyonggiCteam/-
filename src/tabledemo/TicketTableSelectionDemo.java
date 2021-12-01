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

	public TicketTableSelectionDemo(int sortOpt, User user, RentSpot spot) { //정렬 방법, 유저 정보, 정류장 정보
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
		
		final String[] columnNames = { "브랜드", "장비", "종류", "가격", "유효기간", "코드"};
		tableModel = new DefaultTableModel(columnNames, 0){ // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		/*
		// 이렇게 하면 안되고, RentSpot이 가지고 있는 정류소의 Ticket 들만 출력해야함.
		
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
		
		// 수정한 방법

		ArrayList <Ticket> searchList = new ArrayList<>(); // 정류장이 가지는 브랜드들의 티켓들 리스트
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
		JButton buttons1 = new JButton("돌아가기");
		buttons1.setBorderPainted(false);
		buttons1.setBackground(new Color(153, 231,35));
		buttons1.addActionListener(this);
		bottom.add(buttons1);
		JButton buttons2 = new JButton("티켓보유중");
		buttons2.setBorderPainted(false);
		buttons2.setBackground(new Color(153, 231,35));
		buttons2.addActionListener(this);
		bottom.add(buttons2);
		JButton buttons3 = new JButton("티켓결제");
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
		// 티켓 찾아주기
		Ticket ticket = RentSystem.ticketMgr.find(rowTexts[5]);
    	
		if (e.getActionCommand().equals("돌아가기")) {
			new RentReturn(user,spot);
			Rent.getInstance().dispose();

		} else if (e.getActionCommand().equals("티켓보유중")) {
			if (user.ticket != null) {
				JOptionPane.showMessageDialog(null, "현재 보유 중인 티켓은 " + user.ticket.code + " 입니다."); // +뒤에 보유중인 티켓 뜨도록
				// GUI_Payment 창이 아니라 CheckBike 창을 불러야 함. 티켓이 있으므로 결재 필요 없음
				new CheckBike(user, spot);
				Rent.getInstance().dispose();
			} else {
				JOptionPane.showMessageDialog(null, "보유중인 티켓이 없습니다.");
			}

		} else if (e.getActionCommand().equals("티켓결제")) {
			//클릭했는지 확인.
			if(rowTexts[0] == null) {
				JOptionPane.showMessageDialog(null, "티켓을 선택해주세요.");
			}			
			else if (user.ticket != null) { // 티켓 보유 중인지 확인.
				JOptionPane.showMessageDialog(null, "티켓을 가지고 있습니다.");
			}
			else {
				new GUI_Payment(user, ticket, spot);  // user 내용 / 클릭한 내용 / 정류소 내용
				Rent.getInstance().dispose();
			}
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		DefaultTableModel data = (DefaultTableModel) (table.getModel());
//		if (e.getActionCommand().equals("돌아가기")) {
//
//		} else if (e.getActionCommand().equals("티켓보유중")) {
//			JOptionPane.showMessageDialog(null, "보유중인 티켓이 없습니다.");
//
//		} else if (e.getActionCommand().equals("티켓결제")) {
//			new GUI_Payment();
//		}
//	}
}