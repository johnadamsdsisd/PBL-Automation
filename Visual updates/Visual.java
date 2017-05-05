//Graphics

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.*;
import java.io.*;
import java.applet.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//http://pekalicious.com/blog/custom-jpanel-cell-with-jbuttons-in-jtable/

public class Visual extends JFrame implements MouseListener {
   private CalendarCardDataModel model;
   private JLabel label;
   private CardVisual cardContainer;
   private JLabel cardInstr;
   private JTable table;
   private CardsManager manager;
   private Calendar now = Calendar.getInstance();
   private int year = now.get(Calendar.YEAR);
   private int month = now.get(Calendar.MONTH) + 1;
   private int day = now.get(Calendar.DAY_OF_MONTH);
   private int center = (int)(WIDTH * 0.82);
   private Font title = new Font("Serif", Font.BOLD, 40);
   private int x = new Random().nextInt(6), highLight = -1;
   private String hacker = "";
   private int yllwIndex = 0, redIndex = 0, blueIndex = 0, greenIndex = 0, purpleIndex = 0, blackIndex = 0, currentCard = -1, loop = 0;
   boolean selected = false;
   private Card current;
   AudioInputStream audioInputStream;
   Clip clip3;
   
   public static void main(String args[])throws IOException {
      new Visual();
   }
   
