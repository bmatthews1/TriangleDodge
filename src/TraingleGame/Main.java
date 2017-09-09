package TraingleGame;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{
    private static final float numSpikes = 40f;

    public static final float WIDTH = 800;
    public static final float HEIGHT = 600;

    Player player;
    ArrayList<Enemy> enemies  = new ArrayList<>();
    final int MAX_ENEMIES = 20;
    final int MAGNITUDE = 2;
    final int ENEMY_RADIUS = 20;
    Random random = new Random();

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
        return random.nextInt() < 0.5 ? true : false;
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
        size((int)WIDTH, (int)HEIGHT);
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



    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }
}
