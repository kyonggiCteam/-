package tabledemo;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Table extends JFrame{
	Table() {
		JPanel top = new JPanel();
	    JPanel middle = new JPanel();
	    JPanel bottom = new JPanel();
	    JButton j1 = new JButton("�뿩");
		JButton j2 = new JButton("����Ű�");
		JButton j3 = new JButton("����");
	    
		/*
		 * JPanel p = new JPanel(); p.setLayout(null);
		 */
		top.setLayout(null);
		middle.setLayout(null);
		bottom.setLayout(null);
		
		add(j1);
		add(j2);
		add(j3);
		setSize(500, 500);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(top);
		add(middle);
		add(bottom);
		
		setTitle("�뿩");
		setVisible(true);
		
		//setBounds(������ġ, ������ġ, ���α���, ���α���);
		j1.setBounds(31, 150, 100, 33);
		j2.setBounds(193, 150, 100, 33);
		j3.setBounds(355, 150, 100, 33);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				Map m = new Map();
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;
	}
}
