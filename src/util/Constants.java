package util;

/**
 * Created by dcmeade on 3/10/2017.
 */
public class Constants
{
    // MISC NUMBERS
    public static final int MAIN_PAGE_WIDTH = 850;

    public static final int MAIN_PAGE_HEIGHT = 1000;

    public static final int DATA_DISPLAY_PAGE_WIDTH = 850;

    public static final int DATA_DISPLAY_PAGE_HEIGHT = 850;

    public static final int LINECHART_WINDOW_HEIGHT = 500;

    public static final int LINECHART_WINDOW_WIDTH = 500;


    // MISC STRINGS
    public static final String WORKOUT_CSV_HEADER = "date,bodyweight,exercise,weight,reps,sets,time";

    public static final String PROJECT_TITLE = "Workout Tracker";

    public static final String DATA_DISPLAY_TITLE = "Data Display";


    // PATHS
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String EXERCISE_PATH = PROJECT_PATH + "\\data\\exercises.txt";

    public static final String LOGS_PATH = PROJECT_PATH + "\\data\\logs\\";
}
