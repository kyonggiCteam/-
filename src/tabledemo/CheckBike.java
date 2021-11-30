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

		final String[] columnNames = { "��ü��ȣ", "�귣��", "����", "�ɼ�"};
		// �� ���� ����
		tableModel = new DefaultTableModel(columnNames, 0);
		
		// �������� �ⱸ�鸸 �������� ����
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
		String btnTexts[] = { "���ư���", "�뿩�ϱ�" };
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
		if (e.getActionCommand().equals("���ư���")) {
			new RentReturn(user, spot);
			frame.setVisible(false);

		} else if (e.getActionCommand().equals("�뿩�ϱ�")) {
			// �귣��� �뿩�Ϸ��� �����Ű� ��ġ�ϴ��� ����
			if (!rowTexts[1].equals(user.ticket.brandName)) {
				JOptionPane.showMessageDialog(null, "�����Ͻ� Ƽ�����δ� �뿩�ϽǼ� �����ϴ�.");
			}
			else {
			JOptionPane.showMessageDialog(null, "�뿩�� �Ϸ�Ǿ����ϴ�.");
			//�뿩
			String VehicleCode = rowTexts[0];
			user.vehicle = RentSystem.vehicleMgr.find(VehicleCode);
			new Table(user);
			frame.setVisible(false);
			}
		}
	}
	
	//����Ȯ�ο�
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