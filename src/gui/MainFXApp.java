package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MainFXApp extends Application
{
    final static Logger logger = Logger.getLogger(MainFXApp.class);

    @FXML
    Button button;




    private void initialize()
    {
        logger.info("Initializing Gui");

        button = new Button();
        button.setOnAction(e -> System.out.println("Here"));

    }





    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            GridPane page = new GridPane();
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();
        }
        catch (Exception ex)
        {
            logger.warn("Error opening fxml file: " + ex);
        }

        initialize();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        BasicConfigurator.configure();

        Application.launch(MainFXApp.class, (java.lang.String[])null);
    }
}
