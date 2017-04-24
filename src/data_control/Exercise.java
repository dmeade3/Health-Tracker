package data_control;

import java.util.Arrays;
import java.util.List;

import static data_control.MuscleGroup.*;

/**
 * Created by dcmeade on 4/24/2017.
 */
public enum Exercise
{
    /*
    # Chest Shoulders
    Ring Dips

    # Arms
    Sandbag Curl
    Sandbag Reverse Curl
    Sandbag Tri Ext

    # Back
    Reg Pullup
    Palms Facing Pullup

    # Legs
    Sandbag Shouldering
    Lunges
    One Leg Calf Raise

    # Abs
    Situps
    Windshield Wipers
    Toe to Bar

    # Neck
    Neck Curl
    */

    PARALLEL_BAR_DIPS("Parallel Bar Dips", true, CHEST, SHOULDERS, TRICEPS),
    RING_DIPS("Ring Dips", true, CHEST, SHOULDERS, TRICEPS),
    ONE_LEG_CALVE_RAISE("One Leg Calve Raise", true, CALVES)
    ;


    public final String exerciseName;
    public final List<MuscleGroup> muscleGroupsList;
    public final boolean bodyweight;

    Exercise(String exerciseName, boolean bodyweight, MuscleGroup... muscleGroupsList)
    {
        this.exerciseName = exerciseName;
        this.bodyweight = bodyweight;
        this.muscleGroupsList = Arrays.asList(muscleGroupsList);
    }
}
