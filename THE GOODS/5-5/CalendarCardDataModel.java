//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.AbstractTableModel;

public class CalendarCardDataModel extends AbstractTableModel {

   private CardTable table;
   private JLabel label;
   private Calendar cal = new GregorianCalendar();
   private CardStorageDatabase storage;
   private String[] days;
   private Integer[][] calArray;
   private Card[][] cardArray;
   private String key = "";
   private int weeks;

   public CalendarCardDataModel(String[] days, JLabel label) {
      storage = new CardStorageDatabase("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/calendar.db");
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
      key = month + " " + year;
      label.setText(key);
      for(int day = 1; day <= numberOfDays; day++){
         setValueAt(day, i / 7, i % 7);
         calArray[i % 7][i / 7] = day;         
         i++;
      }
      fireTableDataChanged();
   }
   
   public void clearCalendar(){
      storage.clear();
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
   
   /*private void updateCards(String old) {
      if (old != null)
         monthMap.put(old, cardArray);
         
      cardArray = monthMap.get(key);
      
      if (cardArray == null)
         cardArray = new Card[7][7];
         
      table.setCards(cardArray);
   }*/
   
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
      Integer cal = calArray[col][row];
      return String.valueOf(key + " - " + (cal == null ? "" : String.valueOf(cal)));
   }
   
   public void setCard(Card card, int row, int col) {
      storage.insert(key + " - " + calArray[col][row], card.getData());
   }
   
   public void deleteCard(int row, int col) {
      storage.delete(key + " - " + calArray[col][row]);
   }
   
   private void printCal() {
      System.out.println("Attempting to print cards");
      CardStorageDatabase storage = new CardStorageDatabase("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/calendar.db");
      System.out.println(storage.getAll());
   }
   
   public boolean checkPoints(int row, int col) {
      return calArray[col][row] != null;
   }
   
   public boolean isCellEditable(int row, int col) {
      return false;
   }
   
   public Class getColumnClass() {
      return String.class;
   }
}