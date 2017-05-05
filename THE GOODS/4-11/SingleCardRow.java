//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import java.io.*;

public class SingleCardRow extends JPanel {
   
   private JLabel cardTitle, cardTime;
   
   public SingleCardRow() {
      setOpaque(true);
      this.setBackground(Color.WHITE);
      BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
      this.add(Box.createVerticalGlue());
      this.setLayout(layout);
      cardTitle = new JLabel();
      cardTitle.setOpaque(true);
      cardTitle.setFont(new Font("TimesRoman", Font.BOLD, 10));
      cardTitle.setBackground(Color.WHITE);
      add(cardTitle);
      cardTime = new JLabel();
      cardTime.setBackground(Color.WHITE);
      cardTime.setFont(new Font("TimesRoman", Font.PLAIN, 8));
      cardTime.setOpaque(true);
      add(cardTime);
   }
   
   public void setData(Card current) {
      cardTitle.setText(current.getTitle().equals("") ? " " + current.getType() + " " : " " + current.getTitle() + " ");
      cardTime.setText(current.getMin() + "-" + current.getMax());
   }
   
   public void setBackgroundColor(Color color) {
      this.setBackground(color);
      cardTitle.setBackground(color);
      cardTime.setBackground(color);
   }
}