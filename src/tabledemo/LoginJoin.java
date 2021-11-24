package tabledemo;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJoin extends JFrame{

	public LoginJoin() {
		//JFrame frm = new JFrame("로그인 화면");
		
		JPanel p = new JPanel();
        p.setLayout(null);
        
        setTitle("메인화면");
		setSize(350, 300);
		setResizable(false);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);

		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		getContentPane().setLayout(null);

		// 버튼 생성
		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입");

		// ★ 버튼 위치와 크기 설정
		login.setBounds(48, 100, 96, 63); 
		join.setBounds(192, 100, 96, 63);

		// ★ 프레임에다가 버튼 추가
		getContentPane().add(login);
		getContentPane().add(join);

		// 로그인 버튼이 눌렸을때
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				Login l = new Login();
				setVisible(false); // 창 안보이게 하기 
			}
		});;

		// 회원가입 버튼이 눌렸을때
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				Join j = new Join();
				setVisible(false); // 창 안보이게 하기 
			}
		});;

		// 프레임이 보이도록 설정
		setVisible(true);

		// ★ 컨텐츠 영역의 크기 표시
		System.out.println(getContentPane().getSize());
	}

}