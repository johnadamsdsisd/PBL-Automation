//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import java.io.*;
import java.util.ArrayList;

public class MiniCardVisual extends JPanel {
   
   private JLabel cardDate;
   private ArrayList<Card> cards;
   private ArrayList<SingleCardRow> rows;
   private JPanel list;
   
   public MiniCardVisual() {
      cards = new ArrayList<>();
      rows = new ArrayList<>();
      list = new JPanel();
      BoxLayout inner = new BoxLayout(list, BoxLayout.Y_AXIS);
      list.setLayout(inner);
      list.setOpaque(true);
      list.setBackground(Color.WHITE);
      cardDate = new JLabel();
      cardDate.setFont(new Font("Serif", Font.PLAIN, 12));
      setOpaque(true);
      BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
      this.setLayout(layout);
      this.setBackground(Color.WHITE);
      this.setToolTipText("Press the delete button to delete item");
      add(cardDate);
      add(list);
   }
   
   public void addItem(Card current) {
      if (current.getTitle().equals("DELETE") && rows.size() > 0)
         list.remove(rows.get(rows.size() - 1));
      else if (!current.getTitle().equals("DELETE") && !cards.contains(current)) {
         cards.add(current);
         SingleCardRow row = new SingleCardRow();
         row.setData(current);
         list.add(row);
         rows.add(row);
         cardDate.setText(String.valueOf(cards.get(0).getDay()));
      }
   }
   
   public void setCardBackground(boolean selected, JTable table) {
      Color color = selected ? table.getSelectionBackground() : table.getBackground();
      this.setBackground(color);
      list.setBackground(color);
         
      for (SingleCardRow row : rows)
         row.setBackgroundColor(color);
   }
}