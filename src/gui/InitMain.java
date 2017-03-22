package gui;

import data_control.DataManager;
import data_control.WodEntry;
import data_control.WorkoutEntry;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import static util.Constants.*;
import static util.MainUtility.getDatesForDropDown;
import static util.MainUtility.getUsers;
import static util.MainUtility.loadExercises;

/**
 * Created by dcmeade on 3/21/2017.
 */
public class InitMain
{
    final static Logger logger = Logger.getLogger(InitMain.class);

    private static GridPane mainGrid;
    private static TextField repsTextField;
    private static TextField setTextField;
    private static TextField timeTextField;
    private static TextField bodyweightTextField;
    private static TextField additionalWeightTextField;
    private static ComboBox exercises;
    private static ComboBox datesComboBox;
    private static ComboBox userComboBox;
    private static Button submitButton;
    private static Button displayDataButton;
    private static Button adminButton;
    private static Label dateLabel;
    private static Label repsLabel;
    private static Label setsLabel;
    private static Label timeLabel;
    private static Label bodyweightLabel;
    private static Label additionalWeightLabel;
    private static Label exerciseLabel;
    private static Label userLabel;
    private static ListView<String> listInfo;
    private static ObservableList<String> workoutData;

    public static void initMain(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, MAIN_PAGE_WIDTH, MAIN_PAGE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(PROJECT_TITLE);
        stage.setMaxHeight(MAIN_PAGE_HEIGHT);
        stage.setMaxWidth(MAIN_PAGE_WIDTH);

        initMainGrid(scene);
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

    private static void initMainGrid(Scene scene)
    {
        mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10, 10, 10, 10)); // Gap around border in pixels
        mainGrid.setVgap(15); // Gap between children in pixels
        mainGrid.setHgap(15); // Gap between children in pixels

        scene.setRoot(mainGrid);

        // sets up main grid for gridpane
        int rowCount = 50;
        int colCount = 50;

        // Set row heights
        for (int i = 0; i < rowCount; i++)
        {
            RowConstraints row = new RowConstraints(MAIN_PAGE_HEIGHT / rowCount);
            mainGrid.getRowConstraints().add(row);
        }

