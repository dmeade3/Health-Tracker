package Graphing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

import static Graphing.GraphUtil.LINECHART_WINDOW_HEIGHT;
import static Graphing.GraphUtil.LINECHART_WINDOW_WIDTH;


// TODO refactor this class

public class LineGraph extends ApplicationFrame
{

    public LineGraph(final String title)
    {
        super(title);

        final XYDataset dataset = GraphUtil.createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        setContentPane(chartPanel);
    }


    private JFreeChart createChart(final XYDataset dataset)
    {
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo 6",      // chart title
                "X",                      // x axis label
                "Y",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        //      final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);

        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    public static void main(final String[] args)
    {
        final LineGraph demo = new LineGraph("Line Chart Demo 6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
















/*package Graphing;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.Arrays;
import java.util.List;

import static Graphing.GraphUtil.*;
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
                SHOW_GRAPH_LENGEND,
                SHOW_GRAPH_TOOLTIP,
                SHOW_GRAPH_URL
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension(LINECHART_WINDOW_WIDTH, LINECHART_WINDOW_HEIGHT));
        setContentPane(chartPanel);
    }

    public static void main( String[ ] args )
    {
        // TODO make an object that would encapsulate the complete data entry (ex: 15.0f, 1970) and would contain the name of the line
        // called eries data, XY series already exists so use that
	    String lineName = "Schools";

        List<Number> xValsList = Arrays.asList(1970, 1980, 1990, 2000, 2010, 2014);

        List<Number> yValsList = Arrays.asList(15.0f, 30.0f, 60.0f, 120.0f, 240.0f, 300.0f);



	    

        LineGraph chart = new LineGraph("School Vs Years", "Number of Schools vs years", lineName, xValsList, yValsList, "Years", "Number of Schools");

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible( true );
    }
}*/