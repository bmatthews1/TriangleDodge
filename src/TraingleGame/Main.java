package TraingleGame;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{

    Player player;
    ArrayList<Enemy> enemies  = new ArrayList<>();
    final int START_NUM_ENEMIES = 10;
    final int MAGNITUDE = 10;
    final double WIDTH = 400;
    final double HEIGHT = 400;

    public void init(){
        player = new Player(new Point((int)(WIDTH/2), (int)(HEIGHT/2)), (double)MAGNITUDE*2, 0.0);
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
        size((int)(WIDTH/2), (int)(HEIGHT/2));
        init();
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
