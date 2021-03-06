package gui;

import gui.components.AdminPane;
import gui.components.MainBorderPane;
import gui.components.MainTabPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import static util.Constants.*;

/**
 * Created by dcmeade on 3/21/2017.
 */
public class InitMain
{
    final static Logger logger = Logger.getLogger(InitMain.class);

    public static void initMain(Stage stage)
    {
        Scene scene = new Scene(new MainBorderPane(new AdminPane(), new MainTabPane(stage)), MAIN_PAGE_WIDTH, MAIN_PAGE_HEIGHT);

        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
        stage.setTitle(PROJECT_TITLE);
        stage.setMaxHeight(MAIN_PAGE_HEIGHT);
        stage.setMaxWidth(MAIN_PAGE_WIDTH);
    }
}
