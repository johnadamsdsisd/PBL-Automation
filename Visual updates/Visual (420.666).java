//Graphics
////////MOVED this.setVisible(true); and this.setSize(); out of CALENDAR METHOD calendar(); these commands were first called respectively.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.sound.sampled.*;
import java.util.*;
import java.io.*;
import java.awt.print.PrinterException;

public class Visual extends JFrame implements MouseListener, KeyListener {
   private CalendarCardDataModel model;
   private CardCell cell;
   private JLabel label;
   private CardVisual cardContainer;
   private JLabel cardInstr;
   private CardTable table;
   private CardsManager manager;
   int center = (int)(WIDTH * 0.82);
   private Font title = new Font("Serif", Font.BOLD, 40);
   private int x = new Random().nextInt(6), highLight = -1;
   private String hacker = "";
   private int yllwIndex = 0, redIndex = 0, blueIndex = 0, greenIndex = 0, purpleIndex = 0, blackIndex = 0, currentCard = -1, loop = 0;
   private boolean selected = false;
   private int[] points;
   private Card current;
   private AudioInputStream audioInputStream;
   private Clip clip3;
   private Clip clip;
   public final static Color hotpink = new Color( 255, 100, 180 );
   public final static Color brenan = new Color( 57,255,20 );
   public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private double H = (int)screenSize.getHeight(), W = (int)screenSize.getWidth();
   private int sliderValue;
   private JLabel hackLabel = new JLabel();
   private boolean stop = false;
   private JScrollPane pane;
   private JPanel panel;
   private JButton y;
   private JButton r; 
   private JButton g;
   private JButton p;
   private JButton b;
   private JButton bl;
   private JButton button;
   private JButton button1;
   private JButton button2;
   private JButton b1;
   private JButton b2;
   private JButton startStop;
   private String music = "Music.wav";

