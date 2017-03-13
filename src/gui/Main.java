package gui;

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

import static util.Constants.*;
import static util.MainUtility.getDatesForDropDown;
import static util.MainUtility.loadExercises;

public class Main extends Application
{
    GridPane mainGrid;

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

    public void initialize(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, MAIN_PAGE_WIDTH, MAIN_PAGE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(PROJECT_TITLE);
        stage.setMaxHeight(MAIN_PAGE_HEIGHT);
        stage.setMaxWidth(MAIN_PAGE_WIDTH);

        initMainGrid(scene);


        /**
         *  TextFields
         */
        final TextField repsTextField = new TextField();
        repsTextField.setPromptText("Reps...");
        GridPane.setConstraints(repsTextField, 2, 2, 3, 1);
        mainGrid.getChildren().add(repsTextField);

        final TextField setTextField = new TextField();
        setTextField.setPromptText("Sets...");
        GridPane.setConstraints(setTextField, 2, 3, 3, 1);
        mainGrid.getChildren().add(setTextField);

        final TextField timeTextField = new TextField();
        timeTextField.setPromptText("Time...");
        GridPane.setConstraints(timeTextField, 2, 4, 3, 1);
        mainGrid.getChildren().add(timeTextField);

        final TextField bodyweightTextField = new TextField();
        bodyweightTextField.setPromptText("Bodyweight...");
        GridPane.setConstraints(bodyweightTextField, 2, 5, 3, 1);
        mainGrid.getChildren().add(bodyweightTextField);

        final TextField userTextField = new TextField();
        userTextField.setPromptText("User...");
        GridPane.setConstraints(userTextField, 2, 6, 3, 1);
        mainGrid.getChildren().add(userTextField);


        /**
         *  Combo Box
         */
        ObservableList<String> exerciseOptions = loadExercises();
        final ComboBox exercises = new ComboBox(exerciseOptions);
        GridPane.setConstraints(exercises, 2, 1, 5, 1);
        mainGrid.getChildren().add(exercises);

        ObservableList<String> dateOptions = getDatesForDropDown();
        final ComboBox datesComboBox = new ComboBox(dateOptions);
        datesComboBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(datesComboBox, 2, 0, 5, 1);
        mainGrid.getChildren().add(datesComboBox);


        /**
         *  Buttons
         */
        Button sampleButton = new Button("Sample Button");
        GridPane.setConstraints(sampleButton, 10, 10, 3, 3);
        mainGrid.getChildren().add(sampleButton);


        /**
         *  Labels
         */
        final Label dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 1, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        mainGrid.getChildren().add(dateLabel);

        final Label repsLabel = new Label("Reps");
        GridPane.setConstraints(repsLabel, 1, 2);
        GridPane.setColumnSpan(repsLabel, 2);
        mainGrid.getChildren().add(repsLabel);

        final Label setsLabel = new Label("Sets");
        GridPane.setConstraints(setsLabel, 1, 3);
        GridPane.setColumnSpan(setsLabel, 2);
        mainGrid.getChildren().add(setsLabel);

        /**
         *  ListView  http://www.java2s.com/Tutorials/Java/JavaFX/0640__JavaFX_ListView.htm
         */
        //ObservableList<String> names = FXCollections .observableArrayList();
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listInfo = new ListView<>(data);
        listInfo.setPrefSize(600, MAIN_PAGE_HEIGHT);
        listInfo.setEditable(true);

        data.addAll("Sample output", "Results of program appear here Results of program appear here Results of program appear here Results of program appear here");
        listInfo.setItems(data);
        mainGrid.add(listInfo, 22, 0, 10, 27);


        /**
         *  Actions
         */
        stage.setOnCloseRequest(e -> Platform.exit());


        sampleButton.setOnAction(e ->
        {
            data.add("Sample button hit...");
        });
    }

    @Override
    public void start(Stage stage)
    {
        initialize(stage);

        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}