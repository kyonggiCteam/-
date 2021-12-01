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
	// CheckBike에서 Frame 창을 닫아주기 위해서 필요. -> Frame을 CheckBike 창에서 만들면 괜찮.
//	static JFrame frame;
//	public static JFrame getFrame() {
////		if (frame == null)
////			frame = new JFrame();
//		return frame;
//	}

	GUI_Payment(User user, Ticket ticket ,RentSpot spot) { //GUI_Payment(User user, Ticket ticket)
		//this.user = user;		
		// 티켓 찾아주기 -> TicketTable에서 찾고 넘겨줌.
//		String code = ticketInform[5];
//		ticket = RentSystem.ticketMgr.find(code);
		this.spot=spot;
		this.ticket = ticket;
		
		setTitle("결제화면");
		setSize(300, 400);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(240, 248, 239));

		// 버튼 생성
		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("결제");
		JButton btn_bck = new JButton("취소");
		
		btn_pay.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_pay.setBackground(new Color(153, 231,35));
		btn_bck.setBorderPainted(false); // 버튼 테두리 설정해제
	    btn_bck.setBackground(new Color(153, 231,35));

		pointText.setFont(new Font("맑은고딕", Font.PLAIN, 20));
		payInfo.setFont(new Font("맑은고딕", Font.BOLD, 15));
		payPoint.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(240, 248, 239));
		payPoint.setBackground(new Color(240, 248, 239));
		
		String str1 = "[선택한 티켓]   " + ticket.code ; // 티켓 기간에 대한 내용도 추가 됐으면 좋겠음..
		String str2 = "[ 가  격 ]    " + ticket.price + " 원";
		String str3 = "[보유 포인트]   " + user.point + " point";
		
		//user받아서 사용할때 string
		/*
		 * String str1 = "선택하신 티켓은 " + user.ticket + " 입니다."; // user.ticket??맞나 -> 아직 구매하지 않았으므로 user가 ticket을 가지고 있지 않음.
		 * str2 = "가격은" + user.name; String str3 = "결제하시겠습니까?"; String str4 =
		 * "현재 보유 포인트는 " + user.point + " pt입니다.";
		 */
		payPoint.setText("[포인트 사용]");
		payInfo.setText("<html>" + str1 + "<br>" + str2 + "<p>" + str3 + "</html>");
		
		// ★ 버튼 위치와 크기 설정 _가로위치, 세로위치, 가로길이, 세로길이
		payInfo.setBounds(30, 30, 200, 150);
		payPoint.setBounds(35, 200, 100, 48);
		pointText.setBounds(180, 210, 70, 30);
		btn_pay.setBounds(50, 300, 96, 33);
		btn_bck.setBounds(150, 300, 96, 33);

		// ★ 프레임에다가 버튼 추가
		c.add(payInfo);
		c.add(payPoint);
		c.add(pointText);
		c.add(btn_pay);
		c.add(btn_bck);
		
		setVisible(true);

		// 결제
		btn_pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				// 티켓 추가  -> 콘솔 안의 함수 호출 => 성공 ( 시작 시간도 저장됨)
				RentSystem.ticketMgr.buyTicket(ticket, user);
				// 정기권 ticket 구매시 남은 일 계산. ->  MainMenu에 남을 일 써줌.
				if(user.ticket.ticketType.equals("정기권"))
					RentSystem.userMgr.updateDate(user);
				
				//1201 수정
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
							strarr[7] = Integer.toString(user.point + (ticket.price / 10) - Integer.parseInt(pointText.getText())); // 포인트 입력
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
			
				JOptionPane.showMessageDialog(null, "결제되었습니다.");			
				
				new CheckBike(user, spot);
				// Frame 따로 만듬. EU => 티켓 보유시에 CheckBike로 이동해야하므로
				
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

		// 취소
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				new Rent(user,spot);
				dispose(); // 창 안보이게 하기
			}
		});
		
		// ★ 컨텐츠 영역의 크기 표시
		System.out.println(getContentPane().getSize());
	}

	//테스트하기 위한 메인
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		GUI_Payment gp = new GUI_Payment();
//	}
}