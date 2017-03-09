package util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class MainUtility
{
    final static Logger logger = Logger.getLogger(MainUtility.class);

    final static String WORKOUT_CSV_HEADER = "date,bodyweight,exercise,reps,sets";


    public static String getCurrentDate()
    {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        return ft.format(dNow);
    }


    public static void main(String... args)
    {
        BasicConfigurator.configure();

        logger.info(getCurrentDate());
    }
}
