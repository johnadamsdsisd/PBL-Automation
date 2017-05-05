//Card Object Class

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.AbstractTableModel;

public class CardTable extends JTable implements MouseListener {

   private CalendarCardDataModel dataModel;
   private TableClickListener tableListener;
   private String[][] cards;
   private CardCell cell;

   public CardTable() {
      super();
      cell = new CardCell();
   }
   
   public CardTable(AbstractTableModel model, TableClickListener listener) {
      super(model);
      dataModel = (CalendarCardDataModel) model;
      tableListener = listener;
      cell = new CardCell();
      addMouseListener(this);
   }
   
   public TableCellRenderer getCellRenderer(int row, int col) { 
      return cell;
   }
   
   public void clearData(){
      dataModel.clearCalendar();
   }
   
   public void repaint() {
      if (cell != null)
         cell.updateSettings();
         
      super.repaint();
   }
   
   public void setData(Card card, int row, int col) {
      dataModel.setCard(card, row, col);
   }
   
   public void delete(int row, int col) {
      dataModel.deleteCard(row, col);
   }
   
   public boolean checkPoint(int row, int col) {
      return dataModel.checkPoints(row, col);
   }
   
   public void setCards(Card[][] ray) {
      //cards = ray;
   }
   
   public void mousePressed(MouseEvent me) {
      tableListener.onTableClicked(rowAtPoint(me.getPoint()), columnAtPoint(me.getPoint()));
   }
   
   public void mouseExited(MouseEvent me) {
   
   }

   public void mouseEntered(MouseEvent me) {
   
   }
   
   public void mouseReleased(MouseEvent me) {
    
   }
   
   public void mouseClicked(MouseEvent me) {
      
   }
}