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

public class Startup {

   public static void main(String args[]) {
      if (new File("db/calendar.db").exists())
         new Login();
      else
         new Intro();
   }
}