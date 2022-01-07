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
		
		setTitle("결제화면");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(240, 248, 239));

		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("결제");
		JButton btn_bck = new JButton("취소");
		
		btn_pay.setBorderPainted(false);
		btn_pay.setBackground(new Color(153, 231,35));
		btn_bck.setBorderPainted(false);
	    btn_bck.setBackground(new Color(153, 231,35));

		pointText.setFont(new Font("맑은고딕", Font.PLAIN, 20));
		payInfo.setFont(new Font("맑은고딕", Font.BOLD, 15));
		payPoint.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(240, 248, 239));
		payPoint.setBackground(new Color(240, 248, 239));
		
		String str1 = "[선택한 티켓]   " + ticket.code ;
		String str2 = "[ 가  격 ]    " + ticket.price + " 원";
		String str3 = "[보유 포인트]   " + user.point + " point";

		payPoint.setText("[포인트 사용]");
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
					JOptionPane.showMessageDialog(null, "보유 포인트를 초과하셨습니다.");
					return;
				}
				
				RentSystem.ticketMgr.buyTicket(ticket, user);
				if(user.ticket.ticketType.equals("정기권"))
					RentSystem.userMgr.updateDate(user);
				// 정기권 내용 데이터 파일에 저장.
				try {
					BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
					BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));

					ArrayList<String[]> arrays = new ArrayList<String[]>();
					String str = null;
					while ((str = bur.readLine()) != null) {
						String[] strarr = str.split(" ");
						if (strarr.length > 0)
							arrays.add(strarr);
						if (strarr[2].matches(user.id)) { // id가 일치하면
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
				

				int usedpoint = Integer.parseInt(pointText.getText()); // 기본적으로 0이 저장.
				try {
					BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
					BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
					
					ArrayList <String[]> arrays = new ArrayList<String[]>(); 
					String str = null;
					while ((str = bur.readLine())!=null) {
						String[] strarr = str.split(" ");
						if(strarr.length>0) arrays.add(strarr);					
						if (strarr[2].matches(user.id)) {
							strarr[5] = ticket.code; // 티켓 입력
							strarr[7] = Integer.toString(user.point - usedpoint); // 포인트 입력
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
		
				if (usedpoint == 0) { // 포인트 사용 안 됨.
					JOptionPane.showMessageDialog(null, "결제되었습니다.");
				} else { // 포인트 사용.
					JOptionPane.showMessageDialog(null, (ticket.price - usedpoint) + "원 결제되었습니다.");
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