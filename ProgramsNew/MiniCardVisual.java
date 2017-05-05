//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import java.io.*;
import java.util.ArrayList;

public class MiniCardVisual extends JPanel {
   
   private JLabel cardTitle, cardTime;
   private ArrayList<Card> cards;
   private ArrayList<SingleCardRow> rows;
   
   public MiniCardVisual() {
      cards = new ArrayList<>();
      rows = new ArrayList<>();
      setOpaque(true);
      BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
      this.setLayout(layout);
      this.setBackground(Color.WHITE);
      this.setToolTipText("Press the delete button to delete item");
   }
   
   public void addItem(Card current) {
      if (current.getTitle().equals("DELETE"))
         remove(rows.get(rows.size() - 1));
      else if (!cards.contains(current)) {
         cards.add(current);
         SingleCardRow row = new SingleCardRow();
         row.setData(current);
         add(row);
         rows.add(row);
      }
   }
   
   public void setCardBackground(boolean selected, JTable table) {
      Color color = selected ? table.getSelectionBackground() : table.getBackground();
      this.setBackground(color);
         
      for (SingleCardRow row : rows)
         row.setBackgroundColor(color);
   }
}