package gui;

import Graphing.ChartDataUtil;
import Graphing.CustomLineGraph;
import Graphing.GraphViewOption;
import data_control.WorkoutEntryField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import static data_control.Exercise.PARALLEL_BAR_DIPS;
import static data_control.Exercise.SANDBAG_CURL;
import static util.Constants.*;

/**
 * Created by dcmeade on 3/21/2017.
 */
public class InitDataDisplay
{
    final static Logger logger = Logger.getLogger(gui.InitMain.class);

    private static GridPane displayDataGridpane;
    private static Button showSampleData;


    public static void initDataDisplay(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, DATA_DISPLAY_PAGE_WIDTH, DATA_DISPLAY_PAGE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(DATA_DISPLAY_TITLE);
        stage.setMaxHeight(DATA_DISPLAY_PAGE_HEIGHT);
        stage.setMaxWidth(DATA_DISPLAY_PAGE_WIDTH);

        initGrid(scene);
        initTextFields();
        initComboBoxes();
        initButtons();
        initLabels();
        initListView();
        initActions(stage);
    }

    private static void initGrid(Scene scene)
    {
        displayDataGridpane = new GridPane();
        displayDataGridpane.setPadding(new Insets(10, 10, 10, 10)); // Gap around border in pixels
        displayDataGridpane.setVgap(15); // Gap between children in pixels
        displayDataGridpane.setHgap(15); // Gap between children in pixels

        scene.setRoot(displayDataGridpane);

        // sets up main grid for gridpane
        int rowCount = 50;
        int colCount = 50;

        // Set row heights
        for (int i = 0; i < rowCount; i++)
        {
            RowConstraints row = new RowConstraints(DATA_DISPLAY_PAGE_HEIGHT / rowCount);
            displayDataGridpane.getRowConstraints().add(row);
        }

        // Set col heights
        for (int i = 0; i < colCount; i++)
        {
            ColumnConstraints col = new ColumnConstraints(DATA_DISPLAY_PAGE_WIDTH / colCount);
            displayDataGridpane.getColumnConstraints().add(col);
        }
    }

    private static void initTextFields()
    {

    }

    private static void initButtons()
    {
        showSampleData = new Button("Show Graph");
        GridPane.setConstraints(showSampleData, 0, 0, 6, 3);
        displayDataGridpane.getChildren().add(showSampleData);
    }

    private static void initComboBoxes()
    {

    }

    private static void initLabels()
    {

    }

    private static void initListView()
    {

    }

    private static void initActions(Stage stage)
    {
        showSampleData.setOnAction((ActionEvent event) ->
        {
            Stage chartStage = new Stage();
            chartStage.setTitle("Workout Tracker Stats");


            // TODO Set Graphviewoption a combo box and select, if doenst comply make a popup saying the error
            // TODO make a private function that looks at the inputs and decides on a title
            CustomLineGraph lineGraph = new CustomLineGraph("Total Volume for All Exercises Over Time (lbs)",
		            ChartDataUtil.createChartData(
				            GraphViewOption.ALL_MUSCLE_GROUP_TOTAL_VOLUME,
				            WorkoutEntryField.exercise,
				            SANDBAG_CURL, PARALLEL_BAR_DIPS // in ALL_... will disregard the given exercises
		            )
            );

            // TODO make these constants
            Scene scene  = new Scene(lineGraph, 800, 600);
            chartStage.setScene(scene);
            chartStage.show();
        });
    }
}
