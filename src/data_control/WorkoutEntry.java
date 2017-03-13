package data_control;

/**
 * Created by dcmeade on 3/9/2017.
 */
public class WorkoutEntry
{
    private String date;
    private float bodyweight;
    private String exercise;
    private int reps;
    private int sets;
    private float time;

    public WorkoutEntry(String date, float bodyweight, String exercise, int reps, int sets, float time)
    {
        this.date = date;
        this.bodyweight = bodyweight;
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
        this.time = time;
    }

    @Override
    public String toString()
    {
        return date + "," + bodyweight + "," + exercise + "," + reps + "," + sets + "," + time;
    }
}
