//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CardVisual extends JPanel {
   
   private JTextArea cardDesc;
   private JLabel cardTitle, cardTime, cardColor;
   
   public CardVisual(MouseListener listener) {
      cardDesc = new JTextArea();
      cardDesc.setBounds(20, 160, 460, 250);
      cardDesc.setLineWrap(true);
      cardDesc.setWrapStyleWord(true);
      cardDesc.setBackground(Color.decode("#00ffffff"));
      cardDesc.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      cardDesc.setEditable(false);
      cardDesc.addMouseListener(listener);
      add(cardDesc);
      cardTitle = new JLabel();
      cardTitle.setBounds(10, 30, 470, 30);
      cardTitle.setHorizontalAlignment(SwingConstants.CENTER);
      cardTitle.setOpaque(true);
      cardTitle.setFont(new Font("TimesRoman", Font.BOLD, 25));
      cardTitle.setBackground(Color.decode("#00ffffff"));
      add(cardTitle);
      cardTime = new JLabel();
      cardTime.setBounds(40, 420, 50, 20);
      cardTime.setBackground(Color.decode("#00ffffff"));
      cardTime.setFont(new Font("TimesRoman", Font.PLAIN, 14));
      cardTime.setOpaque(true);
      add(cardTime);
      cardColor = new JLabel();
      cardColor.setOpaque(true);
      cardColor.setBounds(0, 80, 490, 50);
      cardColor.setBackground(getBackground());
      add(cardColor);
   }
   
   public void setData(Card current) {
      cardTitle.setText(current.getTitle());
      cardColor.setBackground(Color.decode(current.getColor()));
      cardDesc.setText(current.getDescription());
      cardTime.setText(current.getMin() + "-" + current.getMax());
   }
}