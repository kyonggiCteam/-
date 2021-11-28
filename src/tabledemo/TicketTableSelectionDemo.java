package tabledemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

//    Payment payMgr = new Payment();

	public TicketTableSelectionDemo(int sortOpt) {
		super(new BorderLayout());
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

		final String[] columnNames = { "브랜드", "장비", "종류", "가격", "유효기간" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		switch (sortOpt) {
		case 1:
			RentSystem.ticketMgr.sortByPrice();
			break;
		case 2:
			RentSystem.ticketMgr.sortByPeriod();
			break;
		case 3:
			RentSystem.ticketMgr.sortByBrand();
			break;
		}

		for (Ticket t : RentSystem.ticketMgr.mList)
			tableModel.addRow(t.getTexts());

		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	JPanel makeBottomPane() {
		JPanel pane = new JPanel();
		/*
		 * pane.setLayout(new GridLayout(2, 1));
		 * 
		 * JPanel center = new JPanel(); center.setLayout(new FlowLayout()); for (int i
		 * = 0; i < 5; i++) { edits[i] = new JTextField("", 10); center.add(edits[i]); }
		 * JButton editBtn = new JButton("저장"); editBtn.setActionCommand("Done");
		 * editBtn.addActionListener(this); center.add(editBtn); pane.add(center);
		 */

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton buttons[] = new JButton[3];
		String btnTexts[] = { "돌아가기", "티켓보유중", "티켓결제" };
		for (int i = 0; i < 3; i++) {
			buttons[i] = new JButton(btnTexts[i]);
			buttons[i].addActionListener(this);
			bottom.add(buttons[i]);
		}
		pane.add(bottom);
		return pane;
	}

	int selectedIndex = -1;

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			moveSelectedToEdits();
		}
	}

	private void moveSelectedToEdits() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e, User user) {
		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		if (e.getActionCommand().equals("돌아가기")) {

		} else if (e.getActionCommand().equals("티켓보유중")) {
			if (user.ticket != null) {
				JOptionPane.showMessageDialog(null, "현재 보유 중인 티켓은 " + user.ticket + " 입니다."); // +뒤에 보유중인 티켓 뜨도록
				new GUI_Payment(); // user정보와 ticket넘겨줘야할듯
				setVisible(false);
			} else
				JOptionPane.showMessageDialog(null, "보유중인 티켓이 없습니다.");

		} else if (e.getActionCommand().equals("티켓결제")) {
			new GUI_Payment();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		if (e.getActionCommand().equals("돌아가기")) {

		} else if (e.getActionCommand().equals("티켓보유중")) {
			JOptionPane.showMessageDialog(null, "보유중인 티켓이 없습니다.");

		} else if (e.getActionCommand().equals("티켓결제")) {
			new GUI_Payment();
		}
	}
}