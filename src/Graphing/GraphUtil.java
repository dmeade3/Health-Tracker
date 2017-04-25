package Graphing;

import data_control.WorkoutEntryFields;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;

import java.util.*;

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

    public static final int LINECHART_WINDOW_HEIGHT = 500; // TODO use

    public static final int LINECHART_WINDOW_WIDTH = 500;


    // Util methods ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static XYChart.Series<Number, Number> createTimeSeries(List<XYChart.Data<Number, Number>> dataItems, GRAPH_DATA_OPTION option, WorkoutEntryFields workoutEntryField, String setName)
    {
        if (!workoutEntryField.getGraphDataOptions().contains(option))
        {
            logger.warn("Option: " + option + " not compatible with field: " + workoutEntryField);
            return null;
        }

        // Hashmap will map the date to the data point
        // operations to either replace it or update it are al that are necessary
        HashMap<Number, Number> hashMap = new HashMap();

        switch (option)
        {
            /*case LOWEST_VALUE:
                for (XYChart.Data dataItem : dataItems)
                {
                    if (hashMap.containsKey(dataItem.getPeriod()) &&
                       (dataItem.getValue().doubleValue() < hashMap.get(dataItem.getPeriod()).doubleValue()))
                    {
                        //System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod() + " with value: " + timeSeriesDataItem.getValue().doubleValue());
                        hashMap.put(dataItem.getPeriod(), dataItem.getValue());
                    }
                    else
                    {
                        hashMap.put(dataItem.getPeriod(), dataItem.getValue());
                    }
                }
                break;

            case HIGHEST_VALUE:
                for (XYChart.Data dataItem : dataItems)
                {
                    if (hashMap.containsKey(dataItem.getPeriod()) &&
                       (dataItem.getValue().doubleValue() > hashMap.get(dataItem.getPeriod()).doubleValue()))
                    {
                        //System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod() + " with value: " + timeSeriesDataItem.getValue().doubleValue());
                        hashMap.put(dataItem.getPeriod(), dataItem.getValue());
                    }
                    else
                    {
                        hashMap.put(dataItem.getPeriod(), dataItem.getValue());
                    }
                }
                break;

            case ADD_UP:
                for (XYChart.Data dataItem : dataItems)
                {
                    //System.out.println(timeSeriesDataItem.getPeriod());

                    if (hashMap.containsKey(dataItem.getPeriod()))
                    {
                        //System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod() + " with value: " + timeSeriesDataItem.getValue().doubleValue());
                        hashMap.put(dataItem.getPeriod(), (dataItem.getValue().doubleValue() + hashMap.get(dataItem.getPeriod()).doubleValue()));
                    }
                    else
                    {
                        hashMap.put(dataItem.getPeriod(), dataItem.getValue());
                    }
                }
                break;*/

            case TOTAL_VOLUME:
                for (XYChart.Data<Number, Number> dataItem : dataItems)
                {
                    /*//System.out.println(timeSeriesDataItem.getPeriod());

                    if (hashMap.containsKey(dataItem.getXValue()))
                    {
                        double updatedValue = dataItem.getYValue().doubleValue() + hashMap.get(dataItem.getXValue()).doubleValue();

                        //System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod() + " with value: " + updatedValue);
                        hashMap.put(dataItem.getXValue().toString(), updatedValue);
                    }*/
                    //else
                    //{
                        //System.out.println("Inserting value: " + timeSeriesDataItem.getValue());
                        hashMap.put(dataItem.getXValue().longValue(), dataItem.getYValue());
                    //}
                }
                break;

            default:
                logger.warn("Option: " + option + " not handled");
        }

        // Create the time series
        XYChart.Series<Number, Number> series = new XYChart.Series();

        series.setName(setName);

        // sort List
        //List<Date> keyList = new ArrayList<>(hashMap.keySet());

        //Collections.sort(keyList, Comparator.naturalOrder());

        //System.out.println(keyList.size());


        /**
         * Here the Date is converted to a string
         */
        for (Map.Entry<Number, Number> entry : hashMap.entrySet())
        {
            //System.out.println(sortedKey);

            //Map.Entry<Number, Number> pair = (Map.Entry<Number, Number>) hashMap.get(sortedKey);

            // add to the time series from the hashmap
            series.getData().add(new XYChart.Data<>(entry.getKey().longValue(), entry.getValue()));
        }

        return series;
    }

    public static
    <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
}
