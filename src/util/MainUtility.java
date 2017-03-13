package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static util.Constants.EXERCISE_PATH;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class MainUtility
{
    final static Logger logger = Logger.getLogger(MainUtility.class);




    public static String getCurrentDate()
    {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        return ft.format(dNow);
    }

    public static ObservableList<String> getDatesForDropDown()
    {
        List<String> listOfDates = new ArrayList<>(14);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyy");

        // Goes 2 weeks back
        for (int i = 0; i < 14; i++)
        {
            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.DATE, - i);

            listOfDates.add(dateFormat.format(cal.getTime()));
        }

        return FXCollections.observableArrayList(listOfDates);
    }

    public static ObservableList<String> loadExercises()
    {
        List<String> listExercises = null;

        try
        {
            listExercises = Files.readAllLines(new File(EXERCISE_PATH).toPath(), Charset.defaultCharset() );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return FXCollections.observableArrayList(listExercises);
    }

    /*public static void main(String... args)
    {
        BasicConfigurator.configure();

        logger.info(getCurrentDate());

        getDatesForDropDown();
    }*/
}
