package gui.components;

import data_control.DataManager;
import data_control.Exercise;
import data_control.WorkoutEntry;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import util.ProgramInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.*;
import static util.MainUtility.*;

/**
 * Created by dcmeade on 4/27/2017.
 */
public class MainPageGridpane extends GridPane
{
    final static Logger logger = Logger.getLogger(MainPageGridpane.class);

    private static TextField repsTextField;
    private static TextField setTextField;
    private static TextField bodyweightTextField;
    private static TextField additionalWeightTextField;
    private static ComboBox<Exercise> exercises;
    private static ComboBox datesComboBox;
    private static ComboBox userComboBox;
    private static Button submitButton;
    private static Button displayDataButton;
    private static Button adminButton;
    private static Label dateLabel;
    private static Label repsLabel;
    private static Label setsLabel;
    private static Label bodyweightLabel;
    private static Label additionalWeightLabel;
    private static Label exerciseLabel;
    private static Label userLabel;
    private static ListView<String> listInfo;
    private static ObservableList<String> workoutData;

    public MainPageGridpane(Stage stage)
    {
        super();

        initSelf();
        initTextFields();
        initComboBoxes();
        initButtons();
        initLabels();
        initListView();
        initActions(stage);
    }

    public static void writeToConsole(String s)
    {
        workoutData.add(s);
    }

    private void initSelf()
    {
        setPadding(new Insets(10, 10, 10, 10)); // Gap around border in pixels
        setVgap(15); // Gap between children in pixels
        setHgap(15); // Gap between children in pixels


        // sets up main grid for gridpane
        int rowCount = 50;
        int colCount = 50;

        // Set row heights
        for (int i = 0; i < rowCount; i++)
        {
            RowConstraints row = new RowConstraints(MAIN_PAGE_HEIGHT / rowCount);
            getRowConstraints().add(row);
        }

        // Set col heights
        for (int i = 0; i < colCount; i++)
        {
            ColumnConstraints col = new ColumnConstraints(MAIN_PAGE_WIDTH / colCount);
            getColumnConstraints().add(col);
        }
    }

    private void initTextFields()
    {
        additionalWeightTextField = new TextField();
        additionalWeightTextField.setPromptText("Additional Weight...");
        GridPane.setConstraints(additionalWeightTextField, 3, 2, 3, 1);
        getChildren().add(additionalWeightTextField);

        repsTextField = new TextField();
        repsTextField.setPromptText("Reps...");
        GridPane.setConstraints(repsTextField, 3, 3, 3, 1);
        getChildren().add(repsTextField);

        setTextField = new TextField();
        setTextField.setPromptText("Sets...");
        GridPane.setConstraints(setTextField, 3, 4, 3, 1);
        getChildren().add(setTextField);

        bodyweightTextField = new TextField();
        bodyweightTextField.setPromptText("Bodyweight...");
        bodyweightTextField.setText(loadOnExitDataEntry("bodyweight"));
        GridPane.setConstraints(bodyweightTextField, 3, 6, 3, 1);
        getChildren().add(bodyweightTextField);
    }

    private void initButtons()
    {
        submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 3, 7, 3, 3);
        getChildren().add(submitButton);

        displayDataButton = new Button("Display Data");
        GridPane.setConstraints(displayDataButton, 3, 10, 5, 3);
        getChildren().add(displayDataButton);

