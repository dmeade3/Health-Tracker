package gui.Graphing.charts;

import javafx.scene.chart.XYChart;

import java.util.Date;
import java.util.List;

import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;

/**
 * Created by dcmeade on 5/4/2017.
 */
public class CustomLineGraphWithTrendLine extends CustomLineGraph
{
    public CustomLineGraphWithTrendLine(String chartTitle, List<Series<Number, Number>> seriesList)
    {
        super(chartTitle, seriesList);

        // setup the chart
        XYChart.Series<Number,Number> seriesTrendLine = new XYChart.Series<>();


        // Add the trendline series
        getData().add(seriesTrendLine);


        /*
        *
        * TODO
        *
        * */

        getStylesheets().add("main.css");

        long now = (new Date()).getTime();

        // setup chart series
        double[] xs = {now-1000, now-900, now-800, now-700, now -600, now-500};
        double[] ys = {0.5, 1.3, 2.4, 5.6, 8.8, 9.1};

        for (int i = 0; i < xs.length; i++)
        {
            seriesTrendLine.getData().add(new XYChart.Data<>(xs[i], ys[i]));
        }

        // calculate the polynomial coefficients and calculate trend points
        double[] coefficients = polyfit(xs, ys, 2);

        for (double x = 0; x <= 5.0; x += 0.05)
        {
            double y = polynomial(x, coefficients);
            seriesTrendLine.getData().add(new XYChart.Data<>(x,y));
        }
    }
}
