package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

        initMainGrid(scene);


        /**
         *  TextFields
         */
        final TextField comment = new TextField();
        comment.setPromptText("Enter your comment.");
        GridPane.setConstraints(comment, 2, 2, 2, 1);
        mainGrid.getChildren().add(comment);


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
        /*Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 0, 3);
        grid.getChildren().add(submitButton);

        Button clearButton = new Button("Clear");
        GridPane.setConstraints(clearButton, 0, 1);
        grid.getChildren().add(clearButton);*/


        /**
         *  Labels
         */
        final Label dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 0, 0);
        GridPane.setColumnSpan(dateLabel, 2);
        mainGrid.getChildren().add(dateLabel);

        final Label infoTextField = new Label();
        GridPane.setConstraints(infoTextField, 1, 3);
        GridPane.setColumnSpan(infoTextField, 2);
        mainGrid.getChildren().add(infoTextField);

        /**
         *  ListView  http://www.java2s.com/Tutorials/Java/JavaFX/0640__JavaFX_ListView.htm
         */
        ObservableList<String> names = FXCollections .observableArrayList();
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listInfo = new ListView<>(data);
        listInfo.setPrefSize(600, MAIN_PAGE_HEIGHT);
        listInfo.setEditable(true);

        names.addAll("Results of program appear here", "Results of program appear here Results of program appear here Results of program appear here Results of program appear here");
        listInfo.setItems(names);
        mainGrid.add(listInfo, 120, 0, 10, 100);


        /**
         *  Actions
         */
        stage.setOnCloseRequest(e -> Platform.exit());

        /*submitButton.setOnAction(e ->
        {
            if ((comment.getText() != null && !comment.getText().isEmpty()))
            {
                infoTextField.setText(name.getText() + " " + lastName.getText() + ", " + "thank you for your comment!");
            }
            else
            {
                infoTextField.setText("You have not left a comment.");
            }
        });

        clearButton.setOnAction(e ->
        {
            name.clear();
            lastName.clear();
            comment.clear();
            infoTextField.setText(null);
        });*/
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