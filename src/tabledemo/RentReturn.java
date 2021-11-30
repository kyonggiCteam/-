package tabledemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.RentSpot;
import rental.User;

public class RentReturn extends JFrame {
	User user;

	RentReturn(User user, RentSpot spot) {
		this.user = user;
		JPanel p = new JPanel();
		p.setLayout(null);

		setTitle("�뿩/�ݳ�");
		setSize(350, 300);
		setResizable(false);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);

		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���̾ƿ� ����
		getContentPane().setLayout(null);

		// ��ư ����
		JButton rent = new JButton("�뿩");
		JButton r_eturn = new JButton("�ݳ�");
		JButton back = new JButton("�ڷΰ���");

		// �� ��ư ��ġ�� ũ�� ����
		rent.setBounds(48, 70, 96, 100);
		r_eturn.setBounds(192, 70, 96, 100);
		back.setBounds(10, 230, 100, 25);

		// �� �����ӿ��ٰ� ��ư �߰�
		getContentPane().add(rent);
		getContentPane().add(r_eturn);
		getContentPane().add(back);

		// ������
		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				// �뿩 ������ Ȯ��
				if(user.vehicle != null)
					JOptionPane.showMessageDialog(rent, "���� �뿩���Դϴ�.");
				else {
					
				Rent r = new Rent(user, spot);
				setVisible(false); // â �Ⱥ��̰� �ϱ�
				}
			}
		});
		;

		// ���ư���
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {// ȸ������â���� �̵�
				// TODO Auto-generated method stub
				Map map = new Map(user);
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});
		;

		ActionListener listener1 = new ButtonListener();
		r_eturn.addActionListener(listener1);

		// �������� ���̵��� ����
		setVisible(true);
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ������ �ִ��� Ȯ���ؾ���..
			if(user.vehicle == null) {
				JOptionPane.showMessageDialog(getContentPane(), "�뿩�� ������/ű���尡 �����ϴ�.");
			}
			else {
			// ���������� �ݳ������ ��..
				
			user.vehicle = null;
			JOptionPane.showMessageDialog(null, "�ݳ��� �Ϸ�Ǿ����ϴ� \n\n �Ҹ��� Į�θ���___kcal�Դϴ�"); // �˾�â
			//JOptionPane.showMessageDialog(null, "�뿩�� �����Ű� �����ϴ�.");
			setVisible(false);
			new Table(user);
			}

		}
	}

}