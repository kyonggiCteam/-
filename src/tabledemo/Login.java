package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSystem;
import rental.User;
import rental.UserManager;

public class Login extends JFrame {
	
	//UserManager userMgr = new UserManager();
	// -> �̷��� �����ϸ� ������ �����Ͱ� ����Ǿ� �ִ� mList�� ������� ������ �ʳ�?
	
	//UserManager �ϴ� ��� �ȵ�.
//	UserManager userMgr = UserManager.getInstance();
	// �α��� �ϸ� User ã����.
	User user;
	public Login() {
		//JPanel p = new JPanel();
		Container c = getContentPane();
		c.setLayout(null);
		
		Label l1 = new Label("���̵�");
		Label l2 = new Label("��й�ȣ");
		add(l1);
		add(l2);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		add(t1);
		add(t2);
		t2.setEchoChar('*');
		JButton j1 = new JButton("�α���");
		//�ڷΰ��� ��ư �߰�
		JButton back = new JButton("�ڷΰ���");
		
		//back.setFont(new Font("�������", Font.BOLD, 9));
		
		j1.setBorderPainted(false); // ��ư �׵θ� ��������
		back.setBorderPainted(false); // ��ư �׵θ� ��������
		j1.setBackground(new Color(153, 231,35));
		back.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));
		setBackground(new Color(240, 248, 239));
		
		add(back);
		add(j1);
		setSize(350, 300);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(������ġ, ������ġ, ���α���, ���α���);
		l1.setBounds(30, 80, 40, 30);
		l2.setBounds(30, 120, 50, 30);
		t1.setBounds(120, 80, 100, 30);
		t2.setBounds(120, 120, 100, 30);
		j1.setBounds(240, 80, 80, 65);
		back.setBounds(15, 225, 100, 30);
		//add(p);
		setVisible(true);
	
		// �α���
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				String id = t1.getText();
				String pw = t2.getText();
				user = RentSystem.userMgr.find(id);
				
				if (user != null && user.passwordMatch(pw)) {
					JOptionPane.showMessageDialog(c, "�α��� ����");
					// user�� Ƽ�� ������ ������ 
					if(user.ticket != null)
						RentSystem.userMgr.updateDate(user);
					new MainMenu(user);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(c, "�ùٸ� ȸ�� ������ �ƴմϴ�. �ٽ� �Է����ּ���.");
			}
		});
		// �ڷΰ���
		back.addActionListener(new ActionListener() { // �ڷΰ���
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginJoin();
				dispose();
			}
		});
	}
}