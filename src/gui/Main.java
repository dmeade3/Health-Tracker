package gui;

import data_control.WorkoutEntry;
import javafx.application.Application;
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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static util.Constants.*;
import static util.MainUtility.getDatesForDropDown;
import static util.MainUtility.loadExercises;

public class Main extends Application
{
    final static Logger logger = Logger.getLogger(Main.class);

    private GridPane mainGrid;
    private TextField repsTextField;
    private TextField setTextField;
    private TextField timeTextField;
    private TextField bodyweightTextField;
    private TextField userTextField;
    private ComboBox exercises;
    private ComboBox datesComboBox;
    private Button sampleButton;
    private Label dateLabel;
    private Label repsLabel;
    private Label setsLabel;
    private Label timeLabel;
    private Label optionalLabel;
    private Label bodyweightLabel;
    private Label userLabel;
    private ListView<String> listInfo;
    private ObservableList<String> workoutData;


    private void initMainGrid(Scene scene)
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

    private void initTextFields()
    {
        repsTextField = new TextField();
        repsTextField.setPromptText("Reps...");
        GridPane.setConstraints(repsTextField, 2, 2, 3, 1);
        mainGrid.getChildren().add(repsTextField);

        setTextField = new TextField();
        setTextField.setPromptText("Sets...");
        GridPane.setConstraints(setTextField, 2, 3, 3, 1);
        mainGrid.getChildren().add(setTextField);

        timeTextField = new TextField();
        timeTextField.setPromptText("Time...");
        GridPane.setConstraints(timeTextField, 2, 4, 3, 1);
        mainGrid.getChildren().add(timeTextField);

        bodyweightTextField = new TextField();
        bodyweightTextField.setPromptText("Bodyweight...");
        GridPane.setConstraints(bodyweightTextField, 2, 5, 3, 1);
        mainGrid.getChildren().add(bodyweightTextField);

        userTextField = new TextField();
        userTextField.setPromptText("User...");
        GridPane.setConstraints(userTextField, 2, 6, 3, 1);
        mainGrid.getChildren().add(userTextField);
    }

    private void initButtons()
    {
        sampleButton = new Button("Submit");
        GridPane.setConstraints(sampleButton, 3, 6, 3, 3);
        mainGrid.getChildren().add(sampleButton);
    }

    private void initComboBoxes()
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

    private void initLabels()
    {
        dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 1, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        mainGrid.getChildren().add(dateLabel);

        repsLabel = new Label("Reps");
        GridPane.setConstraints(repsLabel, 1, 2);
        GridPane.setColumnSpan(repsLabel, 2);
        mainGrid.getChildren().add(repsLabel);

        setsLabel = new Label("Sets");
        GridPane.setConstraints(setsLabel, 1, 3);
        GridPane.setColumnSpan(setsLabel, 2);
        mainGrid.getChildren().add(setsLabel);

        timeLabel = new Label("Time");
        GridPane.setConstraints(timeLabel, 1, 4);
        GridPane.setColumnSpan(timeLabel, 2);
        mainGrid.getChildren().add(timeLabel);

        optionalLabel = new Label("optional");
        GridPane.setConstraints(optionalLabel, 5, 4);
        GridPane.setColumnSpan(optionalLabel, 2);
        mainGrid.getChildren().add(optionalLabel);

        bodyweightLabel = new Label("Bodyweight");
        GridPane.setConstraints(bodyweightLabel, 0, 5);
        GridPane.setColumnSpan(bodyweightLabel, 3);
        mainGrid.getChildren().add(bodyweightLabel);

        userLabel = new Label("User");
        GridPane.setConstraints(userLabel, 1, 6);
        GridPane.setColumnSpan(userLabel, 2);
        mainGrid.getChildren().add(userLabel);
    }

    private void initListView()
    {
        workoutData = FXCollections.observableArrayList();
        listInfo = new ListView<>(workoutData);

        listInfo.setPrefSize(600, MAIN_PAGE_HEIGHT);
        listInfo.setEditable(true);

        workoutData.addAll("................................................................Output................................................................");
        listInfo.setItems(workoutData);
        mainGrid.add(listInfo, 19, 0, 13, 27);
    }

    private void initActions(Stage stage)
    {
        stage.setOnCloseRequest(e -> Platform.exit());


        sampleButton.setOnAction(e ->
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
                        Integer.parseInt(repsTextField.getText()),
                        Integer.parseInt(setTextField.getText()),
                        time);

                workoutData.add(workoutEntry.toString());
            }
            catch (NumberFormatException ex)
            {
                logger.warn("Formatting error when submitting workout entry");
                workoutData.add("Formatting error when submitting workout entry");
            }
        });
    }

    public void initialize(Stage stage)
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

    @Override
    public void start(Stage stage)
    {
        initialize(stage);

        stage.show();
    }

    public static void main(String[] args)
    {
        BasicConfigurator.configure();

        launch(args);
    }
}