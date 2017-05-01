package data_control;

import java.util.Arrays;
import java.util.List;

import static data_control.MuscleGroup.*;

/**
 * Created by dcmeade on 4/24/2017.
 */
public enum Exercise
{
	   PARALLEL_BAR_DIPS("Parallel Bar Dips",      true,  false, CHEST, SHOULDERS, TRICEPS),
               RING_DIPS("Ring Dips",              true,  false, CHEST, SHOULDERS, TRICEPS),
	    HANDSTAND_PUSHUP("Handstand Pushup",       true,  false, SHOULDERS, TRICEPS),
	              PUSHUP("Pushup",                 true,  false, CHEST, TRICEPS, SHOULDERS),
	    CLAPPING_PUSHUPS("Clapping Pushup",        true,  false, CHEST, TRICEPS, SHOULDERS),
	     REAR_DELT_FLIES("Rear Delt Flies",        false, false, SHOULDERS),
     ONE_LEG_CALVE_RAISE("One Leg Calve Raise",    true,  true,  CALVES),
	 TWO_LEG_CALVE_RAISE("Two Leg Calve Raise",    true,  false, CALVES),
            SANDBAG_CURL("Sandbag Curl",           false, true,  BICEPS, FOREARMS),
    SANDBAG_REVERSE_CURL("Sandbag Reverse Curl",   false, true,  BICEPS, FOREARMS),
  RING_TRICEP_EXT_BUTTON("Ring Tricep Extension Belly Button", true, false, TRICEPS, ABS),
	 SANDBAG_SHOULDERING("Sandbag Shouldering",    false, false, BACK_NON_LATS_ERECTORS, ERECTORS, ABS, LATS, FOREARMS, CHEST, HAMSTRINGS, QUADS, SHOULDERS, GLUTES),
	SANDBAG_GOOD_MORNING("Sandbag Good Morning",   false, false, BACK_NON_LATS_ERECTORS, ERECTORS, ABS, LATS, HAMSTRINGS, GLUTES),
		  BEAR_HUG_SQUAT("Bear Hug Squat",         true,  false, BACK_NON_LATS_ERECTORS, LATS, ABS, GLUTES, QUADS, CHEST),
	               LUNGE("Lunge",                  true,  false, QUADS, GLUTES),
	        RING_PULLUPS("Ring Pullups",           true,  false, LATS, BICEPS, FOREARMS),
		AROUND_THE_WORLD("Around The World",       false, true,  ABS, SHOULDERS),
	               SITUP("Situp",                  true,  false, ABS),
	   KNEE_RING_ROLLOUT("Knee Ring Rollout",      true,  false, ABS, LATS),
	       TURKISH_GETUP("Turkish Getup",          true,  false, ABS),
	          TOE_TO_BAR("Toe To Bar",             true,  false, ABS, FOREARMS),
	   WINDSHIELD_WIPERS("Windshield Wipers",      true,  false, ABS, FOREARMS),
	       RUSSIAN_TWIST("Russian Twist",          true,  true,  ABS),
		  REGULAR_PULLUP("Regular Pullup",         true,  false, LATS, BICEPS, FOREARMS),
		   BICEPS_PULLUP("Biceps Pullup",          true,  false, LATS, BICEPS, FOREARMS),
		  PULLUP_PIPE_15("Pullup pipe 1.5in",      true,  false, LATS, BICEPS, FOREARMS),
		   PULLUP_PIPE_2("Pullup pipe 2in",        true,  false, LATS, BICEPS, FOREARMS),
	   CANNONBALL_PULLUP("Cannonball Pullup",      true,  false, LATS, BICEPS, FOREARMS),
	        TOWEL_PULLUP("Towel Pullup",           true,  false, LATS, BICEPS, FOREARMS),
		       MUSCLE_UP("Muscle Up",              true,  false, LATS, BICEPS, FOREARMS, TRICEPS, SHOULDERS, CHEST),
	           NECK_CURL("Neck Curl",              false, false, NECK),
	            NECK_EXT("Neck Extension",         false, false, NECK),
	   BOARD_2_PINCH_SEC("2 Board Pinch One Hand", false, true,  FOREARMS),
	   BOARD_3_PINCH_SEC("3 Board Pinch One Hand", false, true,  FOREARMS),
HAND_2_2_BOARD_PINCH_SEC("2 Board Pinch Two Hand", false, true,  FOREARMS),
HAND_2_3_BOARD_PINCH_SEC("3 Board Pinch Two Hand", false, true,  FOREARMS),
            HAND_GRIPPER("Hand Gripper",           false, true,  FOREARMS)
    ;

    public final String exerciseName;
    public final List<MuscleGroup> muscleGroupsList;
    public final boolean bodyweight;
	public final boolean unilateral;

    Exercise(String exerciseName, boolean bodyweight, boolean unilateral, MuscleGroup... muscleGroupsList)
    {
        this.exerciseName = exerciseName;
        this.bodyweight = bodyweight;
	    this.unilateral = unilateral;
        this.muscleGroupsList = Arrays.asList(muscleGroupsList);
    }

	public static Exercise nameToEnum(String findName)
	{
		for (Exercise exercise: Exercise.values())
		{
			if (exercise.exerciseName.equals(findName))
			{
				return exercise;
			}
		}

		return null;
	}
}
