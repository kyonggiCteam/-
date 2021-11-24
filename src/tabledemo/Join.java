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
		Label l1 = new Label("�̸�");
		Label l2 = new Label("��ȭ��ȣ");
		Label l3 = new Label("���̵�");
		Label l4 = new Label("�н�����");
		Label l5 = new Label("�н����� Ȯ��");
		Label l6 = new Label("���� ����");
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
		t4.setEchoChar('*'); // �н�����
		t5.setEchoChar('*'); // �н����� Ȯ��
		JButton j1 = new JButton("����");
		add(j1);
		setSize(400, 400);
		setResizable(false);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);
		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
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
		setTitle("ȸ������");
		setVisible(true);
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent T) {// ȸ������ ������ ����
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
					JOptionPane.showMessageDialog(null, "ȸ������ ����");
					Login l = new Login();
					setVisible(false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ȸ������ ����");
				}
			}
		});
	}

}
