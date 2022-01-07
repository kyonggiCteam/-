package tabledemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import rental.RentSystem;
import rental.User;
import rental.Vehicle;

public class MainMenu extends JFrame {

   Vehicle vehicle;
   Image img_b, img_k;
   User user;

   public MainMenu(User user) {
      JButton btn_rnt = new JButton("�뿩/�ݳ�", new ImageIcon("����.png"));
      JButton btn_rpt = new JButton("����Ű�", new ImageIcon("�Ű�.png"));
      JButton btn_mpg = new JButton("����������", new ImageIcon("�ΰ�.png"));
      JButton btn_lgt = new JButton("�α׾ƿ�");
      JLabel currentinfo = new JLabel();
      JLabel currentvehicle = new JLabel();

      JLabel b1 = new JLabel(new ImageIcon("������.png"));
      JLabel b2 = new JLabel(new ImageIcon("ű����.png"));
      
      getContentPane().setBackground(new Color(240, 248, 239));
      btn_rnt.setBorderPainted(false);
      btn_rnt.setBackground(new Color(153, 231, 35));
      btn_rpt.setBorderPainted(false);
      btn_rpt.setBackground(new Color(153, 231, 35));
      btn_mpg.setBorderPainted(false);
      btn_mpg.setBackground(new Color(153, 231, 35));
      btn_lgt.setBorderPainted(false);
      btn_lgt.setBackground(new Color(153, 231, 35));
      btn_rnt.setFont(new Font("�������", Font.BOLD, 11));

      add(btn_rnt);
      add(btn_rpt);
      add(btn_mpg);
      add(btn_lgt);
      add(currentinfo);
      add(currentvehicle);
      setSize(500, 500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setTitle("System Main Display");

      btn_rnt.setBounds(20, 140, 140, 50);
      btn_rpt.setBounds(170, 140, 140, 50);
      btn_mpg.setBounds(320, 140, 150, 50);
      btn_lgt.setBounds(10, 420, 100, 33);
      currentinfo.setBounds(40, 210, 400, 170);
      currentvehicle.setBounds(190, 20, 100, 100);

      b1.setBounds(0, 0, 100, 100);
      b2.setBounds(0, 0, 100, 100);

      String a = user.name + "�� �ȳ��ϼ���";
      String b = null;
      String c = null;
      if (user.vehicle != null) {
         b = "���� �뿩���� �ⱸ�� �ڵ�� " + user.vehicle.code + " �Դϴ�.";
         if (user.vehicle.code.charAt(0) == 'B') {
            add(b1);
            currentvehicle.add(b1);
         }
         else if (user.vehicle.code.charAt(0) == 'S') {
            add(b2);
            currentvehicle.add(b2);
         }
      } else
         b = "";
      if (user.ticket != null && user.ticket.ticketType.equals("�����"))
         c = "����� " + user.ticket.code + "�� " + user.leftday + "�� ���ҽ��ϴ�.";
      else
         c = "";

      currentinfo.setText("<html>" + a + "<br>" + b + "<br>" + c + "</html>");
      currentinfo.setHorizontalAlignment(JLabel.CENTER); // �ؽ�Ʈ ��� ����

      currentinfo.setOpaque(true);
      currentinfo.setBackground(new Color(255, 255, 255));
      
      currentvehicle.setOpaque(false);

      setVisible(true);

      btn_rnt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new Map(user);
            dispose();
         }
      });
      
      btn_rpt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String code = JOptionPane.showInputDialog(null, "���峭 ��ǰ�� �ڵ带 �Է����ּ���.", "��ǰ�ڵ�");
            
            if (code == null) {
                JOptionPane.showMessageDialog(null, "�Ű� ��ҵǾ����ϴ�.");
                return;
            }
            
            vehicle = RentSystem.vehicleMgr.find(code);
            
            if (vehicle == null) {
                JOptionPane.showMessageDialog(null, "�������� �ʴ� �ڵ��ȣ�Դϴ�.");
                return;
            }
            
            int msgflag = RentSystem.vehicleMgr.breakdownReport(code);
            if (msgflag == 0)
		         JOptionPane.showMessageDialog(null, "�Ű� �����Ǿ����ϴ�.");
		    else
		         JOptionPane.showMessageDialog(null, "�̹� �Ű� ������ �������Դϴ�."); 
         }
      });

      btn_mpg.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new MyPage(user);
            dispose();
         }
      });

      btn_lgt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new LoginJoin();
            dispose();
         }
      });
   }
}