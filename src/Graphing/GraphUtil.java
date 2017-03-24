package Graphing;

import data_control.WorkoutEntryFields;
import org.apache.log4j.Logger;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dcmeade on 3/23/2017.
 */
public class GraphUtil
{

    // logger //
    final static Logger logger = Logger.getLogger(GraphUtil.class);

    // Constants ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final boolean SHOW_GRAPH_LENGEND = true;
    public static final boolean SHOW_GRAPH_TOOLTIP = true;
    public static final boolean SHOW_GRAPH_URL = false;

    public static final int LINECHART_WINDOW_HEIGHT = 500;

    public static final int LINECHART_WINDOW_WIDTH = 500;


    // Util methods ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static TimeSeries createTimeSeries(List<TimeSeriesDataItem> timeSeriesDataItems, GRAPH_DATA_OPTION option, WorkoutEntryFields workoutEntryField)   // TODO want to take a list of XYSeries as the args
    {
        if (!workoutEntryField.getGraph_data_options().contains(option))
        {
            logger.warn("Option: " + option + " not compatible with field: " + workoutEntryField);
            return null;
        }

        // Hashmap will map the date to the data point
        // operations to either replace it or update it are al that are necessary
        HashMap<RegularTimePeriod, Number> hashMap = new HashMap();

        switch (option)
        {
            case LOWEST_VALUE:
                for (TimeSeriesDataItem timeSeriesDataItem : timeSeriesDataItems)
                {
                    if (hashMap.containsKey(timeSeriesDataItem.getPeriod().getStart()) &&
                       (timeSeriesDataItem.getValue().doubleValue() < hashMap.get(timeSeriesDataItem.getPeriod().getStart()).doubleValue()))
                    {
                        System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod().getStart() + " with value: " + timeSeriesDataItem.getValue().doubleValue());
                        hashMap.put(timeSeriesDataItem.getPeriod(), timeSeriesDataItem.getValue());
                    }
                    else
                    {
                        hashMap.put(timeSeriesDataItem.getPeriod(), timeSeriesDataItem.getValue());
                    }
                }
                break;


            default:
                logger.warn("Option: " + option + " not handled");
        }


        TimeSeries timeSeries = new TimeSeries(workoutEntryField);

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());


            timeSeries.add((RegularTimePeriod) pair.getKey(), (Number) pair.getValue());

            it.remove();
        }

        return timeSeries;
    }
}