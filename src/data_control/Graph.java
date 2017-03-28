package data_control;

import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by dcmeade on 3/28/2017.
 */
public abstract class Graph
{
    protected String frameTitle;

    protected ApplicationFrame applicationFrame;


    public Graph(String frameTitle)
    {
        this.frameTitle = frameTitle;

        applicationFrame = new ApplicationFrame(frameTitle);
    }

    public abstract JFreeChart createChart(String chartTitle, String xAxisLabel, String yAxisLabel);
}
