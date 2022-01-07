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
      JButton btn_rnt = new JButton("대여/반납", new ImageIcon("지도.png"));
      JButton btn_rpt = new JButton("고장신고", new ImageIcon("신고.png"));
      JButton btn_mpg = new JButton("마이페이지", new ImageIcon("인간.png"));
      JButton btn_lgt = new JButton("로그아웃");
      JLabel currentinfo = new JLabel();
      JLabel currentvehicle = new JLabel();

      JLabel b1 = new JLabel(new ImageIcon("자전거.png"));
      JLabel b2 = new JLabel(new ImageIcon("킥보드.png"));
      
      getContentPane().setBackground(new Color(240, 248, 239));
      btn_rnt.setBorderPainted(false);
      btn_rnt.setBackground(new Color(153, 231, 35));
      btn_rpt.setBorderPainted(false);
      btn_rpt.setBackground(new Color(153, 231, 35));
      btn_mpg.setBorderPainted(false);
      btn_mpg.setBackground(new Color(153, 231, 35));
      btn_lgt.setBorderPainted(false);
      btn_lgt.setBackground(new Color(153, 231, 35));
      btn_rnt.setFont(new Font("맑은고딕", Font.BOLD, 11));

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

      String a = user.name + "님 안녕하세요";
      String b = null;
      String c = null;
      if (user.vehicle != null) {
         b = "현재 대여중인 기구의 코드는 " + user.vehicle.code + " 입니다.";
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
      if (user.ticket != null && user.ticket.ticketType.equals("정기권"))
         c = "정기권 " + user.ticket.code + "은 " + user.leftday + "일 남았습니다.";
      else
         c = "";

      currentinfo.setText("<html>" + a + "<br>" + b + "<br>" + c + "</html>");
      currentinfo.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬

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
            String code = JOptionPane.showInputDialog(null, "고장난 물품의 코드를 입력해주세요.", "물품코드");
            
            if (code == null) {
                JOptionPane.showMessageDialog(null, "신고가 취소되었습니다.");
                return;
            }
            
            vehicle = RentSystem.vehicleMgr.find(code);
            
            if (vehicle == null) {
                JOptionPane.showMessageDialog(null, "존재하지 않는 코드번호입니다.");
                return;
            }
            
            int msgflag = RentSystem.vehicleMgr.breakdownReport(code);
            if (msgflag == 0)
		         JOptionPane.showMessageDialog(null, "신고가 접수되었습니다.");
		    else
		         JOptionPane.showMessageDialog(null, "이미 신고가 접수된 자전거입니다."); 
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