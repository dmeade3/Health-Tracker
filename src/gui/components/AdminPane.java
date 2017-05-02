package gui.components;

import data_control.DataManager;
import fitbit.Fitbit;
import fitbit.FitbitAuthenticationException;
import fitbit.StepCount;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import org.joda.time.LocalDate;
import util.Constants;

import static util.MainUtility.DATE_FORMAT_LONG;

/**
 * Created by dcmeade on 4/10/2017.
 */
public class AdminPane extends TitledPane
{
    private Button refreshDataButton;

    public AdminPane()
    {
        super();

        setText("Admin Section");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        refreshDataButton = new Button("Refresh");

        gridPane.add(refreshDataButton, 0, 0);

        setAlignment(Pos.CENTER);
        setContent(gridPane);
        setCollapsible(false);


        Fitbit fitbit = null; // TODO make this read in csv
        try
        {
            fitbit = Fitbit.create( "dcmstrat@gmail.com", "Spagett94");
        }
        catch (FitbitAuthenticationException e)
        {
            e.printStackTrace();
        }

        addListeners(fitbit);
    }

    private void addListeners(Fitbit fitbit)
    {
        refreshDataButton.setOnMouseClicked(event ->
        {
            System.out.println("Refreshing Program Data");


            // TODO refactor this to be easier to add other refreshers

            // Load Step data to csv

            // TODO for loop through how many days of data you are going to keep track of, unless it takes a long time then find another way






            for (StepCount sc : fitbit.getStepCount(LocalDate.now()))
            {

                // Todo Make this string in the StepCount object
                DataManager.fitbitCsvWriter("\"" + DATE_FORMAT_LONG.format(sc.getInterval().getStart().toDate()) + "\"" + "," + "\"" + sc.getValue() + "\"", "steps.csv", Constants.STEPS_CSV_HEADER);
            }






        });
    }
}
