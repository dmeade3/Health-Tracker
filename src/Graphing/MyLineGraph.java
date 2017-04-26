package Graphing;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.util.Date;

import static util.MainUtility.DATE_FORMAT;
import static util.ProgramInfo.CHART_HISTORY_DAYS;

public class MyLineGraph extends LineChart {
    //private TimeSeriesCollection dataset = new TimeSeriesCollection(); // TODO might not need this

    public MyLineGraph(String chartTitle)
    {
        //creating the chart
        super(getDateFormatedNumberAxis(), new NumberAxis());

        setTitle(chartTitle);
    }

    private static NumberAxis getDateFormatedNumberAxis()
    {
        NumberAxis numberAxis = new NumberAxis();

        numberAxis.setAutoRanging(false);
        try
        {
            // TODO Eventually have this be a set number in the config
            numberAxis.setUpperBound(DATE_FORMAT.parse(DATE_FORMAT.format(new Date())).getTime());

            // Gets the upper bound minus the correct number of days in milliseconds
            numberAxis.setLowerBound(numberAxis.getUpperBound() - (86400000 * CHART_HISTORY_DAYS));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        numberAxis.setTickLength(15);

        // TODO 3 is the days between low and upper bound
        numberAxis.setTickUnit((numberAxis.getUpperBound() - numberAxis.getLowerBound()) / CHART_HISTORY_DAYS);


        numberAxis.setTickLabelFormatter(new StringConverter<Number>()
        {
            @Override
            public String toString(Number object)
            {
                return DATE_FORMAT.format(new Date(object.longValue()));
            }

            @Override
            public Number fromString(String string)
            {
                return 0;
            }
        });

        return numberAxis;
    }
}