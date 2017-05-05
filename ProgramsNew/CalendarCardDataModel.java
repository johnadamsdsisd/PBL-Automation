//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.AbstractTableModel;

public class CalendarCardDataModel extends AbstractTableModel {

   private JLabel label;
   private Calendar cal = new GregorianCalendar();
   private String[] days;
   private int[][] calArray;
   private Card[][] cardArray;
   private int weeks;

   public CalendarCardDataModel(String[] days, JLabel label) {
      cardArray = new Card[7][7];
      this.days = days;
      this.label = label;
   }
   
   public void updateCalendar() {
      weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
      calArray = new int[7][6];
      int startDay = cal.get(Calendar.DAY_OF_WEEK);
      int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      int i = startDay - 1;
      for(int day = 1; day <= numberOfDays; day++){
         setValueAt(day, i / 7, i % 7);
         calArray[i % 7][i / 7] = day;
         i++;
      }
      String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
      int year = cal.get(Calendar.YEAR);
      label.setText(month + " " + year);
      fireTableDataChanged();
   }
   
   public void nextMonth() {
      cal.add(Calendar.MONTH, +1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      updateCalendar();
   }
   
   public void previousMonth() {
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      updateCalendar();
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
         int cal = calArray[col][row];
         return cal == 0 ? "" : String.valueOf(cal);
      }
   }
   
   public void setCard(Card card, int row, int col) {
      cardArray[row][col] = card;
   }
   
   public boolean isCellEditable(int row, int col) {
      return false;
   }
   
   public Class getColumnClass() {
      return Card.class;
   }
}