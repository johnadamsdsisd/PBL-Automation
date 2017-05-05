//Card Object Class

public class Card
{

   private static final String separator = "CARDSEP";

   private int row;
   private String title;
   private String type;
   private String description;
   private String color;
   private int MIN;
   private int MAX;
   private String attributes;
   private int count;
   private int day;
   
   public Card() {
      row = -1;
      title = "";
      type = "";
      description = "";
      color = "";
      MIN = 1;
      MAX = 2;
      attributes = "";
      count = 0;
   }
   
   public Card(String all) {
      String[] cards = all.split(separator);
      row = 0;
      title = cards[0];
      type = cards[1];
      description = cards[2];
      color = cards[3];
      MIN = Integer.parseInt(cards[4]);
      MAX = Integer.parseInt(cards[5]);
      attributes = cards[6];
   }
   
   public Card(int r, String t, String ty, String des, String c, int min, int max, String att) {
      row = r;
      title = t;
      type = ty;
      description = des;
      color = c;
      MIN = min;
      MAX = max;
      attributes = att;
      count = 1;
   }
   
   public Card(int r, String t, String ty, String des, String c, int min, int max, String att, int co) {
      row = r;
      title = t;
      type = ty;
      description = des;
      color = c;
      MIN = min;
      MAX = max;
      attributes = att;
      count = co;
   }
   
   public Card(int day) {
      row = -1;
      title = "";
      type = "";
      description = "";
      color = "";
      MIN = 1;
      MAX = 2;
      attributes = "";
      count = 0;
      this.day = day;
   }
   
   public void setData(Card card) {
      row = card.getRow();
      title = card.getTitle();
      type = card.getType();
      description = card.getDescription();
      color = card.getColor();
      MIN = card.getMin();
      MAX = card.getMax();
      attributes = card.getAttributes();
      count = 1;
   }
   
   public int getRow() {
      return row;
   }
   
   public String getTitle() {
      return title;
   }
   
   public void setTitle(String t) {
      title = t.trim();
   }
   
   public String getType() {
      return type;
   }
   
   public void setType(String t) {
      type = t.trim();
   }
   
   public String getDescription() {
      return description;
   }
   
   public void setDescription(String d) {
      description = d.trim();
   }
   
   public String getColor() {
      return color;
   }
   
   public void setColor(String c) {
      color = c;
   }
   
   public int getMin() {
      return MIN;
   }
   
   public void setMin(int m) {
      MIN = m;
   }
   
   public int getMax() {
      return MAX;
   }
   
   public void setMax(int m) {
      MAX = m;
   }
   
   public void setTime(String time) {
      String[] times = time.split("-");
      try {
         MIN = Integer.parseInt(times[0].trim());
         MAX = Integer.parseInt(times[1].trim());
      } 
      catch (NumberFormatException e) {
         System.out.println("Error at " + type + " " + title);
      }
   }
   
   public String getTime() {
      return MIN + " - " + MAX;
   }
   
   public String getAttributes() {
      return attributes;
   }
   
   public void setAttributes(String a) {
      attributes = a;
   }
   
   public int getCount() {
      return count;
   }
   
   public void setDay(int day) {
      this.day = day;
   }
   
   public int getDay() {
      return day;
   }
   
   public String getData() {
      return title + separator + type + separator + description + separator + color + separator + MIN + separator + MAX + separator + attributes;
   }
   
   public String toString() {
      //return row + " | " + title + " | " + type + " | " + description + " | " + color + " | " + MIN + " - " + MAX + " | " + attributes + " | " + count;
      //return title + " | " + type + " | " + description + " | " + MIN + " - " + MAX + " | " + attributes;
      return String.valueOf(day);
   }
}