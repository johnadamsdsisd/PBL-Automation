//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Scanner;

public class DatabaseTest {
    
   public static void main(String[] args) {
      System.out.println("Welcome to database manager!\n");
      DatabaseManager manager = new DatabaseManager();
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter command: ");
      while(!getCommand(scan, manager).equals("stop")) {
         System.out.print("\nEnter next command: ");
      }
      manager.close();
   }
   
   private static String getCommand(Scanner scan, DatabaseManager manager) {
      switch(scan.nextLine().toLowerCase()) {
         case "insert":
            System.out.print("\nEnter string of title to insert: ");
            String title = scan.nextLine();
            System.out.print("\nEnter type to insert: ");
            String type = scan.nextLine();
            System.out.print("\nEnter description to insert: ");
            String desc = scan.nextLine();
            System.out.print("\nEnter color of item to insert: ");
            String color = scan.next();
            System.out.print("\nEnter min time of item to insert: ");
            int min = scan.nextInt();
            System.out.print("\nEnter max time of item to insert: ");
            int max = scan.nextInt();
            System.out.print("\nEnter extra attrs of item to insert: ");
            String attrs = scan.next();
            manager.insert(title, type, desc, color, min, max, attrs);
            return "";
         case "query":
            System.out.println();
            System.out.print("Select rows with what text? ");
            System.out.println("\n" + manager.select(scan.next()));
            return "";
         case "select":
            System.out.println();
            System.out.print("Enter row to select: ");
            System.out.println("\n Card data: " + manager.select(scan.nextInt()));
            return "";
         case "print":
            System.out.println("\n" + manager);
            return "";
         case "delete":
            System.out.print("\nEnter the index of row to delete: ");
            manager.delete(scan.next());
            return "";
         case "quit":
            System.out.println("\nThanks for using database manager!");
            return "stop";
         default:
            System.out.print("\nYour commands are insert, query, update, delete, print, and quit: ");
            return getCommand(scan, manager);
      }
   }
}