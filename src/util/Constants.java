package util;

/**
 * Created by dcmeade on 3/10/2017.
 */
public class Constants
{
    // MISC NUMBERS
    public static final int MAIN_PAGE_WIDTH = 2000;

    public static final int MAIN_PAGE_HEIGHT = 1200;

    public static final int DATA_DISPLAY_PAGE_WIDTH = 800;

    public static final int DATA_DISPLAY_PAGE_HEIGHT = 600;



    // MISC STRINGS
    public static final String WORKOUT_CSV_HEADER = "date,bodyweight,exercise,weight,reps,sets";

    public static final String STEPS_CSV_HEADER = "date,steps";

    public static final String PROJECT_TITLE = "Health Tracker";

    public static final String DATA_DISPLAY_TITLE = "Data Display";


    // PATHS
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String LOGS_PATH = PROJECT_PATH + "\\data\\logs\\";

    public static final String ON_EXIT_INFO_PATH = PROJECT_PATH + "\\data\\" + "OnExitInfo.cfg";
}
