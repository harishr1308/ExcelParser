package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    public static Logger logger = Logger.getRootLogger();

    /**
     * Configure the logger. To be called before anything else
     */
    public static void ConfigureLogger(String filePath) {


        //here it is assumed that your apache and NetBeans folder are in /home/username
        // PropertyConfigurator.configure("src/java/utilities/logger.properties");
        PropertyConfigurator.configure(filePath);
        //String[] logSavefilePath = filePath.split("classes");
        //  PropertyConfigurator.configure("H:/CSTEP Official data/EM_3rd August/src/java/utilities/logger.properties");
        TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm ");


        String currentTime = df.format(now);

        FileAppender appender = null;
        PatternLayout p = new PatternLayout("%d %-5p [%t] %-17c{2} (%13F:%L) %3x - %m%n");
//        try {
//          //  appender = new FileAppender(p, "/media/New Volume/CSTEP Official data/EM_3rd August/src/java/com/logs/test_" + currentTime + ".txt");
//             appender = new FileAppender(p, logSavefilePath[0]+"classes/com/logs/test_" + currentTime + ".txt");
//        } catch (Exception e) {
//        }
//        logger.addAppender(appender);

        logger.setLevel(Level.DEBUG);
        logger.info("logging initialized");
    }
}
