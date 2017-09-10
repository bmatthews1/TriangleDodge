package TraingleGame;

import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{
    private static final float numSpikes = 40f;

    public static float WIDTH , HEIGHT , CENTER_X ,CENTER_Y , RADIUS;

    Player player;
    ArrayList<Enemy> enemies  = new ArrayList<>();
    ArrayList<Triangle> triangles = new ArrayList<>();
    final int START_NUM_ENEMIES = 10;
    final int MAGNITUDE = 10;


    private void init(){
        player = new Player(new Point((int)(WIDTH/2), (int)(HEIGHT/2)), (float)MAGNITUDE*2, 0.0);
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
        size(800, 600);
        fullScreen();
    }

    @Override
    public void draw(){
        if (frameCount == 1){
            colorMode(HSB, 360, 100, 100, 100);
            WIDTH = width;
            HEIGHT = height;
            CENTER_X = WIDTH/2f;
            CENTER_Y = HEIGHT/2f;
            RADIUS = (float)Math.sqrt(WIDTH*WIDTH + HEIGHT*HEIGHT)/2;
            init();
        }

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

        if (random(1) < .4 && triangles.size() < 50){
            Triangle t = Triangle.getRandomTriangle(RADIUS/2);
            System.out.println(t);
            triangles.add(t);
        }

        if (frameCount%20 == 0) System.out.println(triangles.size());
        for (int i = 0; i < triangles.size(); i++){
            Triangle t = triangles.get(i);
            t.update();
            drawTriangle(t);
            if (!t.inBounds()){
                triangles.remove(i);
                i--;
                System.out.println("removing triangle");
            }
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
            e.update();
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

    private void drawTriangle(Triangle t){
        stroke(t.hue, 100, 100, 10);
        strokeWeight(1);
        fill(t.hue, 100, 100, 5);
        triangle(t.points[0].x + t.x, t.points[0].y + t.y,
                 t.points[1].x + t.x, t.points[1].y + t.y,
                 t.points[2].x + t.x, t.points[2].y + t.y);
    }

    /**
     * draws the specified enemy
     *
     * @param e
     */
    private void drawEnemy(Enemy e){
        strokeWeight(2);
        if (e.attraction) {
            fill(200, 60, 80);
            stroke(200, 100, 100);
            float innerRadius = e.radius*.2f;
            float angle = 0;
            beginShape();
            for (int i = 0; i < numSpikes; i++) {
                float radius = i%2 == 0 ? e.radius : innerRadius;
                angle = (i/numSpikes)*PI*2;
                float x = cos(angle)*radius + e.center.x;
                float y = sin(angle)*radius + e.center.y;
                vertex(x, y);
            }
            endShape(CLOSE);
        } else {
            fill(0, 60, 80);
            stroke(0, 100, 100);
            ellipse(e.center.x, e.center.y, e.radius*2, e.radius*2);
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
