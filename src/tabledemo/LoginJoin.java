package tabledemo;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJoin extends JFrame{

	public LoginJoin() {
		//JFrame frm = new JFrame("�α��� ȭ��");
		
		JPanel p = new JPanel();
        p.setLayout(null);
        
        setTitle("����ȭ��");
		setSize(350, 300);
		setResizable(false);
		// �������� ȭ�� ����� ��ġ
		setLocationRelativeTo(null);

		// �������� �ݾ��� �� �޸𸮿��� ���ŵǵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���̾ƿ� ����
		getContentPane().setLayout(null);

		// ��ư ����
		JButton login = new JButton("�α���");
		JButton join = new JButton("ȸ������");

		// �� ��ư ��ġ�� ũ�� ����
		login.setBounds(48, 100, 96, 63); 
		join.setBounds(192, 100, 96, 63);

		// �� �����ӿ��ٰ� ��ư �߰�
		getContentPane().add(login);
		getContentPane().add(join);

		// �α��� ��ư�� ��������
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				Login l = new Login();
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;

		// ȸ������ ��ư�� ��������
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				Join j = new Join();
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;

		// �������� ���̵��� ����
		setVisible(true);

		// �� ������ ������ ũ�� ǥ��
		System.out.println(getContentPane().getSize());
	}

}