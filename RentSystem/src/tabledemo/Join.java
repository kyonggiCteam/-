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
	  
      JButton btn_bck = new JButton("뒤로가기");
      btn_bck.setBorderPainted(false);
      btn_bck.setBackground(new Color(153, 231,35));
      c.add(btn_bck);
      JButton btn_chk = new JButton("확인");
      btn_chk.setBorderPainted(false);
      btn_chk.setBackground(new Color(153, 231,35));
      c.add(btn_chk);
      
      Label[] label = { new Label("이름"), new Label("전화번호"), new Label("아이디"),
    		  new Label("패스워드"), new Label("패스워드 확인"), new Label("면허 유무") };
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
      JButton j1 = new JButton("가입");
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

      setTitle("회원가입");
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
               JOptionPane.showMessageDialog(c, "이미 존재하는 아이디입니다.");
            } else {
               flag = 1;
               JOptionPane.showMessageDialog(c, "사용할 수 있는 아이디입니다.");
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

               if (name.equals("")||phoneNumber.equals("")||id.equals("")||pwd.equals("")) // 모든 칸을 입력하지 않았을때
                  JOptionPane.showMessageDialog(null, "입력할 내용을 모두 입력해주세요.");
               else if (yes.isSelected() == false && no.isSelected() == false) // 면허 여부 선택 안했을때
                  JOptionPane.showMessageDialog(null, "면허 보유 여부를 선택해주세요.");
               else if (flag == 0) // 아이디 확인 안하고 가입하는 것 방지
                  JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.");
               else if (!pwd.equals(pwdCheck)) // 비밀번호 확인 불일치
                  JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
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
                  JOptionPane.showMessageDialog(null, "회원가입 성공");
                  new Login();
                  dispose();
               }
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "회원가입 실패");
            }
         }
      });
   }

}