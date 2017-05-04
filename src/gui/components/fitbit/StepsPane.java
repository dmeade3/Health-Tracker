package gui.components.fitbit;

import gui.Graphing.charts.CustomBarGraph;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import util.Constants;
import util.ProgramInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.Constants.LOGS_PATH;

/**
 * Created by dcmeade on 5/2/2017.
 */
public class StepsPane extends FlowPane
{
	private HashMap<String, Integer> stepDataMap = new HashMap<>();
	final CustomBarGraph barChart = new CustomBarGraph();
	final FlowPane summaryFlowPane = new FlowPane();

    public StepsPane()
    {
        setPadding(new Insets(5, 0, 5, 0));
        setVgap(4);
        setHgap(4);

	    barChart.setTitle("Steps Taken");
	    barChart.getXAxis().setLabel("Time");
	    barChart.getYAxis().setLabel("Steps");

	    try (Stream<String> stream = Files.lines(Paths.get(LOGS_PATH + System.getProperty("file.separator") + ProgramInfo.CURRENT_USER + System.getProperty("file.separator") + "steps.csv")))
	    {
		    readInStepData(stream);

			addBarChart();

		    addSummaryFlowPane();
	    }
	    catch (IOException e)
	    {
		    e.printStackTrace();
	    }
    }

	private void addSummaryFlowPane()
	{
		// Spacing for summary pane
		summaryFlowPane.getChildren().add(new Label("\t\t\t\t\t\t\t\t\t\t"));
		summaryFlowPane.getChildren().add(new Label("\t\t\t\t\t\t\t\t\t\t"));
		summaryFlowPane.getChildren().add(new Label("\t\t\t\t\t\t\t\t\t\t"));
		summaryFlowPane.getChildren().add(new Label("\t\t\t\t\t\t\t\t\t\t"));

		// Calculate total reps
		int totalSteps = 0;
		for (Map.Entry<String, Integer> entry : stepDataMap.entrySet())
		{
			totalSteps += entry.getValue();
		}

		summaryFlowPane.getChildren().add(new Label("\tTotal Steps: " + totalSteps));

		getChildren().add(summaryFlowPane);
	}

	private void readInStepData(Stream<String> stream)
	{
		// TODO only add if within the date range

		// Read in file
		for (String line : stream.collect(Collectors.toList()))
		{
			// Skip header
			if (line.contains(Constants.STEPS_CSV_HEADER))
			{
				continue;
			}

			String date = line.split(",")[0].replaceAll("\"", "");
			int stepCount = Integer.parseInt(line.split(",")[1].replaceAll("\"", ""));

			stepDataMap.put(date, stepCount);
		}
	}

	private void addBarChart()
	{
		XYChart.Series series = new XYChart.Series();
		series.setName("Steps");

		for (Map.Entry<String, Integer> entry : stepDataMap.entrySet())
		{
			// add to the series from the hashmap
			series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
		}

		barChart.getData().addAll(series);

		getChildren().add(barChart);
	}
}
