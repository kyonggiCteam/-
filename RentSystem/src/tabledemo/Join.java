package tabledemo;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JRadioButton;

import rental.RentSystem;

public class Join extends JFrame {
   String id;
   int flag = 0;
   
   public Join() {
	  Container c = getContentPane();
	  c.setLayout(null);
	  c.setBackground(new Color(240, 248, 239));
	  
      JButton btn_bck = new JButton("�ڷΰ���");
      btn_bck.setBorderPainted(false);
      btn_bck.setBackground(new Color(153, 231,35));
      c.add(btn_bck);
      JButton btn_chk = new JButton("Ȯ��");
      btn_chk.setBorderPainted(false);
      btn_chk.setBackground(new Color(153, 231,35));
      c.add(btn_chk);
      
      Label[] label = { new Label("�̸�"), new Label("��ȭ��ȣ"), new Label("���̵�"),
    		  new Label("�н�����"), new Label("�н����� Ȯ��"), new Label("���� ����") };
      for(int i = 0; i < label.length; i++)
    	  c.add(label[i]);

      TextField[] text = {new TextField(), new TextField(), new TextField(), 
    		  new TextField(), new TextField() };
      for(int i = 0; i < text.length; i++)
    	  c.add(text[i]);
      
      JRadioButton yes = new JRadioButton("O");
      JRadioButton no = new JRadioButton("X");
      ButtonGroup bg = new ButtonGroup();
      yes.setBorderPainted(false);
      yes.setBackground(new Color(240, 248, 239));
      no.setBorderPainted(false);
      no.setBackground(new Color(240, 248, 239));
      
      bg.add(yes); add(yes);
      bg.add(no); add(no);
      text[3].setEchoChar('*');
      text[4].setEchoChar('*');
      JButton j1 = new JButton("����");
      j1.setBorderPainted(false);
      j1.setBackground(new Color(153, 231,35));
      c.add(j1);
      setSize(400, 400);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      label[0].setBounds(40, 20, 40, 40);
      label[1].setBounds(40, 60, 50, 40);
      label[2].setBounds(40, 100, 40, 40);
      label[3].setBounds(40, 140, 50, 40);
      label[4].setBounds(40, 180, 75, 40);
      label[5].setBounds(40, 220, 60, 40);
      text[0].setBounds(125, 20, 170, 30);
      text[1].setBounds(125, 60, 170, 30);
      text[2].setBounds(125, 100, 170, 30);
      text[3].setBounds(125, 140, 170, 30);
      text[4].setBounds(125, 180, 170, 30);
      yes.setBounds(125, 220, 50, 30);
      no.setBounds(180, 220, 50, 30);
      j1.setBounds(153, 270, 100, 40);
      btn_bck.setBounds(25, 320, 85, 30);
      btn_chk.setBounds(305, 100, 60, 28);

      setTitle("ȸ������");
      setVisible(true);

      btn_bck.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new LoginJoin();
            dispose();
         }
      });;
      
      btn_chk.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (RentSystem.userMgr.idDuplicate(text[2].getText())) {
               JOptionPane.showMessageDialog(c, "�̹� �����ϴ� ���̵��Դϴ�.");
            } else {
               flag = 1;
               JOptionPane.showMessageDialog(c, "����� �� �ִ� ���̵��Դϴ�.");
            }
         }
      });;
      
      j1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent T) {
            try {
               String name = text[0].getText();
               String phoneNumber = text[1].getText();
               id = text[2].getText();
               String pwd = text[3].getText();
               String pwdCheck = text[4].getText();
               String license = null;
               if(yes.isSelected())
                  license = "1";
               else if(no.isSelected())
                  license = "0";

               if (name.equals("")||phoneNumber.equals("")||id.equals("")||pwd.equals("")) // ��� ĭ�� �Է����� �ʾ�����
                  JOptionPane.showMessageDialog(null, "�Է��� ������ ��� �Է����ּ���.");
               else if (yes.isSelected() == false && no.isSelected() == false) // ���� ���� ���� ��������
                  JOptionPane.showMessageDialog(null, "���� ���� ���θ� �������ּ���.");
               else if (flag == 0) // ���̵� Ȯ�� ���ϰ� �����ϴ� �� ����
                  JOptionPane.showMessageDialog(null, "���̵� �ߺ� Ȯ���� ���ּ���.");
               else if (!pwd.equals(pwdCheck)) // ��й�ȣ Ȯ�� ����ġ
                  JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
               else {
                  String[] userInfo = {name, phoneNumber, id, pwd, license};
                  RentSystem.userMgr.join(userInfo);
                  
                  BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
                  bos.newLine();
                  bos.write(name + " ");
                  bos.write(phoneNumber + " ");
                  bos.write(id + " ");
                  bos.write(pwd + " ");
                  bos.write(license + " ");
                  bos.write("end");
                  bos.close();
                  JOptionPane.showMessageDialog(null, "ȸ������ ����");
                  new Login();
                  dispose();
               }
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "ȸ������ ����");
            }
         }
      });
   }

}