package gui.Graphing.charts;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;


/**
 * Project: Health-Tracker
 * File Name: CustomBarGraph.java
 * <p>
 * Created by David on 5/2/2017.
 */
public class CustomBarGraph extends BarChart
{
	public CustomBarGraph()
	{
		super(new CategoryAxis(), new NumberAxis());

	}
}
