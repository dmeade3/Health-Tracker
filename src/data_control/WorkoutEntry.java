package data_control;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class WorkoutEntry
{
    String date;
    float bodyweight;
    String exercise;
    int reps;
    int sets;

    public WorkoutEntry(String date, float bodyweight, String exercise, int reps, int sets)
    {
        this.date = date;
        this.bodyweight = bodyweight;
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
    }

    @Override
    public String toString()
    {
        return date + "," + bodyweight + "," + exercise + "," + reps + "," + sets;
    }
}
