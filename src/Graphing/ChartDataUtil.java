package Graphing;

import data_control.Exercise;
import data_control.WorkoutEntry;
import data_control.WorkoutEntryField;
import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static data_control.DataManager.readInUserData;

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
	        // Section is all total volume so it disregards the exercise input
            case ALL_TOTAL_VOLUME:
                // TODO Set workoutentryfield a combo box and select, if doenst comply make a popup saying the error
                // TODO should be passed this list, have utility class create the data given arguements, /  enum options / or only enum options
                List<XYChart.Data<Number, Number>> chartData = null;

	            // Section is all total volume so it disregards the exercise input
                for (Exercise exercise : Exercise.values())
                {
	                try
	                {
		                // TODO all is for the date, should eventually be a daterange object

		                // this gets all workout entries
		                chartData = WorkoutEntry.getWorkoutValues(readInUserData("all", exercise), workoutEntryField);
	                }
	                catch (ParseException e)
	                {
		                e.printStackTrace();
	                }

	                XYChart.Series<Number, Number> series = GraphUtil.createTimeSeries(chartData, GraphDataOption.TOTAL_VOLUME, workoutEntryField, exercise);

	                //System.out.println(exercise);
	                //System.out.println(series.getData().size());

	                if (!series.getData().isEmpty())
	                {
		                seriesList.add(series);
	                }
                }
                break;






            default:
                System.out.println("Option " + graphViewOption + " not currently supported.");
        }

        return seriesList;
    }
}
