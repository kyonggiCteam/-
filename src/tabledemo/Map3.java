package tabledemo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map3 extends JPanel {
	JButton j1 = new JButton("A �����Ϸ����������");
	JButton j2 = new JButton("B ������ġ���ǿ�");
	JButton j3 = new JButton("C ���̽�����");
	JButton j4 = new JButton("D ����౹");
	JButton j5 = new JButton("E ��Ⱓȣ�п�");
	JButton j6 = new JButton("F �׸�����");
	JButton j7 = new JButton("G �����츮���ǿ�");
	JButton j8 = new JButton("H CU��������Ż����");
	JButton back = new JButton("�ڷΰ���");

	Image img = new ImageIcon(Main.class.getResource("../image/background.png")).getImage();

	Map3() {
		setLayout(null);
		setSize(700, 500);
		setVisible(true);
		// setBounds(������ġ, ������ġ, ���α���, ���α���);
		j1.setBounds(610, 10, 165, 30);
		add(j1);
		j2.setBounds(610, 45, 165, 30);
		add(j2);
		j3.setBounds(610, 80, 165, 30);
		add(j3);
		j4.setBounds(610, 115, 165, 30);
		add(j4);
		j5.setBounds(610, 150, 165, 30);
		add(j5);
		j6.setBounds(610, 185, 165, 30);
		add(j6);
		j7.setBounds(610, 220, 165, 30);
		add(j7);
		j8.setBounds(610, 255, 165, 30);
		add(j8);
		back.setBounds(10, 505, 100, 25);
		add(back);

		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		j8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				RentReturn rr = new RentReturn();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				Main m = new Main();
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}