   public Visual() {
      super("PBL");
      manager = new CardsManager();
      label = new JLabel();
      label.setHorizontalAlignment(SwingConstants.CENTER);
            
      Calendar();
      CardContainer(); 
      Hack();
      Music();
      
      JButton button = new JButton("Next Card");
      button.setForeground(Color.BLACK);
      JButton button1 = new JButton("Previous Card");
      button1.setForeground(Color.BLACK);
      JButton y = new JButton("Required Cards");
      y.setForeground(Color.MAGENTA);
      JButton r = new JButton("Content Workshops");
      r.setForeground(Color.GREEN);
      JButton g = new JButton("Expert Workshops");
      g.setForeground(Color.RED);
      JButton p = new JButton("Assessments");
      p.setForeground(Color.YELLOW);
      JButton b = new JButton("Emerging Workshops");
      b.setForeground(Color.ORANGE);
      JButton bl = new JButton("Project Time");
      bl.setForeground(Color.WHITE);
      setSize(1515, 1000);
      add(button);
      add(button1);
      add(y);
      add(r);
      add(g);
      add(p);
      add(b);
      add(bl);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      button.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               switch(currentCard) {
                  case 1:
                     yllwIndex = (yllwIndex < (manager.getMax(CardsManager.YELLOW) - 1)) ? yllwIndex + 1 : 0;
                     updateCard();
                     break;
                  case 2:
                     redIndex = (redIndex < (manager.getMax(CardsManager.RED) - 1)) ? redIndex + 1 : 0;
                     updateCard();
                     break;
                  case 3:
                     greenIndex = (greenIndex < (manager.getMax(CardsManager.GREEN) - 1)) ? greenIndex + 1 : 0;
                     updateCard();
                     break;
                  case 4:
                     purpleIndex = (purpleIndex < (manager.getMax(CardsManager.PURPLE) - 1)) ? purpleIndex + 1 : 0;
                     updateCard();
                  case 5:
                     blueIndex = (blueIndex < (manager.getMax(CardsManager.BLUE) - 1)) ? blueIndex + 1 : 0;
                     updateCard();
                     break;
                  case 6:
                     blackIndex = (blackIndex < (manager.getMax(CardsManager.BLACK) - 1)) ? blackIndex + 1 : 0;
                     updateCard();
                     break;
               }
            }
         });
      button1.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               switch(currentCard) {
                  case 1:
                     yllwIndex = (yllwIndex > 0) ? yllwIndex - 1 : manager.getMax(CardsManager.YELLOW) - 1;
                     updateCard();
                     break;
                  case 2:
                     redIndex = (redIndex > 0) ? redIndex - 1 : manager.getMax(CardsManager.RED) - 1;
                     updateCard();
                     break;
                  case 3:
                     greenIndex = (greenIndex > 0) ? greenIndex - 1 : manager.getMax(CardsManager.GREEN) - 1;
                     updateCard();
                     break;
                  case 4:
                     purpleIndex = (purpleIndex > 0) ? purpleIndex - 1 : manager.getMax(CardsManager.PURPLE) - 1;
                     updateCard();
                  case 5:
                     blueIndex = (blueIndex > 0) ? blueIndex - 1 : manager.getMax(CardsManager.BLUE) - 1;
                     updateCard();
                     break;
                  case 6:
                     blackIndex = (blackIndex > 0) ? blackIndex - 1 : manager.getMax(CardsManager.BLACK);
                     updateCard();
                     break;
               }
            }
         });
      y.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               currentCard = 1;
               updateCard();
            }
         });
      r.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               currentCard = 2;
               updateCard();
            }
         });
      g.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               currentCard = 3;
               updateCard();
            }
         });
      p.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               currentCard = 4;
               updateCard();
            }
         });
      b.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               currentCard = 5;
               updateCard();
            }
         });
      bl.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               currentCard = 6;
               updateCard();
            }
         });
      button.setLayout(null);
      button.setLocation(1275,540);
      button.setSize(200,50);
      button.setBackground(Color.white);
      button1.setLayout(null);
      button1.setLocation(1005,540);
      button1.setSize(200,50);
      button1.setBackground(Color.white);
      y.setBackground(Color.yellow);
      y.setLayout(null);
      y.setLocation(1070,660);
      y.setSize(150,100);
      r.setBackground(Color.red);
      r.setLayout(null);
      r.setLocation(1220,660);
      r.setSize(150,100);
      g.setBackground(Color.green);
      g.setLayout(null);
      g.setLocation(1070,760);
      g.setSize(150,100);
      p.setBackground(Color.magenta);
      p.setLayout(null);
      p.setLocation(1220,760);
      p.setSize(150,100);
      b.setBackground(Color.blue);
      b.setLayout(null);
      b.setLocation(1070,860);
      b.setSize(150,100);
      bl.setBackground(Color.black);
      bl.setLayout(null);
      bl.setLocation(1220,860);
      bl.setSize(150,100);   
      addMouseListener(this);
   }
   
   public void mousePressed(MouseEvent me) { 
      //System.out.println(me);
      //System.out.println(selected);
      if(((me.getXOnScreen() >= 998 && me.getXOnScreen() <= 1498) && (me.getYOnScreen() >= 50 && me.getYOnScreen() <= 550))) {
         highLight++;
         highLight();
      }
   }
   
   public void mouseExited(MouseEvent me) {
   
   }

   public void mouseEntered(MouseEvent me) {
   
   }
   
   public void mouseReleased(MouseEvent me) {
   
   }
   
   public void mouseClicked(MouseEvent me) {
   
   }

   public void paint(Graphics g) {
      super.paint(g);   
           
      if (currentCard == 11) {
         while(true) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0 ,1500, 1000);
            try {
               Thread.sleep(100);
            } 
            catch(InterruptedException ex) {
               Thread.currentThread().interrupt();
            }
            g.setColor(Color.WHITE);
            g.fillRect(0,0,1500,1000);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g.drawString("YOU HAVE BEEN HACKED", 400, 300);
            try {
               Thread.sleep(100);
            } 
            catch(InterruptedException ex) {
               Thread.currentThread().interrupt();
            }
         }
      }
   }
   
   public void updateCard() {   
      switch(currentCard) {
         case 1:
            current = manager.getYellow(yllwIndex);
            break;
         case 2: 
            current = manager.getRed(redIndex);
            break;
         case 3:
            current = manager.getGreen(greenIndex);
            break;
         case 4:
            current = manager.getPurple(purpleIndex);
            break;
         case 5:
            current = manager.getBlue(blueIndex);
            break;
         case 6:
            current = manager.getBlack(blackIndex);
            break;
         default:
            break;              
      }
      
      if(highLight%2 == 0) {
         highLight++;
         highLight();
      }
      
      showCard();
   }
   
   private void showCard() {
      cardContainer.setVisible(true);
      if (current != null)
         cardContainer.setData(current);
   }
   
   private void highLight() {
      if(highLight%2 == 0)
         cardContainer.setBorder(BorderFactory.createLineBorder(Color.decode(current.getColor()), 10));
      else
         cardContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
   }
   
   public static void crashComputer() {
      while(true) {
         Thread thread = new Thread(
            new Runnable() {
               @Override
               public void run() {
                  while(true) {
                     crashComputer();
                  }
               }
            });
         thread.start();
      }
   }
   
   public void Calendar() {
      this.setSize(1000,837);
      this.setVisible(true);
      
      JButton b1 = new JButton("<");
      b1.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.previousMonth();
            }
         });
   
      JButton b2 = new JButton(">");
      b2.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.nextMonth();
            }
         });
   
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(b1, BorderLayout.WEST);
      panel.add(label,BorderLayout.CENTER);
      panel.add(b2,BorderLayout.EAST);
      
      table = new JTable(model = new CalendarCardDataModel(new String[]{"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}, label));
      table.setDefaultRenderer(MiniCardVisual.class, new CalendarCardRenderer());
      JScrollPane pane = new JScrollPane(table);
      //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.setRowHeight(table.getRowHeight()+134);
      
      add(panel,BorderLayout.NORTH);
      add(pane,BorderLayout.CENTER);
      model.updateCalendar();
   }
   
   public void Hack() {
   ////////////////////////////////////////////////////////
      switch(x) 
      {
         case 0:
            hacker = "CLICK HERE TO WIN THE LOTTERY";
            break;
         case 1:
            hacker = "CLICK HERE TO MEET TRUMP";
            break;
         case 2:
            hacker = "CLICK HERE FOR $1000000";
            break;
         case 3:
            hacker = "CLICK HERE FOR A FREE HOUSE";
            break;
         case 4:
            hacker = "CLICK HERE TO AUTO COMPLETE";
            break;
         case 5:
            hacker = "CLICK HERE FOR BRENAN";
      }
      JButton hack = new JButton(hacker);
      hack.setForeground(Color.ORANGE);
      hack.setBackground(Color.BLUE);
      add(hack);
      hack.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
              //crashComputer();
               currentCard = 11;
               try{
                  String soundName = "Sound.wav";    
                  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                  Clip clip = AudioSystem.getClip();
                  clip.open(audioInputStream);
                  clip.loop(300);
               } 
               catch (Exception f) {
                  System.err.println(f.getMessage());
               }
               repaint();
            }
         });
      hack.setLayout(null);
      hack.setLocation(100,810);
      hack.setSize(300,80);
   ////////////////////////////////////////////////////////
   }

   public void CardContainer() { 
      cardContainer = new CardVisual(this);
      cardContainer.setLayout(null);
      cardContainer.setBounds(998, 50, 490, 470);
      cardContainer.setBackground(Color.WHITE);
      cardContainer.setOpaque(true);
      cardContainer.setVisible(false);
      add(cardContainer);
      cardInstr = new JLabel();
      cardInstr.setFont(title);
      cardInstr.setText("Select a card type");
      cardInstr.setBounds(1070, 600, 400, 50);
      cardInstr.setOpaque(true);
      add(cardInstr);
   }
   
   public void Music() {
      JButton startStop = new JButton("Start Music");
      startStop.setForeground(Color.ORANGE);
      startStop.setBackground(Color.BLUE);
      add(startStop);
      startStop.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               try{
                  if(loop % 2 == 0) {
                     audioInputStream = AudioSystem
                        .getAudioInputStream(new File("Music.wav"));
                     clip3 = AudioSystem.getClip();
                     clip3.open(audioInputStream);
                     clip3.loop(Clip.LOOP_CONTINUOUSLY);
                     clip3.start();
                     loop++;
                     startStop.setText("Stop Music");
                  } 
                  else{
                     clip3.stop();
                     startStop.setText("Start Music");
                     loop++;
                  }
               } 
               catch (Exception f) {
                  System.err.println(f.getMessage());
               } 
            }
         });
      startStop.setLayout(null);
      startStop.setLocation(500,810);
      startStop.setSize(300,80);
   }

}
