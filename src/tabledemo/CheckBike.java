package tabledemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import rental.User;
import rental.Brand;
import rental.RentSpot;
import rental.RentSpotManager;
import rental.RentSystem;
import rental.Ticket;
import rental.Vehicle;
import rental.VehicleManager;

public class CheckBike extends JPanel implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table = null;
	JTextField edits[] = new JTextField[5];
	
	User user;
    DefaultTableModel tableModel;
    Frame frame;
	String[] rowTexts = new String[4];
	RentSpot spot;

//	VehicleManager vehicleMgr = new VehicleManager();

	public CheckBike(User user, Frame frame,RentSpot spot) {
		super(new BorderLayout());
		this.user = user;
		this.frame = frame;		
		this.spot = spot;
		
		ticketTableInit();
		JPanel pane = makeBottomPane();
		add(pane, BorderLayout.PAGE_END);
	}

	void ticketTableInit() {
//		vehicleMgr.readAll("vehicle.txt", new Factory<Vehicle>() {
//			public Vehicle create() {
//				return new Vehicle();
//			}
//		});

		final String[] columnNames = { "개체번호", "브랜드", "종류", "옵션"};
		// 셀 수정 막기
		tableModel = new DefaultTableModel(columnNames, 0);
		
		// 정류소의 기구들만 나오도록 수정
		for (Vehicle v : spot.vehicleList) {
			tableModel.addRow(v.getTexts());
		}

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

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton buttons[] = new JButton[2];
		String btnTexts[] = { "돌아가기", "대여하기" };
		for (int i = 0; i < 2; i++) {
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
			for (int i = 0; i < rowTexts.length; i++)
				rowTexts[i] = (String)tableModel.getValueAt(selectedIndex, i);
			//moveSelectedToEdits();
		}
	}

	private void moveSelectedToEdits() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
//		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		if (e.getActionCommand().equals("돌아가기")) {
			new RentReturn(user, spot);
			frame.setVisible(false);

		} else if (e.getActionCommand().equals("대여하기")) {
			// 브랜드와 대여하려는 자전거가 일치하는지 여부
			if (!rowTexts[1].equals(user.ticket.brandName)) {
				JOptionPane.showMessageDialog(null, "보유하신 티켓으로는 대여하실수 없습니다.");
			}
			else {
			JOptionPane.showMessageDialog(null, "대여가 완료되었습니다.");
			//대여
			String VehicleCode = rowTexts[0];
			user.vehicle = RentSystem.vehicleMgr.find(VehicleCode);
			new Table(user);
			frame.setVisible(false);
			}
		}
	}
	
	//실행확인용
//	public static void main(String args[]) {
//		JFrame frame = new JFrame("TableSelectionDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        CheckBike newContentPane = new CheckBike();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//	}
}