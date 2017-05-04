package data_control.workout;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dcmeade on 4/24/2017.
 */
public enum Exercise
{
	   PARALLEL_BAR_DIPS("Parallel Bar Dips",      true,  false, MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS),
               RING_DIPS("Ring Dips",              true,  false, MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS),
	    HANDSTAND_PUSHUP("Handstand Pushup",       true,  false, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS),
	              PUSHUP("Pushup",                 true,  false, MuscleGroup.CHEST, MuscleGroup.TRICEPS, MuscleGroup.SHOULDERS),
	    CLAPPING_PUSHUPS("Clapping Pushup",        true,  false, MuscleGroup.CHEST, MuscleGroup.TRICEPS, MuscleGroup.SHOULDERS),
	     REAR_DELT_FLIES("Rear Delt Flies",        false, false, MuscleGroup.SHOULDERS),
     ONE_LEG_CALVE_RAISE("One Leg Calve Raise",    true,  true,  MuscleGroup.CALVES),
	 TWO_LEG_CALVE_RAISE("Two Leg Calve Raise",    true,  false, MuscleGroup.CALVES),
            SANDBAG_CURL("Sandbag Curl",           false, true,  MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
    SANDBAG_REVERSE_CURL("Sandbag Reverse Curl",   false, true,  MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
  RING_TRICEP_EXT_BUTTON("Ring Tricep Extension Belly Button", true, false, MuscleGroup.TRICEPS, MuscleGroup.ABS),
	 SANDBAG_SHOULDERING("Sandbag Shouldering",    false, false, MuscleGroup.BACK_NON_LATS_ERECTORS, MuscleGroup.ERECTORS, MuscleGroup.ABS, MuscleGroup.LATS, MuscleGroup.FOREARMS, MuscleGroup.CHEST, MuscleGroup.HAMSTRINGS, MuscleGroup.QUADS, MuscleGroup.SHOULDERS, MuscleGroup.GLUTES),
	SANDBAG_GOOD_MORNING("Sandbag Good Morning",   false, false, MuscleGroup.BACK_NON_LATS_ERECTORS, MuscleGroup.ERECTORS, MuscleGroup.ABS, MuscleGroup.LATS, MuscleGroup.HAMSTRINGS, MuscleGroup.GLUTES),
	               LUNGE("Lunge",                  true,  false, MuscleGroup.QUADS, MuscleGroup.GLUTES),
	         SISSY_SQUAT("Sissy Squat",            true,  false, MuscleGroup.QUADS),
	        RING_PULLUPS("Ring Pullups",           true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
		AROUND_THE_WORLD("Around The World",       false, true,  MuscleGroup.ABS, MuscleGroup.SHOULDERS),
	               SITUP("Situp",                  true,  false, MuscleGroup.ABS),
	   KNEE_RING_ROLLOUT("Knee Ring Rollout",      true,  false, MuscleGroup.ABS, MuscleGroup.LATS),
	       TURKISH_GETUP("Turkish Getup",          true,  false, MuscleGroup.ABS),
	          TOE_TO_BAR("Toe To Bar",             true,  false, MuscleGroup.ABS, MuscleGroup.FOREARMS),
	   WINDSHIELD_WIPERS("Windshield Wipers",      true,  false, MuscleGroup.ABS, MuscleGroup.FOREARMS),
	       RUSSIAN_TWIST("Russian Twist",          true,  true,  MuscleGroup.ABS),
		  REGULAR_PULLUP("Regular Pullup",         true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
		   BICEPS_PULLUP("Biceps Pullup",          true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
		  PULLUP_PIPE_15("Pullup pipe 1.5in",      true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
		   PULLUP_PIPE_2("Pullup pipe 2in",        true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
	   CANNONBALL_PULLUP("Cannonball Pullup",      true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
	        TOWEL_PULLUP("Towel Pullup",           true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS),
		       MUSCLE_UP("Muscle Up",              true,  false, MuscleGroup.LATS, MuscleGroup.BICEPS, MuscleGroup.FOREARMS, MuscleGroup.TRICEPS, MuscleGroup.SHOULDERS, MuscleGroup.CHEST),
	           NECK_CURL("Neck Curl",              false, false, MuscleGroup.NECK),
	            NECK_EXT("Neck Extension",         false, false, MuscleGroup.NECK),
	   BOARD_2_PINCH_SEC("2 Board Pinch One Hand", false, true,  MuscleGroup.FOREARMS),
	   BOARD_3_PINCH_SEC("3 Board Pinch One Hand", false, true,  MuscleGroup.FOREARMS),
HAND_2_2_BOARD_PINCH_SEC("2 Board Pinch Two Hand", false, true,  MuscleGroup.FOREARMS),
HAND_2_3_BOARD_PINCH_SEC("3 Board Pinch Two Hand", false, true,  MuscleGroup.FOREARMS),
            HAND_GRIPPER("Hand Gripper",           false, true,  MuscleGroup.FOREARMS)
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
