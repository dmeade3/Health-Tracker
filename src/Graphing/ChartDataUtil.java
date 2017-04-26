package Graphing;

import data_control.Exercise;
import data_control.WorkoutEntry;
import data_control.WorkoutEntryField;
import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static data_control.DataManager.readInUserData;
import static data_control.Exercise.PARALLEL_BAR_DIPS;

/**
 * Created by dcmeade on 4/26/2017.
 */
public class ChartDataUtil
{
    public static List<XYChart.Series<Number, Number>> createChartData(GraphViewOption graphViewOption, WorkoutEntryField workoutEntryField, Exercise... exerciseList)
    {
        ArrayList<XYChart.Series<Number, Number>> seriesList = new ArrayList();

        switch(graphViewOption)
        {
            case ALL_TOTAL_VOLUME:
                // TODO Set workoutentryfield a combo box and select, if doenst comply make a popup saying the error
                // TODO should be passed this list, have utility class create the data given arguements, /  enum options / or only enum options
                List<XYChart.Data<Number, Number>> chartData = null;
                try
                {
                    // TODO all is for the date, should eventually be a daterange object
                    chartData = WorkoutEntry.getWorkoutValues(readInUserData("all", Exercise.values()), workoutEntryField);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }

                if (!chartData.isEmpty())
                {
                    seriesList.add(GraphUtil.createTimeSeries(chartData, GraphDataOption.TOTAL_VOLUME, workoutEntryField, PARALLEL_BAR_DIPS.exerciseName));
                }
                break;






            default:
                System.out.println("Option " + graphViewOption + " not currently supported.");
        }

        return seriesList;
    }
}