        // Set col heights
        for (int i = 0; i < colCount; i++)
        {
            ColumnConstraints col = new ColumnConstraints(MAIN_PAGE_WIDTH / colCount);
            mainGrid.getColumnConstraints().add(col);
        }
    }

    private static void initTextFields()
    {
        additionalWeightTextField = new TextField();
        additionalWeightTextField.setPromptText("Additional Weight...");
        GridPane.setConstraints(additionalWeightTextField, 3, 2, 3, 1);
        mainGrid.getChildren().add(additionalWeightTextField);

        repsTextField = new TextField();
        repsTextField.setPromptText("Reps...");
        GridPane.setConstraints(repsTextField, 3, 3, 3, 1);
        mainGrid.getChildren().add(repsTextField);

        setTextField = new TextField();
        setTextField.setPromptText("Sets...");
        GridPane.setConstraints(setTextField, 3, 4, 3, 1);
        mainGrid.getChildren().add(setTextField);

        timeTextField = new TextField();
        timeTextField.setPromptText("Time...");
        GridPane.setConstraints(timeTextField, 3, 5, 3, 1);
        mainGrid.getChildren().add(timeTextField);

        bodyweightTextField = new TextField();
        bodyweightTextField.setPromptText("Bodyweight...");
        GridPane.setConstraints(bodyweightTextField, 3, 6, 3, 1);
        mainGrid.getChildren().add(bodyweightTextField);
    }

    private static void initButtons()
    {
        submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 3, 7, 3, 3);
        mainGrid.getChildren().add(submitButton);

        displayDataButton = new Button("Display Data");
        GridPane.setConstraints(displayDataButton, 3, 10, 5, 3);
        mainGrid.getChildren().add(displayDataButton);

        adminButton = new Button("Admin");
        GridPane.setConstraints(adminButton, 3, 11, 3, 3);
        mainGrid.getChildren().add(adminButton);
    }

    private static void initComboBoxes()
    {
        ObservableList<String> exerciseOptions = loadExercises();

        // load wods
        for (WodEntry wod : WodEntry.values())
        {
            exerciseOptions.add(wod.getWorkoutEntry().getExercise());
        }

        exercises = new ComboBox(exerciseOptions);
        GridPane.setConstraints(exercises, 2, 1, 5, 1);
        mainGrid.getChildren().add(exercises);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Load dates
        ObservableList<String> dateOptions = getDatesForDropDown();
        datesComboBox = new ComboBox(dateOptions);
        datesComboBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(datesComboBox, 2, 0, 5, 1);
        mainGrid.getChildren().add(datesComboBox);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ObservableList<String> users = getUsers();
        userComboBox = new ComboBox(users);

        userComboBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(userComboBox, 3, 7, 5, 1);
        mainGrid.getChildren().add(userComboBox);
    }

    private static void initLabels()
    {
        dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 1, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        mainGrid.getChildren().add(dateLabel);

        exerciseLabel = new Label("Exercise");
        GridPane.setConstraints(exerciseLabel, 0, 1);
        GridPane.setColumnSpan(exerciseLabel, 4);
        mainGrid.getChildren().add(exerciseLabel);

        additionalWeightLabel = new Label("Added Weight");
        GridPane.setConstraints(additionalWeightLabel, 0, 2);
        GridPane.setColumnSpan(additionalWeightLabel, 4);
        mainGrid.getChildren().add(additionalWeightLabel);

        repsLabel = new Label("Reps");
        GridPane.setConstraints(repsLabel, 1, 3);
        GridPane.setColumnSpan(repsLabel, 2);
        mainGrid.getChildren().add(repsLabel);

        setsLabel = new Label("Sets");
        GridPane.setConstraints(setsLabel, 1, 4);
        GridPane.setColumnSpan(setsLabel, 2);
        mainGrid.getChildren().add(setsLabel);

        timeLabel = new Label("Time");
        GridPane.setConstraints(timeLabel, 1, 5);
        GridPane.setColumnSpan(timeLabel, 2);
        mainGrid.getChildren().add(timeLabel);

        bodyweightLabel = new Label("Bodyweight");
        GridPane.setConstraints(bodyweightLabel, 0, 6);
        GridPane.setColumnSpan(bodyweightLabel, 3);
        mainGrid.getChildren().add(bodyweightLabel);

        userLabel = new Label("User");
        GridPane.setConstraints(userLabel, 1, 7);
        GridPane.setColumnSpan(userLabel, 2);
        mainGrid.getChildren().add(userLabel);
    }

    private static void initListView()
    {
        workoutData = FXCollections.observableArrayList();
        listInfo = new ListView<>(workoutData);

        listInfo.setPrefSize(600, MAIN_PAGE_HEIGHT);
        listInfo.setEditable(true);

        writeToConsole(".............................................................Output.............................................................");
        listInfo.setItems(workoutData);
        mainGrid.add(listInfo, 10, 0, 15, 27);
    }

    private static void initActions(Stage stage)
    {
        // Stop application on gui exit
        stage.setOnCloseRequest(e -> Platform.exit());

        // Display data page
        displayDataButton.setOnAction(e ->
        {
            Stage displayDataStage = new Stage();

            InitDataDisplay.initDataDisplay(displayDataStage);

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

        // listener for if the exercise that is selected is a wod
        exercises.setOnAction(e ->
        {
            if (exercises.getSelectionModel().getSelectedItem() != null)
            {
                if (exercises.getSelectionModel().getSelectedItem().toString().contains("Wod "))
                {
                    setTextField.setDisable(true);
                    repsTextField.setDisable(true);
                    additionalWeightTextField.setText("0");
                }
                else
                {
                    setTextField.setDisable(false);
                    repsTextField.setDisable(false);
                }
            }
        });

        // Commit a workout entry
        submitButton.setOnAction(e ->
        {
            // Check if the fields are filled first except for time, it is optional
            WorkoutEntry workoutEntry = null;

            try
            {
                Float time = 0f;

                try
                {
                    time = Float.parseFloat(timeTextField.getText());
                }
                catch (NumberFormatException numExp)
                { // Do nothing, its allowed to be null
                }

                if (exercises.getSelectionModel().getSelectedItem().toString().contains("Wod "))
                {
                    for (WodEntry wodEntry : WodEntry.values())
                    {
                        if (exercises.getSelectionModel().getSelectedItem().toString().contains(wodEntry.getWorkoutEntry().getExercise()))
                        {
                            workoutEntry = wodEntry.getWorkoutEntry();
                            break;
                        }
                    }

                    if (workoutEntry == null)
                    {
                        // something went very wrong because the wod was loaded somewhere but not from the enum
                        throw new Exception("something went very wrong because the wod was loaded somewhere but not from the enum");
                    }
                    else
                    {
                        if (time == 0f)
                        {
                            throw new Exception("For wods time needs to be set");
                        }

                        // Need to make sure date, bodyweight, time are not blank and are set in the workout entry
                        workoutEntry.setDate((String) datesComboBox.getSelectionModel().getSelectedItem());
                        workoutEntry.setBodyweight(Float.valueOf(bodyweightTextField.getText()));
                        workoutEntry.setTime(time);
                    }
                }
                else
                {
                    workoutEntry = new WorkoutEntry((String) datesComboBox.getSelectionModel().getSelectedItem(),
                            Float.valueOf(bodyweightTextField.getText()),
                            (String) exercises.getSelectionModel().getSelectedItem(),
                            Float.valueOf(additionalWeightTextField.getText()),
                            Integer.parseInt(repsTextField.getText()),
                            Integer.parseInt(setTextField.getText()),
                            time);
                }

                // Check if the user is set for the path
                if (datesComboBox.getSelectionModel().getSelectedItem().equals(""))
                {
                    throw new Exception("User cant be blank");
                }

                DataManager.storeWorkoutEntry(workoutEntry, (String) datesComboBox.getSelectionModel().getSelectedItem(), (String) datesComboBox.getSelectionModel().getSelectedItem());
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