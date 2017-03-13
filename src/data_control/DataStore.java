package data_control;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static util.Constants.LOGS_PATH;
import static util.Constants.WORKOUT_CSV_HEADER;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class DataStore
{
    final static Logger logger = Logger.getLogger(DataStore.class);


    ArrayList<WorkoutEntry> workoutEntries = new ArrayList<>();

    public void readInUserData(String user)
    {
        // todo check if the user dir exists

        // get all csv files
        Collection files = FileUtils.listFiles(new File(LOGS_PATH + System.getProperty("file.separator") + user), new RegexFileFilter(".+\\.(csv)"), DirectoryFileFilter.DIRECTORY);

        for (Object file : files)
        {

            System.out.println(((File)file).getAbsoluteFile());

        }


    }

    public void storeWorkoutEntry(WorkoutEntry workoutEntry, String user, String date)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;

        String filePath = LOGS_PATH + System.getProperty("file.separator") + user + System.getProperty("file.separator") + date.replaceAll("/", "_") + ".csv";

        try
        {
            File file = new File(filePath);
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                // true = append file
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);

                logger.info("Creating new log file: " + filePath);

                file.createNewFile();
                bw.write(WORKOUT_CSV_HEADER + "\n" + workoutEntry.toString() + "\n");
            }
            else
            {
                // true = append file
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);

                bw.write(workoutEntry.toString() + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }

                if (fw != null)
                {
                    fw.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String... args)
    {
        BasicConfigurator.configure();

        DataStore dataStore = new DataStore();

        /*WorkoutEntry workoutEntry = new WorkoutEntry("3/13/2017", 195, "Pullups", 20, 5, 33.44f);

        dataStore.storeWorkoutEntry(workoutEntry, "David", "3/13/2017");*/


        dataStore.readInUserData("David");

        for (WorkoutEntry workoutEntry : dataStore.workoutEntries)
        {
            System.out.println(workoutEntry.toString());
        }
    }
}
