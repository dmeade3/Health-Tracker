package util;

import gui.Graphing.charts.CustomLineGraphWithTrendLine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class Test extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// setup the chart
		XYChart.Series<Number,Number> series1 = new XYChart.Series<>();


		// setup chart series
		double[] xs = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
		double[] ys = {0.5, 1.3, 2.4, 5.6, 8.8, 9.1};

		for (int i = 0; i < xs.length; i++)
		{
			series1.getData().add(new XYChart.Data<>(xs[i], ys[i]));
		}

		LineChart<Number,Number> chart = new CustomLineGraphWithTrendLine("Test Title", Arrays.asList(series1));







		// setup scene and stage
		Scene scene = new Scene( chart );

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}


	public static void main(String[] args)
	{
		launch(args);
	}
}