package gui.components;

import gui.components.fitbit.StepsPane;
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

        Tab bodyfatTab =           new Tab("Bodyfat%");
        Tab waterPercentageTab =   new Tab("Water%");
        Tab boneMassTab =          new Tab("Bone Mass");
        Tab floorsTab =            new Tab("Floors");
        Tab caloriesEatenTab =     new Tab("Calories Eaten");
        Tab caloriesBurnedTab =    new Tab("Calories Burned");
        Tab caloriesLeftToEatTab = new Tab("Calories Left To Eat");
        Tab heartRateTab =         new Tab("Heart Rate");
        Tab sleepTab =             new Tab("Sleep");
        Tab waterConsumedTab =     new Tab("Water Consumed");
        Tab activeMinutesTab =     new Tab("Active Minutes");
        Tab restingHeartRateTab =  new Tab("Resting Heart Rate");
        Tab pulseWaveVelocityTab = new Tab("Pulse Wave Velocity");
        Tab weightTab =            new Tab("Weight");
        Tab bloodPressureTab =     new Tab("Blood Pressure");
        Tab stepsTab =             new Tab("Steps", new StepsPane());
        Tab goalsTab =             new Tab("Goals");
        Tab exerciseTab =          new Tab("Exercise", new WorkoutPageGridpane(stage));

        // Order here matters
        getTabs().add(stepsTab);
        getTabs().add(bodyfatTab);
        getTabs().add(waterPercentageTab);
        getTabs().add(boneMassTab);
        getTabs().add(floorsTab);
        getTabs().add(caloriesEatenTab);
        getTabs().add(caloriesBurnedTab);
        getTabs().add(caloriesLeftToEatTab);
        getTabs().add(heartRateTab);
        getTabs().add(sleepTab);
        getTabs().add(waterConsumedTab);
        getTabs().add(activeMinutesTab);
        getTabs().add(restingHeartRateTab);
        getTabs().add(pulseWaveVelocityTab);
        getTabs().add(weightTab);
        getTabs().add(bloodPressureTab);
        getTabs().add(goalsTab);
        getTabs().add(exerciseTab);

	    // Make tabs non deletable
	    for (Tab tab : getTabs())
	    {
			tab.closableProperty().setValue(false);
	    }
    }
}
