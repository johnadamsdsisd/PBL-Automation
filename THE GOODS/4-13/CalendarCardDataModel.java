//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import javax.swing.table.AbstractTableModel;

public class CalendarCardDataModel extends AbstractTableModel {

   private CardTable table;
   private JLabel label;
   private Calendar cal = new GregorianCalendar();
   private Map<String, Card[][]> monthMap;
   private String[] days;
   private Integer[][] calArray;
   private Card[][] cardArray;
   private String oldKey = null;
   private int weeks;

   public CalendarCardDataModel(String[] days, JLabel label) {
      monthMap = new HashMap<String, Card[][]>();
      this.days = days;
      this.label = label;
   }
   
   public void setTable(CardTable table) {
      this.table = table;
   }
   
   public void updateCalendar() {
      cal.set(Calendar.DAY_OF_MONTH, 1);
      weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
      calArray = new Integer[7][6];
      int startDay = cal.get(Calendar.DAY_OF_WEEK);
      int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      int i = startDay - 1;
      String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
      int year = cal.get(Calendar.YEAR);
      updateCards(oldKey, month + year);
      oldKey = month + " " + year;
      label.setText(oldKey);
      for(int day = 1; day <= numberOfDays; day++){
         setValueAt(day, i / 7, i % 7);
         calArray[i % 7][i / 7] = day;
         
         if (cardArray[i % 7][i / 7] != null)
            cardArray[i % 7][i / 7].setDay(day);
         
         i++;
      }
      fireTableDataChanged();
   }
   
   public void clearCalendar(){
      monthMap.clear();
      cardArray = new Card[7][7];
      fireTableDataChanged();
   }
   
   public void nextMonth() {
      cal.add(Calendar.MONTH, +1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      updateCalendar();
      
      if (table != null)
         table.repaint();
   }
   
   public void previousMonth() {
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      updateCalendar();
      
      if (table != null)
         table.repaint();
   }
   
   private void updateCards(String old, String key) {
      if (old != null)
         monthMap.put(old, cardArray);
         
      cardArray = monthMap.get(key);
      
      if (cardArray == null)
         cardArray = new Card[7][7];
         
      table.setCards(cardArray);
   }
   
   public int getColumnCount() {
      return days.length;
   }
   
   public int getRowCount() {
      return weeks;
   }
   
   public String getColumnName(int index) {
      return days[index];
   }
   
   public Object getValueAt(int row, int col) {
      if (cardArray[row][col] != null)
         return cardArray[row][col];
      else {
         Integer cal = calArray[col][row];
         return cal == null ? "" : String.valueOf(cal);
      }
   }
   
   public Card[][] setCard(Card card, int row, int col) {
      card.setDay(calArray[col][row]);
      cardArray[row][col] = card;
      return cardArray;
   }
   
   public boolean checkPoints(int row, int col) {
      return calArray[col][row] != null;
   }
   
   public boolean isCellEditable(int row, int col) {
      return false;
   }
   
   public Class getColumnClass() {
      return Card.class;
   }
}