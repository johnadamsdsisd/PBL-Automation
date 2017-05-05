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
   private ArrayList<SingleCardRow> rows;
   private JPanel list;
   
   public MiniCardVisual() {
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
   
   public void setItems(String current) {
      if (current.contains("- ")) {
         list.removeAll();
         rows.clear();
         CardStorageDatabase db = new CardStorageDatabase("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/calendar.db");
         ArrayList<String> list = db.getList(current);
      
         for (String card : list) {
            SingleCardRow row = new SingleCardRow();
            row.setData(new Card(card));
            this.list.add(row);
            rows.add(row);
         }
      
         cardDate.setText(current.split("-")[1]);
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