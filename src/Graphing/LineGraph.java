package Graphing;

import data_control.Graph;
import data_control.WorkoutEntry;
import data_control.WorkoutEntryFields;
import org.apache.log4j.BasicConfigurator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.ui.RefineryUtilities;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import static Graphing.GraphUtil.LINECHART_WINDOW_HEIGHT;
import static Graphing.GraphUtil.LINECHART_WINDOW_WIDTH;
import static data_control.DataManager.readInUserData;

public class LineGraph extends Graph
{
    private TimeSeriesCollection dataset = new TimeSeriesCollection();

    public LineGraph(String frameTitle, String chartTitle, String xAxisLabel, String yAxisLabel)
    {
        super(frameTitle);

        final JFreeChart chart = createChart(chartTitle, xAxisLabel, yAxisLabel);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        applicationFrame.setContentPane(chartPanel);

        // Set the number formatting for the y axis
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        DecimalFormat pctFormat = new DecimalFormat("###.##");
        rangeAxis.setNumberFormatOverride(pctFormat);

        applicationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);
    }

    public JFreeChart createChart(String chartTitle, String xAxisLabel, String yAxisLabel)
    {
        return ChartFactory.createTimeSeriesChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel,
                dataset
        );
    }

    public TimeSeriesCollection getDataset()
    {
        return dataset;
    }

    public static void main(final String[] args)
    {
        BasicConfigurator.configure();

        LineGraph lineGraph = new LineGraph("Workout Tracker", "Total Volume for Parallel Bar Dips Over Time", "Date", "Volume in lbs");

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

        lineGraph.dataset.addSeries(GraphUtil.createTimeSeries(data, GRAPH_DATA_OPTION.LOWEST_VALUE, workoutEntryField, "Parallel Bar Dips"));
    }
}