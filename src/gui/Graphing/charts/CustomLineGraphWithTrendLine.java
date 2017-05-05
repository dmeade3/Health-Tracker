package gui.Graphing.charts;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;

/**
 * Created by dcmeade on 5/4/2017.
 */
public class CustomLineGraphWithTrendLine extends LineChart // TODO Eventually have this extend CustomLineGraph
{
    public CustomLineGraphWithTrendLine(String chartTitle, List<XYChart.Series<Number, Number>> seriesList)
    {
        super(new NumberAxis(), new NumberAxis());

        // setup the chart
        XYChart.Series<Number,Number> seriesTrendLine = new XYChart.Series<>();

	    seriesTrendLine.setName("Trendline");

        // Add the trendline series
        getData().add(seriesTrendLine);

        getStylesheets().add("customLineGraphWithTrendLine.css");

        // setup chart series
	    List<Double> xAxis = new ArrayList<>();
	    List<Double> yAxis = new ArrayList<>();

	    for (Series<Number, Number> series : seriesList)
	    {
			for (Data<Number, Number> data : series.getData())
			{
				xAxis.add(data.getXValue().doubleValue());

				yAxis.add(data.getYValue().doubleValue());
			}
	    }

		// Convert x Axis to array
	    double[] arrayXAxis = xAxis.stream().mapToDouble(Double::doubleValue).toArray();
	    double[] arrayYAxis = yAxis.stream().mapToDouble(Double::doubleValue).toArray();

        // calculate the polynomial coefficients and calculate trend points
        double[] coefficients = polyfit(arrayXAxis, arrayYAxis, 4); // TODO maybe make the number a passable arg <- must be in a range

	    double start = Double.MAX_VALUE;
	    double end = Double.MIN_VALUE;

	    for (int i = 0; i < xAxis.size() ; i++)
	    {
			if (arrayXAxis[i] < start)
			{
				start = arrayXAxis[i];
			}

		    else if (arrayXAxis[i] > end)
		    {
				end = arrayXAxis[i];
		    }
	    }

        for (double x = start; x <= end; x += 0.05)
        {
            double y = polynomial(x, coefficients);
            seriesTrendLine.getData().add(new XYChart.Data<>(x, y));
        }

	    getData().addAll(seriesList);
    }
}
