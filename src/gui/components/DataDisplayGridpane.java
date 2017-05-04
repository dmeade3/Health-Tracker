package gui.components;

import data_control.workout.Exercise;
import data_control.workout.WorkoutEntryField;
import gui.Graphing.ChartDataUtil;
import gui.Graphing.GraphViewOption;
import gui.Graphing.charts.CustomLineGraph;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import static util.Constants.DATA_DISPLAY_PAGE_HEIGHT;
import static util.Constants.DATA_DISPLAY_PAGE_WIDTH;

/**
 * Created by dcmeade on 4/27/2017.
 */
public class DataDisplayGridpane extends GridPane
{
    final static Logger logger = Logger.getLogger(gui.InitMain.class);

    private Label showDataLabel;
    private Label prTableLabel;
    private Button showData;
    private Button prButton;
    private ComboBox<GraphViewOption> comboGraphViewOption;
    private ComboBox<Exercise> comboExercise;

    public DataDisplayGridpane()
    {
        super();

        initSelf();
        initTextFields();
        initComboBoxes();
        initButtons();
        initLabels();
        initListView();
        initActions();
    }

    private  void initSelf()
    {
        setPadding(new Insets(10, 10, 10, 10)); // Gap around border in pixels
        setVgap(15); // Gap between children in pixels
        setHgap(15); // Gap between children in pixels

        // sets up main grid for gridpane
        /*int rowCount = 10;
        int colCount = 10;

        // Set row heights
        for (int i = 0; i < rowCount; i++)
        {
            RowConstraints row = new RowConstraints(DATA_DISPLAY_PAGE_HEIGHT / rowCount);
            getRowConstraints().add(row);
        }

        // Set col heights
        for (int i = 0; i < colCount; i++)
        {
            ColumnConstraints col = new ColumnConstraints(DATA_DISPLAY_PAGE_WIDTH / colCount);
            getColumnConstraints().add(col);
        }*/
    }

    private void initTextFields()
    {

    }

    private void initButtons()
    {
        showData = new Button("Show Graph");
        add(showData, 5, 5);

        prButton = new Button("Show Prs");
        add(prButton, 8, 5);
    }

    private void initComboBoxes()
    {
        comboGraphViewOption = new ComboBox<>();
        comboGraphViewOption.setItems(FXCollections.observableArrayList(GraphViewOption.values()));
        comboGraphViewOption.getSelectionModel().selectFirst();
        add(comboGraphViewOption, 5, 4);

        comboExercise = new ComboBox<>();
        comboExercise.setItems(FXCollections.observableArrayList(Exercise.values()));
        comboExercise.getSelectionModel().selectFirst();
        add(comboExercise, 8, 4);
    }

    private void initLabels()
    {
        showDataLabel = new Label("Data Display View");
        add(showDataLabel, 5, 3);

        prTableLabel = new Label("Pr Table View");
        add(prTableLabel, 8, 3);
    }

    private void initListView()
    {

    }

    private void initActions()
    {
        showData.setOnAction((ActionEvent event) ->
        {
            Stage chartStage = new Stage();
            chartStage.setTitle("Workout Stats");

            // TODO Set Graphviewoption a combo box and select, if doenst comply make a popup saying the error
            // TODO make a private function that looks at the inputs and decides on a title
            CustomLineGraph lineGraph = new CustomLineGraph(
                    "Total Volume for All Muscle Groups Over Time (lbs)",
                    ChartDataUtil.createChartData(
                            comboGraphViewOption.getValue(),
                            WorkoutEntryField.exercise, // TODO make it so this field is associated witht the graphviewoption
                            null // in ALL_... will disregard the given exercises
                    )
            );

            Scene scene  = new Scene(lineGraph, DATA_DISPLAY_PAGE_WIDTH, DATA_DISPLAY_PAGE_HEIGHT);
            chartStage.setScene(scene);
            chartStage.show();
        });

        prButton.setOnAction((ActionEvent event) ->
        {
            Stage chartStage = new Stage();
            chartStage.setTitle("Pr Table");

            // TODO Set Graphviewoption a combo box and select, if doenst comply make a popup saying the error
            // TODO make a private function that looks at the inputs and decides on a title
            /*CustomLineGraph lineGraph = new CustomLineGraph(
                    "Total Volume for All Muscle Groups Over Time (lbs)",
                    ChartDataUtil.createChartData(
                            comboGraphViewOption.getValue(),
                            WorkoutEntryField.exercise, // TODO make it so this field is associated witht the graphviewoption
                            null // in ALL_... will disregard the given exercises
                    )
            );*/

            Scene scene  = new Scene(new PrTableView(comboExercise.getSelectionModel().getSelectedItem()), 250, DATA_DISPLAY_PAGE_HEIGHT);
            chartStage.setScene(scene);
            chartStage.show();
        });
    }
}
