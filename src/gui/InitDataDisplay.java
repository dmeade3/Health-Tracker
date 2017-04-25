package gui;

import Graphing.GRAPH_DATA_OPTION;
import Graphing.GraphUtil;
import Graphing.MyLineGraph;
import data_control.Exercise;
import data_control.WorkoutEntry;
import data_control.WorkoutEntryFields;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.*;

import static data_control.DataManager.readInUserData;
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

            chartStage.setTitle("Line Chart Sample");

            MyLineGraph lineGraph = new MyLineGraph("Total Volume for All Exercises Over Time (lbs)");

            // TODO Set a combo box and select, if doenst comply make a popup saying the error
            WorkoutEntryFields workoutEntryField = WorkoutEntryFields.exercise;

            try
            {
                ArrayList<XYChart.Series<Number, Number>> seriesList = new ArrayList();

                for (Exercise exercise : Exercise.values())
                {
                    List<XYChart.Data<Number, Number>> chartData = WorkoutEntry.getWorkoutValues(readInUserData("David", "all", exercise.exerciseName), workoutEntryField);

                    seriesList.add(GraphUtil.createTimeSeries(chartData, GRAPH_DATA_OPTION.TOTAL_VOLUME, workoutEntryField, exercise.exerciseName));
                }

                //XYChart.Data<Number, Number> min = new XYChart.Data<>(DATE_FORMAT.parse("12-31-9999"), 0);
                //XYChart.Data<Number, Number> max = new XYChart.Data<>(DATE_FORMAT.parse("00-00-0000"), 0);

                // Find the min and max
                /*for (XYChart.Series<Number, Number> series : seriesList)
                {
                    if (series.getData().size() > 0)
                    {
                        XYChart.Data<Number, Number> indexFirst = series.getData().get(0);
                        XYChart.Data<Number, Number> indexLast = series.getData().get(series.getData().size() - 1);

                        if (min.getXValue().after(indexFirst.getXValue()))
                        {
                            min = indexFirst;
                        }

                        if (max.getXValue().before(indexLast.getXValue()))
                        {
                            max = indexLast;
                        }
                    }
                }*/

                // Add in missing values
                /*for (XYChart.Series<Number, Number> series : seriesList)
                {
                    /*List<Date> datesThatNeedToBeAdded = new ArrayList<>();

                    for (Date dateInBetweenRange : getDaysBetweenAndIncludingDates(DATE_FORMAT.parse(min.getXValue()), DATE_FORMAT.parse(max.getXValue())))
                    {
                        for (XYChart.Data<Number, Number> dataPoint : series.getData())
                        {
                            // Found a data that needs to be inserted
                            if (!dataPoint.getXValue().equals(DATE_FORMAT.format(dateInBetweenRange)))
                            {
                                datesThatNeedToBeAdded.add(dateInBetweenRange);
                            }
                        }
                    }

                    // Add in the missing dates
                    for (Date needToAddDate : datesThatNeedToBeAdded)
                    {
                        XYChart.Data newPoint = new XYChart.Data<>(DATE_FORMAT.format(needToAddDate));

                        series.getData().add(newPoint);
                    }*/

                    //series.setData(series.getData().sorted());
                //}*/

                for (XYChart.Series series : seriesList)
                {
                    for (Object data : series.getData().sorted())
                    {
                        System.out.println(data);
                    }

                    System.out.println();
                }

                lineGraph.getData().addAll(seriesList);

                lineGraph.getXAxis().invalidateRange(Arrays.asList(0));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }



            // TODO make these constants
            Scene scene  = new Scene(lineGraph, 800, 600);
            chartStage.setScene(scene);
            chartStage.show();
        });
    }

    public static List<Date> getDaysBetweenAndIncludingDates(Date startdate, Date enddate)
    {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        dates.add(enddate);

        return dates;
    }
}
