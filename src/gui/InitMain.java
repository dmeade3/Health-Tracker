package gui;

import data_control.DataManager;
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

import static util.Constants.MAIN_PAGE_HEIGHT;
import static util.Constants.MAIN_PAGE_WIDTH;
import static util.Constants.PROJECT_TITLE;
import static util.MainUtility.getDatesForDropDown;
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
    private static TextField userTextField;
    private static TextField weightTextField;
    private static ComboBox exercises;
    private static ComboBox datesComboBox;
    private static Button submitButton;
    private static Button displayDataButton;
    private static Button adminButton;
    private static Label dateLabel;
    private static Label repsLabel;
    private static Label setsLabel;
    private static Label timeLabel;
    private static Label optionalLabel;
    private static Label bodyweightLabel;
    private static Label weightLabel;
    private static Label everciseLabel;
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
        weightTextField = new TextField();
        weightTextField.setPromptText("Weight...");
        GridPane.setConstraints(weightTextField, 3, 2, 3, 1);
        mainGrid.getChildren().add(weightTextField);

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

        userTextField = new TextField();
        userTextField.setPromptText("User...");
        GridPane.setConstraints(userTextField, 3, 7, 3, 1);
        mainGrid.getChildren().add(userTextField);
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
        exercises = new ComboBox(exerciseOptions);
        GridPane.setConstraints(exercises, 2, 1, 5, 1);
        mainGrid.getChildren().add(exercises);

        ObservableList<String> dateOptions = getDatesForDropDown();
        datesComboBox = new ComboBox(dateOptions);
        datesComboBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(datesComboBox, 2, 0, 5, 1);
        mainGrid.getChildren().add(datesComboBox);
    }

    private static void initLabels()
    {
        dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 1, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        mainGrid.getChildren().add(dateLabel);

        everciseLabel = new Label("Exercise");
        GridPane.setConstraints(everciseLabel, 0, 1);
        GridPane.setColumnSpan(everciseLabel, 4);
        mainGrid.getChildren().add(everciseLabel);

        weightLabel = new Label("Weight");
        GridPane.setConstraints(weightLabel, 1, 2);
        GridPane.setColumnSpan(weightLabel, 4);
        mainGrid.getChildren().add(weightLabel);

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

        optionalLabel = new Label("optional");
        GridPane.setConstraints(optionalLabel, 6, 5);
        GridPane.setColumnSpan(optionalLabel, 4);
        mainGrid.getChildren().add(optionalLabel);

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

                workoutEntry = new WorkoutEntry((String) datesComboBox.getSelectionModel().getSelectedItem(),
                        Float.valueOf(bodyweightTextField.getText()),
                        (String) exercises.getSelectionModel().getSelectedItem(),
                        Float.valueOf(weightTextField.getText()),
                        Integer.parseInt(repsTextField.getText()),
                        Integer.parseInt(setTextField.getText()),
                        time);

                DataManager.storeWorkoutEntry(workoutEntry, userTextField.getText(), (String) datesComboBox.getSelectionModel().getSelectedItem());
            }
            catch (NumberFormatException ex)
            {
                logger.warn("Formatting error when submitting workout entry");
                writeToConsole("Formatting error when submitting workout entry");
            }
        });
    }
}
