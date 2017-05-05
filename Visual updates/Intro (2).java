//Graphics

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.sound.sampled.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Intro extends JFrame implements ComponentListener {

   private JPasswordField textPassword, pass1, pass2;
   private JPanel mainMenu, newPassword;
   private JButton intro, newPass;
   public final static Color brenan = new Color( 57,255,20 );
   private JTextArea textField, textField1, textField2, textField3, textField4, textField5;
   private int wrong = 0;
   private boolean ask = false;
   public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private double height = (int) screenSize.getHeight(), width = (int) screenSize.getWidth();

   public Intro() {
      try {
         this.setIconImage(ImageIO.read(new File("res/ic_calendar.png")));
      } 
      catch (Exception e) {
      
      }
      setSize((int) width, (int) height);
      setLayout(null);
      intro();
      addComponentListener(this);
   }
   
   private void intro() 
   {
   
      newPassword = new JPanel();
      newPassword.setLocation((int)(width/2), (int)(height/2));
      newPassword.setSize((int)(width/2), (int)(height/2));
      newPassword.setVisible(true);
      pass1 = new JPasswordField(10);
      pass1.setToolTipText("Set Your Password");
      pass2 = new JPasswordField(10);
      pass2.setToolTipText("Verify Your Password");
      add(newPassword);
      newPassword.add(pass1);
      newPassword.add(pass2);
      textField5 = new JTextArea();
      textField5.setText("Make Sure Both Passwords Are The same!");
      textField5.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
      textField5.setForeground(Color.BLACK);
      textField5.setLocation((int)(width/1.5), (int)(height/1.1));
      textField5.setSize((int)(width/10), (int)(height/49));
      textField5.setOpaque(false);
      textField5.setEditable(false);
      textField5.setHighlighter(null);
      textField5.setVisible(false);
      newPassword.add(textField5);
      newPass = new JButton("Set Password");
      newPass.setToolTipText("Click This After You Enter Your Password!");
      newPass.setForeground(brenan);
      newPass.setBackground(Color.BLUE);
      newPass.setBorder(BorderFactory.createLineBorder(brenan,2));
      newPass.setFont(new Font("Rockwell", Font.BOLD, (int)(height/50)));
      newPass.setLocation((int)(width/1.5),(int)(height/1.46));
      newPass.setSize((int)(width/5.486),(int)(height/13.5));
      newPassword.add(newPass);
     
      newPass.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  String setPass = String.valueOf(pass1.getPassword());
                  String verifyPass = String.valueOf(pass2.getPassword());
                  if(setPass.equals(verifyPass))
                  {
                     ask = true;  
                  }
                  else
                  {
                     textField5.setVisible(true);
                  } 
               }
            });
      this.setVisible(true);     
     
    
      if (ask) {
         mainMenu = new JPanel();
         mainMenu.setLocation((int)(width/2.205), (int)(height/1.6));
         mainMenu.setSize((int)(width/12), (int)(height/18));
         add(mainMenu);
         textPassword = new JPasswordField(10);
         textPassword.setToolTipText("Enter Your Password Here!");
         mainMenu.add(textPassword);
         intro = new JButton("New Calendar");
         intro.setToolTipText("Click This After You Enter Your Password!");
         intro.setForeground(brenan);
         intro.setBackground(Color.BLUE);
         intro.setBorder(BorderFactory.createLineBorder(brenan,2));
         intro.setFont(new Font("Rockwell", Font.BOLD, (int)(height/50)));
         intro.setLocation((int)(width/2.477),(int)(height/1.46));
         intro.setSize((int)(width/5.486),(int)(height/13.5));
         add(intro);
         getRootPane().setDefaultButton(intro);
         
         textField1 = new JTextArea();
         textField1.setText("Welcome To The");
         textField1.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/11)));
         textField1.setForeground(Color.BLACK);
         textField1.setLocation((int)(width/3.2), (int)(height/20));
         textField1.setSize((int)(width/1.6), (int)(height/10));
         textField1.setOpaque(false);
         textField1.setEditable(false);
         textField1.setHighlighter(null);
         textField2 = new JTextArea();
         textField2.setText("PBL CALENDAR");
         textField2.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/7)));
         textField2.setForeground(Color.BLUE);
         textField2.setLocation((int)(width/5.654), (int)(height/5.9));
         textField2.setSize((int)(width/1.2), (int)(height/6));
         textField2.setOpaque(false);
         textField2.setEditable(false);
         textField2.setHighlighter(null);
         textField3 = new JTextArea();
         textField3.setText("CREATOR");
         textField3.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/9)));
         textField3.setForeground(Color.BLUE);
         textField3.setLocation((int)(width/2.954), (int)(height/2.9));
         textField3.setSize((int)(width/1.2), (int)(height/6));
         textField3.setOpaque(false);
         textField3.setEditable(false);
         textField3.setHighlighter(null);
         textField4 = new JTextArea();
         textField4.setText("Enter Password");
         textField4.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
         textField4.setForeground(Color.BLACK);
         textField4.setLocation((int)(width/2.7), (int)(height/1.1));
         textField4.setSize((int)(width/20), (int)(height/49));
         textField4.setOpaque(false);
         textField4.setEditable(false);
         textField4.setHighlighter(null);
         add(textField1);
         add(textField2);
         add(textField3);
         mainMenu.add(textField4);
      
         intro.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  String password = String.valueOf(textPassword.getPassword());
                  if(password.equals("t"))
                  {
                     launchVisual();  
                  } 
                  else {
                     wrong++;
                     
                     //if (wrong == 3)
                        //Visual.getReadyToRumble();
                  }
               }
            });
         this.setVisible(true);
      } 
   }
   
   private void changeSize() {
      if (ask) {
         textField1.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/11)));
         textField1.setLocation((int)(width/3.2), (int)(height/20));
         textField1.setSize((int)(width/1.6), (int)(height/10));
         textField2.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/7)));
         textField2.setLocation((int)(width/5.654), (int)(height/5.9));
         textField2.setSize((int)(width/1.2), (int)(height/6));
         textField3.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/9)));
         textField3.setLocation((int)(width/2.954), (int)(height/2.9));
         textField3.setSize((int)(width/1.2), (int)(height/6));
         textField4.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
         textField4.setLocation((int)(width/2.7), (int)(height/1.1));
         textField4.setSize((int)(width/20), (int)(height/49));
         mainMenu.setLocation((int)(width/2.205), (int)(height/1.6));
         mainMenu.setSize((int)(width/12), (int)(height/18));
         intro.setFont(new Font("Rockwell", Font.BOLD, (int)(height/50)));
         intro.setLocation((int)(width/2.477),(int)(height/1.46));
         intro.setSize((int)(width/5.486),(int)(height/13.5));
      }
   }
   
   public void componentResized(ComponentEvent e) {
      height = e.getComponent().getHeight();
      width = e.getComponent().getWidth();
      changeSize();
   }
   
   public void componentMoved(ComponentEvent e) {
      
   }
   
   public void componentShown(ComponentEvent e) {
    
   }
   
   public void componentHidden(ComponentEvent e) {
   
   }
   
   private void launchVisual() {
      new Visual();
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
   }
}
