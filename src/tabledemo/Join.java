package tabledemo;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Join extends JFrame {
	public Join() {
		JPanel p = new JPanel();
		p.setLayout(null);
		Label l1 = new Label("이름");
		Label l2 = new Label("전화번호");
		Label l3 = new Label("아이디");
		Label l4 = new Label("패스워드");
		Label l5 = new Label("패스워드 확인");
		Label l6 = new Label("면허 유무");
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();

		JRadioButton yes = new JRadioButton("O");
		JRadioButton no = new JRadioButton("X");
		ButtonGroup bg = new ButtonGroup();

		add(t1);
		add(t2);
		add(t3);
		add(t4);
		add(t5);
		bg.add(yes); add(yes);
	    bg.add(no); add(no);
		t4.setEchoChar('*'); // 패스워드
		t5.setEchoChar('*'); // 패스워드 확인
		JButton j1 = new JButton("가입");
		add(j1);
		setSize(400, 400);
		setResizable(false);
		// 프레임을 화면 가운데에 배치
		setLocationRelativeTo(null);
		// 프레임을 닫았을 때 메모리에서 제거되도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1.setBounds(40, 20, 40, 40);
		l2.setBounds(40, 60, 50, 40);
		l3.setBounds(40, 100, 40, 40);
		l4.setBounds(40, 140, 50, 40);
		l5.setBounds(40, 180, 75, 40);
		l6.setBounds(40, 220, 60, 40);
		t1.setBounds(125, 20, 200, 30);
		t2.setBounds(125, 60, 200, 30);
		t3.setBounds(125, 100, 200, 30);
		t4.setBounds(125, 140, 200, 30);
		t5.setBounds(125, 180, 200, 30);
		yes.setBounds(125, 220, 50, 30);
		no.setBounds(180, 220, 50, 30);
		j1.setBounds(153, 290, 80, 30);
		add(p);
		setTitle("회원가입");
		setVisible(true);
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent T) {// 회원가입 데이터 저장
				try {
					BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
					bos.write(t1.getText() + " ");
					bos.write(t2.getText() + " ");
					bos.write(t3.getText() + " ");
					bos.write(t4.getText() + " ");
					if(yes.isSelected() == true)
						bos.write("1\n");
					else if(no.isSelected() == true)
						bos.write("0\n");
					bos.close();
					JOptionPane.showMessageDialog(null, "회원가입 성공");
					Login l = new Login();
					setVisible(false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "회원가입 실패");
				}
			}
		});
	}

}
