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
		// 티켓 찾아주기
		String code = ticketInform[5];
		ticket = RentSystem.ticketMgr.find(code);
		this.spot=spot;
		
		setTitle("결제화면");
		setSize(500, 500);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		Container c = getContentPane();
		c.setLayout(null);

		// 버튼 생성
		JLabel payInfo = new JLabel();
		JLabel payPoint = new JLabel();
		TextField pointText = new TextField("0");
		JButton btn_pay = new JButton("결제");
		JButton btn_bck = new JButton("취소");

		pointText.setFont(new Font("맑은고딕", Font.PLAIN, 25));
		payInfo.setFont(new Font("맑은고딕", Font.PLAIN, 25));
		payPoint.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		
		payInfo.setOpaque(true);
		payPoint.setOpaque(true);
		payInfo.setBackground(new Color(0, 250, 0));
		payPoint.setBackground(new Color(0, 250, 0));
		
		String str1 = "선택하신 티켓은 " + ticket.code +  " 입니다."; // user.ticket??맞나
		String str2 = "가격은 " + ticket.price + "원입니다.";
		String str3 = "결제하시겠습니까?";
		String str4 = "현재 보유 포인트는 " + user.point + "pt입니다.";
		
		//user받아서 사용할때 string
		/*
		 * String str1 = "선택하신 티켓은 " + user.ticket + " 입니다."; // user.ticket??맞나 String
		 * str2 = "가격은" + user.name; String str3 = "결제하시겠습니까?"; String str4 =
		 * "현재 보유 포인트는 " + user.point + " pt입니다.";
		 */
		payPoint.setText("사용할 포인트를 적어주세요.");
		payInfo.setText("<html>" + str1 + "<br>" + str2 + "<p>" + str3 + "<p>" + str4 + "</html>");
		
		// ★ 버튼 위치와 크기 설정 _가로위치, 세로위치, 가로길이, 세로길이
		payInfo.setBounds(30, 30, 430, 250);
		payPoint.setBounds(45, 300, 300, 48);
		pointText.setBounds(360, 300, 70, 48);
		btn_pay.setBounds(145, 400, 96, 33);
		btn_bck.setBounds(246, 400, 96, 33);

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
				// 티켓 추가
				user.ticket = ticket;
				JOptionPane.showMessageDialog(null, "결제되었습니다.");
				//new CheckBike(); // 자전거 조회
				
				
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

		// 취소
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				new Rent(user,spot);
				setVisible(false); // 창 안보이게 하기
			}
		});
		;
		
		// ★ 컨텐츠 영역의 크기 표시
		System.out.println(getContentPane().getSize());
	}

	//테스트하기 위한 메인
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		GUI_Payment gp = new GUI_Payment();
//	}
}