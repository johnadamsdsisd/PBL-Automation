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

public class Login extends JFrame implements ComponentListener {

   private JPasswordField textPassword;
   private JPanel mainMenu;
   private JButton login;
   public final static Color brenan = new Color( 57,255,20 );
   private JTextArea textField, textField1, textField2, textField3, textField4;
   private int wrong = 0;
   private boolean ask = false;
   public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private double height = (int) screenSize.getHeight(), width = (int) screenSize.getWidth();

   public Login() {
      try {
         this.setIconImage(ImageIO.read(new File("res/ic_calendar.png")));
      } 
      catch (Exception e) {
      
      }
      setSize((int) width, (int) height);
      setLayout(null);
      login();
      addComponentListener(this);
   }
   
   private void login()
   {
      if (ask) {
         mainMenu = new JPanel();
         mainMenu.setLocation((int)(width/2.205), (int)(height/1.6));
         mainMenu.setSize((int)(width/12), (int)(height/18));
         add(mainMenu);
         textPassword = new JPasswordField(10);
         textPassword.setToolTipText("Enter Your Password Here!");
         mainMenu.add(textPassword);
         login = new JButton("Continue Calendar");
         login.setToolTipText("Click This After You Enter Your Password!");
         login.setForeground(brenan);
         login.setBackground(Color.BLUE);
         login.setBorder(BorderFactory.createLineBorder(brenan,2));
         login.setFont(new Font("Rockwell", Font.BOLD, (int)(height/50)));
         login.setLocation((int)(width/2.477),(int)(height/1.46));
         login.setSize((int)(width/5.486),(int)(height/13.5));
         add(login);
         getRootPane().setDefaultButton(login);
         
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
      
         login.addActionListener(
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
      else
         launchVisual();
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
         login.setFont(new Font("Rockwell", Font.BOLD, (int)(height/50)));
         login.setLocation((int)(width/2.477),(int)(height/1.46));
         login.setSize((int)(width/5.486),(int)(height/13.5));
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