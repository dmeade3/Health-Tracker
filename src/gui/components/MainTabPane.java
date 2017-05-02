package gui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Created by dcmeade on 5/2/2017.
 */
public class MainTabPane extends TabPane
{
    public MainTabPane(Stage stage)
    {
        super();

        /*
        *   Floors
            Cals Burned
            Cals Left to Eat
            Heart Rate
            Sleep
            Water
            Active Minutes
            Resting Heart Rate
            Velocity thing for withings scale
            Weight
            Blood Pressure
            Steps
            Health Goals
            Exercise
        * */

        Tab exerciseTab = new Tab("Exercise", new WorkoutPageGridpane(stage));



        getTabs().add(exerciseTab);
    }
}
