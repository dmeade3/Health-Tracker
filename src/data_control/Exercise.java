package data_control;

import java.util.Arrays;
import java.util.List;

import static data_control.MuscleGroup.*;

/**
 * Created by dcmeade on 4/24/2017.
 */
public enum Exercise
{
	   PARALLEL_BAR_DIPS("Parallel Bar Dips",      true,  CHEST, SHOULDERS, TRICEPS),
               RING_DIPS("Ring Dips",              true,  CHEST, SHOULDERS, TRICEPS),
	    HANDSTAND_PUSHUP("Handstand Pushup",       true,  SHOULDERS, TRICEPS),
	              PUSHUP("Pushup",                 true,  CHEST, TRICEPS, SHOULDERS),
	    CLAPPING_PUSHUPS("Clapping Puhsup",        true,  CHEST, TRICEPS, SHOULDERS),
	     REAR_DELT_FLIES("Rear Delt Flies",        false, SHOULDERS),
     ONE_LEG_CALVE_RAISE("One Leg Calve Raise",    true,  CALVES),
	 TWO_LEG_CALVE_RAISE("Two Leg Calve Raise",    true,  CALVES),
            SANDBAG_CURL("Sandbag Curl",           false, BICEPS, FOREARMS),
    SANDBAG_REVERSE_CURL("Sandbag Reverse Curl",   false, BICEPS, FOREARMS),
  RING_TRICEP_EXT_BUTTON("Ring Tricep Extension Belly Button", true, TRICEPS, ABS),
	 SANDBAG_SHOULDERING("Sandbag Shouldering",    false, BACK_NON_LATS_ERECTORS, ERECTORS, ABS, LATS, FOREARMS, CHEST, HAMSTRINGS, QUADS, SHOULDERS, GLUTES),
	SANDBAG_GOOD_MORNING("Sandbag Good Morning",   false, BACK_NON_LATS_ERECTORS, ERECTORS, ABS, LATS, HAMSTRINGS, GLUTES),
		  BEAR_HUG_SQUAT("Bear Hug Squat",         true,  BACK_NON_LATS_ERECTORS, LATS, ABS, GLUTES, QUADS, CHEST),
	               LUNGE("Lunge",                  true,  QUADS, GLUTES),
	        RING_PULLUPS("Ring Pullups",           true,  LATS, BICEPS, FOREARMS),
		AROUND_THE_WORLD("Around The World",       false, ABS, SHOULDERS),
	               SITUP("Situp",                  true,  ABS),
	   KNEE_RING_ROLLOUT("Knee Ring Rollout",      true,  ABS),
	       TURKISH_GETUP("Turkish Getup",          true,  ABS),
	          TOE_TO_BAR("Toe To Bar",             true,  ABS, FOREARMS),
	   WINDSHIELD_WIPERS("Windshield Wipers",      true,  ABS, FOREARMS),
	       RUSSIAN_TWIST("Russian Twist",          true,  ABS),
		  REGULAR_PULLUP("Regular Pullup",         true,  LATS, BICEPS, FOREARMS),
		   BICEPS_PULLUP("Biceps Pullup",          true,  LATS, BICEPS, FOREARMS),
		  PULLUP_PIPE_15("Pullup pipe 1.5in",      true,  LATS, BICEPS, FOREARMS),
		   PULLUP_PIPE_2("Pullup pipe 2in",        true,  LATS, BICEPS, FOREARMS),
	   CANNONBALL_PULLUP("Cannonball Pullup",      true,  LATS, BICEPS, FOREARMS),
	        TOWEL_PULLUP("Towel Pullup",           true,  LATS, BICEPS, FOREARMS),
		       MUSCLE_UP("Muscle Up",              true,  LATS, BICEPS, FOREARMS, TRICEPS, SHOULDERS, CHEST),
	           NECK_CURL("Neck Curl",              false, NECK),
	            NECK_EXT("Neck Extension",         false, NECK),
	   BOARD_2_PINCH_SEC("2 Board Pinch One Hand", false, FOREARMS),
	   BOARD_3_PINCH_SEC("3 Board Pinch One Hand", false, FOREARMS),
HAND_2_2_BOARD_PINCH_SEC("2 Board Pinch Two Hand", false, FOREARMS),
HAND_2_3_BOARD_PINCH_SEC("3 Board Pinch Two Hand", false, FOREARMS)
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
