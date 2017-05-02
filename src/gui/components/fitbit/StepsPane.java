package gui.components.fitbit;

import fitbit.Fitbit;
import fitbit.FitbitAuthenticationException;
import fitbit.StepCount;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.joda.time.LocalDate;

import static util.MainUtility.DATE_FORMAT_LONG;

/**
 * Created by dcmeade on 5/2/2017.
 */
public class StepsPane extends FlowPane
{
    public StepsPane()
    {
        setPadding(new Insets(5, 0, 5, 0));
        setVgap(4);
        setHgap(4);

        Fitbit fitbit = null; // TODO make this read in csv
        try
        {
            fitbit = Fitbit.create( "dcmstrat@gmail.com", "Spagett94");
        }
        catch (FitbitAuthenticationException e)
        {
            e.printStackTrace();
        }

        // Load the

        for (StepCount sc : fitbit.getStepCount(LocalDate.now()))
        {
            Label stepLabel = new Label(DATE_FORMAT_LONG.format(sc.getInterval().getStart().toDate()) + " " + sc.getValue());
            stepLabel.setMinWidth(1900);

            //getChildren().add(stepLabel);
        }
    }
}
