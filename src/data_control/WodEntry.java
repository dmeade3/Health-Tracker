package data_control;

import org.apache.log4j.Logger;

/**
 * Created by dcmeade on 3/22/2017.
 */

public enum WodEntry
{
    // Numbers are the totals of the particular wod
    // Must start with "Wod "
    ANGIE("Wod Angie", 0, 400, 1);


    private WorkoutEntry workoutEntry;

    final Logger logger = Logger.getLogger(WodEntry.class);

    WodEntry(String exercise, float additionalWeight, int reps, int sets)
    {
        try
        {
            if (!exercise.startsWith("Wod "))
            {
                throw new Exception("Wod entry must start with \"Wod \"");
            }
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage());
        }

        workoutEntry = new WorkoutEntry("", -1, exercise, additionalWeight, reps, sets, -1);
    }

    public WorkoutEntry getWorkoutEntry()
    {
        return workoutEntry;
    }

    @Override
    public String toString()
    {
        return workoutEntry.toString();
    }
}