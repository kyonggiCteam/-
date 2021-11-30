package tabledemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSpot;
import rental.User;

public class RentReturn extends JFrame {
	User user;

	RentReturn(User user, RentSpot spot) {
		this.user = user;
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

		// 빌리기
		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				// 대여 중인지 확인
				if(user.vehicle != null)
					JOptionPane.showMessageDialog(rent, "현재 대여중입니다.");
				else {
					
				Rent r = new Rent(user, spot);
				setVisible(false); // 창 안보이게 하기
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
			// 빌리고 있는지 확인해야함..
			if(user.vehicle == null) {
				JOptionPane.showMessageDialog(getContentPane(), "대여한 자전거/킥보드가 없습니다.");
			}
			else {
			// 실질적으로 반납해줘야 함..
				
			user.vehicle = null;
			JOptionPane.showMessageDialog(null, "반납이 완료되었습니다 \n\n 소모한 칼로리는___kcal입니다"); // 팝업창
			//JOptionPane.showMessageDialog(null, "대여한 자전거가 없습니다.");
			setVisible(false);
			new Table(user);
			}

		}
	}

}