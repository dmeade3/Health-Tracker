package gui.components;


import data_control.DataManager;
import data_control.DateRange;
import data_control.workout.Exercise;
import data_control.workout.WorkoutEntry;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.ParseException;
import java.util.*;

import static util.MainUtility.DATE_FORMAT;

/**
 * Created by dcmeade on 4/28/2017.
 */
public class PrTableView extends TableView
{

    public PrTableView(Exercise exercise)
    {
        TableColumn exerciseNameCol = new TableColumn(exercise.exerciseName);

        TableColumn weightCol = new TableColumn("Weight");
        weightCol.setSortType(TableColumn.SortType.DESCENDING);
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightCol.prefWidthProperty().bind(widthProperty().multiply(0.50));

        TableColumn repsCol = new TableColumn("Reps");
        repsCol.setCellValueFactory(new PropertyValueFactory<>("reps"));
        repsCol.prefWidthProperty().bind(widthProperty().multiply(0.50));


        exerciseNameCol.getColumns().addAll(weightCol, repsCol);

        setItems(loadData(exercise));
        getColumns().addAll(exerciseNameCol);
    }

    private ObservableList<Pr> loadData(Exercise exercise)
    {
        List<WorkoutEntry> exerciseData;
        LinkedHashMap<Double, Pr> prs = new LinkedHashMap<>();

        try
        {
            exerciseData = DataManager.readInUserData(new DateRange(DATE_FORMAT.parse("04-18-2017"), new Date()), exercise);

            for (WorkoutEntry workoutEntry : exerciseData)
            {
                double weight = workoutEntry.getAdditionalWeight();

                if (exercise.bodyweight)
                {
                    weight += workoutEntry.getBodyweight();
                }


                if (prs.containsKey(weight) && (prs.get(weight).getReps() < workoutEntry.getReps()))
                {
                    prs.put(weight, new Pr(weight, (double) workoutEntry.getReps()));
                }
                else if (!prs.containsKey(weight))
                {
                    prs.put(weight, new Pr(weight, (double) workoutEntry.getReps()));
                }


                System.out.println(weight + "    " + workoutEntry.getReps());
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(prs.values());
    }

    public static class Pr
    {
        private final SimpleDoubleProperty weight;
        private final SimpleDoubleProperty reps;

        private Pr(Double weight, Double reps)
        {
            this.weight = new SimpleDoubleProperty(weight);
            this.reps = new SimpleDoubleProperty(reps);
        }

        public Double getWeight() {
            return weight.get();
        }

        public void setWeight(Double fName)
        {
            weight.set(fName);
        }

        public Double getReps()
        {
            return reps.get();
        }

        public void setReps(Double fName)
        {
            reps.set(fName);
        }
    }
}
