//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
 
   private static final String TABLE_NAME = "DATA";
   private static final String ID = "_id";
   private static final String TITLE = "title";
   private static final String TYPE = "type";
   private static final String DESC = "card";
   private static final String COLOR = "color";
   private static final String MIN = "min";
   private static final String MAX = "max";
   private static final String ATTRS = "attrs";
   private static final String DATABASE_VALIDITY_CHECKSUM = "checksum";
   private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " VARCHAR(30), " + TYPE + " VARCHAR(30), " + DESC + " VARCHAR(300), " + COLOR + " VARCHAR(255), " + MIN + " INTEGER, " + MAX + " INTEGER, " + ATTRS + " VARCHAR(255), " + DATABASE_VALIDITY_CHECKSUM + " INTEGER);";
   private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + TITLE + "," + TYPE + "," + DESC + "," + COLOR + "," + MIN + "," + MAX + "," + ATTRS + "," + DATABASE_VALIDITY_CHECKSUM + ") " + "VALUES ('";
   private static final String DELETE = "DELETE from " + TABLE_NAME + " where " + ID + " = ";
   private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET ";
   private Statement db;
   private Connection conn;
   
   //TODO add database_validity_checksum
   
   public DatabaseManager(String database) {
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
   
   public void insert(String title, String type, String desc, String color, int min, int max, String attrs) {
      try {
         db.executeUpdate(INSERT + title + "', '" + type + "', '" + desc + "', '" + color + "', '" + min + "', '" + max + "', '" + attrs + "', " + 98496136 + ");");
      } 
      catch (SQLException e) {
         System.out.println(e);
      }
   }
   
   public void insert(String title, String type, String desc, String time, String attrs) {
      try {
         String color = getColor(type);
         int[] times = getTime(time);
         db.executeUpdate(INSERT + title + "', '" + type + "', '" + desc + "', '" + color + "', " + times[0] + ", " + times[1] + ", '" + attrs + "', " + 98496136 + ");");
      } 
      catch (SQLException e) {
         System.out.println(e);
      }
   }
   
   public String select() {
      try {
         return getDataFromSelection(db.executeQuery("SELECT * FROM " + TABLE_NAME));
      } 
      catch (SQLException sle) {
         System.out.println(sle);
         return "";
      }
   }
   
   public Card select(int row) {
      try {
         String items = getCardData(db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + row));
         String[] data = items.split("%");
         return new Card(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], Integer.parseInt(data[5].split("-")[0]), Integer.parseInt(data[5].split("-")[1]), data[6]);
      } catch(SQLException sqe) {
         return null;
      }
   }
   
   public ArrayList<Card> getAllCards() {
      try {
         ArrayList<Card> temp = new ArrayList<>();
         ResultSet rs = db.executeQuery("SELECT * FROM " + TABLE_NAME);
         while (rs.next()) {
            temp.add(new Card(rs.getInt(ID), rs.getString(TITLE), rs.getString(TYPE), rs.getString(DESC), rs.getString(COLOR), rs.getInt(MIN), rs.getInt(MAX), rs.getString(ATTRS), 1));
         }
         return temp;
      } catch (SQLException sqe) {
         return null;
      }
   }
   
   private String getCardData(ResultSet rs) throws SQLException {
      String result = rs.getInt(ID) + "%" + rs.getString(TITLE) + "%" + rs.getString(TYPE) + "%" + rs.getString(DESC) + "%" + rs.getString(COLOR) + "%" + rs.getInt(MIN) + "-" + rs.getInt(MAX) + "%" + rs.getString(ATTRS);
      rs.close();
      return result;
   }
   
   private String getDataFromSelection(ResultSet rs) throws SQLException {
      String result = "% " + ID + " % " + TITLE + " % " + TYPE + " % " + DESC + " % " + COLOR + " % TIME % " + ATTRS + " %\n";
      while (rs.next())
         result += rs.getInt(ID) + "  % " + rs.getString(TITLE) + " % " + rs.getString(TYPE) + " % " + rs.getString(DESC) + " % " + rs.getString(COLOR) + " % " + rs.getInt(MIN) + " - " + rs.getInt(MAX) + " % " + rs.getString(ATTRS) + "%\n";
      rs.close();
      return result;
   }
   
   public void update(String cat, String old, String update) {
      try {
         switch(cat.toLowerCase()) {
            case "index":
               db.executeUpdate(UPDATE + ID + " = " + update + " where " + ID + " = " + old);
               break;
         }
      } 
      catch (SQLException sqe) {
      
      }
   }
   
   public void delete(String index) {
      try {
         db.executeUpdate(DELETE + index);
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
   
   private String getColor(String type) {
      switch(type) {
         case "ANTICIPATED WORKSHOP":
            return "#ce1c1f";
         case "EMERGING WORKSHOP":
            return "#1c28ce";
         case "PRESENTATION PREPARATION":
            return "#0f9625";
         case "FORMATIVE ASSESSMENT":
            return "#300342";
         case "PROJECT":
            return "#000000";
         default:
            return "#f3f72a";
      }
   }
   
   private int[] getTime(String time) {
      int[] times = new int[2];
      String[] strings = time.split("-");
      times[0] = Integer.parseInt(strings[0].trim());
      times[1] = Integer.parseInt(strings[1].trim());
      return times;
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