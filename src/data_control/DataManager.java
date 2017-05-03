package data_control;

import org.apache.log4j.Logger;
import util.ProgramInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static gui.components.WorkoutPageGridpane.writeToConsole;
import static util.Constants.LOGS_PATH;
import static util.Constants.WORKOUT_CSV_HEADER;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class DataManager
{
    final static Logger logger = Logger.getLogger(DataManager.class);

    // Reads in all from a user back to a certain date
    // if date is "all" then all user data read in
    public static List<WorkoutEntry> readInUserData(DateRange dateRange, Exercise exercise)
    {
        // get all csv files
        ArrayList<WorkoutEntry> workoutEntries = new ArrayList<>();

        //System.out.println("Reading in file: " + ((File)file).getAbsoluteFile());
        try (BufferedReader br = new BufferedReader(new FileReader(new File(LOGS_PATH + System.getProperty("file.separator") + ProgramInfo.CURRENT_USER + System.getProperty("file.separator") + "workoutEntries.csv"))))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (line.startsWith(WORKOUT_CSV_HEADER))
                {
                    continue;
                }

                WorkoutEntry workoutEntry = WorkoutEntry.parseWorkoutEntry(line);

                // Check if the first provided is before the file date || date is equal to all
                if (dateRange.inRange(workoutEntry.getDate()))
                {
                    //System.out.println(workoutEntry.getExercise().exerciseName + "      " + exercise);
                    if (workoutEntry.getExercise().exerciseName.equals(exercise.exerciseName))
                    {
                        workoutEntries.add(workoutEntry);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return workoutEntries;
    }

    public static List<WorkoutEntry> readInUserData(DateRange dateRange, MuscleGroup muscleGroup)
    {
        // get all csv files
        ArrayList<WorkoutEntry> workoutEntries = new ArrayList<>();

        //System.out.println("Reading in file: " + ((File)file).getAbsoluteFile());
        try (BufferedReader br = new BufferedReader(new FileReader(new File(LOGS_PATH + System.getProperty("file.separator") + ProgramInfo.CURRENT_USER + System.getProperty("file.separator") + "workoutEntries.csv"))))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (line.startsWith(WORKOUT_CSV_HEADER))
                {
                    continue;
                }

                WorkoutEntry workoutEntry = WorkoutEntry.parseWorkoutEntry(line);

                // TODO get rid of all, should be a daterange option
                // Check if the first provided is before the file date || date is equal to all
                if (dateRange.inRange(workoutEntry.getDate()))
                {
                    //System.out.println(workoutEntry.getExercise().muscleGroupsList.contains(muscleGroup) + "      " + muscleGroup);
                    if (workoutEntry.getExercise().muscleGroupsList.contains(muscleGroup))
                    {
                        workoutEntries.add(workoutEntry);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return workoutEntries;
    }

    public static void storeWorkoutEntry(WorkoutEntry workoutEntry, String user, String date)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;

        String filePath = LOGS_PATH + System.getProperty("file.separator") + user + System.getProperty("file.separator") + "workoutEntries.csv";

        try
        {
            File file = new File(filePath);
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                try
                {
                    file.createNewFile();

                    // true = append file
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);

                    logger.info("Creating new log file: " + filePath);

                    bw.write(WORKOUT_CSV_HEADER + "\n" + workoutEntry.toString() + "\n");
                    writeToConsole("Adding entry: " + workoutEntry.toString());
                }
                catch (IOException ioExp)
                {
                    logger.warn("File path cannot be used: " + file.getAbsolutePath());

                    writeToConsole("Path not able to be created, User may be wrong / not exist");
                }
            }
            else
            {
                // true = append file
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);

                bw.write(workoutEntry.toString() + "\n");

                writeToConsole("Adding entry: " + workoutEntry.toString());
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

    public static void fitbitCsvWriter(String writeOutString, String filename, String header)
    {
        // Make from here down a generic function to write to a csv file if entry doesnt exist
        // Fitbit csv writer
        BufferedWriter bw = null;
        FileWriter fw = null;

        // TODO make the user dynamic
        String filePath = LOGS_PATH + System.getProperty("file.separator") + "David" + System.getProperty("file.separator") + filename;

        String date = writeOutString.split(",")[0];

        try
        {
            File file = new File(filePath);
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                try
                {
                    file.createNewFile();

                    // true = append file
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);

                    logger.info("Creating new log file: " + filePath);

                    bw.write(header + "\n" + writeOutString + "\n");
                }
                catch (IOException ioExp)
                {
                    logger.warn("File path cannot be used: " + file.getAbsolutePath());

                    writeToConsole("Path not able to be created, User may be wrong / not exist");
                }
            }
            else
            {
                String currentContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
	            
	            // replace the entry in place
	            currentContent = currentContent.replaceFirst(date + ".*", writeOutString);

	            // If does not exist in file add to the end
	            if (!currentContent.contains(writeOutString.trim()))
	            {
		            currentContent += writeOutString + "\n";
	            }

	            Files.write(Paths.get(String.valueOf(file.toPath())), currentContent.getBytes());
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
}
