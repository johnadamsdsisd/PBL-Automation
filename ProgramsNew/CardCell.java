//Card Object Class

import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CardCell extends DefaultTableCellRenderer {

   private MiniCardVisual[][] cards;

   public CardCell() {
      cards = new MiniCardVisual[7][7];
   }
   
   public void updateCell(JTable table, Card data, int row, int column) {
      getTableCellRendererComponent(table, data, false, false, row, column);
   }
   
   public void setValues(Object value, int row, int col) {
      if (cards[row][col] == null) {
         MiniCardVisual mini = new MiniCardVisual();
         mini.addItem((Card) value);
         mini.setSize(100, 150);
         cards[row][col] = mini;
      } 
      else
         cards[row][col].addItem((Card) value);
   }
   
   public Component getTableCellRendererComponent(JTable table, Object data, boolean isSelected, boolean hasFocus, int row, int col) {
      if (cards[row][col] == null) {
         MiniCardVisual mini = new MiniCardVisual();
         mini.addItem((Card) data);
         mini.setSize(100, 150);
         cards[row][col] = mini;
      } 
      else
         cards[row][col].addItem((Card) data);
         
      cards[row][col].setCardBackground(isSelected, table); 
      return cards[row][col];
   }
}