package data_control;

import Graphing.GRAPH_DATA_OPTION;

import java.util.Arrays;
import java.util.List;

import static Graphing.GRAPH_DATA_OPTION.ADD_UP;
import static Graphing.GRAPH_DATA_OPTION.HIGHEST_VALUE;
import static Graphing.GRAPH_DATA_OPTION.LOWEST_VALUE;

/**
 * Created by dcmeade on 3/24/2017.
 */
public enum WorkoutEntryFields
{

    date(),
    bodyweight(LOWEST_VALUE),
    exercise(),
    reps(HIGHEST_VALUE, LOWEST_VALUE, ADD_UP),
    sets(HIGHEST_VALUE, LOWEST_VALUE, ADD_UP),
    time(LOWEST_VALUE),
    additionalWeight(HIGHEST_VALUE)
    ;

    private GRAPH_DATA_OPTION[] graph_data_options;

    WorkoutEntryFields(GRAPH_DATA_OPTION... graph_data_options)
    {
        this.graph_data_options = graph_data_options;
    }

    public List<GRAPH_DATA_OPTION> getGraph_data_options()
    {
        return Arrays.asList(graph_data_options);
    }
}
