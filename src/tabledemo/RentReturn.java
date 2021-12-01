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
import javax.swing.JPanel;

import rental.RentSpot;
import rental.RentSystem;
import rental.User;

public class RentReturn extends JFrame {
	//User user;

	RentReturn(User user, RentSpot spot) {
		//this.user = user;

		setTitle("대여/반납");
		setSize(350, 300);
		setLayout(null);
		setResizable(false);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);

		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		Container c = getContentPane();
		c.setLayout(null);

		// 버튼 생성
		JButton rent = new JButton("대여");
		JButton r_eturn = new JButton("반납");
		JButton back = new JButton("뒤로가기");
		
		rent.setBorderPainted(false); // 버튼 테두리 설정해제
		rent.setBackground(new Color(153, 231,35));
		r_eturn.setBorderPainted(false); // 버튼 테두리 설정해제
		r_eturn.setBackground(new Color(153, 231,35));
		back.setBorderPainted(false); // 버튼 테두리 설정해제
		back.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));

		// ★ 버튼 위치와 크기 설정
		rent.setBounds(48, 70, 96, 100);
		r_eturn.setBounds(192, 70, 96, 100);
		back.setBounds(10, 230, 100, 25);

		// ★ 프레임에다가 버튼 추가
		c.add(rent);
		c.add(r_eturn);
		c.add(back);

		// 빌리기
		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				// 대여 중인지 확인
				if (user.vehicle != null)
					JOptionPane.showMessageDialog(rent, "현재 대여중입니다.");
				else {
					Rent r = new Rent(user, spot);
					dispose(); // 창 안보이게 하기
				}
			}
		});
		;

		// 돌아가기
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				Map map = new Map(user);
				dispose(); // 창 안보이게 하기
			}
		});
		;

		//ActionListener listener1 = new ButtonListener();
		// 반납
		r_eturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (user.vehicle == null) {
					JOptionPane.showMessageDialog(getContentPane(), "대여한 자전거/킥보드가 없습니다.");
				} 
				else {
					// 반납함수 호출(반납해주고 이용시간 계산)
					int usemin = RentSystem.rentSpotMgr.returnVehicle(user, spot);
					// 칼로리 계산 
					int kcal = 4 * usemin;
					// 추가 계산 함수 호출
					if(user.ticket.ticketType.equals("일일권")) {
						int moreprice = RentSystem.ticketMgr.morePay(usemin);
						// 추가 계산 창 띄우기 -> 일단 나중에.
					}
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
					// 이용시간 알려주기
					JOptionPane.showMessageDialog(null, "반납이 완료되었습니다 \n이용시간은 " + usemin + "분입니다.\n소모한 칼로리는"+ kcal +"Kcal 입니다\n "); // 팝업창
//					JOptionPane.showMessageDialog(null, "반납이 완료되었습니다 \n\n 소모한 칼로리는_Kcal입니다");
					// JOptionPane.showMessageDialog(null, "대여한 자전거가 없습니다.");
					dispose();
					new MainMenu(user);
				}
			}
		});

		// 프레임이 보이도록 설정
		setVisible(true);
	}

}