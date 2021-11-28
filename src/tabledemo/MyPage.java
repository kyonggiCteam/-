package tabledemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.User;

public class MyPage extends JFrame {

	
	public MyPage(User user) {
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JLabel myInfo = new JLabel();
		
		JLabel title = new JLabel("My Page");
		JPanel bottom1 = new JPanel();
		JPanel bottom2 = new JPanel();	
		JPanel bottom3 = new JPanel();
		
		JButton btn_mdf = new JButton("ȸ������ ����");
		JButton btn_bck = new JButton("���ư���");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		String flaglcs = null;
		
		if (user.license == 1)
			flaglcs = "����";
		else if (user.license == 0)
			flaglcs = "����";
			
		
		String str1 = "���� ȸ������";
		String str2 = "- �̸�: " + user.name;
		String str3 = "- ���̵�: " + user.id;
		String str4 = "- ��ȭ��ȣ: " + user.phoneNumber;
		String str5 = "- ����: " + flaglcs;
		String str6 = "- ������ Ƽ�Ϲ�ȣ: " + user.ticket.code;
		String str7 = "- ���� ����Ʈ: " + user.point;
		
		setTitle("MyPage");
	    	
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		bottom.setLayout(new GridLayout(1, 3));
			
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(bottom, BorderLayout.PAGE_END);
		pane.add(myInfo, BorderLayout.CENTER);
		top.add(title);
		bottom.add(bottom1);
		bottom.add(bottom2);
		bottom.add(bottom3);
		
		bottom1.add(btn_bck);
		bottom2.add(btn_mdf);
		
		myInfo.setOpaque(true);
		myInfo.setBackground(new Color(0, 250, 0));
		
		myInfo.setHorizontalAlignment(JLabel.CENTER);
		myInfo.setText("<html>"+str1+"<br>"+str2+"<p>"+str3+"<p>"+str4+"<p>"+str5+"<p>"+str6+"<p>"+str7+"</html>"); // �ؽ�Ʈ �ٹٲ�
		
		
		
		setVisible(true);
		
		btn_mdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//�������� â���� �̵�.
				// TODO Auto-generated method stub
				ModifyMyInfo m = new ModifyMyInfo(user);
				setVisible(false); // â �Ⱥ��̰� �ϱ� 
			}
		});;
		
		btn_bck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//�޴��� �̵�
				// TODO Auto-generated method stub
				Table t = new Table(user);
				setVisible(false); // â �Ⱥ��̰� �ϱ�
			}
		});;
		    
		/*class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String buttonName = e.getActionCommand();
		           
				if (buttonName.equals("ȸ������ ����")) {
					ModifyMyInfo m = new ModifyMyInfo();
					m.run();
					setVisible(false);
				} else if (buttonName.equals("���ư���")) {
					Table t = new Table();
					setVisible(false);
				} 
			}
		}*/
	}
	
	
    
	
   
    
}