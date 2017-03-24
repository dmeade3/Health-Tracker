package Graphing;

import data_control.WorkoutEntry;
import data_control.WorkoutEntryFields;
import org.apache.log4j.BasicConfigurator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.text.ParseException;
import java.util.List;

import static Graphing.GraphUtil.LINECHART_WINDOW_HEIGHT;
import static Graphing.GraphUtil.LINECHART_WINDOW_WIDTH;
import static data_control.DataManager.readInUserData;


// TODO refactor this class

public class LineGraph extends ApplicationFrame
{

    private TimeSeriesCollection dataset = new TimeSeriesCollection();

    public LineGraph(String title, String chartTitle, String xAxisLabel, String yAxisLabel)
    {
        super(title);

        final JFreeChart chart = createChart(chartTitle, xAxisLabel, yAxisLabel);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        setContentPane(chartPanel);
    }


    private JFreeChart createChart(String chartTitle, String xAxisLabel, String yAxisLabel)
    {
        return ChartFactory.createTimeSeriesChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel,
                dataset,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );
    }

    public static void main(final String[] args)
    {
        BasicConfigurator.configure();

        LineGraph lineGraph = new LineGraph("Test title", "Test chart title", "x axis label", "y axis label");

        WorkoutEntryFields workoutEntryField = WorkoutEntryFields.reps;

        List<TimeSeriesDataItem> data = null;

        List<TimeSeriesDataItem> data2 = null;

        try
        {
            data = WorkoutEntry.getWorkoutValues(readInUserData("David", "all"), workoutEntryField);

            data2 = WorkoutEntry.getWorkoutValues(readInUserData("David", "all"), WorkoutEntryFields.bodyweight);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


        lineGraph.dataset.addSeries(GraphUtil.createTimeSeries(data, GRAPH_DATA_OPTION.ADD_UP, workoutEntryField));

        lineGraph.dataset.addSeries(GraphUtil.createTimeSeries(data2, GRAPH_DATA_OPTION.LOWEST_VALUE, WorkoutEntryFields.bodyweight));

        lineGraph.pack();
        RefineryUtilities.centerFrameOnScreen(lineGraph);
        lineGraph.setVisible(true);
    }
}