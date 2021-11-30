package tabledemo;

import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSystem;
import rental.User;
import rental.Vehicle;
import rental.VehicleManager;

public class Table extends JFrame{

	Vehicle vehicle;

	public Table(User user) {
		
		
		
		JPanel top = new JPanel();
	    JPanel middle = new JPanel();
	    JPanel bottom = new JPanel();
	    JButton btn_rnt = new JButton("�뿩 �� �ݳ�");
		JButton btn_rpt = new JButton("����Ű�");
		JButton btn_mpg = new JButton("����������");
		JButton btn_lgt = new JButton("�α׾ƿ�");
		JLabel currentinfo = new JLabel();
	    
		/*
		 * JPanel p = new JPanel(); p.setLayout(null);
		 */
		top.setLayout(null);
		middle.setLayout(null);
		bottom.setLayout(null);
		
		add(btn_rnt);
		add(btn_rpt);
		add(btn_mpg);
		add(btn_lgt);
		add(currentinfo);
		setSize(500, 500);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(top);
		add(middle);
		add(bottom);
		
		setTitle("System Main Display");
		setVisible(true);
		
		//setBounds(������ġ, ������ġ, ���α���, ���α���);
		btn_rnt.setBounds(31, 150, 100, 33);
		btn_rpt.setBounds(193, 150, 100, 33);
		btn_mpg.setBounds(355, 150, 100, 33);
		btn_lgt.setBounds(10,400,100,33);
		currentinfo.setBounds(40,200,400,170);
		
		//���� �α��ο��� ���Ƿ� ������ vehicle�� ���������Ƿ� �̺κ� ����
		String a = user.name + "�� �ȳ��ϼ���";
		String b = null;
		if (user.vehicle != null) {
			b = "���� �뿩���� �ⱸ�� �ڵ�� " + user.vehicle.code + " �Դϴ�.";
		}
		else
			b = "";
		
		currentinfo.setText("<html>"+a+"<br>"+b+"</html>"); // �ؽ�Ʈ �ٹٲ�
		currentinfo.setHorizontalAlignment(JLabel.CENTER); // �ؽ�Ʈ ��� ����
		
		currentinfo.setOpaque(true);
		currentinfo.setBackground(new Color(0, 250, 0));
		
		// �뿩 ��ư �̺�Ʈ ������ �뿩 â���� �̵�
		btn_rnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Map rent = new Map(user);  
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});
		// ����Ű�
		btn_rpt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String report = JOptionPane.showInputDialog(null,"���峭 ��ǰ�� �ڵ带 �Է����ּ���.","��ǰ�ڵ�"); // �˾�â
				
				vehicle = RentSystem.vehicleMgr.find(report);
				
				int msgflag = 0; // �׳� �޽��� ���� �ΰ��� ���ÿ� ������ �÷��׷� �б��� ��������ϴ�.
				
				if (report == null) 
					JOptionPane.showMessageDialog(null, "�Ű� ��ҵǾ����ϴ�.");
				else {
					if (vehicle==null) 
						JOptionPane.showMessageDialog(null, "�������� �ʴ� �ڵ��ȣ�Դϴ�.");
					else {
						try {
							
							// null ���ϸ� �α׾ƿ��� ���ϰ� ���� ��ǰ ��Ű�� �Ű� �����ߴٰ� ���ɴϴ�.
							BufferedReader vehicleReader = null;
							BufferedWriter vehicleWriter = null;
							
							vehicleReader = new BufferedReader(new FileReader("vehicle.txt"));
							vehicleWriter = new BufferedWriter(new FileWriter("vehicle.txt", true));
							
							ArrayList <String[]> arrays = new ArrayList<String[]>(); 
							String str = null;
							
							while ((str = vehicleReader.readLine())!=null) {
								String[] strarr = str.split(" ");
								if(strarr.length>0) arrays.add(strarr);
								if (strarr[0].matches(report)) {
									if (strarr[2].equals("-1")) {
										msgflag = 1;
									}
									strarr[2] = "-1";
								}
							}
							new FileOutputStream("vehicle.txt").close();
							
							for (int i=0; i < arrays.size(); i++) {
								String[] outputarr = arrays.get(i);
								for (int k=1; k < outputarr.length; k++) {
									outputarr[0] = outputarr[0] + " " + outputarr[k];
								}
								String oneLine = outputarr[0];
								if (i==arrays.size()-1) {
									vehicleWriter.write(oneLine);
								}
								else {
									vehicleWriter.write(oneLine + "\n");
								}
							}
							vehicleReader.close();
							vehicleWriter.close();
						} catch (Exception ex) {
						}
						
						if (msgflag==0)
							JOptionPane.showMessageDialog(null, "�Ű� �����Ǿ����ϴ�.");	
						else 
							JOptionPane.showMessageDialog(null, "�̹� �Ű� ������ �������Դϴ�.");
					}
				}
			}
		});
		// ����������
		btn_mpg.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { //������������ �̵�
				// TODO Auto-generated method stub
				MyPage m = new MyPage(user);
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});
		// �α׾ƿ�
		btn_lgt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//�α׾ƿ��Ͽ� ȸ������â���� �̵�
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin();
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});
		
	}
	
	// yr��
//	public Table() {
//		JPanel top = new JPanel();
//	    JPanel middle = new JPanel();
//	    JPanel bottom = new JPanel();
//	    JButton j1 = new JButton("�뿩");
//		JButton j2 = new JButton("����Ű�");
//		JButton j3 = new JButton("����");
//	    
//		/*
//		 * JPanel p = new JPanel(); p.setLayout(null);
//		 */
//		top.setLayout(null);
//		middle.setLayout(null);
//		bottom.setLayout(null);
//		
//		add(j1);
//		add(j2);
//		add(j3);
//		setSize(500, 500);
//		// �������� ȭ�� ����� ��ġ
//		setLocationRelativeTo(null);
//		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		add(top);
//		add(middle);
//		add(bottom);
//		
//		setTitle("�뿩");
//		setVisible(true);
//		
//		//setBounds(������ġ, ������ġ, ���α���, ���α���);
//		j1.setBounds(31, 150, 100, 33);
//		j2.setBounds(193, 150, 100, 33);
//		j3.setBounds(355, 150, 100, 33);
//		
//	}
}