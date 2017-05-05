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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.print.PrinterException;

public class Visual extends JFrame implements MouseListener, KeyListener, ComponentListener {
   private CalendarCardDataModel model;
   private CardCell cell;
   private JLabel label;
   private JPanel calContainer, panel4;
   private CardVisual cardContainer;
   private JLabel cardInstr;
   private JMenuBar menuBar;
   private JTextArea textField;
   private JButton r, g, b, y, p, bl, nextCard, button1, b1, b2, hack, startStop, prev, next, X;
   private CardTable table;
   private JScrollPane pane;
   private CardsManager manager;
   int center = (int)(WIDTH * 0.82);
   private Font title = new Font("Serif", Font.BOLD, 40);
   private int x = new Random().nextInt(6), highLight = -1, sliderValue;
   private String hacker = "", music = "";
   private int yllwIndex = 0, redIndex = 0, blueIndex = 0, greenIndex = 0, purpleIndex = 0, blackIndex = 0, currentCard = -1, loop = 0;
   private boolean selected = false, stop = false, changeSize = false;
   private int[] points;
   private Card current;
   private AudioInputStream audioInputStream, audioInputStream1;
   private Clip clip3;
   private Clip clip;
   private Clip clip2;
   public final static Color hotpink = new Color( 255, 100, 180 );
   public final static Color brenan = new Color( 57,255,20 );
   public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private double height = (int) screenSize.getHeight(), width = (int) screenSize.getWidth();

