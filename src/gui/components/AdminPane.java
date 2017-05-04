package gui.components;

import data_control.DataManager;
import fitbit.Fitbit;
import fitbit.FitbitAuthenticationException;
import fitbit.StepCount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.joda.time.LocalDate;
import util.Constants;
import util.ProgramInfo;
import util.TimeRange;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static util.Constants.ON_EXIT_INFO_PATH;
import static util.MainUtility.DATE_FORMAT_LONG;
import static util.MainUtility.getUsers;

/**
 * Created by dcmeade on 4/10/2017.
 */
public class AdminPane extends TitledPane
{
    private Button refreshDataButton;
	private ComboBox<String> userComboBox;
	private static Label userLabel;

	private LabeledDatePicker startLabeledDatePicker;
	private LabeledDatePicker endLabeledDatePicker;
	private LabeledComboBox timeRangeComboBox;

    public AdminPane()
    {
        super();

        setText("Admin Section");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        refreshDataButton = new Button("Refresh");

	    addUserComboBox(gridPane);

	    addDateRange(gridPane);

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

	private void addUserComboBox(GridPane gridPane)
	{
		ObservableList<String> users = getUsers();
		userComboBox = new ComboBox(users);
		userLabel = new Label("User:");

		String prevUser = loadOnExitDataEntry("user");

		int ctr = 0;

		if (prevUser != null)
		{
			for (String item : users)
			{
				if (item.equals(prevUser))
				{
					break;
				}
				else
				{
					ctr++;
				}
			}
		}

		// Listener for user change
		userComboBox.valueProperty().addListener((ov, t, newValue) ->
		{
			ProgramInfo.CURRENT_USER = newValue;
		});

		userComboBox.getSelectionModel().select(ctr);
		gridPane.add(userLabel, 2, 0);
		gridPane.add(userComboBox, 3, 0);
	}

	public static String loadOnExitDataEntry(String target)
	{
		try
		{
			List<String> lines = Files.readAllLines(Paths.get(ON_EXIT_INFO_PATH));

			for (String s : lines)
			{
				// user
				if (s.split(":")[0].equals(target))
				{
					return s.split(":")[1];
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private void addDateRange(GridPane gridPane)
	{
		startLabeledDatePicker = new LabeledDatePicker(new Date(ProgramInfo.startDate.getTime()).toLocalDate(), "Start Date");
		gridPane.add(startLabeledDatePicker, 6, 0);

		endLabeledDatePicker = new LabeledDatePicker(new Date(ProgramInfo.endDate.getTime()).toLocalDate(), "End Date");
		gridPane.add(endLabeledDatePicker, 8, 0);

		// Make the combobox
		ComboBox<TimeRange> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(TimeRange.values());

		timeRangeComboBox = new LabeledComboBox(comboBox, "Preset Range");
		gridPane.add(timeRangeComboBox, 7, 0);
	}

    private void addListeners(Fitbit fitbit)
    {
        refreshDataButton.setOnMouseClicked(event ->
        {
            System.out.println("Refreshing Program Data Fitbit");

            // TODO Find way to make this very compact but makes sense, maybe make a function that you can pass a list to EX: fitbit.getStepCount(LocalDate.now()) and have
            // TODO the rest taken care of


			// Updates the csv file for steps
            for (StepCount sc : fitbit.getStepCount(LocalDate.now()))
            {
                // Todo Make this string in the StepCount object
                DataManager.fitbitCsvWriter("\"" + DATE_FORMAT_LONG.format(sc.getInterval().getStart().toDate()) + "\"" + "," + "\"" + sc.getValue() + "\"", "steps.csv", Constants.STEPS_CSV_HEADER);
            }



            // TODO make a refresh button for the other sources here too like myfitnesspal and withing




			// TODO find a way to trigger center gui reload here
        });









	    endLabeledDatePicker.getDatePicker().setDayCellFactory((p) -> new DateCell()
	    {
		    java.time.LocalDate minDate;

		    @Override
		    public void updateItem(java.time.LocalDate ld, boolean bln)
		    {
			    super.updateItem(ld, bln);

			    if (startLabeledDatePicker.getDatePicker().getValue() == null)
			    {
				    minDate = java.time.LocalDate.now().minusYears(1000);
			    }
			    else
			    {
				    minDate = startLabeledDatePicker.getDatePicker().getValue();
			    }

			    setDisable(ld.isAfter(java.time.LocalDate.now()) || ld.isBefore(minDate));


			    Instant instant = Instant.from(endLabeledDatePicker.getDatePicker().getValue().atStartOfDay(ZoneId.systemDefault()));

			    ProgramInfo.endDate = Date.from(instant);

			    // TODO
			    //RootPage.reloadCenter(MainProgramDatastore.getInstance().getSelectedMainTabIndex());
		    }
	    });

	    // TODO restrict the end date to be after the start date
	    startLabeledDatePicker.getDatePicker().setDayCellFactory((p) -> new DateCell()
	    {
		    java.time.LocalDate maxDate;

		    @Override
		    public void updateItem(java.time.LocalDate ld, boolean bln)
		    {
			    super.updateItem(ld, bln);

			    maxDate = endLabeledDatePicker.getDatePicker().getValue();

			    setDisable(ld.isAfter(maxDate));


			    Instant instant = Instant.from(startLabeledDatePicker.getDatePicker().getValue().atStartOfDay(ZoneId.systemDefault()));

			    ProgramInfo.startDate = Date.from(instant);

			    // TODO
			    //RootPage.reloadCenter(MainProgramDatastore.getInstance().getSelectedMainTabIndex());
		    }
	    });

	    timeRangeComboBox.getComboBox().valueProperty().addListener(event ->
	    {
		    TimeRange timeRange = (TimeRange) timeRangeComboBox.getComboBox().getSelectionModel().getSelectedItem();

		    endLabeledDatePicker.getDatePicker().setValue(java.time.LocalDate.now());
		    startLabeledDatePicker.getDatePicker().setValue(java.time.LocalDate.now().minusDays(timeRange.getDayCount()));

		    Instant startInstant = Instant.from(startLabeledDatePicker.getDatePicker().getValue().atStartOfDay(ZoneId.systemDefault()));
		    Instant endInstant = Instant.from(endLabeledDatePicker.getDatePicker().getValue().atStartOfDay(ZoneId.systemDefault()));

		    ProgramInfo.startDate = Date.from(startInstant);
		    ProgramInfo.endDate = Date.from(endInstant);

		    // TODO
		    //RootPage.reloadAllButAdminAndBottom(MainProgramDatastore.getInstance().getSelectedMainTabIndex());
	    });
    }
}
