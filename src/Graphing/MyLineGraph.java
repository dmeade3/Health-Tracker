package Graphing;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class MyLineGraph extends LineChart
{
    //private TimeSeriesCollection dataset = new TimeSeriesCollection();

    public MyLineGraph(String chartTitle)
    {
        //super(frameTitle);

        /*final JFreeChart chart = createChart(chartTitle, xAxisLabel, yAxisLabel);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        applicationFrame.setContentPane(chartPanel);

        // Set the number formatting for the y axis
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        DecimalFormat pctFormat = new DecimalFormat("###.##");
        rangeAxis.setNumberFormatOverride(pctFormat);*/

        //creating the chart
        super(new CategoryAxis(), new NumberAxis());

        setTitle(chartTitle);
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