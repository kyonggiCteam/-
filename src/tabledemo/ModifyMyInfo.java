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
		
    	setTitle("�� ���� ����");
    	
    	getContentPane().setLayout(null);
    	
		
		Label l_name = new Label("�̸�");
		Label l_tellnum = new Label("��ȭ��ȣ");
		Label l_pwd = new Label("�н�����");
		Label l_pwdcheck = new Label("�н����� Ȯ��");
		Label l_license = new Label("���� ����");
		
		add(l_name);
		add(l_tellnum);
		add(l_pwd);
		add(l_pwdcheck);
		add(l_license);
		
		JButton btn_bck = new JButton("���ư���");
		add(btn_bck);
		
		JButton btn_mdf = new JButton("����");
		add(btn_mdf);
		
		TextField tfname = new TextField();
		TextField tftellnum = new TextField();
		TextField tfpwd = new TextField();
		TextField tfpwdcheck = new TextField();
		
		tfpwd.setEchoChar('*'); // �н�����
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
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				try {
					
					String inputname = tfname.getText();
					String inputtelnum = tftellnum.getText();
					String inputpw = tfpwd.getText();
					String inputpwcheck = tfpwdcheck.getText();

					if (inputname.equals("")||inputtelnum.equals("")||inputpw.equals("")) // ��� ĭ�� �Է����� �ʾ�����
						JOptionPane.showMessageDialog(null, "�Է��� ������ ��� �Է����ּ���.");
					else if (yes.isSelected() != true && no.isSelected() != true) // ���� ���� ���� ��������
						JOptionPane.showMessageDialog(null, "���� ���� ���θ� �������ּ���.");
					else if (!inputpw.equals(inputpwcheck)) // ��й�ȣ Ȯ�� ����ġ
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					else {
						// ������ ���Ͽ� �Է�
						BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
						/*
						��������Ʈ�� ������ �ͼ� �ε��� ���� ����� �̿��� ������ ã�Ƽ� �����κи� �����ϵ��� �޼ҵ带 ¥����
						��������Ʈ�� ���� �ʾ� �� ����� �������� ����
						����� ���ο� �����ͷ� ����Ǵ� �����
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
						// ���� ���� ����
						user.name = inputname;
						user.phoneNumber = inputtelnum;
						user.pwd = inputpw;
						if(yes.isSelected() == true)
							user.license = 1;
						else if(no.isSelected() == true)
							user.license = 0;
						// ���� �ܰ��
						JOptionPane.showMessageDialog(null, "�� ������ �����Ǿ����ϴ�.");
						MyPage m = new MyPage(userList, user);
						setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ȸ������ ����");
				}
			}
		});;
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				MyPage m = new MyPage(userList, user);
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;
		
	}
  
    
}
