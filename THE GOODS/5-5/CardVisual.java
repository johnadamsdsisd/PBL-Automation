//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CardVisual extends JPanel implements MouseListener {
   
   private JTextArea cardDesc;
   private JLabel cardTitle, cardTime, cardColor;
   private CardClickListener clickListener;
   public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
   public CardVisual(CardClickListener listener, double H, double W) {
      cardDesc = new JTextArea();
      cardDesc.setBounds((int)(W/96),(int)(H/7.715), (int)(W/4.1739),(int)(H/3.6));
      cardDesc.setLineWrap(true);
      cardDesc.setWrapStyleWord(true);
      cardDesc.setBackground(Color.decode("#00ffffff"));
      cardDesc.setFont(new Font("TimesRoman", Font.PLAIN, (int)(H/54)));
      cardDesc.setEditable(false);
      cardDesc.setHighlighter(null);
      add(cardDesc);
      cardTitle = new JLabel();
      cardTitle.setBounds((int)(W/192),(int)(H/36), (int)(W/4.085),(int)(H/36));
      cardTitle.setHorizontalAlignment(SwingConstants.CENTER);
      cardTitle.setOpaque(true);
      cardTitle.setFont(new Font("TimesRoman", Font.BOLD, (int)(H/48)));
      cardTitle.setBackground(Color.decode("#00ffffff"));
      add(cardTitle);
      cardTime = new JLabel();
      cardTime.setBounds((int)(W/48),(int)(H/2.3), (int)(W/38.4),(int)(H/96));
      cardTime.setBackground(Color.decode("#00ffffff"));
      cardTime.setFont(new Font("TimesRoman", Font.PLAIN, (int)(H/77.143)));
      cardTime.setOpaque(true);
      add(cardTime);
      cardColor = new JLabel();
      cardColor.setOpaque(true);
      cardColor.setBounds(0,(int)(H/13.5), (int)(W/3.918),(int)(H/21.6));
      cardColor.setBackground(getBackground());
      add(cardColor);
      clickListener = listener;
      addMouseListener(this);
      cardDesc.addMouseListener(this);
   }
   
   public void resize(int W, int H) {
      cardDesc.setBounds((int)(W/96),(int)(H/7.715), (int)(W/4.1739),(int)(H/3.6));
      cardDesc.setFont(new Font("TimesRoman", Font.PLAIN, (int)(H/54)));
      cardTitle.setBounds((int)(W/192),(int)(H/36), (int)(W/4.085),(int)(H/36));
      cardTitle.setFont(new Font("TimesRoman", Font.BOLD, (int)(H/48)));
      cardTime.setBounds((int)(W/48),(int)(H/2.3), (int)(W/38.4),(int)(H/96));
      cardTime.setFont(new Font("TimesRoman", Font.PLAIN, (int)(H/77.143)));
      cardColor.setBounds(0,(int)(H/13.5), (int)(W/3.918),(int)(H/21.6));
   }
   
   public void setData(Card current) {
      cardTitle.setText(current.getTitle());
      cardColor.setBackground(Color.decode(current.getColor()));
      cardDesc.setText(current.getDescription());
      cardTime.setText(current.getMin() + "-" + current.getMax());
   }
   
   public void mousePressed(MouseEvent me) { 
      clickListener.onCardClicked();
   }
   
   public void mouseExited(MouseEvent me) {
   
   }

   public void mouseEntered(MouseEvent me) {
   
   }
   
   public void mouseReleased(MouseEvent me) {
    
   }
   
   public void mouseClicked(MouseEvent me) {
      
   }
}