package tabledemo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
		setSize(350, 350);
		setLocationRelativeTo(null);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(240, 248, 239));
    	setTitle("내 정보 수정");
    	
    	getContentPane().setLayout(null);
    	
		Label l_name = new Label("이름");
		Label l_tellnum = new Label("전화번호");
		Label l_pwd = new Label("패스워드");
		Label l_pwdcheck = new Label("패스워드 확인");
		Label l_license = new Label("면허 유무");
		
		c.add(l_name);
		c.add(l_tellnum);
		c.add(l_pwd);
		c.add(l_pwdcheck);
		c.add(l_license);
		
		JButton btn_bck = new JButton("뒤로가기");
		
		btn_bck.setBorderPainted(false);
	    btn_bck.setBackground(new Color(153, 231,35));
		
	    c.add(btn_bck);
		
		JButton btn_mdf = new JButton("수정");
		
		btn_mdf.setBorderPainted(false);
	    btn_mdf.setBackground(new Color(153, 231,35));
		
	    c.add(btn_mdf);
		
		TextField tfname = new TextField();
		TextField tftellnum = new TextField();
		TextField tfpwd = new TextField();
		TextField tfpwdcheck = new TextField();
		
		tfpwd.setEchoChar('*');
	    tfpwdcheck.setEchoChar('*');
		
	    c.add(tfname);
	    c.add(tftellnum);
	    c.add(tfpwd);
	    c.add(tfpwdcheck);
		
		JRadioButton yes = new JRadioButton("O");
		JRadioButton no = new JRadioButton("X");
		ButtonGroup bg = new ButtonGroup();
		
		yes.setBorderPainted(false);
	    yes.setBackground(new Color(240, 248, 239));
	    no.setBorderPainted(false);
	    no.setBackground(new Color(240, 248, 239));
		
		bg.add(yes); c.add(yes);
	    bg.add(no); c.add(no);
	    
		l_name.setBounds(40, 20, 40, 40);
		l_tellnum.setBounds(40, 60, 50, 40);
		l_pwd.setBounds(40, 100, 50, 40);
		l_pwdcheck.setBounds(40, 140, 75, 40);
		l_license.setBounds(40, 180, 75, 40);

		tfname.setBounds(125, 20, 170, 30);
		tftellnum.setBounds(125, 60, 170, 30);
		tfpwd.setBounds(125, 100, 170, 30);
		tfpwdcheck.setBounds(125, 140, 170, 30);

		yes.setBounds(125, 180, 50, 30);
		no.setBounds(180, 180, 50, 30);
		btn_mdf.setBounds(120, 220, 100, 40);
		btn_bck.setBounds(25, 270, 85, 30);

		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			@Override
			public void actionPerformed(ActionEvent e) {
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
						
						user.name = inputname;
						user.phoneNumber = inputtelnum;
						user.pwd = inputpw;
						
						if(yes.isSelected() == true)
							user.license = 1;
						else if(no.isSelected() == true)
							user.license = 0;
						
						JOptionPane.showMessageDialog(null, "내 정보가 수정되었습니다.");
						new MyPage(user);
						dispose();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "정보 수정에 실패했습니다.");
				}
			}
		});
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MyPage(user);
				dispose();
			}
		});
	}
}