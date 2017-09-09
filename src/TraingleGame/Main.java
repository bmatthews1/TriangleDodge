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

    public enum GameState {
        menu,
        init,
        playing,
        paused,
        gameover
    }

    GameState gameState = GameState.menu;

    @Override
    public void settings(){
        size(400,400);
    }

    @Override
    public void draw(){
        background(0, 0, 0);
        switch (gameState){
            case menu : menu();
                break;
            case playing : playing();
                break;
            case paused : paused();
                break;
            case gameover: gameOver();
                break;
        }
    }

    /**
     * called when the gameState is menu
     */
    public void menu(){

    }

    /**
     * called when the gameState is playing
     */
    public void playing(){

    }

    /**
     * called when the gameState is paused
     */
    public void paused(){

    }

    /**
     * called when the gameState is gameover
     */
    public void gameOver(){

    }

    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
