package gui.Graphing;

import data_control.*;
import data_control.workout.Exercise;
import data_control.workout.MuscleGroup;
import data_control.workout.WorkoutEntry;
import data_control.workout.WorkoutEntryField;
import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static data_control.DataManager.readInUserData;
import static util.MainUtility.DATE_FORMAT;

/**
 * Created by dcmeade on 4/26/2017.
 */
public class ChartDataUtil
{
    public static List<XYChart.Series<Number, Number>> createChartData(GraphViewOption graphViewOption, WorkoutEntryField workoutEntryField, Exercise... exerciseList)
    {
        ArrayList<XYChart.Series<Number, Number>> seriesList = new ArrayList<>();

        List<XYChart.Data<Number, Number>> chartData = null;

        switch(graphViewOption)
        {
	        // Section is all total volume so it disregards the exercise input
            case ALL_TOTAL_VOLUME:

                for (Exercise exercise : Exercise.values())
                {
	                try
	                {
		                // TODO all is for the date, should eventually be a daterange object
		                // This gets all workout entries within the date range associated with the exercise
		                chartData = WorkoutEntry.getWorkoutValues(readInUserData(new DateRange(DATE_FORMAT.parse("04-24-2017"), new Date()), exercise), workoutEntryField);

                        // Creates a series from the chart data from the exercise
                        XYChart.Series<Number, Number> series = GraphUtil.createTimeSeries(chartData, GraphDataOption.TOTAL_VOLUME, workoutEntryField, exercise.exerciseName);

                        if (!series.getData().isEmpty())
                        {
                            seriesList.add(series);
                        }
	                }
	                catch (NullPointerException | ParseException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;

            case ALL_MUSCLE_GROUP_TOTAL_VOLUME:

                // TODO Still needs testing to verify if working

                for (MuscleGroup muscleGroup : MuscleGroup.values())
                {
                    try
                    {
                        // TODO all is for the date, should eventually be a daterange object
                        // This gets all workout entries within the date range associated with the exercise
                        chartData = WorkoutEntry.getMuscleGroupValues(readInUserData(new DateRange(DATE_FORMAT.parse("04-24-2017"), new Date()), muscleGroup));

                        // Creates a series from the chart data from the exercise
                        XYChart.Series<Number, Number> series = GraphUtil.createTimeSeries(chartData, GraphDataOption.TOTAL_VOLUME, workoutEntryField, muscleGroup.name());

                        if (!series.getData().isEmpty())
                        {
                            seriesList.add(series);
                        }
                    }
                    catch (NullPointerException | ParseException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;






            default:
                System.out.println("Option " + graphViewOption + " not currently supported.");
        }

        return seriesList;
    }
}
