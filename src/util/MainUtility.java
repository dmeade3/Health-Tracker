package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

import static util.Constants.EXERCISE_PATH;
import static util.Constants.LOGS_PATH;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class MainUtility
{
    final static Logger logger = Logger.getLogger(MainUtility.class);


    public static String getCurrentDate()
    {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyy");

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

    public static ObservableList<String> getUsers()
    {
        File file = new File(LOGS_PATH);

        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        return FXCollections.observableArrayList(directories);
    }

    public static ObservableList<String> loadExercises()
    {
        try
        {
            List<String> listExercises = listExercises = Files.readAllLines(new File(EXERCISE_PATH).toPath(), Charset.defaultCharset());

            int ctr = 0;
            List<Integer> indecesToRemove = new ArrayList<>();

            for (String s : listExercises)
            {
                if (s.trim().startsWith("#") || s.equals(""))
                {
                    indecesToRemove.add(ctr);
                }

                ctr++;
            }

            List <String> filteredList = new ArrayList<>();

            ctr = 0;
            // Perform the remove operation
            for (String s : listExercises)
            {
                if (!indecesToRemove.contains(ctr))
                {
                    filteredList.add(s);
                }

                ctr++;
            }

            return FXCollections.observableArrayList(filteredList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
