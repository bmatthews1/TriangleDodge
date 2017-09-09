package TraingleGame;

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
    final float WIDTH = 400;
    final float HEIGHT = 400;


    private void init(){
        player = new Player(new Point((int)(WIDTH/2), (int)(HEIGHT/2)), (float)MAGNITUDE*2, 0.0);


        for (int i = 0; i < 20; i++){
            enemies.add(new Enemy(new Point(random(width), random(height)), 20, random(1)<.5f, player));
        }
    }

    public enum GameState {
        menu,
        init,
        playing,
        paused,
        gameover
    }

    public GameState gameState = GameState.playing;

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
    private void menu(){

    }

    /**
     * called when the gameState is playing
     */
    private void playing(){
        for (Enemy e : enemies){
            drawEnemy(e);
        }
    }

    /**
     * called when the gameState is paused
     */
    private void paused(){

    }

    /**
     * called when the gameState is gameover
     */
    private void gameOver(){

    }

    /**
     * draws the specified enemy
     *
     * @param e
     */
    private void drawEnemy(Enemy e){
        strokeWeight(2);
        if (e.attraction) {
            fill(0, 60, 80);
            stroke(0, 100, 100);
            float innerRadius = e.radius*.2f;
            float angle = 0;
            beginShape();
            for (int i = 0; i < 20; i++) {
                float radius = i%2 == 0 ? e.radius : innerRadius;
                angle = (i/20.0f)*PI*2;
                float x = cos(angle)*radius + e.center.x;
                float y = sin(angle)*radius + e.center.y;
                vertex(x, y);
            }
            endShape(CLOSE);
        } else {
            fill(200, 60, 80);
            stroke(200, 100, 100);
            ellipse(e.center.x, e.center.y, e.radius, e.radius);
        }
    }

    /**
     * draws the specified player
     */
    private void drawPlayer(){

    }



    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
