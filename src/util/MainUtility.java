package util;

import data_control.workout.Exercise;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
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

    public final static SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("MM-dd-yyyy  hh:mm:ss a");

    public static final DecimalFormat NUMBER_FORMAT  = new DecimalFormat( "#,###" );

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

    public static void hackTooltipStartTiming(Tooltip tooltip)
    {
        try
        {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(175)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
