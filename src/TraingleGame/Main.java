package TraingleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane,800, 600);
        stage.setScene(scene);
        stage.show();

        Player p = new Player();
        p.a = new Point(4,5);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
