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
    private float additionalWeight;

    public WorkoutEntry(String date, float bodyweight, String exercise, float additionalWeight, int reps, int sets, float time)
    {
        this.date = date;
        this.bodyweight = bodyweight;
        this.exercise = exercise;
        this.additionalWeight = additionalWeight;
        this.reps = reps;
        this.sets = sets;
        this.time = time;
    }

    public String getDate()
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

    public float getTime()
    {
        return time;
    }

    public float getAdditionalWeight()
    {
        return additionalWeight;
    }

    public String getExercise()
    {
        return exercise;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setBodyweight(float bodyweight)
    {
        this.bodyweight = bodyweight;
    }

    public void setExercise(String exercise)
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

    public void setTime(float time)
    {
        this.time = time;
    }

    public void setAdditionalWeight(float additionalWeight)
    {
        this.additionalWeight = additionalWeight;
    }

    @Override
    public String toString()
    {
        return date + "," + bodyweight + "," + exercise + "," + additionalWeight + "," + reps + "," + sets + "," + time;
    }
}
