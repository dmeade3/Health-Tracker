package util;

import data_control.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static util.Constants.LOGS_PATH;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class MainUtility
{
    final static Logger logger = Logger.getLogger(MainUtility.class);

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    // is date2 older than date1
    public static boolean stringDateCompareTo(String date1, String date2)
    {
        String[] dateArray1 = date1.split("-");
        String[] dateArray2 = date2.split("-");

        // move the year to the front for the compare
        String temp1, temp2, temp3;
        temp1 = dateArray1[0];
        temp2 = dateArray1[1];
        temp3 = dateArray1[2];

        dateArray1[0] = temp3;
        dateArray1[1] = temp1;
        dateArray1[2] = temp2;

        temp1 = dateArray2[0];
        temp2 = dateArray2[1];
        temp3 = dateArray2[2];

        dateArray2[0] = temp3;
        dateArray2[1] = temp1;
        dateArray2[2] = temp2;

        // do the comparison
        if (dateArray1.length == dateArray2.length)
        {
            for (int i = 0; i < dateArray1.length; i++)
            {
                //System.out.println(Integer.parseInt(dateArray1[i]) + "   " + Integer.parseInt(dateArray2[i]));

                if (Integer.parseInt(dateArray1[i]) > Integer.parseInt(dateArray2[i]))
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            logger.warn("Date array length error");
            return false;
        }
    }

    public static String getCurrentDate()
    {
        return DATE_FORMAT.format(new Date());
    }

    public static ObservableList<String> getDatesForDropDown()
    {
        List<String> listOfDates = new ArrayList<>(14);

        // Goes 2 weeks back
        for (int i = 0; i < 14; i++)
        {
            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.DATE, - i);

            listOfDates.add(DATE_FORMAT.format(cal.getTime()));
        }

        return FXCollections.observableArrayList(listOfDates);
    }

    public static ObservableList<String> getUsers()
    {
        File file = new File(LOGS_PATH);

        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        return FXCollections.observableArrayList(directories);
    }

    public static ObservableList<Exercise> loadExercises()
    {
        return FXCollections.observableList(Arrays.asList(Exercise.values()));
    }
}
