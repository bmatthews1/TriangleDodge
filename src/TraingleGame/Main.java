package TraingleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{

    Player player;
    ArrayList<Enemy> enemies  = new ArrayList<>();
    final int START_NUM_ENEMIES = 10;
    final int MAGNITUDE = 10;

    public void init(){
//        player = new Player();
    }

    public enum GameState {
        menu,
        init,
        playing,
        paused,
        gameover
    }

    public GameState gameState = GameState.menu;

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
        m.init();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
