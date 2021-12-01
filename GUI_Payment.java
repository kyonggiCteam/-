package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import rental.RentSpot;
import rental.RentSystem;
import rental.Ticket;
import rental.User;

public class GUI_Payment extends JFrame {

	//User user;
	Ticket ticket;
	RentSpot spot;
	// CheckBike���� Frame â�� �ݾ��ֱ� ���ؼ� �ʿ�. -> Frame�� CheckBike â���� ����� ����.
//	static JFrame frame;
//	public static JFrame getFrame() {
////		if (frame == null)
////			frame = new JFrame();
//		return frame;
//	}

	GUI_Payment(User user, Ticket ticket ,RentSpot spot) { //GUI_Payment(User user, Ticket ticket)
		//this.user = user;		
		// Ƽ�� ã���ֱ� -> TicketTable���� ã�� �Ѱ���.
//		String code = ticketInform[5];
//		ticket = RentSystem.ticketMgr.find(code);
		this.spot=spot;
		this.ticket = ticket;
		
		setTitle("����ȭ��");
		setSize(300, 400);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���̾ƿ� ����
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(240, 248, 239));

		// ��ư ����
		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("����");
		JButton btn_bck = new JButton("���");
		
		btn_pay.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_pay.setBackground(new Color(153, 231,35));
		btn_bck.setBorderPainted(false); // ��ư �׵θ� ��������
	    btn_bck.setBackground(new Color(153, 231,35));

		pointText.setFont(new Font("�������", Font.PLAIN, 20));
		payInfo.setFont(new Font("�������", Font.BOLD, 15));
		payPoint.setFont(new Font("�������", Font.BOLD, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(240, 248, 239));
		payPoint.setBackground(new Color(240, 248, 239));
		
		String str1 = "[������ Ƽ��]   " + ticket.code ; // Ƽ�� �Ⱓ�� ���� ���뵵 �߰� ������ ������..
		String str2 = "[ ��  �� ]    " + ticket.price + " ��";
		String str3 = "[���� ����Ʈ]   " + user.point + " point";
		
		//user�޾Ƽ� ����Ҷ� string
		/*
		 * String str1 = "�����Ͻ� Ƽ���� " + user.ticket + " �Դϴ�."; // user.ticket??�³� -> ���� �������� �ʾ����Ƿ� user�� ticket�� ������ ���� ����.
		 * str2 = "������" + user.name; String str3 = "�����Ͻðڽ��ϱ�?"; String str4 =
		 * "���� ���� ����Ʈ�� " + user.point + " pt�Դϴ�.";
		 */
		payPoint.setText("[����Ʈ ���]");
		payInfo.setText("<html>" + str1 + "<br>" + str2 + "<p>" + str3 + "</html>");
		
		// �� ��ư ��ġ�� ũ�� ���� _������ġ, ������ġ, ���α���, ���α���
		payInfo.setBounds(30, 30, 200, 150);
		payPoint.setBounds(35, 200, 100, 48);
		pointText.setBounds(180, 210, 70, 30);
		btn_pay.setBounds(50, 300, 96, 33);
		btn_bck.setBounds(150, 300, 96, 33);

		// �� �����ӿ��ٰ� ��ư �߰�
		c.add(payInfo);
		c.add(payPoint);
		c.add(pointText);
		c.add(btn_pay);
		c.add(btn_bck);
		
		setVisible(true);

		// ����
		btn_pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				// Ƽ�� �߰�  -> �ܼ� ���� �Լ� ȣ�� => ���� ( ���� �ð��� �����)
				RentSystem.ticketMgr.buyTicket(ticket, user);
				// ����� ticket ���Ž� ���� �� ���. ->  MainMenu�� ���� �� ����.
				if(user.ticket.ticketType.equals("�����"))
					RentSystem.userMgr.updateDate(user);
				
				//1201 ����
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
							strarr[7] = Integer.toString(user.point + (ticket.price / 10) - Integer.parseInt(pointText.getText())); // ����Ʈ �Է�
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
			
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");			
				
				new CheckBike(user, spot);
				// Frame ���� ����. EU => Ƽ�� �����ÿ� CheckBike�� �̵��ؾ��ϹǷ�
				
//				frame = new JFrame("CheckBike");
//		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		        //Create and set up the content pane.
//		        BikeTable newContentPane = new BikeTable(user, spot);
//		        newContentPane.setOpaque(true); //content panes must be opaque
//		        frame.setContentPane(newContentPane);
//		        //add(newContentPane);
//
//		        //Display the window.
//		        frame.pack();
//		        frame.setVisible(true);
		        
		        dispose();
			}
		});

		// ���
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				new Rent(user,spot);
				dispose(); // â �Ⱥ��̰� �ϱ�
			}
		});
		
		// �� ������ ������ ũ�� ǥ��
		System.out.println(getContentPane().getSize());
	}

	//�׽�Ʈ�ϱ� ���� ����
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		GUI_Payment gp = new GUI_Payment();
//	}
}