   public Visual() {
      super("PBL");
      this.setTitle("PBL Calendar");
      manager = new CardsManager();
      label = new JLabel();
      label.setHorizontalAlignment(SwingConstants.CENTER);
      this.setSize((int)(W/1.92),(int)(H/1.29));
      
      
      music();
      calendar();
      card();
      hack();
      
      ////////////////////////////////////////////////////////////////////////////////////////////////
      JPanel panel4 = new JPanel(new GridLayout(1,1,1,1));
      panel4.setSize((int)(W/3),(int)(H/5));
      panel4.setLocation((int)(W/3.5),(int)(H/2.561));
      add(panel4);
      
      JMenuBar menuBar = new JMenuBar();
      menuBar.setBackground(Color.WHITE);
   
    // File Menu, F - Mnemonic
      JMenu fileMenu = new JMenu("File");
      JMenu fileMenu1 = new JMenu("Options");
      fileMenu.setMnemonic(KeyEvent.VK_O);
      fileMenu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(fileMenu);
      menuBar.add(fileMenu1);
   
    // File->New, N - Mnemonic
      JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
      fileMenu.add(newMenuItem);
      
      JMenuItem newMenuItem1 = new JMenuItem("Save", KeyEvent.VK_S);
      fileMenu.add(newMenuItem1);
      
      JMenuItem newMenuItem2 = new JMenuItem("Help", KeyEvent.VK_H);
      fileMenu.add(newMenuItem2);
      
      JTextArea textField = new JTextArea();
      textField.setText("Welcome To The Program! This program will help you create a solid project for your students. " 
                        + "To start, select the yellow button (Required Cards). Scroll through until you find the project "
                        + "launch card. Select this card then click on the date you want to start the project. "
                        + "The card will now appear on your calendar. Now pick another card using one of the colored buttons. "
                        + "One you select a card, place it on the calendar after the launch date. Each colored button has a different "
                        + "group of project components you can add to your project. Continue adding cards until you are satified with your project. "
                        + "Once finished, make sure your project ends with presentation of some sort and a celbration (Both found in the required cards. "
                        + "You have now created a prject!!! Now that you are finished, you can print your calendar plan using the print button below the calendar. "
                        + "If you ever feel like listening to some relaxing or upbeat music during your project creation time, select the start music button. If you "
                        + "ever have an ad you don't wish to see, click on it and it will take 36 seconds to dissapear.");
      textField.setLineWrap(true);
      textField.setFont(new java.awt.Font("TIMES NEW ROMAN", Font.ITALIC | Font.BOLD, 15));
      textField.setForeground(Color.BLACK);
      textField.setBackground(Color.WHITE);
      textField.setVisible(false);
      textField.setColumns(20);
      textField.setEditable(false);
      textField.setWrapStyleWord(true);
      panel4.add(textField);
      JButton X = new JButton("X");
      X.setForeground(Color.RED);
      X.setLocation((int)(W/1.6725),(int)(H/2.76));
      X.setSize((int)W/45,(int)H/35);
      X.setVisible(false);
      add(X);
      
      newMenuItem2.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               textField.setVisible(true);
               panel.setVisible(false);
               pane.setVisible(false);
               X.setVisible(true);
               X.addActionListener(
                  new ActionListener() {
                     public void actionPerformed(ActionEvent ae) {
                        textField.setVisible(false);
                        panel.setVisible(true);
                        pane.setVisible(true);
                        X.setVisible(false);
                     }
                  });   
            }
         });
      
      menuBar.setOpaque(true);
      menuBar.setVisible(true);
      add(menuBar,BorderLayout.SOUTH);
      /////////////////////////////////////////////////////////////////////////////////////////////////
      
      
      
      button2 = new JButton("Print Calendar");
      button2.setFont(new Font("Rockwell", Font.BOLD, (int)(H/54)));
      button2.setBorder(BorderFactory.createLineBorder(Color.decode("#00BFFF"),2));
      button2.setForeground(Color.decode("#00BFFF"));
      button = new JButton("Next Card");
      button.setFont(new Font("TimesRoman", Font.BOLD, (int)(H/50)));
      button.setForeground(Color.BLACK);
      button1 = new JButton("Previous Card");
      button1.setFont(new Font("TimesRoman", Font.BOLD, (int)(H/50)));
      button1.setForeground(Color.BLACK);
      y = new JButton("Required Cards");
      y.setFont(new Font("Serif", Font.BOLD, (int)(H/64)));
      y.setForeground(Color.MAGENTA);
      r = new JButton("Content Workshops");
      r.setFont(new Font("Serif", Font.BOLD, (int)(H/70)));
      r.setForeground(Color.GREEN);
      g = new JButton("Expert Workshops");
      g.setFont(new Font("Serif", Font.BOLD, (int)(H/64)));
      g.setForeground(Color.RED);
      p = new JButton("Assessments");
      p.setFont(new Font("Serif", Font.BOLD, (int)(H/64)));
      p.setForeground(Color.YELLOW);
      b = new JButton("Emerging Workshops");
      b.setFont(new Font("Serif", Font.BOLD, (int)(H/77.5)));
      b.setForeground(Color.ORANGE);
      bl = new JButton("Project Time");
      bl.setFont(new Font("Serif", Font.BOLD, (int)(H/64)));
      bl.setForeground(Color.WHITE);
      setSize((int)W,(int)H);
      add(button);
      add(button1);
      add(button2);
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
      button2.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {
                  table.print();
               } 
               catch (PrinterException pe) {
                  System.err.println("Error printing: " + pe.getMessage());
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
      button.setLocation((int)(W/1.25),(int)(H/1.958));
      button.setSize((int)W/9,(int)H/21);
      button.setBackground(Color.white);
      button1.setLayout(null);
      button1.setLocation((int)(W/1.5),(int)(H/1.958));
      button1.setSize((int)W/9,(int)H/21);
      button1.setBackground(Color.white);
      
      button2.setLayout(null);
      button2.setLocation((int)(W/6.6),(int)(H/1.17));
      button2.setSize((int)(W/5.486),(int)(H/13.5));    
      button2.setBackground(Color.black);
      
      y.setBackground(Color.yellow);
      y.setLayout(null);
      y.setLocation((int)(W/1.45),(int)(H/1.636));
      y.setSize((int)W/10,(int)H/11);
      r.setBackground(Color.red);
      r.setLayout(null);
      r.setLocation((int)(W/1.266),(int)(H/1.636));
      r.setSize((int)W/10,(int)H/11);
      g.setBackground(Color.green);
      g.setLayout(null);
      g.setLocation((int)(W/1.45),(int)(H/1.421));
      g.setSize((int)W/10,(int)H/11);
      p.setBackground(Color.decode("#AA00FF"));
      p.setLayout(null);
      p.setLocation((int)(W/1.266),(int)(H/1.421));
      p.setSize((int)W/10,(int)H/11);
      b.setBackground(Color.blue);
      b.setLayout(null);
      b.setLocation((int)(W/1.45),(int)(H/1.256));
      b.setSize((int)W/10,(int)H/11);
      bl.setBackground(Color.black);
      bl.setLayout(null);
      bl.setLocation((int)(W/1.266),(int)(H/1.256));
      bl.setSize((int)W/10,(int)H/11);   
      addMouseListener(this);
      
      //this.setVisible(true);
   }
   
   public void mousePressed(MouseEvent me) { 
   
   }
   
   public void mouseExited(MouseEvent me) {
   
   }

   public void mouseEntered(MouseEvent me) {
   
   }
   
   public void mouseReleased(MouseEvent me) {
   
   }
   
   public void mouseClicked(MouseEvent me) {
      //System.out.println(me);
      //System.out.println(selected);
      if(((me.getXOnScreen() >= (int)(W/1.504) && me.getXOnScreen() <= (int)(W/1.0868)) && (me.getYOnScreen() >= (int)(H/15.6) && me.getYOnScreen() <= (int)(H/1.9)))) {
         highLight++;
         highLight();
      } 
      if (selected && me.getXOnScreen() < (int)(W/1.9) && me.getYOnScreen() > (int)(H/13.5) && me.getXOnScreen() > 0 && me.getYOnScreen() < (int)(H/1.301204819277108)) {
         table.setData(current, table.rowAtPoint(me.getPoint()), table.columnAtPoint(me.getPoint()));
         table.repaint();
         highLight++;
         highLight();
      } 
      else if (me.getXOnScreen() < (int)(W/1.9) && me.getYOnScreen() > (int)(H/13.5) && me.getXOnScreen() > 0 && me.getYOnScreen() < (int)(H/1.301204819277108))
         points = new int[]{table.rowAtPoint(me.getPoint()), table.columnAtPoint(me.getPoint())};
   }
   
   public void keyTyped(KeyEvent ke) {
      
   }

    /** Handle the key-pressed event from the cell. */
   public void keyPressed(KeyEvent ke) {
      if (points != null && String.valueOf(ke.getKeyChar()).equals("")) {
         table.setData(new Card(0, "DELETE", "DELETE", "DELETE", "DELETE", 0, 0, "DELETE"), points[0], points[1]);
         points = null;
         table.repaint();
      }
   }

   public void keyReleased(KeyEvent e) {
       
   }

   public static void main(String args[])throws IOException {
      new Visual();
   }

   public void paint(Graphics g) {
      super.paint(g);            
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
      if(highLight % 2 == 0) {
         selected = true;
         cardContainer.setBorder(BorderFactory.createLineBorder(Color.decode(current.getColor()), 10));
      } 
      else {
         selected = false;
         cardContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
      }
   }

   private void card() {
      cardContainer = new CardVisual(this, H, W);
      cardContainer.setLayout(null);
      cardContainer.setBounds((int)(W/1.514), (int)(H/27), (int)(W/3.918), (int)(H/2.16));
      cardContainer.setBackground(Color.WHITE);
      cardContainer.setOpaque(true);
      cardContainer.setVisible(false);
      add(cardContainer);
      cardInstr = new JLabel();
      cardInstr.setFont(new Font("Serif", Font.BOLD, (int)(H/27)));
      cardInstr.setText("Select a card type");
      cardInstr.setBounds((int)(W/1.4),(int)(H/1.792),(int)(W/4.8),(int)(H/21.6));
      cardInstr.setOpaque(true);
      add(cardInstr);
   }
   
   private void calendar() {
      this.setVisible(true);
      b1 = new JButton("<");
      b1.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.previousMonth();
            }
         });
   
      b2 = new JButton(">");
      b2.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.nextMonth();
            }
         });
   
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(b1, BorderLayout.WEST);
      panel.add(label,BorderLayout.CENTER);
      panel.add(b2,BorderLayout.EAST);
      
      cell = new CardCell();
      table = new CardTable(model = new CalendarCardDataModel(new String[]{"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}, label));
      table.addMouseListener(this);
      pane = new JScrollPane(table);
      //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.setRowHeight(table.getRowHeight() + 134);
   
      add(panel,BorderLayout.NORTH);
      add(pane,BorderLayout.CENTER);
      model.updateCalendar();
   }
   
   public void hack() {
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
      hack.setForeground(brenan);
      hack.setBackground(Color.BLACK);
      hack.setFont(new Font("Rockwell", Font.BOLD, (int)(H/63.529)));
      hack.setBorder(BorderFactory.createLineBorder(brenan,2));
      add(hack);
      
      JButton hackButton = new JButton();
      hackButton.setFont(new Font("Serif", Font.BOLD, (int)(H/20)));
      hackButton.setForeground(Color.RED);
      add(hackButton);
      
      hackButton.setBackground(Color.black);
      hackButton.setLayout(null);
      hackButton.setLocation(0,0);
      hackButton.setSize((int)W,(int)H);
      hackButton.setVisible(false);
      
      hack.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               try{
                  stop = false;
               
                  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sound.wav"));
                  clip = AudioSystem.getClip();
                  clip.open(audioInputStream);
                  clip.loop(Clip.LOOP_CONTINUOUSLY);
                  FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                  gainControl.setValue(-30);
                  clip.start();
                  panel.setVisible(false);
                  pane.setVisible(false);
                  y.setVisible(false);
                  r.setVisible(false);
                  g.setVisible(false);
                  p.setVisible(false);
                  b.setVisible(false);
                  bl.setVisible(false);
                  button.setVisible(false);
                  button1.setVisible(false);
                  button2.setVisible(false);
                  b1.setVisible(false);
                  b2.setVisible(false);
                  hack.setVisible(false);
                  startStop.setVisible(false);
                  cardInstr.setVisible(false);
                  hackButton.setVisible(true);
               
                  hackButton.addActionListener(
                        new ActionListener() {
                           public void actionPerformed(ActionEvent e) {
                              stop = true;
                              panel.setVisible(true);
                              pane.setVisible(true);
                              y.setVisible(true);
                              r.setVisible(true);
                              g.setVisible(true);
                              p.setVisible(true);
                              b.setVisible(true);
                              bl.setVisible(true);
                              button.setVisible(true);
                              button1.setVisible(true);
                              button2.setVisible(true);
                              b1.setVisible(true);
                              b2.setVisible(true);
                              hack.setVisible(true);
                              startStop.setVisible(true);
                              cardInstr.setVisible(true);
                              clip.stop();
                              hackButton.setVisible(false);
                           }
                        });
                   
                  Runnable thread1 = 
                        new Runnable()
                        {
                           public void run()
                           {
                              int time = 36;
                              int q = -30;
                              while(q<gainControl.getMaximum() && !stop){
                                 try {
                                    Thread.sleep(1000);
                                 } 
                                 catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                 }
                                 gainControl.setValue(q);
                                 q++;
                                 hackButton.setText(Integer.toString(time));
                                 time--;
                                 if(q>gainControl.getMaximum()){
                                    setVisible(false);
                                    getReadyToRumble();
                                 }
                              }
                              
                           }
                        };
                  new Thread(thread1).start();
                     
               }     
               
               catch (Exception f) {
                  System.err.println(f.getMessage());
               }
            }
         });
      hack.setLayout(null);
      hack.setLocation((int)(W/24),(int)(H/1.301));
      hack.setSize((int)(W/5.486),(int)(H/13.5));
   ////////////////////////////////////////////////////////
   }
   
   public void music() {
      startStop = new JButton("Start Music");
      startStop.setForeground(brenan);
      startStop.setBackground(Color.BLACK);
      startStop.setFont(new Font("Rockwell", Font.BOLD, (int)(H/54)));
      startStop.setBorder(BorderFactory.createLineBorder(brenan,2));
      add(startStop);
      JPanel panel3 = new JPanel(new GridLayout(1, 0, 1, 0));
      panel3.setSize((int)(W/3),(int)(H/20));                 
      panel3.setLocation((int)(W/2.9538),(int)(H/1.161));
      panel3.setOpaque(true);
      add(panel3);
      JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);  
      slider.setMinorTickSpacing(5);  
      slider.setMajorTickSpacing(1);  
      slider.setPaintTicks(true);  
      slider.setPaintLabels(true);
      slider.setFont(new Font("Rockwell", Font.BOLD, (int)(H/80)));
      slider.setOpaque(true);
      //slider.setVisible(true);
      panel3.add(slider);
      panel3.setVisible(false);
      JLabel volume = new JLabel();
      volume.setFont(new Font("Serif", Font.BOLD, (int)(H/50)));
      volume.setText("Volume");
      volume.setBounds((int)(W/2.06),(int)(H/1.12),(int)(W/4.8),(int)(H/21.6));
      volume.setOpaque(true);    
      add(volume);
      volume.setVisible(false);
            
      startStop.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               try{
                  if(loop % 2 == 0) {
                     if(loop % 4 == 0)
                        music = "Music1.wav";
                     else
                        music = "Music.wav";
                     slider.setVisible(true);
                     panel3.setVisible(true);
                     volume.setVisible(true);
                  
                     audioInputStream = AudioSystem.getAudioInputStream(new File(music));
                     clip3 = AudioSystem.getClip();
                     clip3.open(audioInputStream);
                     clip3.loop(Clip.LOOP_CONTINUOUSLY);
                     FloatControl gainControl = (FloatControl)clip3.getControl(FloatControl.Type.MASTER_GAIN);
                     gainControl.setValue(-20);
                     clip3.start();
                     slider.setValue(0);
                     gainControl.setValue(slider.getValue()-14);
                     slider.addChangeListener(
                        new ChangeListener() {
                           public void stateChanged(ChangeEvent e) {
                              sliderValue = ((JSlider)e.getSource()).getValue();
                              if(sliderValue-14>gainControl.getMaximum())
                                 gainControl.setValue(gainControl.getMaximum());
                              else
                                 gainControl.setValue(sliderValue-14);
                           }
                        });
                     
                     startStop.setForeground(Color.RED);
                     startStop.setBackground(Color.BLACK);
                     startStop.setBorder(BorderFactory.createLineBorder(Color.RED,2));
                     startStop.setText("Stop Music");
                     loop++;
                  }
                  else{
                     slider.setVisible(false);
                     panel3.setVisible(false);
                     volume.setVisible(false);
                     clip3.stop();
                     startStop.setText("Start Music");
                     startStop.setForeground(brenan);
                     startStop.setBackground(Color.BLACK);
                     startStop.setBorder(BorderFactory.createLineBorder(brenan,2));
                     loop++;
                  }
               } 
               catch (Exception f) {
                  System.err.println(f.getMessage());
               } 
            }
         });
      startStop.setLayout(null);
      startStop.setLocation((int)(W/3.69),(int)(H/1.3));
      startStop.setSize((int)(W/5.4857),(int)(H/13.5));
   }
   
   public void getReadyToRumble(){
      try {
         Thread.sleep(5000);
      } 
      catch(InterruptedException ex) {
         Thread.currentThread().interrupt();
      }
      UpdateCalendar update = new UpdateCalendar(); 
      try{
         update.Update();  
      }
      catch(IOException e){}
   }
}
