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
		XYChart.Series<Number,Number> series2 = new XYChart.Series<>();


		// setup chart series
		double[] xs = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
		double[] ys = {50, 20, 60, 70, 30, 50};

		for (int i = 0; i < xs.length; i++)
		{
			series1.getData().add(new XYChart.Data<>(xs[i], ys[i]));
		}

		double[] xs2 = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
		double[] ys2 = {80, 10, 40, 80, 70, 100};

		for (int i = 0; i < xs2.length; i++)
		{
			series2.getData().add(new XYChart.Data<>(xs2[i], ys2[i]));
		}

		LineChart<Number,Number> chart = new CustomLineGraphWithTrendLine("Test Title", Arrays.asList(series1, series2));


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