   public Visual() {
      super("PBL");
      this.setTitle("PBL Calendar");
      try {
         this.setIconImage(ImageIO.read(new File("res/ic_calendar.png")));
      } 
      catch (Exception e) {
      
      }
      manager = new CardsManager();
      
      calendar();
      card();
      hack();
      music();
      menu();
      
      nextCard = new JButton("Next Card");
      nextCard.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
      nextCard.setForeground(Color.BLACK);
      button1 = new JButton("Previous Card");
      button1.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
      button1.setForeground(Color.BLACK);
      y = new JButton("Required Cards");
      y.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      y.setForeground(Color.MAGENTA);
      r = new JButton("Content Workshops");
      r.setFont(new Font("Serif", Font.BOLD, (int)(height/70)));
      r.setForeground(Color.GREEN);
      g = new JButton("Expert Workshops");
      g.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      g.setForeground(Color.RED);
      p = new JButton("Assessments");
      p.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      p.setForeground(Color.YELLOW);
      b = new JButton("Emerging Workshops");
      b.setFont(new Font("Serif", Font.BOLD, (int)(height/77.5)));
      b.setForeground(Color.ORANGE);
      bl = new JButton("Project Time");
      bl.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      bl.setForeground(Color.WHITE);
      setSize((int)width,(int)height);
      add(nextCard);
      add(button1);
      add(y);
      add(r);
      add(g);
      add(p);
      add(b);
      add(bl);
      setLayout(null);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      nextCard.addActionListener(
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
      nextCard.setLayout(null);
      nextCard.setBackground(Color.white);
      button1.setLayout(null);
      button1.setBackground(Color.white);
      
      y.setBackground(Color.yellow);
      y.setLayout(null);
      r.setBackground(Color.red);
      r.setLayout(null);
      g.setBackground(Color.green);
      g.setLayout(null);
      p.setBackground(Color.decode("#AA00FF"));
      p.setLayout(null);
      b.setBackground(Color.blue);
      b.setLayout(null);
      bl.setBackground(Color.black);
      bl.setLayout(null);   
      addMouseListener(this);
      table.addKeyListener(this);
      addComponentListener(this);
      addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent we)
            { 
               String ObjButtons[] = {"Yes", "No"};
               int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","PBL Calendar: Close App?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);               
               if(PromptResult==JOptionPane.YES_OPTION)
               {
                  System.exit(0);
               }
            }
         });
      login(true);
   }
   
   private void login(boolean ask)
   {
      if (ask) {
         JPanel mainMenu = new JPanel();
         mainMenu.setLocation((int)(width/2.3), (int)(height/2.6));
         mainMenu.setSize((int)(width/15), (int)(height/5));
         add(mainMenu);
         JPasswordField textPassword = new JPasswordField(10);
         mainMenu.add(textPassword);
         JButton login = new JButton("Login");
         mainMenu.add(login);
      
         login.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  String password = String.valueOf(textPassword.getPassword());
                  if(password.equals("TylerIsTrash"))
                  {
                     mainMenu.setVisible(false);
                     calContainer.setVisible(true);
                     y.setVisible(true);
                     r.setVisible(true);
                     g.setVisible(true);
                     p.setVisible(true);
                     b.setVisible(true);
                     bl.setVisible(true);
                     nextCard.setVisible(true);
                     button1.setVisible(true);
                     prev.setVisible(true);
                     next.setVisible(true);
                     hack.setVisible(true);
                     startStop.setVisible(true);
                     cardInstr.setVisible(true);
                     menuBar.setVisible(true);  
                  }
               }
            
            });
         calContainer.setVisible(false);
         y.setVisible(false);
         r.setVisible(false);
         g.setVisible(false);
         p.setVisible(false);
         b.setVisible(false);
         bl.setVisible(false);
         prev.setVisible(false);
         nextCard.setVisible(false);
         button1.setVisible(false);
         next.setVisible(false);
         hack.setVisible(false);
         startStop.setVisible(false);
         cardInstr.setVisible(false);
         menuBar.setVisible(false);
      }
      this.setVisible(true);
   }
   
   public void mousePressed(MouseEvent me) { 
      if(((me.getXOnScreen() >= (int)(width/1.504) && me.getXOnScreen() <= (int)(width/1.0868)) && (me.getYOnScreen() >= (int)(height/15.6) && me.getYOnScreen() <= (int)(height/1.9)))) {
         highLight++;
         highLight();
      } 
      if (selected && me.getXOnScreen() < (int)(width/1.9) && me.getYOnScreen() > (int)(height/13.5) && me.getXOnScreen() > 0 && me.getYOnScreen() < (int)(height/1.301204819277108)) {
         int row = table.rowAtPoint(me.getPoint());
         int col = table.columnAtPoint(me.getPoint());
         if (model.checkPoints(row, col)) {
            table.setData(current, row, col);
            table.repaint();
            highLight++;
            highLight();
         }
      } 
      else if (me.getXOnScreen() < (int)(width/1.9) && me.getYOnScreen() > (int)(height/13.5) && me.getXOnScreen() > 0 && me.getYOnScreen() < (int)(height/1.301204819277108))
         points = new int[]{table.rowAtPoint(me.getPoint()), table.columnAtPoint(me.getPoint())};
   }
   
   public void mouseExited(MouseEvent me) {
   
   }

   public void mouseEntered(MouseEvent me) {
   
   }
   
   public void mouseReleased(MouseEvent me) {
    
   }
   
   public void mouseClicked(MouseEvent me) {
      
   }
   
   public void keyTyped(KeyEvent ke) {
      
   }

    /** heightandle the key-pressed event from the cell. */
   public void keyPressed(KeyEvent ke) {
      if (points != null && String.valueOf(ke.getKeyChar()).equals("")) {
         //table.setData(new Card(0, "DELETE", "DELETE", "DELETE", "DELETE", 0, 0, "DELETE"), points[0], points[1]);
         table.delete(points[0], points[1]);
         table.repaint();
      }
   }

   public void keyReleased(KeyEvent e) {
       
   }
   
   public void componentResized(ComponentEvent e) {
      if (changeSize) {
         double h = e.getComponent().getHeight();
         double w = e.getComponent().getWidth();
      
         height = h;
         width = w;
      
      
         changeSize();
      //System.out.println(e.getComponent().getHeight() + " " + e.getComponent().getWidth()) ;
      //   changeSize(e.getComponent().getHeight(), e.getComponent().getWidth());
      } 
      else
         changeSize = true;
   }
   
   public void componentMoved(ComponentEvent e) {
      
   }
   
   public void componentShown(ComponentEvent e) {
      
   }
   
   public void componentHidden(ComponentEvent e) {
   
   }

   public static void main(String args[])throws IOException {
      new Visual();
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
   
   private void changeSize() {
      //Set bounds and font for card and color menu:
      y.setLocation((int)(width/1.45),(int)(height/1.636));
      y.setSize((int)width/10,(int)height/11);
      r.setLocation((int)(width/1.266),(int)(height/1.636));
      r.setSize((int)width/10,(int)height/11);
      g.setLocation((int)(width/1.45),(int)(height/1.420));
      g.setSize((int)width/10,(int)height/11);
      p.setLocation((int)(width/1.266),(int)(height/1.420));
      p.setSize((int)width/10,(int)height/11);
      b.setLocation((int)(width/1.45),(int)(height/1.256));
      b.setSize((int)width/10,(int)height/11);
      bl.setLocation((int)(width/1.266),(int)(height/1.256));
      bl.setSize((int)width/10,(int)height/11);
      y.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      r.setFont(new Font("Serif", Font.BOLD, (int)(height/70)));
      g.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      p.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      b.setFont(new Font("Serif", Font.BOLD, (int)(height/77.5)));
      bl.setFont(new Font("Serif", Font.BOLD, (int)(height/64)));
      label.setBounds((int) (width / 32), 0, (int) ((width/1.9) - (width / 32) - (width / 32)), (int) (height / 53));
      prev.setBounds(0, 0, (int) (width / 32), (int) (height / 53));
      next.setBounds((int) ((width/1.9) - (width / 32)), 0, (int) (width / 32), (int) (height / 53));
      cardContainer.setBounds((int)(width/1.514), (int)(height/27), (int)(width/3.918), (int)(height/2.16));
      cardContainer.setSize((int) width, (int) height); //Method to set the content of the card to correct size
      cardInstr.setBounds((int)(width/1.4),(int)(height/1.792),(int)(width/4.8),(int)(height/21.6));
      cardInstr.setFont(new Font("Serif", Font.BOLD, (int)(height/27)));
      cardInstr.setFont(new Font("Serif", Font.BOLD, (int)(height/27)));
      nextCard.setLocation((int)(width/1.25),(int)(height/1.958));
      nextCard.setSize((int)width/9,(int)height/21);
      nextCard.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
      button1.setFont(new Font("TimesRoman", Font.BOLD, (int)(height/50)));
      button1.setLocation((int)(width/1.5),(int)(height/1.958));
      button1.setSize((int)width/9,(int)height/21);
      
      //Set bounds and font for music and hack:
      startStop.setLocation((int)(width/3.69),(int)(height/1.26));
      startStop.setSize((int)(width/5.4857),(int)(height/13.5));
      startStop.setFont(new Font("Rockwell", Font.BOLD, (int)(height/54)));
      hack.setFont(new Font("Rockwell", Font.BOLD, (int)(height/63.529)));
      hack.setLocation((int)(width/24),(int)(height/1.26));
      hack.setSize((int)(width/5.486),(int)(height/13.5));
      hack.setFont(new Font("Rockwell", Font.BOLD, (int)(height/63.529)));
     
      //Set bounds and heights for calendar and menu:
      calContainer.setBounds(0, (int) height / 40, (int) (width/1.9), (int)(height/1.31204819277108));
      table.setRowHeight((int) (height / 8));
      pane.setBounds(0, (int) (height / 53), (int) (width / 1.9), (int) ((height/1.301204819277108) - (height / 20)));
      menuBar.setBounds(0, 0, (int) width, (int) height / 40);
      
      //Set bounds for random junk
      panel4.setSize((int)(width/3),(int)(height/5));
      panel4.setLocation((int)(width/3.5),(int)(height/2.561));
      textField.setFont(new java.awt.Font("TIMES NEW ROMAN", Font.ITALIC | Font.BOLD, (int)(height/77.14)));
      X.setLocation((int)(width/1.6725),(int)(height/2.76));
      X.setSize((int)width/45,(int)height/35);
   }

   private void card() {
      cardContainer = new CardVisual(this, height, width);
      cardContainer.setLayout(null);
      cardContainer.setBounds((int)(width/1.514), (int)(height/27), (int)(width/3.918), (int)(height/2.16));
      cardContainer.setBackground(Color.WHITE);
      cardContainer.setOpaque(true);
      cardContainer.setVisible(false);
      add(cardContainer);
      cardInstr = new JLabel();
      cardInstr.setFont(new Font("Serif", Font.BOLD, (int)(height/27)));
      cardInstr.setText("Select a card type");
      cardInstr.setOpaque(true);
      add(cardInstr);
   }
   
   private void calendar() {      
      prev = new JButton("<");
      prev.setBounds(0, 0, (int) (width / 32), (int) (height / 53));
      prev.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.previousMonth();
            }
         });
   
      next = new JButton(">");
      next.setBounds((int) ((width/1.9) - (width / 32)), 0, (int) (width / 32), (int) (height / 53));
      next.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               model.nextMonth();
            }
         });
         
      label = new JLabel();
      label.setBounds((int) (width / 32), 0, (int) ((width/1.9) - (width / 32) - (width / 32)), (int) (height / 53));
      label.setHorizontalAlignment(SwingConstants.CENTER);
   
      calContainer = new JPanel();
      calContainer.setBounds(0, (int) height / 30, (int) (width/1.9), (int)(height/1.301204819277108));
      calContainer.setLayout(null);
      calContainer.add(prev);
      calContainer.add(label);
      calContainer.add(next);
      
      cell = new CardCell();
      table = new CardTable(model = new CalendarCardDataModel(new String[]{"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}, label));
      model.setTable(table);
      table.addMouseListener(this);
      table.setRowSelectionAllowed(false);
      pane = new JScrollPane(table);
      calContainer.add(pane);
      add(calContainer);
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
      hack = new JButton(hacker);
      hack.setForeground(brenan);
      hack.setBackground(Color.BLACK);
      hack.setFont(new Font("Rockwell", Font.BOLD, (int)(height/63.529)));
      hack.setBorder(BorderFactory.createLineBorder(brenan,2));
      add(hack);
      
      JButton hackButton = new JButton();
      hackButton.setFont(new Font("Serif", Font.BOLD, (int)(height/20)));
      hackButton.setForeground(Color.RED);
      add(hackButton);
      
      hackButton.setBackground(Color.black);
      hackButton.setLayout(null);
      hackButton.setLocation(0,0);
      hackButton.setSize((int)width,(int)height);
      hackButton.setVisible(false);
      
      hack.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               try {
                  stop = false;
               
                  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Sound.wav"));
                  clip = AudioSystem.getClip();
                  clip.open(audioInputStream);
                  clip.loop(Clip.LOOP_CONTINUOUSLY);
                  FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                  gainControl.setValue(-30);
                  clip.start();
                  calContainer.setVisible(false);
                  y.setVisible(false);
                  r.setVisible(false);
                  g.setVisible(false);
                  p.setVisible(false);
                  b.setVisible(false);
                  prev.setVisible(false);
                  nextCard.setVisible(false);
                  button1.setVisible(false);
                  next.setVisible(false);
                  hack.setVisible(false);
                  startStop.setVisible(false);
                  cardInstr.setVisible(false);
                  hackButton.setVisible(true);
               
                  hackButton.addActionListener(
                        new ActionListener() {
                           public void actionPerformed(ActionEvent e) {
                              stop = true;
                              calContainer.setVisible(true);
                              y.setVisible(true);
                              r.setVisible(true);
                              g.setVisible(true);
                              p.setVisible(true);
                              b.setVisible(true);
                              nextCard.setVisible(true);
                              button1.setVisible(true);
                              prev.setVisible(true);
                              next.setVisible(true);
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
               catch (LineUnavailableException | UnsupportedAudioFileException | IOException f) {
                  System.err.println("error here");
                  System.err.println(f.getMessage());
               }
            }
         });
      hack.setLayout(null);
   ////////////////////////////////////////////////////////
   }
   
   public void music() {
      startStop = new JButton("Start Music");
      startStop.setForeground(brenan);
      startStop.setBackground(Color.BLACK);
      startStop.setBorder(BorderFactory.createLineBorder(brenan,2));
      add(startStop);
      JPanel panel3 = new JPanel(new GridLayout(1, 0, 1, 0));
      panel3.setSize((int)(width/3),(int)(height/20));                 
      panel3.setLocation((int)(width/2.9538),(int)(height/1.14));
      panel3.setOpaque(true);
      add(panel3);
      JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);  
      slider.setMinorTickSpacing(5);  
      slider.setMajorTickSpacing(1);  
      slider.setPaintTicks(true);  
      slider.setPaintLabels(true);
      slider.setFont(new Font("Rockwell", Font.BOLD, (int)(height/80)));
      slider.setOpaque(true);
      //slider.setVisible(true);
      panel3.add(slider);
      panel3.setVisible(false);
      JLabel volume = new JLabel();
      volume.setFont(new Font("Serif", Font.BOLD, (int)(height/50)));
      volume.setText("Volume");
      volume.setBounds((int)(width/2.1),(int)(height/1.2),(int)(width/4.8),(int)(height/21.6));
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
                        music = "res/Music1.wav";
                     else
                        music = "res/Music.wav";
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
      startStop.setLocation((int)(width/3.69),(int)(height/1.3));
      startStop.setSize((int)(width/5.4857),(int)(height/13.5));
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
   
   private void menu() {
      panel4 = new JPanel(new GridLayout(1,1,1,1));
      panel4.setSize((int)(width/3),(int)(height/5));
      panel4.setLocation((int)(width/3.5),(int)(height/2.561));
      add(panel4);
      
      menuBar = new JMenuBar();
      menuBar.setBackground(Color.WHITE);
   
      // File Menu, F - Mnemonic
      JMenu fileMenu = new JMenu("File");
      JMenu optionsMenu = new JMenu("Options");
      optionsMenu.setMnemonic(KeyEvent.VK_O);
      fileMenu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(fileMenu);
      menuBar.add(optionsMenu);
      
      final JMenuItem disableAdsItem = new JMenuItem("Disable Ads", KeyEvent.VK_D);
      optionsMenu.add(disableAdsItem);
      
      disableAdsItem.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               hack.setVisible(!hack.isVisible());
               disableAdsItem.setText(hack.isVisible() ? "Disable Ads" : "Enable Ads");
               if(hack.isVisible() == false)
               { 
                  try{
                     audioInputStream1 = AudioSystem.getAudioInputStream(new File("res/Music2.wav"));
                     clip2 = AudioSystem.getClip();
                     clip2.open(audioInputStream1);
                     clip2.loop(Clip.LOOP_CONTINUOUSLY);
                     FloatControl gainControl1 = (FloatControl)clip2.getControl(FloatControl.Type.MASTER_GAIN);
                     gainControl1.setValue(gainControl1.getMaximum()); 
                  }
                  catch (Exception f) {
                     System.err.println(f.getMessage());
                  }
               }
               else
               {
                  clip2.stop();
               }
            
            }
         });
         
      JMenuItem disableMusicItem = new JMenuItem("Disable Music", KeyEvent.VK_D);
      optionsMenu.add(disableMusicItem);
      
      disableMusicItem.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               startStop.setVisible(!startStop.isVisible());
               disableMusicItem.setText(startStop.isVisible() ? "Disable Music" : "Enable Music");
            }
         });
   
    // File->Clear Calendar, C - Mnemonic
      JMenuItem clearCalendar = new JMenuItem("Clear Calendar", KeyEvent.VK_C);
      fileMenu.add(clearCalendar);
      
      clearCalendar.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            { 
               table.clearData();
               table.repaint();
            }
         });
      
      JMenuItem newMenuItem1 = new JMenuItem("Save", KeyEvent.VK_S);
      fileMenu.add(newMenuItem1);
      
      JMenuItem newMenuItem2 = new JMenuItem("Help", KeyEvent.VK_H);
      fileMenu.add(newMenuItem2);
      
      JMenuItem newMenuItem7 = new JMenuItem("Print", KeyEvent.VK_P);
      fileMenu.add(newMenuItem7);
      
      newMenuItem7.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               try {
                  table.print();
               } 
               catch (PrinterException pe) {
                  System.err.println("Error printing: " + pe.getMessage());
               }
            }
         });
      
      textField = new JTextArea();
      textField.setText("Welcome To The Program! This program will help you create a solid project for your students. " 
                        + "To start, select the yellow button (Required Cards). Scroll through until you find the project "
                        + "launch card. Select this card then click on the date you want to start the project. "
                        + "The card will now appear on your calendar. Now pick another card using one of the colored buttons. "
                        + "One you select a card, place it on the calendar after the launch date. If you wish to remove a card, click on the cell which contains the project "
                        + "element you wish to delete and hit the delete key on the keyboard. Each colored button has a different "
                        + "group of project components you can add to your project. Continue adding cards until you are satified with your project. "
                        + "Once finished, make sure your project ends with presentation of some sort and a celbration (Both found in the required cards. "
                        + "You have now created a prject!!! You can print your project plan by clicking file print. "
                        + "If you ever feel like listening to some relaxing or upbeat music during your project creation time, select the start music button. If you "
                        + "ever have an ad you don't wish to see, click on it and it will take 36 seconds to dissapear or pay us some money then click disable ads!");
      textField.setLineWrap(true);
      textField.setFont(new java.awt.Font("TIMES NEW ROMAN", Font.ITALIC | Font.BOLD, 14));
      textField.setForeground(Color.BLACK);
      textField.setBackground(Color.WHITE);
      //textField.setVisible(false);
      textField.setColumns(20);
      textField.setEditable(false);
      textField.setWrapStyleWord(true);
      panel4.setVisible(false);
      panel4.add(textField);
      X = new JButton("X");
      X.setForeground(Color.RED);
      X.setLocation((int)(width/1.6725),(int)(height/2.76));
      X.setSize((int)width/45,(int)height/35);
      X.setVisible(false);
      add(X);
      
      newMenuItem2.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               panel4.setVisible(true);
               calContainer.setVisible(false);
               X.setVisible(true);
               X.addActionListener(
                  new ActionListener() {
                     public void actionPerformed(ActionEvent ae) {
                        panel4.setVisible(false);
                        calContainer.setVisible(true);
                        X.setVisible(false);
                     }
                  });   
            }
         });
      
      menuBar.setOpaque(true);
      menuBar.setVisible(true);
      add(menuBar);
      /////////////////////////////////////////////////////////////////////////////////////////////////
   }
}
