package Graphing;

import data_control.WorkoutEntryFields;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;

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

    public static final int LINECHART_WINDOW_HEIGHT = 500; // TODO use

    public static final int LINECHART_WINDOW_WIDTH = 500;


    // Util methods ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static XYChart.Series<String, Number> createTimeSeries(List<XYChart.Data<String, Number>> dataItems, GRAPH_DATA_OPTION option, WorkoutEntryFields workoutEntryField, String setName)
    {

        /*final XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date1 = new Date();

        for (int i = 0; i <= 10; i += 1)
        {
            date1.setTime(date1.getTime() + i * 11111);
            series1.getData().add(new XYChart.Data(dateFormat.format(date1), Math.random() * 500));
        }

        return series1;*/ // TODO get rid of when done with



        if (!workoutEntryField.getGraphDataOptions().contains(option))
        {
            logger.warn("Option: " + option + " not compatible with field: " + workoutEntryField);
            return null;
        }

        // Hashmap will map the date to the data point
        // operations to either replace it or update it are al that are necessary
        HashMap<String, Number> hashMap = new HashMap();

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
                for (XYChart.Data dataItem : dataItems)
                {
                    //System.out.println(timeSeriesDataItem.getPeriod());

                    if (hashMap.containsKey(dataItem.getXValue()))
                    {
                        double updatedValue = dataItem.getYValue() + hashMap.get(dataItem.getXValue());

                        //System.out.println("Updating value for Date: " + timeSeriesDataItem.getPeriod() + " with value: " + updatedValue);
                        hashMap.put(dataItem.getPeriod(), updatedValue);
                    }
                    else
                    {
                        //System.out.println("Inserting value: " + timeSeriesDataItem.getValue());
                        hashMap.put(dataItem.getXValue(), dataItem.getYValue());
                    }
                }
                break;

            default:
                logger.warn("Option: " + option + " not handled");
        }

        // Create the time series
        XYChart.Series<String, Number> series = new XYChart.Series();

        series.setName(setName);


        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();

            // add to the time series from the hashmap
            series.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
            it.remove();
        }

        return series;
    }
}