        adminButton = new Button("Admin");
        GridPane.setConstraints(adminButton, 3, 11, 3, 3);
        getChildren().add(adminButton);
    }

    private void initComboBoxes()
    {
        // TODO make this take an exercise object
        ObservableList<Exercise> exerciseOptions = loadExercises();

        exercises = new ComboBox(exerciseOptions);

        exercises.setCellFactory(new Callback<ListView<Exercise>,ListCell<Exercise>>()
        {
            @Override
            public ListCell<Exercise> call(ListView<Exercise> p)
            {
                final ListCell<Exercise> cell = new ListCell<Exercise>()
                {
                    @Override
                    protected void updateItem(Exercise exercise, boolean bln)
                    {
                        super.updateItem(exercise, bln);

                        if(exercise != null)
                        {
                            setText(exercise.exerciseName);
                        }
                        else
                        {
                            setText(null);
                        }
                    }
                };

                return cell;
            }
        });

        GridPane.setConstraints(exercises, 2, 1, 5, 1);
        getChildren().add(exercises);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Load dates
        ObservableList<String> dateOptions = getDatesForDropDown();
        datesComboBox = new ComboBox(dateOptions);
        datesComboBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(datesComboBox, 2, 0, 5, 1);
        getChildren().add(datesComboBox);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ObservableList<String> users = getUsers();
        userComboBox = new ComboBox(users);

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
        userComboBox.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override public void changed(ObservableValue ov, String t, String newValue)
            {
                ProgramInfo.CURRENT_USER = newValue;
            }
        });

        userComboBox.getSelectionModel().select(ctr);
        GridPane.setConstraints(userComboBox, 3, 7, 5, 1);
        getChildren().add(userComboBox);
    }

    private void initLabels()
    {
        dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 1, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        getChildren().add(dateLabel);

        exerciseLabel = new Label("Exercise");
        GridPane.setConstraints(exerciseLabel, 0, 1);
        GridPane.setColumnSpan(exerciseLabel, 4);
        getChildren().add(exerciseLabel);

        additionalWeightLabel = new Label("Added Weight");
        GridPane.setConstraints(additionalWeightLabel, 0, 2);
        GridPane.setColumnSpan(additionalWeightLabel, 4);
        getChildren().add(additionalWeightLabel);

        repsLabel = new Label("Reps or Secs");
        GridPane.setConstraints(repsLabel, 0, 3);
        GridPane.setColumnSpan(repsLabel, 4);
        getChildren().add(repsLabel);

        setsLabel = new Label("Sets");
        GridPane.setConstraints(setsLabel, 1, 4);
        GridPane.setColumnSpan(setsLabel, 2);
        getChildren().add(setsLabel);

        bodyweightLabel = new Label("Bodyweight");
        GridPane.setConstraints(bodyweightLabel, 0, 6);
        GridPane.setColumnSpan(bodyweightLabel, 3);
        getChildren().add(bodyweightLabel);

        userLabel = new Label("User");
        GridPane.setConstraints(userLabel, 1, 7);
        GridPane.setColumnSpan(userLabel, 2);
        getChildren().add(userLabel);
    }

    private void initListView()
    {
        workoutData = FXCollections.observableArrayList();
        listInfo = new ListView<>(workoutData);

        listInfo.setPrefSize(600, MAIN_PAGE_HEIGHT);
        listInfo.setEditable(true);

        writeToConsole(".............................................................Output.............................................................");
        listInfo.setItems(workoutData);
        add(listInfo, 10, 0, 15, 27);
    }

    private static void writeOutPreviousInfo(List<String> info)
    {
        File fnew = new File(ON_EXIT_INFO_PATH);
        FileWriter fileWriter = null;

        try
        {
            fnew.createNewFile();

            fileWriter = new FileWriter(fnew);

            for (String s : info)
            {
                fileWriter.write(s + "\n");
            }
        }
        catch (IOException e)
        {
            logger.warn("Could not overwrite onExit file: " + e);
        }
        finally
        {
            try
            {
                fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static String loadOnExitDataEntry(String target)
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

    private static void initActions(Stage stage)
    {
        // Stop application on gui exit
        stage.setOnCloseRequest(e ->
        {
            // Call the write out Previous info

            List<String> info = new ArrayList<>();

            // Collect data i want to write out to load on next start
            info.add("user:" + userComboBox.getSelectionModel().getSelectedItem());

            if (bodyweightTextField.getText() != null)
            {
                info.add("bodyweight:" + bodyweightTextField.getText());
            }

            writeOutPreviousInfo(info);

            Platform.exit();
        });

        // Display data page
        displayDataButton.setOnAction(e ->
        {
            Stage displayDataStage = new Stage();
            Scene scene = new Scene(new DataDisplayGridpane(), DATA_DISPLAY_PAGE_WIDTH, DATA_DISPLAY_PAGE_HEIGHT);
            displayDataStage.setScene(scene);

            stage.setTitle("Data Display");

            displayDataStage.show();
        });

        // Display admin page
        adminButton.setOnAction(e ->
        {
            // TODO
            Stage displayAdminStage = new Stage();

            //InitDataDisplay.initDataDisplay(displayAdminStage);

            //displayAdminStage.show();
        });

        // Commit a workout entry
        submitButton.setOnAction(e ->
        {
            // Check if the fields are filled first except for time, it is optional
            WorkoutEntry workoutEntry = null;

            try
            {
                workoutEntry = new WorkoutEntry(
                        DATE_FORMAT.parse(datesComboBox.getSelectionModel().getSelectedItem().toString()),
                        Float.valueOf(bodyweightTextField.getText()),
                        exercises.getSelectionModel().getSelectedItem(),
                        Float.valueOf(additionalWeightTextField.getText()),
                        Integer.parseInt(repsTextField.getText()),
                        Integer.parseInt(setTextField.getText())
                );

                // Check if the user is set for the path
                if (datesComboBox.getSelectionModel().getSelectedItem().equals(""))
                {
                    throw new Exception("User cant be blank");
                }

                DataManager.storeWorkoutEntry(workoutEntry, (String) userComboBox.getSelectionModel().getSelectedItem(), (String) datesComboBox.getSelectionModel().getSelectedItem());
            }
            catch (Exception ex)
            {
                logger.warn("Formatting error when submitting workout entry: " + ex);
                writeToConsole("Formatting error when submitting workout entry");
                writeToConsole(ex.getMessage());
            }
        });
    }
}
