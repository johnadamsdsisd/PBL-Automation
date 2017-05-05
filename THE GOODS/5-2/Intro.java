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

public class Intro extends JFrame {

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
   }
   
   private void intro() { 
      this.setVisible(true);
   }
   
   private void changeSize() {
      
   }
}
