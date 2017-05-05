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

public class Settings extends JFrame {

   private SettingsSetListener listener;
   private JCheckBox cardTime;
   private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private double height = (int) screenSize.getHeight() * .75, width = (int) screenSize.getWidth() * .75;

   public Settings(SettingsSetListener listener) {
      super("PBL Settings");
      this.setTitle("PBL Settings");
      setLayout(null);
      add(Box.createVerticalGlue());
      try {
         this.setIconImage(ImageIO.read(new File("res/ic_calendar.png")));
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      setSize((int) width, (int) height);
      settingsOptions();
      loadOptions();
      setCloseOp();
      this.listener = listener;
      this.setVisible(true);
   }
   
   private void loadOptions() {
      try {
         Scanner scan = new Scanner(new File("res/settings.txt"));
         cardTime.setSelected(Boolean.parseBoolean(scan.nextLine()));
      } 
      catch (Exception e) {
         System.out.println(e);
      }
   }
   
   private void settingsOptions() {
      cardTime = new JCheckBox("Show total card time");
      cardTime.setBounds(0, 0, (int) width, 100);
      cardTime.setHorizontalTextPosition(SwingConstants.LEFT);
      cardTime.setFont(new Font("TimesRoman", Font.BOLD, (int)(20)));
      add(cardTime);      
   }
   
   private void setCloseOp() {
      addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent we)
            { 
               try {
                  PrintWriter writer = new PrintWriter("res/settings.txt", "UTF-8");
                  writer.println(String.valueOf(cardTime.isSelected()));
                  writer.close();
               } 
               catch (Exception e) {
                  System.out.println(e);
               }
               listener.settingsSet();
            }
         });
   }
}
