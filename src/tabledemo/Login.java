package tabledemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.*;
import mgr.*;

public class Login extends JFrame {
	
	
	public Login(ArrayList<User> userList) {
		JPanel p = new JPanel();
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
		add(j1);
		JButton btn_bck = new JButton("back");
		add(btn_bck);
		setSize(350, 300);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1.setBounds(40, 50, 40, 30);
		l2.setBounds(40, 90, 50, 30);
		t1.setBounds(120, 50, 150, 30);
		t2.setBounds(120, 90, 150, 30);
		j1.setBounds(125, 200, 80, 30);
		btn_bck.setBounds(250, 220, 70, 30);
		add(p);
		setVisible(true);
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				LoginJoin l = new LoginJoin(userList);
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {// �α��� �Ҷ�
				String id = t1.getText();
				String pw = t2.getText();
				
				/*
				for (User finduser: userList) {
					if (id.equals(finduser.id) && pw.equals(finduser.pwd))
						member = finduser;
					else
						member = null;
				}
				
				if (member != null) {
					JOptionPane.showMessageDialog(null, member.name+"�� �ݰ����ϴ�.");
					
				}
				else
					JOptionPane.showMessageDialog(null, "�ùٸ� ȸ�� ������ �ƴմϴ�. �ٽ� �Է����ּ���.");
				*/
				
				User member = new User();
				Vehicle userbike = new Vehicle();
				Ticket userticket = new Ticket();
				
				member.id = "moon442";
				member.pwd = "4321";
				member.name = "����ȣ";
				member.license = 1;
				member.phoneNumber = "01027271010";
				member.point = 0;
				member.vehicle = userbike;
				member.ticket = userticket;
				
				userbike.code = "BI0101";
				userticket.code = "V01";
				
				JOptionPane.showMessageDialog(null, member.name+"�� �ݰ����ϴ�.");
				Table t = new Table(userList, member);
				setVisible(false);
				}
			
		});

	}
}