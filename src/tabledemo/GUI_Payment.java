package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	User user;
	Ticket ticket;
	RentSpot spot;

	GUI_Payment(User user, String[] ticketInform,RentSpot spot) { //GUI_Payment(User user, Ticket ticket)
		//this.user = user;		
		// Ƽ�� ã���ֱ�
		String code = ticketInform[5];
		ticket = RentSystem.ticketMgr.find(code);
		this.spot=spot;
		
		setTitle("����ȭ��");
		setSize(500, 500);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���̾ƿ� ����
		Container c = getContentPane();
		c.setLayout(null);

		// ��ư ����
		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("����");
		JButton btn_bck = new JButton("���");

		pointText.setFont(new Font("�������", Font.PLAIN, 25));
		payInfo.setFont(new Font("�������", Font.PLAIN, 25));
		payPoint.setFont(new Font("�������", Font.PLAIN, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(0, 250, 0));
		payPoint.setBackground(new Color(0, 250, 0));
		
		String str1 = "�����Ͻ� Ƽ���� " + ticket.code +  " �Դϴ�."; // user.ticket??�³�
		String str2 = "������ " + ticket.price + "���Դϴ�.";
		String str3 = "�����Ͻðڽ��ϱ�?";
		String str4 = "���� ���� ����Ʈ�� " + user.point + "pt�Դϴ�.";
		
		//user�޾Ƽ� ����Ҷ� string
		/*
		 * String str1 = "�����Ͻ� Ƽ���� " + user.ticket + " �Դϴ�."; // user.ticket??�³� String
		 * str2 = "������" + user.name; String str3 = "�����Ͻðڽ��ϱ�?"; String str4 =
		 * "���� ���� ����Ʈ�� " + user.point + " pt�Դϴ�.";
		 */
		payPoint.setText("����� ����Ʈ�� �����ּ���.");
		payInfo.setText("<html>" + str1 + "<br>" + str2 + "<p>" + str3 + "<p>" + str4 + "</html>");
		
		// �� ��ư ��ġ�� ũ�� ���� _������ġ, ������ġ, ���α���, ���α���
		payInfo.setBounds(30, 30, 430, 250);
		payPoint.setBounds(45, 300, 300, 48);
		pointText.setBounds(360, 300, 70, 48);
		btn_pay.setBounds(145, 400, 96, 33);
		btn_bck.setBounds(246, 400, 96, 33);

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
				// Ƽ�� �߰�
				user.ticket = ticket;
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
				//new CheckBike(); // ������ ��ȸ
				
				
				JFrame frame = new JFrame("CheckBike");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        //Create and set up the content pane.
		        CheckBike newContentPane = new CheckBike(user, frame, spot);
		        newContentPane.setOpaque(true); //content panes must be opaque
		        frame.setContentPane(newContentPane);
		        //add(newContentPane);

		        //Display the window.
		        frame.pack();
		        frame.setVisible(true);
		        
		        setVisible(false);
			}
		});

		// ���
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				new Rent(user,spot);
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;
		
		// �� ������ ������ ũ�� ǥ��
		System.out.println(getContentPane().getSize());
	}

	//�׽�Ʈ�ϱ� ���� ����
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		GUI_Payment gp = new GUI_Payment();
//	}
}