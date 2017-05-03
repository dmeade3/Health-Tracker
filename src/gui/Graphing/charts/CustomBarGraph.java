package gui.Graphing.charts;

import javafx.beans.NamedArg;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

import static util.MainUtility.DATE_FORMAT;
import static util.MainUtility.NUMBER_FORMAT;
import static util.MainUtility.hackTooltipStartTiming;

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
