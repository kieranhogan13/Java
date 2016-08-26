package ie.kieranhogan.misnapper;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String getANewFilename() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");

        return "snap" + dateFormat.format(new Date()) + ".jpg";
    }
}
