package gui.components;

import javafx.scene.layout.BorderPane;

/**
 * Created by dcmeade on 5/2/2017.
 */
public class MainBorderPane extends BorderPane
{
    public MainBorderPane(AdminPane adminPane, MainTabPane mainTabPane)
    {
        setTop(adminPane);

        setCenter(mainTabPane);
    }
}
