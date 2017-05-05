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
   private boolean showTime;
   
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
      try {
            Scanner scan = new Scanner(new File("res/settings.txt"));
            showTime = Boolean.parseBoolean(scan.nextLine());
         } 
         catch (Exception e) {
            System.out.println(e);
         }
   }
   
   public void setItems(String current) {
      if (current.contains("- ")) {
         String total = "";
         list.removeAll();
         rows.clear();
         CardStorageDatabase db = new CardStorageDatabase("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/calendar.db");
         ArrayList<String> list = db.getList(current);
      
         for (String card : list) {
            SingleCardRow row = new SingleCardRow();
            Card data = new Card(card);
            total = calculateTotal(total, data.getTime());
            row.setData(data);
            this.list.add(row);
            rows.add(row);
         }
      
         cardDate.setText(current.split("-")[1]);
         
         if (showTime && list.size() > 0) {
            JLabel time = new JLabel();
            time.setText("Total time: " + total);
            time.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            this.list.add(time);
         }
      }
   }
   
   private String calculateTotal(String total, String next) {
      if (total.equals(""))
         return next;
      else {
         String[] current = total.split(" - ");
         String[] points = next.split(" - ");
         int less = Integer.parseInt(points[0]) + Integer.parseInt(current[0]);
         int more = Integer.parseInt(points[1]) + Integer.parseInt(current[1]);
         return less + " - " + more;
      }
   }
   
   public void setShowTime(boolean show) {
      showTime = show;
   }
}