package tabledemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSystem;
import rental.User;
import rental.Vehicle;
import rental.VehicleManager;

public class MainMenu extends JFrame{

	Vehicle vehicle;
	Image img_b, img_k;
	User user;

	public MainMenu(User user) {
		
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
		
		top.setBackground(new Color(240, 248, 239));
		middle.setBackground(new Color(240, 248, 239));
		bottom.setBackground(new Color(240, 248, 239));
		
		btn_rnt.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_rnt.setBackground(new Color(153, 231,35));
		btn_rpt.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_rpt.setBackground(new Color(153, 231,35));
		btn_mpg.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_mpg.setBackground(new Color(153, 231,35));
		btn_lgt.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_lgt.setBackground(new Color(153, 231,35));
		
		btn_rnt.setFont(new Font("�������", Font.BOLD, 11));
		
		add(btn_rnt);
		add(btn_rpt);
		add(btn_mpg);
		add(btn_lgt);
		add(currentinfo);
		setSize(500, 400);
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
		btn_rnt.setBounds(31, 40, 100, 50);
		btn_rpt.setBounds(193, 40, 100, 50);
		btn_mpg.setBounds(355, 40, 100, 50);
		btn_lgt.setBounds(10,315,100,33);
		currentinfo.setBounds(40,120,400,170);
		
		//���� �α��ο��� ���Ƿ� ������ vehicle�� ���������Ƿ� �̺κ� ����
		String a = user.name + "�� �ȳ��ϼ���";
		String b = null;
		String c = null;
		if (user.vehicle != null) {
			b = "���� �뿩���� �ⱸ�� �ڵ�� " + user.vehicle.code + " �Դϴ�.";
		}
		else
			b = "";
		if(user.ticket != null && user.ticket.ticketType.equals("�����"))
			c = "����� " + user.ticket.code + "�� " + user.leftday + "�� ���ҽ��ϴ�.";
		else
			c = "";
			
		
//		currentinfo.setText("<html>"+a+"<br>"+b+"</html>"); // �ؽ�Ʈ �ٹٲ�
		currentinfo.setText("<html>"+a+"<br>"+b+"<br>"+c+"</html>");
		currentinfo.setHorizontalAlignment(JLabel.CENTER); // �ؽ�Ʈ ��� ����
		
		
		// * �� �ٲ� �ʿ� ���� ��....
		currentinfo.setOpaque(true);
		currentinfo.setBackground(new Color(255,255,255));
		
		// �뿩 ��ư �̺�Ʈ ������ �뿩 â���� �̵�
		btn_rnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Map rent = new Map(user);  
				dispose(); // â �Ⱥ��̰� �ϱ� 
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
					if (vehicle == null) 
						JOptionPane.showMessageDialog(null, "�������� �ʴ� �ڵ��ȣ�Դϴ�.");
					else { // ����� ���� �Էµ� ��,
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
				dispose(); // â �Ⱥ��̰� �ϱ� 
			}
		});
		// �α׾ƿ�
		btn_lgt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//�α׾ƿ��Ͽ� ȸ������â���� �̵�
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin();
				dispose(); // â �Ⱥ��̰� �ϱ� 
			}
		});
		
	}
	
	//�ȉ�
	public void paintComponent(Graphics g) {
		if(user.vehicle.code.charAt(0) == 'B')
			img_b = new ImageIcon("������.png").getImage();
		else if(user.vehicle.code.charAt(0) == 'K')
			img_k = new ImageIcon("ű����.png").getImage();
	}
}