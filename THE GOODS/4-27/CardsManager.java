//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class CardsManager {

   private DatabaseManager manager;
   private Scanner scan;
   private ArrayList<Card> red, yellow, green, purple, black, blue;
   public static final int YELLOW = 1, RED = 2, GREEN = 3, PURPLE = 4, BLUE = 5, BLACK = 6;

   public CardsManager() {
      manager = new DatabaseManager("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/pblcards.db");
      initColors();
      loadCards();
   }
   
   private void initColors() {
      red = new ArrayList<>();
      yellow = new ArrayList<>();
      green = new ArrayList<>();
      purple = new ArrayList<>();
      black = new ArrayList<>();
      blue = new ArrayList<>();
   }
   
   private void loadCards() {
      ArrayList<Card> cards = manager.getAllCards();
      for (int i = 0; i < cards.size(); i++) {
         switch (cards.get(i).getType().trim()) {
         case "ANTICIPATED WORKSHOP":
            red.add(cards.get(i));
            break;
         case "EMERGING WORKSHOP":
            blue.add(cards.get(i));
            break;
         case "PRESENTATION PREPARATION":
            green.add(cards.get(i));
            break;
         case "FORMATIVE ASSESSMENT":
            purple.add(cards.get(i));
            break;
         case "PROJECT":
            black.add(cards.get(i));
            break;
         default:
            yellow.add(cards.get(i));
            break;
         }
      }
   }
   
   public Card getRed(int pos) {
      return red.get(pos);
   }
   
   public Card getBlue(int pos) {
      return blue.get(pos);
   }
   
   public Card getGreen(int pos) {
      return green.get(pos);
   }
   
   public Card getBlack(int pos) {
      return black.get(pos);
   }
   
   public Card getYellow(int pos) {
      return yellow.get(pos);
   }
   
   public Card getPurple(int pos) {
      return purple.get(pos);
   }
   
   public Card getRandomCard() {
      Random rand = new Random();
      return manager.select(rand.nextInt(48));
   }
   
   public int getMax(int card) {
      switch (card) {
         case YELLOW:
            return yellow.size();
         case RED:
            return red.size();
         case GREEN:
            return green.size();
         case BLUE:
            return blue.size();
         case PURPLE:
            return purple.size();
         case BLACK:
            return black.size();
         default:
            return -1;
      }
   }
   
   public void close() {
      manager.close();
   }
   
   public String toString() {
      return manager.toString();
   }
}