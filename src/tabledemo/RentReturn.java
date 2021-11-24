package tabledemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RentReturn extends JFrame {
	RentReturn() {
		JPanel p = new JPanel();
		p.setLayout(null);

		setTitle("대여/반납");
		setSize(350, 300);
		setResizable(false);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);

		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		getContentPane().setLayout(null);

		// 버튼 생성
		JButton rent = new JButton("대여");
		JButton r_eturn = new JButton("반납");
		JButton back = new JButton("뒤로가기");

		// ★ 버튼 위치와 크기 설정
		rent.setBounds(48, 70, 96, 100);
		r_eturn.setBounds(192, 70, 96, 100);
		back.setBounds(10, 230, 100, 25);

		// ★ 프레임에다가 버튼 추가
		getContentPane().add(rent);
		getContentPane().add(r_eturn);
		getContentPane().add(back);

		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				Rent r = new Rent();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				Map map = new Map();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		ActionListener listener1 = new ButtonListener();
		r_eturn.addActionListener(listener1);

		// 프레임이 보이도록 설정
		setVisible(true);
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "반납이 완료되었습니다 \n\n 소모한 칼로리는___kcal입니다"); // 팝업창
			//JOptionPane.showMessageDialog(null, "대여한 자전거가 없습니다.");
			setVisible(false);
		}
	}

}
