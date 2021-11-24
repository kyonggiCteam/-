package tabledemo;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Table extends JFrame{
	Table() {
		JPanel top = new JPanel();
	    JPanel middle = new JPanel();
	    JPanel bottom = new JPanel();
	    JButton j1 = new JButton("대여");
		JButton j2 = new JButton("고장신고");
		JButton j3 = new JButton("결제");
	    
		/*
		 * JPanel p = new JPanel(); p.setLayout(null);
		 */
		top.setLayout(null);
		middle.setLayout(null);
		bottom.setLayout(null);
		
		add(j1);
		add(j2);
		add(j3);
		setSize(500, 500);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(top);
		add(middle);
		add(bottom);
		
		setTitle("대여");
		setVisible(true);
		
		//setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		j1.setBounds(31, 150, 100, 33);
		j2.setBounds(193, 150, 100, 33);
		j3.setBounds(355, 150, 100, 33);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				Map m = new Map();
				setVisible(false); // 창 안보이게 하기 
			}
		});;
	}
}
