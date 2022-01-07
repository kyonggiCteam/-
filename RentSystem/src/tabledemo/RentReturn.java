package tabledemo;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JOptionPane;

import rental.RentSpot;
import rental.RentSystem;
import rental.Ticket;
import rental.User;

public class RentReturn extends JFrame {

	RentReturn(User user, RentSpot spot) {
		setTitle("�뿩/�ݳ�");
		setSize(350, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		JButton rent = new JButton("�뿩");
		JButton r_eturn = new JButton("�ݳ�");
		JButton back = new JButton("�ڷΰ���");
		
		rent.setBorderPainted(false);
		rent.setBackground(new Color(153, 231,35));
		r_eturn.setBorderPainted(false);
		r_eturn.setBackground(new Color(153, 231,35));
		back.setBorderPainted(false);
		back.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));

		rent.setBounds(48, 70, 96, 100);
		r_eturn.setBounds(192, 70, 96, 100);
		back.setBounds(10, 230, 100, 25);

		c.add(rent);
		c.add(r_eturn);
		c.add(back);

		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (user.vehicle != null)
					JOptionPane.showMessageDialog(rent, "���� �뿩���Դϴ�.");
				else {
					new Rent(user, spot);
					dispose();
				}
			}
		});

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Map(user);
				dispose();
			}
		});

		r_eturn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            if (user.vehicle == null) {
	               JOptionPane.showMessageDialog(getContentPane(), "�뿩�� ������/ű���尡 �����ϴ�.");
	            } 
	            else {
	               Ticket tmp = user.ticket;
	               int usemin = RentSystem.rentSpotMgr.returnVehicle(user, spot);
	               int kcal = 4 * usemin;

	               try {
	                  BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
	                  BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));

	                  ArrayList<String[]> arrays = new ArrayList<String[]>();
	                  String str = null;
	                  while ((str = bur.readLine()) != null) {
	                           String[] strarr = str.split(" ");
	                           if (strarr.length > 0)
	                              arrays.add(strarr);
	                           if (strarr[2].matches(user.id)) {
	                              if ( tmp.ticketType.contentEquals("���ϱ�"))
	                                 strarr[5] = "null";
	                              strarr[6] = "null";
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
	               JOptionPane.showMessageDialog(null, "�ݳ��� �Ϸ�Ǿ����ϴ� \n�̿�ð��� " + usemin + "���Դϴ�.\n�Ҹ��� Į�θ���"+ kcal +"Kcal �Դϴ�\n ");
	               dispose();
	               new MainMenu(user);
	            }
	         }
	      });

		setVisible(true);
	}
}