package tabledemo;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import rental.User;

public class Join extends JFrame {
	
	int flag = 0; // 아이디 확인 안하고 가입 시도 거름망 역할
	
	public Join(ArrayList<User> userList) {
		JPanel p = new JPanel();
		p.setLayout(null);
		JButton btn_bck = new JButton("back");
		add(btn_bck);
		JButton btn_chk = new JButton("확인");
		add(btn_chk);
		
		
		
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
		t1.setBounds(125, 20, 170, 30);
		t2.setBounds(125, 60, 170, 30);
		t3.setBounds(125, 100, 170, 30);
		t4.setBounds(125, 140, 170, 30);
		t5.setBounds(125, 180, 170, 30);
		yes.setBounds(125, 220, 50, 30);
		no.setBounds(180, 220, 50, 30);
		j1.setBounds(153, 290, 80, 30);
		btn_bck.setBounds(300, 320, 70, 30);
		btn_chk.setBounds(305, 100, 60, 28);
		add(p);
		setTitle("회원가입");
		setVisible(true);
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin(userList);
				setVisible(false); // 창 안보이게 하기 
			}
		});;
		
		btn_chk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				
				try {
					String s;
					String[] array;
					int dupflag = 0; // 분기점을 위한 flag 인수 0이면 중복 아님, 1이면 중복
					BufferedReader bos = new BufferedReader(new FileReader("user.txt"));
					while ((s = bos.readLine()) != null) {
						array = s.split(" ");
						if (t3.getText().equals(array[2])) {
							dupflag = 1;
							flag = dupflag;
							break;
						}
					}
					if (dupflag == 0)
						JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디 입니다.");  // 팝업창
					if (dupflag == 1)
						JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다.");  // 팝업창
					bos.close(); // 파일입출력끝나면 close
				} catch (IOException E) {
					E.printStackTrace();
				}
			}
		});;
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent T) {// 회원가입 데이터 저장
				try {
					
					String inputname = t1.getText();
					String inputtelnum = t2.getText();
					String inputid = t3.getText();
					String inputpw = t4.getText();
					String inputpwcheck = t5.getText();

					if (inputname.equals("")||inputtelnum.equals("")||inputid.equals("")||inputpw.equals("")) // 모든 칸을 입력하지 않았을때
						JOptionPane.showMessageDialog(null, "입력할 내용을 모두 입력해주세요.");
					else if (yes.isSelected() != true && no.isSelected() != true) // 면허 여부 선택 안했을때
						JOptionPane.showMessageDialog(null, "면허 보유 여부를 선택해주세요.");
					else if (flag==1) // 아이디 확인 안하고 가입하는 것 방지용
						JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다.");
					else if (!inputpw.equals(inputpwcheck)) // 비밀번호 확인 불일치
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					else {
						// 데이터 파일에 입력
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
						// 다음 단계로
						JOptionPane.showMessageDialog(null, "회원가입 성공");
						Login l = new Login(userList);
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "회원가입 실패");
				}
			}
		});
	}

}
