package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MainPage extends Application
{
    final static Logger logger = Logger.getLogger(MainPage.class);

    @Override
    public void start(Stage stage)
    {
        InitMain.initMain(stage);

        stage.show();
    }

    public static void main(String[] args)
    {
        BasicConfigurator.configure();

        launch(args);
    }
}