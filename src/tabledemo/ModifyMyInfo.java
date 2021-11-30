package tabledemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;



import rental.User;

public class ModifyMyInfo extends JFrame {

	
	public ModifyMyInfo(User user) {
		
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
		btn_bck.setBounds(350, 390, 100, 30);

		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
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
						BufferedReader bur = new BufferedReader(new FileReader("user.txt"));
						BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
						
						
						ArrayList <String[]> arrays = new ArrayList<String[]>(); 
						String str = null;
						while ((str = bur.readLine())!=null) {
							String[] strarr = str.split(" ");
							if(strarr.length>0) arrays.add(strarr);
							
							if (strarr[2].matches(user.id)) {
								strarr[0] = inputname;
								strarr[1] = inputtelnum;
								strarr[2] = user.id;
								strarr[3] = inputpw;
								if(yes.isSelected() == true)
									strarr[4] = "1";
								else if(no.isSelected() == true)
									strarr[4] = "0";
							}
						}
						
						
						new FileOutputStream("user.txt").close();
						
						for (int i=0; i < arrays.size(); i++) {
							String[] outputarr = arrays.get(i);
							for (int k=1; k < outputarr.length; k++) {
								outputarr[0] = outputarr[0] + " " + outputarr[k];
							}
							String oneLine = outputarr[0];
							if (i==arrays.size()-1) {
								bos.write(oneLine);
							}
							else {
								bos.write(oneLine + "\n");
							}
						}
						
						bur.close();
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
						MyPage m = new MyPage(user);
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "정보 수정에 실패했습니다.");
				}
			}
		});;
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				MyPage m = new MyPage(user);
				setVisible(false); // 창 안보이게 하기 
			}
		});;
		
	}
  
    
}