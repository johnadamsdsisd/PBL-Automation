
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class DataRetriever {

   private static Scanner text;
   private static DatabaseManager manager;

   public static void main(String[] args) {
      try {
         text = new Scanner(new File("E:/Java/Project/TestDatabase/carddata.txt"));
      } 
      catch(FileNotFoundException fnfe) {
         System.out.println(fnfe);
      }
      manager = new DatabaseManager("jdbc:sqlite:E:/Java/Project/TestDatabase/db/pblcards.db");
      String type = text.nextLine();
      String title = text.nextLine();
      String desc = text.nextLine();
      String attrs = text.nextLine();
      String time = text.nextLine();
      manager.insert(title, type, desc, time, attrs);
      do {
         text.nextLine();
         
         type = text.nextLine();
         title = text.nextLine();
         desc = text.nextLine();
         attrs = text.nextLine();
         time = text.nextLine();
         manager.insert(title, type, desc, time, attrs);
      } while(text.hasNextLine());
      System.out.println(manager);
      manager.close();
   }
}