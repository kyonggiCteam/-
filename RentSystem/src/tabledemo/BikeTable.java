package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

import rental.User;
import rental.RentSpot;
import rental.RentSystem;
import rental.Vehicle;

public class BikeTable extends JPanel implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table = null;
	JTextField edits[] = new JTextField[5];
	
	User user;
    DefaultTableModel tableModel;
	String[] rowTexts = new String[4];
	RentSpot spot;

	public BikeTable(User user,RentSpot spot) {
		super(new BorderLayout());
		this.user = user;	
		this.spot = spot;
		
		ticketTableInit();
		JPanel pane = makeBottomPane();
		add(pane, BorderLayout.PAGE_END);
	}

	void ticketTableInit() {
		final String[] columnNames = { "��ü��ȣ", "�귣��", "����", "�ɼ�"};
		tableModel = new DefaultTableModel(columnNames, 0);

		for (Vehicle v : spot.vehicleList) {
			if(v.brandName.equals(user.ticket.brandName))
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
		JButton buttons1 = new JButton("�ڷΰ���");
		buttons1.setBorderPainted(false);
		buttons1.setBackground(new Color(153, 231,35));
		buttons1.addActionListener(this);
		bottom.add(buttons1);
		JButton buttons2 = new JButton("�뿩�ϱ�");
		buttons2.setBorderPainted(false);
		buttons2.setBackground(new Color(153, 231,35));
		buttons2.addActionListener(this);
		bottom.add(buttons2);
		
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
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�ڷΰ���")) {
			new RentReturn(user, spot);
			CheckBike.getInstance().dispose();

		} else if (e.getActionCommand().equals("�뿩�ϱ�")) {
	         String VehicleCode = rowTexts[0];
	         Vehicle vehicle = RentSystem.vehicleMgr.find(VehicleCode);
			if (!rowTexts[1].equals(user.ticket.brandName)) {
				JOptionPane.showMessageDialog(null, "�����Ͻ� Ƽ�����δ� �뿩�ϽǼ� �����ϴ�.");
			} 
			else if (vehicle.code.charAt(0) == 'S' && user.license == 0) {
				JOptionPane.showMessageDialog(null, "���㸦 �����ϰ� ���� �ʽ��ϴ�.");
				return;
			}
			else {
			JOptionPane.showMessageDialog(null, "�뿩�� �Ϸ�Ǿ����ϴ�.");

			try {
			BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
			BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
			
			
			ArrayList <String[]> arrays = new ArrayList<String[]>(); 
			String str = null;
			while ((str = bur.readLine())!=null) {
				String[] strarr = str.split(" ");
				if(strarr.length>0) arrays.add(strarr);					
				if (strarr[2].matches(user.id)) {
					strarr[6] = rowTexts[0];
				}
			}

			new FileOutputStream("user.txt").close();
			
			for (int i=0; i < arrays.size(); i++) {
				String[] outputarr = arrays.get(i);
				for (int k=1; k < outputarr.length; k++) {
					outputarr[0] = outputarr[0] + " " + outputarr[k];
				}
				String oneLine = outputarr[0];
				if (i==arrays.size()-1) {
					bos.write(oneLine);
				}
				else {
					bos.write(oneLine + "\n");
				}
			}
			
			bur.close();
			bos.close();
			} catch (Exception ex) {
				ex.getStackTrace();
			}

			RentSystem.rentSpotMgr.rentalVehicle(user, vehicle, spot);
			
			new MainMenu(user);
			CheckBike.getInstance().dispose();
			}
		}
	}
}