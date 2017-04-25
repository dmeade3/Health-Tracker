package Graphing;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.util.Date;

import static util.MainUtility.DATE_FORMAT;

public class MyLineGraph extends LineChart {
    //private TimeSeriesCollection dataset = new TimeSeriesCollection(); // TODO might not need this

    public MyLineGraph(String chartTitle)
    {
        //creating the chart
        super(getDateFormatedNumberAxis(), new NumberAxis());

        setTitle(chartTitle);
    }

    private static NumberAxis getDateFormatedNumberAxis()
    {
        NumberAxis numberAxis = new NumberAxis();

        numberAxis.setAutoRanging(false);
        try
        {
            // TODO Eventually have this be a set number in the config
            numberAxis.setLowerBound(DATE_FORMAT.parse("04-22-2017").getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        numberAxis.setUpperBound(new Date().getTime());
        numberAxis.setTickUnit(.1);
        numberAxis.setTickLength(1);


        numberAxis.setTickLabelFormatter(new StringConverter<Number>()
        {
            @Override
            public String toString(Number object)
            {
                return DATE_FORMAT.format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string)
            {
                return 0;
            }
        });

        return numberAxis;
    }

    public static void main(final String[] args)
    {
        /*BasicConfigurator.configure();

        MyLineGraph lineGraph = new MyLineGraph("Workout Tracker", "Total Volume for Parallel Bar Dips Over Time", "Date", "Volume in lbs");

        WorkoutEntryFields workoutEntryField = WorkoutEntryFields.bodyweight;

        List<TimeSeriesDataItem> data = null;

        try
        {
            data = WorkoutEntry.getWorkoutValues(readInUserData("David", "all", "all"), workoutEntryField);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        lineGraph.dataset.addSeries(GraphUtil.createTimeSeries(data, GRAPH_DATA_OPTION.LOWEST_VALUE, workoutEntryField, "Parallel Bar Dips"));*/
    }
}