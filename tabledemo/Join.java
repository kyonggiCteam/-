package tabledemo;

import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import rental.RentSystem;
import rental.User;
import rental.UserManager;

public class Join extends JFrame {
	String id;
	int flag = 0; // 아이디 확인 안하고 가입 시도 거름망 역할 -> 오 좋은 듯

	public Join() {
//      JPanel p = new JPanel();
//      p.setLayout(null);
		Container c = getContentPane();
		c.setLayout(null);

		JButton btn_bck = new JButton("back");
		c.add(btn_bck);
		JButton btn_chk = new JButton("확인");
		c.add(btn_chk);

		Label[] label = { new Label("이름"), new Label("전화번호"), new Label("아이디"), new Label("패스워드"), new Label("패스워드 확인"),
				new Label("면허 유무") };
		for (int i = 0; i < label.length; i++)
			c.add(label[i]);

		TextField[] text = { new TextField(), new TextField(), new TextField(), new TextField(), new TextField() };
		for (int i = 0; i < text.length; i++)
			c.add(text[i]);

		JRadioButton yes = new JRadioButton("O");
		JRadioButton no = new JRadioButton("X");
		ButtonGroup bg = new ButtonGroup();

		bg.add(yes);
		add(yes);
		bg.add(no);
		add(no);
		text[3].setEchoChar('*'); // 패스워드
		text[4].setEchoChar('*'); // 패스워드 확인
		JButton j1 = new JButton("가입");
		c.add(j1);
		setSize(400, 400);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 위치, 크기 설정
		label[0].setBounds(40, 20, 40, 40);
		label[1].setBounds(40, 60, 50, 40);
		label[2].setBounds(40, 100, 40, 40);
		label[3].setBounds(40, 140, 50, 40);
		label[4].setBounds(40, 180, 75, 40);
		label[5].setBounds(40, 220, 60, 40);
		text[0].setBounds(125, 20, 170, 30);
		text[1].setBounds(125, 60, 170, 30);
		text[2].setBounds(125, 100, 170, 30);
		text[3].setBounds(125, 140, 170, 30);
		text[4].setBounds(125, 180, 170, 30);
		yes.setBounds(125, 220, 50, 30);
		no.setBounds(180, 220, 50, 30);
		j1.setBounds(153, 290, 80, 30);
		btn_bck.setBounds(300, 320, 70, 30);
		btn_chk.setBounds(305, 100, 60, 28);
		// add(p);
		setTitle("회원가입");
		setVisible(true);

		// back
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				new LoginJoin();
				setVisible(false); // 창 안보이게 하기
			}
		});
		;

		// 아이디 확인
		btn_chk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입창으로 이동
				// TODO Auto-generated method stub
				if (RentSystem.userMgr.idDuplicate(id)) {
					JOptionPane.showMessageDialog(c, "이미 존재하는 아이디입니다.");
				} else {
					flag = 1;
					JOptionPane.showMessageDialog(c, "사용할 수 있는 아이디입니다.");
				}
			}
		});
		;

		// 가입 클릭시 -> 확인 안 해봄..
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent T) {// 회원가입 데이터 저장
				try {
					String name = text[0].getText();
					String phoneNumber = text[1].getText();
					id = text[2].getText();
					String pwd = text[3].getText();
					String pwdCheck = text[4].getText();
					String license = null;
					if (yes.isSelected())
						license = "1";
					else if (no.isSelected())
						license = "0";

					if (name.equals("") || phoneNumber.equals("") || id.equals("") || pwd.equals("")) // 모든 칸을 입력하지 않았을때
						JOptionPane.showMessageDialog(null, "입력할 내용을 모두 입력해주세요.");
					else if (yes.isSelected() == false && no.isSelected() == false) // 면허 여부 선택 안했을때
						JOptionPane.showMessageDialog(null, "면허 보유 여부를 선택해주세요.");
					else if (flag == 0) // 아이디 확인 안하고 가입하는 것 방지용
						JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.");
					else if (!pwd.equals(pwdCheck)) // 비밀번호 확인 불일치
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					else {
						String[] userInfo = { name, phoneNumber, id, pwd, license };
						RentSystem.userMgr.join(userInfo);

						BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
						bos.newLine();
						bos.write(name + " ");
						bos.write(phoneNumber + " ");
						bos.write(id + " ");
						bos.write(pwd + " ");
						bos.write(license);
						bos.close();
						JOptionPane.showMessageDialog(null, "회원가입 성공");
						new Login();
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "회원가입 실패");
				}
			}
		});
	}

}