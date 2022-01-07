package tabledemo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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

import rental.RentSpot;
import rental.RentSystem;
import rental.Ticket;
import rental.User;

public class GUI_Payment extends JFrame {
	Ticket ticket;
	RentSpot spot;

	GUI_Payment(User user, Ticket ticket ,RentSpot spot) {
		this.spot=spot;
		this.ticket = ticket;
		
		setTitle("����ȭ��");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(240, 248, 239));

		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("����");
		JButton btn_bck = new JButton("���");
		
		btn_pay.setBorderPainted(false);
		btn_pay.setBackground(new Color(153, 231,35));
		btn_bck.setBorderPainted(false);
	    btn_bck.setBackground(new Color(153, 231,35));

		pointText.setFont(new Font("�������", Font.PLAIN, 20));
		payInfo.setFont(new Font("�������", Font.BOLD, 15));
		payPoint.setFont(new Font("�������", Font.BOLD, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(240, 248, 239));
		payPoint.setBackground(new Color(240, 248, 239));
		
		String str1 = "[������ Ƽ��]   " + ticket.code ;
		String str2 = "[ ��  �� ]    " + ticket.price + " ��";
		String str3 = "[���� ����Ʈ]   " + user.point + " point";

		payPoint.setText("[����Ʈ ���]");
		payInfo.setText("<html>" + str1 + "<br>" + str2 + "<p>" + str3 + "</html>");
		
		payInfo.setBounds(30, 30, 200, 150);
		payPoint.setBounds(35, 200, 100, 48);
		pointText.setBounds(180, 210, 70, 30);
		btn_pay.setBounds(50, 300, 96, 33);
		btn_bck.setBounds(150, 300, 96, 33);

		c.add(payInfo);
		c.add(payPoint);
		c.add(pointText);
		c.add(btn_pay);
		c.add(btn_bck);
		
		setVisible(true);

		btn_pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (user.point < Integer.parseInt(pointText.getText())) {
					JOptionPane.showMessageDialog(null, "���� ����Ʈ�� �ʰ��ϼ̽��ϴ�.");
					return;
				}
				
				RentSystem.ticketMgr.buyTicket(ticket, user);
				if(user.ticket.ticketType.equals("�����"))
					RentSystem.userMgr.updateDate(user);
				// ����� ���� ������ ���Ͽ� ����.
				try {
					BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
					BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));

					ArrayList<String[]> arrays = new ArrayList<String[]>();
					String str = null;
					while ((str = bur.readLine()) != null) {
						String[] strarr = str.split(" ");
						if (strarr.length > 0)
							arrays.add(strarr);
						if (strarr[2].matches(user.id)) { // id�� ��ġ�ϸ�
							strarr[8] = "" + user.startyear;
							strarr[9] = "" + user.startmonth;
							strarr[10] = "" + user.startdate;
						}
					}

					new FileOutputStream("user.txt").close();

					for (int i = 0; i < arrays.size(); i++) {
						String[] outputarr = arrays.get(i);
						for (int k = 1; k < outputarr.length; k++) {
							outputarr[0] = outputarr[0] + " " + outputarr[k];
						}
						String oneLine = outputarr[0];
						if (i == arrays.size() - 1) {
							bos.write(oneLine);
						} else {
							bos.write(oneLine + "\n");
						}
					}

					bur.close();
					bos.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
				

				int usedpoint = Integer.parseInt(pointText.getText()); // �⺻������ 0�� ����.
				try {
					BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
					BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
					
					ArrayList <String[]> arrays = new ArrayList<String[]>(); 
					String str = null;
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);					
						if (strarr[2].matches(user.id)) {
							strarr[5] = ticket.code; // Ƽ�� �Է�
							strarr[7] = Integer.toString(user.point - usedpoint); // ����Ʈ �Է�
							RentSystem.userMgr.usePoint(user, usedpoint);
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
		
				if (usedpoint == 0) { // ����Ʈ ��� �� ��.
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
				} else { // ����Ʈ ���.
					JOptionPane.showMessageDialog(null, (ticket.price - usedpoint) + "�� �����Ǿ����ϴ�.");
				}		
			
			new CheckBike(user, spot);
	        dispose();
			}
		});

		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Rent(user,spot);
				dispose();
			}
		});
	}
}