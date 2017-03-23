package Graphing;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.List;

import static util.Constants.LINECHART_WINDOW_HEIGHT;
import static util.Constants.LINECHART_WINDOW_WIDTH;

public class LineGraph extends ApplicationFrame
{
    final static Logger logger = Logger.getLogger(LineGraph.class);

    public LineGraph( String applicationTitle, String chartTitle, String lineName, List<Number> xValsList, List<Number> yValsList, String xAxisLabel, String yAxisLabel)
    {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                xAxisLabel, yAxisLabel,
                createDataset(lineName, xValsList, yValsList),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        setContentPane(chartPanel);
    }


    private DefaultCategoryDataset createDataset(String lineName, List<Number> xValsList, List<Number> yValsList)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        try
        {
            if (yValsList.size() != xValsList.size())
            {
                throw new Exception("Input lists must be the same size");
            }

            for (int i = 0; i < yValsList.size(); i++)
            {
                dataset.addValue(yValsList.get(i), lineName, (Comparable) xValsList.get(i));
            }
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage());
        }

        return dataset;
    }

    public static void main( String[ ] args )
    {
        // TODO make an object that would encapsulate the complete data entry (ex: 15.0f, 1970) and would contain the name of the line
	    String lineName = "Schools";

        List<Number> xValsList = Arrays.asList(1970, 1980, 1990, 2000, 2010, 2014);

        List<Number> yValsList = Arrays.asList(15.0f, 30.0f, 60.0f, 120.0f, 240.0f, 300.0f);



	    

        LineGraph chart = new LineGraph("School Vs Years", "Number of Schools vs years", lineName, xValsList, yValsList, "Years", "Number of Schools");

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible( true );
    }
}