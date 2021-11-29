package tabledemo;

import java.awt.Graphics;
import java.awt.Image;
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

import rental.User;
//import tabledemo.Map1.MyActionListener;

public class Map2 extends JPanel implements ActionListener{
	JButton j1 = new JButton("A �����Ĺ�");
	JButton j2 = new JButton("B �����Ϸ��챤����");
	JButton j3 = new JButton("C �����ұ�ȸ");
	JButton j4 = new JButton("D ������������");
	JButton j5 = new JButton("E ���Ǳ�ȸ");
	JButton j6 = new JButton("F ����E���Ѽ������Ʈ");
	JButton j7 = new JButton("G �ѷ��긣��������");
	JButton j8 = new JButton("H ����Ǫ������");
	JButton back = new JButton("�ڷΰ���");
	User user;
	Map map;

	Image img = new ImageIcon("image/background.png").getImage();

	Map2(User user, Map m) {
		this.user = user;
		map = m;
		setLayout(null);
		setSize(700, 500);
		setVisible(false);
		// setBounds(������ġ, ������ġ, ���α���, ���α���);
		j1.setBounds(610, 10, 165, 30);
		add(j1);
		j2.setBounds(610, 45, 165, 30);
		add(j2);
		j3.setBounds(610, 80, 165, 30);
		add(j3);
		j4.setBounds(610, 115, 165, 30);
		add(j4);
		j5.setBounds(610, 150, 165, 30);
		add(j5);
		j6.setBounds(610, 185, 165, 30);
		add(j6);
		j7.setBounds(610, 220, 165, 30);
		add(j7);
		j8.setBounds(610, 255, 165, 30);
		add(j8);
		back.setBounds(10, 505, 100, 25);
		add(back);
		j1.addActionListener(this);	
		j2.addActionListener(this);
		j3.addActionListener(this);
		j4.addActionListener(this);		
		j5.addActionListener(this);
		j6.addActionListener(this);
		j7.addActionListener(this);
		j8.addActionListener(this);
		
		//�ڷΰ���
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // �ڷΰ���
				// TODO Auto-generated method stub
				new Table(user);
				m.setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RentReturn rr = new RentReturn(user);
		String buttonName = e.getActionCommand();
		
		try {
			BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
			BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
			
			String[] spotnamearr = buttonName.split(" ");
			String spotname = spotnamearr[1];
			
			ArrayList <String[]> arrays = new ArrayList<String[]>(); 
			String str = null;
			int add = 5; // ���ã�� �ִ� ���� �Ǻ���
			

			// yes ���� �� option=0 , no ���� �� option=1
			if(user.oftenSpotList.contains(spotname)) {
				int option =
				JOptionPane.showConfirmDialog(null, "�� �������� ���ã�� ��Ͽ��� �����Ͻðڽ��ϱ�?","���ã��",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
			
				if (option == 0) {
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);
						
						if (strarr[2].matches(user.id)) {
							for (int i=5;!strarr[i].equals("end");i++) {
								if (strarr[i].equals(spotname)) {
									for (int j=i;!strarr[j].equals("end");j++) {
										strarr[j]=strarr[j+1];
									}
								}
							}
						}
					}
					user.oftenSpotList.remove(spotname);
					JOptionPane.showMessageDialog(null, "���ã�� ��Ͽ��� ���ܵ˴ϴ�.");
					
					new FileOutputStream("user.txt").close();
				
					for (int i=0; i < arrays.size(); i++) {
						String[] outputarr = arrays.get(i);
						for (int k=1; !outputarr[k].equals("end"); k++) {
								outputarr[0] = outputarr[0] + " " + outputarr[k];
						}
						String oneLine = outputarr[0]+" end";
						if (i==arrays.size()-1) {
							bos.write(oneLine);
						}
						else {
							bos.write(oneLine + "\n");
						}
					}
					
				
					bur.close();
					bos.close();
					
				}
			}
			
			else {
				int option =
				JOptionPane.showConfirmDialog(null, "�� �������� ���ã�� ���������� �����Ͻðڽ��ϱ�?","���ã��",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
				if (option == 0) {
					
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);
						
						if (strarr[2].matches(user.id)) {
							for (int i=5;!strarr[i].equals("end");i++) {
								add = i+1;
								
							}
							if (add<=7) {
								strarr[add] = spotname + " end";
								user.oftenSpotList.add(spotname);
							}
						}
					}
					if (add>=8) 
						JOptionPane.showMessageDialog(null, "���ã��� �ִ� 3���� �����ұ����� �����Ͻ� �� �ֽ��ϴ�.");
					
					
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
				}
			} 
		} catch (Exception ex) {
			 ex.printStackTrace();
		}
		
		map.setVisible(false); // â �Ⱥ��̰� �ϱ�
	}	
	
}