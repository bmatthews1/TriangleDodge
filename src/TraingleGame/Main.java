package TraingleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import processing.core.PApplet;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{

    @Override
    public void settings(){
        size(400,400);
    }

    @Override
    public void draw(){
        background(0, 0, 0);
    }

    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
