package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Container;
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

	// UserManager userMgr = new UserManager();
	// -> �̷��� �����ϸ� ������ �����Ͱ� ����Ǿ� �ִ� mList�� ������� ������ �ʳ�?

	// UserManager �ϴ� ��� �ȵ�.
//	UserManager userMgr = UserManager.getInstance();
	// �α��� �ϸ� User ã����.
	User user;

	public Login() {
		// JPanel p = new JPanel();
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
		// �ڷΰ��� ��ư �߰�
		JButton back = new JButton("Back");
		add(back);
		back.setBounds(20, 200, 65, 30);
		add(j1);
		setSize(350, 300);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ũ��, ��ġ ����.
		l1.setBounds(40, 50, 40, 30);
		l2.setBounds(40, 90, 50, 30);
		t1.setBounds(120, 50, 150, 30);
		t2.setBounds(120, 90, 150, 30);
		j1.setBounds(125, 200, 80, 30);
		// add(p);
		setVisible(true);

		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {// �α��� �Ҷ�
				String id = t1.getText();
				String pw = t2.getText();
				user = RentSystem.userMgr.find(id);

				if (user != null && user.passwordMatch(pw)) {
					JOptionPane.showMessageDialog(c, "�α��� ����");
					// ���� ȭ������ �Ѿ�� �� �߰�..
					new Table(user);
					setVisible(false);
				} else
					JOptionPane.showMessageDialog(c, "�ùٸ� ȸ�� ������ �ƴմϴ�. �ٽ� �Է����ּ���.");
			}
		});
		back.addActionListener(new ActionListener() { // �ڷΰ���
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginJoin();
				setVisible(false);
			}
		});
	}
}