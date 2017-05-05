//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.sql.*;
import java.util.ArrayList;

public class CardStorageDatabase {
 
   private static final String TABLE_NAME = "DATA";
   private static final String ID = "_id";
   private static final String KEY = "key";
   private static final String CARD = "card";
   private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY + " VARCHAR(30), " + CARD + " VARCHAR(300));";
   private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + KEY + "," + CARD + ") " + "VALUES ('";
   private static final String DELETE = "DELETE from " + TABLE_NAME + " where " + KEY + " = ";
   private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET ";
   private Statement db;
   private Connection conn;
   
   //TODO add database_validity_checksum
   
   public CardStorageDatabase(String database) {
      conn = null;
      db = null;
      try {
         conn = DriverManager.getConnection(database);
         db = conn.createStatement();
         createTable();
      } 
      catch (SQLException e) {
         System.out.println(e.getMessage());
      }    
   }
   
   private void createTable() {
      try {
         db.executeUpdate(CREATE_TABLE);
      } 
      catch (SQLException e) {
         System.out.println(e);
      }
   }
   
   public void insert(String key, String card) {
      try {
         db.executeUpdate(INSERT + key + "', '" + card + "');");
      } 
      catch (SQLException e) {
         System.out.println(e);
      }
   }
   
   public String select() {
      try {
         return db.executeQuery("SELECT * FROM " + TABLE_NAME).getString(CARD);
      } 
      catch (SQLException sle) {
         System.out.println(sle);
         return "";
      }
   }
   
   public String getAll() {
      try {
         String list = "";
         ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE_NAME);
         while (rs.next())
            list += rs.getString(KEY) + " " + rs.getString(CARD) + "\n";
            
         return list;
      } 
      catch (SQLException sle) {
         System.out.println(sle);
         return "";
      }
   }
   
   public ArrayList<String> getList(String row) {
      try {
         ArrayList<String> list = new ArrayList<>();
         ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = '" + row + "'");
         while (rs.next())
            list.add(rs.getString(CARD));
            
         return list;
      } 
      catch (SQLException sle) {
         System.out.println(sle);
         return new ArrayList<>();
      }
   }
   
   public String select(String row) {
      try {
         return db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = " + row).getString(CARD);
      } 
      catch(SQLException sqe) {
         return null;
      }
   }
   
   /*public void update(String cat, String old, String update) {
      try {
         switch(cat.toLowerCase()) {
            case "index":
               db.executeUpdate(UPDATE + ID + " = " + update + " where " + ID + " = " + old);
               break;
         }
      } 
      catch (SQLException sqe) {
      
      }
   }*/
   
   public void delete(String index) {
      try {
         int latest = getLatest(index);
         db.executeUpdate(DELETE + "'" + index + "' AND " + ID + " = " + latest);
      } 
      catch (SQLException sle) {
         System.out.println(sle);
      }
   }
   
   private int getLatest(String index) {
      try {
         ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = '" + index + "'");
         int newest = -1;
         do
            newest = Math.max(newest, rs.getInt(ID));
         while (rs.next());
         return newest;
      } catch (SQLException sle) {
         System.out.println(sle);
         return -1;
      }
   }
   
   public void clear() {
      try {
         db.executeUpdate("DELETE FROM " + TABLE_NAME);
      } 
      catch (SQLException sle) {
         System.out.println(sle);
      }
   }
   
   private String printArray(String[] array) {
      String text = "";
      for (int i = 0; i < array.length; i++)
         text += array[i];
      return text;
   }
   
   public String toString() {
      return select();
   }
   
   public String close() {
      if (conn != null) {
         try {
            conn.close();
            db.close();
            return "Closed Successfully";
         } 
         catch (SQLException e) {
            return e.toString();
         }
      } 
      else
         return "";
   }
}