package data_control;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static util.Constants.LOGS_PATH;
import static util.Constants.WORKOUT_CSV_HEADER;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class DataStore
{
    final static Logger logger = Logger.getLogger(DataStore.class);


    ArrayList<WorkoutEntry> workoutEntries = new ArrayList<>();

    public void readInData(String name)
    {

    }

    public void storeData(WorkoutEntry workoutEntry, String user, String date)
    {

    }

    public void makeNewCsvFile(String user, String date)
    {
        String filePath = LOGS_PATH + System.getProperty("file.separator") + user + System.getProperty("file.separator") + date.replaceAll("/", "_") + ".csv";

        File file = new File(filePath);

        try
        {
            if (!file.createNewFile())
            {
                System.out.println("File is created: " + filePath);

                try
                {
                    PrintWriter writer = new PrintWriter(file, "UTF-8");
                    writer.println(WORKOUT_CSV_HEADER);
                    writer.close();
                }
                catch (IOException e)
                {
                    logger.warn("Could not writ to file: " + filePath);
                }
            }
            else
            {
                System.out.println("File already exists: " + filePath);
            }
        }
        catch (IOException e)
        {
            logger.warn("Could not create new log file: user: " + filePath);
        }
    }

    public static void main(String... args)
    {
        BasicConfigurator.configure();

        DataStore dataStore = new DataStore();

        dataStore.makeNewCsvFile("David", "3/10/2017");
    }
}
