package tabledemo;

import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.User;

public class Table extends JFrame {

	public Table(User user) {
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

		add(btn_rnt);
		add(btn_rpt);
		add(btn_mpg);
		add(btn_lgt);
		add(currentinfo);
		setSize(500, 500);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(top);
		add(middle);
		add(bottom);

		setTitle("System Main Display");
		setVisible(true);

		// setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		btn_rnt.setBounds(31, 150, 100, 33);
		btn_rpt.setBounds(193, 150, 100, 33);
		btn_mpg.setBounds(355, 150, 100, 33);
		btn_lgt.setBounds(10, 400, 100, 33);
		currentinfo.setBounds(40, 200, 400, 170);

		// 현재 로그인에서 임의로 유저와 vehicle을 설정했으므로 이부분 유의
		String a = user.name + "님 안녕하세요";
		String b = null;
		if (user.vehicle != null) {
			b = "현재 대여중인 기구의 코드는 " + user.vehicle.code + " 입니다.";
		} else
			b = "";

		currentinfo.setText("<html>" + a + "<br>" + b + "</html>"); // 텍스트 줄바꿈
		currentinfo.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬

		currentinfo.setOpaque(true);
		currentinfo.setBackground(new Color(0, 250, 0));

		// 대여 버튼 이벤트 리스너 대여 창으로 이동
		btn_rnt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Map rent = new Map(user); // 일단 User 안 받기
				setVisible(false); // 창 안보이게 하기
			}
		});
		// 고장신고
		btn_rpt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String report = JOptionPane.showInputDialog(null, "고장난 물품의 코드를 입력해주세요.", "물품코드"); // 팝업창
				if (report == null)
					JOptionPane.showMessageDialog(null, "신고가 취소되었습니다.");
				else
					JOptionPane.showMessageDialog(null, "신고가 접수되었습니다.");
				// 고장 자전거를 고장 자전거로 상태 변경
				// VehicleMgr.find(report).state = -1
			}
		});
		// 마이페이지
		btn_mpg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 마이페이지로 이동
				// TODO Auto-generated method stub
				MyPage m = new MyPage(user);
				setVisible(false); // 창 안보이게 하기
			}
		});
		// 로그아웃
		btn_lgt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 로그아웃하여 회원가입창으로 이동
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin();
				setVisible(false); // 창 안보이게 하기
			}
		});

	}

	// yr님
//	public Table() {
//		JPanel top = new JPanel();
//	    JPanel middle = new JPanel();
//	    JPanel bottom = new JPanel();
//	    JButton j1 = new JButton("대여");
//		JButton j2 = new JButton("고장신고");
//		JButton j3 = new JButton("결제");
//	    
//		/*
//		 * JPanel p = new JPanel(); p.setLayout(null);
//		 */
//		top.setLayout(null);
//		middle.setLayout(null);
//		bottom.setLayout(null);
//		
//		add(j1);
//		add(j2);
//		add(j3);
//		setSize(500, 500);
//		// 프레임을 화면 가운데에 배치
//		setLocationRelativeTo(null);
//		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		add(top);
//		add(middle);
//		add(bottom);
//		
//		setTitle("대여");
//		setVisible(true);
//		
//		//setBounds(가로위치, 세로위치, 가로길이, 세로길이);
//		j1.setBounds(31, 150, 100, 33);
//		j2.setBounds(193, 150, 100, 33);
//		j3.setBounds(355, 150, 100, 33);
//		
//	}
}