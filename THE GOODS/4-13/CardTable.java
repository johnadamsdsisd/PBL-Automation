//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.AbstractTableModel;

public class CardTable extends JTable {

   private CalendarCardDataModel dataModel;
   private Card[][] cards;
   private CardCell cell;

   public CardTable() {
      super();
      cards = new Card[8][8];
      cell = new CardCell();
   }
   
   public CardTable(AbstractTableModel model) {
      super(model);
      dataModel = (CalendarCardDataModel) model;
      cards = new Card[7][7];
      cell = new CardCell();
   }
   
   public TableCellRenderer getCellRenderer(int row, int col) {
      if (cards[row][col] != null) 
         return cell;
      else
         return super.getCellRenderer(row, col);
   }
   
   public void clearData(){
      cards = new Card[7][7];
      dataModel.clearCalendar();
   }
   
   public void setData(Card card, int row, int col) {
      cards = dataModel.setCard(card, row, col);
   }
   
   public boolean checkPoint(int row, int col) {
      return dataModel.checkPoints(row, col);
   }
   
   public void setCards(Card[][] ray) {
      cards = ray;
   }
}