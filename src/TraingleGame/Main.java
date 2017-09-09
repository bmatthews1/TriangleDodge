package TraingleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    }

    public static void main(String[] args){
        launch(args);
    }
}
