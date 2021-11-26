package tabledemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import rental.User;

public class ModifyMyInfo extends JFrame {
	
	public ModifyMyInfo(ArrayList<User> userList, User user) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
    	setTitle("내 정보 수정");
    	
    	getContentPane().setLayout(null);
    	
		
		Label l_name = new Label("이름");
		Label l_tellnum = new Label("전화번호");
		Label l_pwd = new Label("패스워드");
		Label l_pwdcheck = new Label("패스워드 확인");
		Label l_license = new Label("면허 유무");
		
		add(l_name);
		add(l_tellnum);
		add(l_pwd);
		add(l_pwdcheck);
		add(l_license);
		
		JButton btn_bck = new JButton("돌아가기");
		add(btn_bck);
		
		JButton btn_mdf = new JButton("수정");
		add(btn_mdf);
		
		TextField tfname = new TextField();
		TextField tftellnum = new TextField();
		TextField tfpwd = new TextField();
		TextField tfpwdcheck = new TextField();
		
		tfpwd.setEchoChar('*'); // 패스워드
	    tfpwdcheck.setEchoChar('*');
		
		add(tfname);
		add(tftellnum);
		add(tfpwd);
		add(tfpwdcheck);
		
		JRadioButton yes = new JRadioButton("O");
		JRadioButton no = new JRadioButton("X");
		ButtonGroup bg = new ButtonGroup();
		bg.add(yes); add(yes);
	    bg.add(no); add(no);
	    
		l_name.setBounds(90, 100, 40, 40);
		l_tellnum.setBounds(90, 140, 50, 40);
		l_pwd.setBounds(90, 180, 40, 40);
		l_pwdcheck.setBounds(90, 220, 50, 40);
		l_license.setBounds(90, 260, 75, 40);

		tfname.setBounds(175, 100, 170, 30);
		tftellnum.setBounds(175, 140, 170, 30);
		tfpwd.setBounds(175, 180, 170, 30);
		tfpwdcheck.setBounds(175, 220, 170, 30);

		yes.setBounds(190, 260, 50, 30);
		no.setBounds(250, 260, 50, 30);
		btn_mdf.setBounds(203, 370, 80, 30);
		btn_bck.setBounds(350, 390, 70, 30);

		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				try {
					
					String inputname = tfname.getText();
					String inputtelnum = tftellnum.getText();
					String inputpw = tfpwd.getText();
					String inputpwcheck = tfpwdcheck.getText();

					if (inputname.equals("")||inputtelnum.equals("")||inputpw.equals("")) // 모든 칸을 입력하지 않았을때
						JOptionPane.showMessageDialog(null, "입력할 내용을 모두 입력해주세요.");
					else if (yes.isSelected() != true && no.isSelected() != true) // 면허 여부 선택 안했을때
						JOptionPane.showMessageDialog(null, "면허 보유 여부를 선택해주세요.");
					else if (!inputpw.equals(inputpwcheck)) // 비밀번호 확인 불일치
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					else {
						// 데이터 파일에 입력
						BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
						/*
						유저리스트를 가지고 와서 인덱스 등의 방법을 이용해 유저를 찾아서 유저부분만 수정하도록 메소드를 짜야함
						유저리스트가 오지 않아 위 기능을 구현하지 못함
						현재는 새로운 데이터로 저장되는 방식임
						 */
						bos.write(tfname.getText() + " ");
						bos.write(tftellnum.getText() + " ");
						bos.write(user.id + " ");
						bos.write(tfpwd.getText() + " ");
						if(yes.isSelected() == true)
							bos.write("1\n");
						else if(no.isSelected() == true)
							bos.write("0\n");
						bos.close();
						// 유저 정보 변경
						user.name = inputname;
						user.phoneNumber = inputtelnum;
						user.pwd = inputpw;
						if(yes.isSelected() == true)
							user.license = 1;
						else if(no.isSelected() == true)
							user.license = 0;
						// 다음 단계로
						JOptionPane.showMessageDialog(null, "내 정보가 수정되었습니다.");
						MyPage m = new MyPage(userList, user);
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "회원가입 실패");
				}
			}
		});;
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				MyPage m = new MyPage(userList, user);
				setVisible(false); // 창 안보이게 하기 
			}
		});;
		
	}
  
    
}
