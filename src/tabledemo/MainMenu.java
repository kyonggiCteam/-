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
	    JButton btn_rnt = new JButton("대여 및 반납");
		JButton btn_rpt = new JButton("고장신고");
		JButton btn_mpg = new JButton("마이페이지");
		JButton btn_lgt = new JButton("로그아웃");
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
		
		btn_rnt.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_rnt.setBackground(new Color(153, 231,35));
		btn_rpt.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_rpt.setBackground(new Color(153, 231,35));
		btn_mpg.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_mpg.setBackground(new Color(153, 231,35));
		btn_lgt.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_lgt.setBackground(new Color(153, 231,35));
		
		btn_rnt.setFont(new Font("맑은고딕", Font.BOLD, 11));
		
		add(btn_rnt);
		add(btn_rpt);
		add(btn_mpg);
		add(btn_lgt);
		add(currentinfo);
		setSize(500, 400);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(top);
		add(middle);
		add(bottom);
		
		setTitle("System Main Display");
		setVisible(true);
		
		//setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		btn_rnt.setBounds(31, 40, 100, 50);
		btn_rpt.setBounds(193, 40, 100, 50);
		btn_mpg.setBounds(355, 40, 100, 50);
		btn_lgt.setBounds(10,315,100,33);
		currentinfo.setBounds(40,120,400,170);
		
		//현재 로그인에서 임의로 유저와 vehicle을 설정했으므로 이부분 유의
		String a = user.name + "님 안녕하세요";
		String b = null;
		String c = null;
		if (user.vehicle != null) {
			b = "현재 대여중인 기구의 코드는 " + user.vehicle.code + " 입니다.";
		}
		else
			b = "";
		if(user.ticket != null && user.ticket.ticketType.equals("정기권"))
			c = "정기권 " + user.ticket.code + "은 " + user.leftday + "일 남았습니다.";
		else
			c = "";
			
		
//		currentinfo.setText("<html>"+a+"<br>"+b+"</html>"); // 텍스트 줄바꿈
		currentinfo.setText("<html>"+a+"<br>"+b+"<br>"+c+"</html>");
		currentinfo.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		
		// * 색 바꿀 필요 있을 듯....
		currentinfo.setOpaque(true);
		currentinfo.setBackground(new Color(255,255,255));
		
		// 대여 버튼 이벤트 리스너 대여 창으로 이동
		btn_rnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Map rent = new Map(user);  
				dispose(); // 창 안보이게 하기 
			}
		});
		// 고장신고
		btn_rpt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String report = JOptionPane.showInputDialog(null,"고장난 물품의 코드를 입력해주세요.","물품코드"); // 팝업창
				
				vehicle = RentSystem.vehicleMgr.find(report);
				
				int msgflag = 0; // 그냥 메시지 띄우면 두개가 동시에 열려서 플래그로 분기점 만들었습니다.
				
				if (report == null) 
					JOptionPane.showMessageDialog(null, "신고가 취소되었습니다.");
				else {
					if (vehicle == null) 
						JOptionPane.showMessageDialog(null, "존재하지 않는 코드번호입니다.");
					else { // 제대로 값이 입력될 때,
						try {
							
							// null 안하면 로그아웃을 안하고 같은 물품 재신고시 신고 접수했다고 나옵니다.
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
							JOptionPane.showMessageDialog(null, "신고가 접수되었습니다.");	
						else 
							JOptionPane.showMessageDialog(null, "이미 신고가 접수된 자전거입니다.");
					}
				}
			}
		});
		// 마이페이지
		btn_mpg.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { //마이페이지로 이동
				// TODO Auto-generated method stub
				MyPage m = new MyPage(user);
				dispose(); // 창 안보이게 하기 
			}
		});
		// 로그아웃
		btn_lgt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//로그아웃하여 회원가입창으로 이동
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin();
				dispose(); // 창 안보이게 하기 
			}
		});
		
	}
	
	//안됌
	public void paintComponent(Graphics g) {
		if(user.vehicle.code.charAt(0) == 'B')
			img_b = new ImageIcon("자전거.png").getImage();
		else if(user.vehicle.code.charAt(0) == 'K')
			img_k = new ImageIcon("킥보드.png").getImage();
	}
}