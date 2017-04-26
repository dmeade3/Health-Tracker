package data_control;

import Graphing.GraphDataOption;

import java.util.Arrays;
import java.util.List;

import static Graphing.GraphDataOption.*;

/**
 * Created by dcmeade on 3/24/2017.
 */
public enum WorkoutEntryField
{
    date(), // Not currently used with anything
    bodyweight(LOWEST_VALUE),
    exercise(TOTAL_VOLUME),
    reps(ADD_UP),
    sets(ADD_UP),
    time(LOWEST_VALUE),
    additionalWeight(HIGHEST_VALUE)
    ;

    private GraphDataOption[] graphDataOptions;

    WorkoutEntryField(GraphDataOption... graphDataOptions)
    {
        this.graphDataOptions = graphDataOptions;
    }

    public List<GraphDataOption> getGraphDataOptions()
    {
        return Arrays.asList(graphDataOptions);
    }
}
