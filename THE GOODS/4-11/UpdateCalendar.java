import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class UpdateCalendar {
   public void Update() throws IOException
   {
      File file = new File("update.bat");
      Desktop desktop = Desktop.getDesktop();
      if(file.exists()) 
         desktop.open(file);
   }
}