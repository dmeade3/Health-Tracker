package data_control;

import Graphing.GRAPH_DATA_OPTION;

import java.util.Arrays;
import java.util.List;

import static Graphing.GRAPH_DATA_OPTION.*;

/**
 * Created by dcmeade on 3/24/2017.
 */
public enum WorkoutEntryFields
{
    date(), // Not currently used with anything
    bodyweight(LOWEST_VALUE),
    exercise(TOTAL_VOLUME),
    reps(ADD_UP),
    sets(ADD_UP),
    time(LOWEST_VALUE),
    additionalWeight(HIGHEST_VALUE)
    ;

    private GRAPH_DATA_OPTION[] graphDataOptions;

    WorkoutEntryFields(GRAPH_DATA_OPTION... graphDataOptions)
    {
        this.graphDataOptions = graphDataOptions;
    }

    public List<GRAPH_DATA_OPTION> getGraphDataOptions()
    {
        return Arrays.asList(graphDataOptions);
    }
}
