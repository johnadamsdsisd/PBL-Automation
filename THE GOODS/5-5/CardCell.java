//Card Object Class

import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.util.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.*;

public class CardCell extends DefaultTableCellRenderer {

   private MiniCardVisual[][] cards;
   private boolean showTotalTime = false;

   public CardCell() {
      cards = new MiniCardVisual[7][7];
      updateSettings();
   }
   
   public void updateCell(JTable table, Card data, int row, int column) {
      getTableCellRendererComponent(table, data, false, false, row, column);
   }
   
   /*public void setValues(Object value, int row, int col) {
      if (cards[row][col] == null) {
         MiniCardVisual mini = new MiniCardVisual();
         mini.setItems((String) value);
         mini.setSize(100, 150);
         cards[row][col] = mini;
      } 
      else
         cards[row][col].setItems((String) value);
   }*/
   
   public Component getTableCellRendererComponent(JTable table, Object data, boolean isSelected, boolean hasFocus, int row, int col) {
      if (data instanceof String) {
         if (cards[row][col] == null) {
            MiniCardVisual mini = new MiniCardVisual();
            mini.setItems((String) data);
            mini.setSize(100, 150);
            cards[row][col] = mini;
         } 
         else
            cards[row][col].setItems((String) data);
         
         cards[row][col].setBorder(BorderFactory.createLineBorder(hasFocus ? Color.decode("#4286f4") : Color.WHITE, 1)); 
         cards[row][col].setShowTime(showTotalTime);
         return cards[row][col];
      } 
      else 
         return null;
   }
   
   public void updateSettings() {
      try {
         Scanner scan = new Scanner(new File("res/settings.txt"));
         showTotalTime = Boolean.parseBoolean(scan.nextLine());
      } 
      catch (Exception e) {
         System.out.println(e);
      }
   }
}