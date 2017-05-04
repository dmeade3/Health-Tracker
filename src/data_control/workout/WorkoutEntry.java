package data_control.workout;

import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static util.Constants.WORKOUT_CSV_HEADER;
import static util.MainUtility.DATE_FORMAT;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class WorkoutEntry
{
    final static Logger logger = Logger.getLogger(WorkoutEntry.class);

    private Date date;
    private float bodyweight;
    private Exercise exercise;
    private int reps;
    private int sets;
    private float additionalWeight;

    public WorkoutEntry(Date date, float bodyweight, Exercise exercise, float additionalWeight, int reps, int sets)
    {
        this.date = date;
        this.bodyweight = bodyweight;
        this.exercise = exercise;
        this.additionalWeight = additionalWeight;
        this.reps = reps;
        this.sets = sets;
    }

    public Date getDate()
    {
        return date;
    }

    public float getBodyweight()
    {
        return bodyweight;
    }

    public int getReps()
    {
        return reps;
    }

    public int getSets()
    {
        return sets;
    }

    public float getAdditionalWeight()
    {
        return additionalWeight;
    }

    public Exercise getExercise()
    {
        return exercise;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setBodyweight(float bodyweight)
    {
        this.bodyweight = bodyweight;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }

    public void setReps(int reps)
    {
        this.reps = reps;
    }

    public void setSets(int sets)
    {
        this.sets = sets;
    }

    public void setAdditionalWeight(float additionalWeight)
    {
        this.additionalWeight = additionalWeight;
    }

    public static WorkoutEntry parseWorkoutEntry(String line)
    {
        List<String> workoutEntryLine = Arrays.asList(line.split(","));

        if (workoutEntryLine.size() != (WORKOUT_CSV_HEADER.split(",").length))
        {
            logger.warn("Wrong number of commas in workout entry: " + workoutEntryLine);

            return null;
        }

        // Handle this like budget handles it, referencing the rows index not just a number
        try {

	        if (Exercise.nameToEnum(workoutEntryLine.get(2)) == null)
	        {
		        System.out.println(workoutEntryLine.get(2) + ":caused an error");

		        throw new ParseException("Error", 0);
	        }

            return new WorkoutEntry(
                    DATE_FORMAT.parse(workoutEntryLine.get(0)),
                    Float.parseFloat(workoutEntryLine.get(1)),
		            Exercise.nameToEnum(workoutEntryLine.get(2)),
                    Float.parseFloat(workoutEntryLine.get(3)),
                    Integer.parseInt(workoutEntryLine.get(4)),
                    Integer.parseInt(workoutEntryLine.get(5))
            );
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // TODO this should be in a different class
    public static List<XYChart.Data<Number, Number>> getWorkoutValues(List<WorkoutEntry> workoutEntries, WorkoutEntryField field) throws ParseException
    {
        String stringField = field.toString();

        List<XYChart.Data<Number, Number>> dataArrayList = new ArrayList<>();

        switch (stringField)
        {
            // TODO some of these might nt make sense to make
            // TODO Should probably make an enum
            case "date":
                logger.warn("Cannot choose date as a field to graph");
                break;

            /*case "bodyweight":
                for (WorkoutEntry workoutEntry : workoutEntries)
                {
                    dataArrayList.add(new XYChart.Data(workoutEntry.date, workoutEntry.bodyweight));
                }
                break;

            case "weight":
                for (WorkoutEntry workoutEntry : workoutEntries)
                {
                    dataArrayList.add(new XYChart.Data(workoutEntry.date, workoutEntry.additionalWeight));
                }
                break;

            case "reps":
                for (WorkoutEntry workoutEntry : workoutEntries)
                {
                    dataArrayList.add(new XYChart.Data(workoutEntry.date, workoutEntry.reps));
                }
                break;

            case "sets":
                for (WorkoutEntry workoutEntry : workoutEntries)
                {
                    dataArrayList.add(new XYChart.Data(workoutEntry.date, workoutEntry.sets));
                }
                break;*/

            case "exercise":
                for (WorkoutEntry workoutEntry : workoutEntries)
                {
	                double weight = workoutEntry.additionalWeight;

	                // Bodyweight exercise
	                if (workoutEntry.exercise.bodyweight)
	                {
						weight += workoutEntry.bodyweight;
	                }

                    // Multiply the reps x the sets for the total volume x (bodyweight + additional weight)
                    double volume = workoutEntry.reps * workoutEntry.sets * weight;

	                // Unilateral
	                if (workoutEntry.exercise.unilateral)
	                {
						volume *= 2;
	                }

                    //System.out.println("Volume: " + volume);
                    dataArrayList.add(new XYChart.Data<>(workoutEntry.date.getTime(), volume));
                }
                break;

            default:
                logger.warn("Could not process field: " + field);
                return null;
        }

        return dataArrayList;
    }

    public static List<XYChart.Data<Number, Number>> getMuscleGroupValues(List<WorkoutEntry> workoutEntries)
    {
        List<XYChart.Data<Number, Number>> dataArrayList = new ArrayList<>();

        // Calculates volume
        for (WorkoutEntry workoutEntry : workoutEntries)
        {
            double weight = workoutEntry.additionalWeight;

            // Bodyweight exercise
            if (workoutEntry.exercise.bodyweight)
            {
                weight += workoutEntry.bodyweight;
            }

            // Multiply the reps x the sets for the total volume x (bodyweight + additional weight)
            double volume = workoutEntry.reps * workoutEntry.sets * weight;

            // Unilateral
            if (workoutEntry.exercise.unilateral)
            {
                volume *= 2;
            }

            //System.out.println("Volume: " + volume);
            dataArrayList.add(new XYChart.Data<>(workoutEntry.date.getTime(), volume));
        }

        return dataArrayList;
    }

    @Override
    public String toString()
    {
        return DATE_FORMAT.format(date) + "," + bodyweight + "," + exercise.exerciseName + "," + additionalWeight + "," + reps + "," + sets;
    }
}
