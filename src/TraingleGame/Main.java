package TraingleGame;

import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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
    final int MAX_ENEMIES = 20;
    final int ENEMY_RADIUS = 20;
    final int MAGNITUDE = 10;

    public void init(){
        player = new Player(new Point((int)(WIDTH/2), (int)(HEIGHT/2)), (double)MAGNITUDE*2, 0.0);
        while (enemies.size() <= MAX_ENEMIES) {
            makeEnemy(getRandomPoint(), ENEMY_RADIUS, getAttraction(), player);
        }
    }

    public void makeEnemy(Point p, int radius, boolean attraction, Player player){
        enemies.add(new Enemy(p, radius, attraction, player));
    }

    public boolean getAttraction(){
        return random(1) < 0.5 ? true : false;
    }

    private Point getRandomPoint() {
        float x;
        float y;
        float rand = random(0, 1);
        if (rand > 0 && rand < 0.25) {
            x = random(0, WIDTH);
            y = HEIGHT;
        } else if (rand > 0.25 && rand < 0.5) {
            x = WIDTH;
            y = random(0, HEIGHT);
        } else if (rand > 0.5 && rand < 0.75) {
            x = random(0, WIDTH);
            y = 0;
        } else {
            x = 0;
            y = random(0, HEIGHT);
        }

        return new Point((int) x, (int) y);
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

        if (random(1) < .4 && triangles.size() < 50){
            Triangle t = Triangle.getRandomTriangle(RADIUS/2);
            System.out.println(t);
            triangles.add(t);
        }

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
            e.update();
            drawEnemy(e);
        }
        drawPlayer();
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
        player.update();
        fill(40);
        stroke(0, 100, 100);
        triangle(player.a.x, player.a.y, player.b.x, player.b.y, player.c.x, player.c.y);
    }

    @Override
    public void keyPressed() {

        if (key == CODED) {
            if (keyCode == UP) {
                player.a.y -= (MAGNITUDE*2);
                player.b.y -= (MAGNITUDE*2);
                player.c.y -= (MAGNITUDE*2);
            } else if (keyCode == DOWN) {
                player.a.y += (MAGNITUDE*2);
                player.b.y += (MAGNITUDE*2);
                player.c.y += (MAGNITUDE*2);
            } else if (keyCode == LEFT) {
                player.a.x -= (MAGNITUDE*2);
                player.b.x -= (MAGNITUDE*2);
                player.c.x -= (MAGNITUDE*2);
            } else if (keyCode == RIGHT) {
                player.a.x += (MAGNITUDE*2);
                player.b.x += (MAGNITUDE*2);
                player.c.x += (MAGNITUDE*2);
            }
        }
    }

    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
