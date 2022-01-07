package tabledemo;


import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJoin extends JFrame{
	public LoginJoin() {
        setTitle("메인화면");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입");
		
		login.setBorderPainted(false);
		join.setBorderPainted(false);
		login.setBackground(new Color(153, 231,35));
		join.setBackground(new Color(153, 231,35));
		c.setBackground(new Color(240, 248, 239));

		login.setBounds(48, 100, 96, 63); 
		join.setBounds(192, 100, 96, 63);

		c.add(login);
		c.add(join);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Login();
				dispose();
			}
		});

		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Join j = new Join();
				dispose();
			}
		});
		
		setVisible(true);
	}
}