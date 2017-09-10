package TraingleGame;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShapeSVG;

import javax.sound.sampled.AudioInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created by Ben on 9/8/2017.
 */
public class Main extends PApplet{
    private static final float numSpikes = 40f;

    public static float WIDTH , HEIGHT , CENTER_X ,CENTER_Y , RADIUS, PORTAL;

    Player player;
    ArrayList<Enemy> enemies  = new ArrayList<>();
    ArrayList<Triangle> triangles = new ArrayList<>();
    static int START_NUM_ENEMIES = 10;
    static int MAX_ENEMIES = 20;
    static int ENEMY_RADIUS = 10;
    static int MAGNITUDE = 15;
    int score = 0;
    int padding = 150;
    boolean menuScreen;

    public void init(){
        player = new Player(new Point((int)(WIDTH/2), (int)(HEIGHT/2)), .4, -Math.PI/2);
        enemies.clear();
        score = 0;
        while (enemies.size() <= MAX_ENEMIES) {
            makeEnemy(getRandomPoint(), ENEMY_RADIUS, getAttraction(), player);
        }
    }

    private void checkCollision(){
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = i+1; j < enemies.size(); j++) {
                if(enemies.get(i).locationOOB(enemies.get(j).center)){

                    enemies.get(i).oobDead = true;
                    enemies.get(j).oobDead = true;
                }else if(!(enemies.get(i).attraction && enemies.get(j).attraction) && enemies.get(i).hasCollide(enemies.get(j))){
                    enemies.get(i).normalDead = true;
                    enemies.get(j).normalDead = true;
                }
            }
            if(player.hasCollide(enemies.get(i))){
                player.dead = true;
            }
        }
        explode();
    }

    private void explode(){
        if(player.dead) gameState = GameState.gameover;
        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).normalDead){
                enemies.remove(i);
                setScore(score += 2);
                makeEnemy(getRandomPoint(), ENEMY_RADIUS, getAttraction(), player);
            }else if(enemies.get(i).oobDead){
                enemies.remove(i);
                makeEnemy(getRandomPoint(), ENEMY_RADIUS, getAttraction(), player);
            }
        }
    }

    private void setScore(int score){
        this.score = score;
    }

    private void makeEnemy(Point p, int radius, boolean attraction, Player player){
        enemies.add(new Enemy(p, radius, attraction, player));
    }

    private boolean getAttraction(){
        return random(1) < 0.5;
    }

    private Point getRandomPoint() {
        float x;
        float y;
        float rand = random(0, 1);
        if (rand > 0 && rand < 0.25) {
            x = random(padding, WIDTH-padding);
            y = HEIGHT-random(padding);
        } else if (rand > 0.25 && rand < 0.5) {
            x = WIDTH-random(padding);
            y = random(padding, HEIGHT-padding);
        } else if (rand > 0.5 && rand < 0.75) {
            x = random(padding, WIDTH-padding);
            y = random(padding);
        } else {
            x = random(padding);
            y = random(padding, HEIGHT-padding);
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

    public GameState gameState = GameState.menu;

    @Override
    public void settings(){
        size(800, 600);
        fullScreen();
    }

    @Override
    public void draw(){
        frame.requestFocus();
        if (frameCount == 1){
            colorMode(HSB, 360, 100, 100, 100);
            WIDTH = width;
            HEIGHT = height;
            CENTER_X = WIDTH/2f;
            CENTER_Y = HEIGHT/2f;
            RADIUS = (float)Math.sqrt(WIDTH*WIDTH + HEIGHT*HEIGHT)/2;
            PORTAL = RADIUS/7f;
            MAX_ENEMIES = (int)(WIDTH*HEIGHT/52000);
            START_NUM_ENEMIES = MAX_ENEMIES;
            padding = (int)(RADIUS/5);
            init();
        }

        background(0, 0, 0);

        if (random(1) < .4 && triangles.size() < 20){
            Triangle t = Triangle.getRandomTriangle(RADIUS/2);
            triangles.add(t);
        }

        for (int i = 0; i < triangles.size(); i++){
            Triangle t = triangles.get(i);
            t.update();
            drawTriangle(t);
            if (!t.inBounds()){
                triangles.remove(i);
                i--;
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
        menuScreen = true;
        PFont font = loadFont("src/ARDESTINE-48.vlw");
        textFont(font, 60);
        fill(255);
        textAlign(CENTER);
        text("Press space to Start", (WIDTH/2), HEIGHT/2);
        textAlign(LEFT);
        text("Brian Bleck", (WIDTH/30), HEIGHT-(HEIGHT/3));
        text("Ben Matthews", (WIDTH/30), HEIGHT-(HEIGHT/4));
        text("Divya Komaravolu", (WIDTH/30), HEIGHT-(HEIGHT/6));
        textAlign(CENTER);
        textFont(font, 250);
        text(" EV  DE", WIDTH-(WIDTH/2), HEIGHT/3);
        drawPlayer();
        textAlign(LEFT);
    }

    /**
     * called when the gameState is playing
     */
    private void playing() {
        menuScreen = false;
        player.update();
        for (Enemy e : enemies) {
            e.update();
            drawEnemy(e);
        }
        drawPlayer();
        checkCollision();
        renderScore();
    }

    /**
     * called when the gameState is paused
     */
    private void paused(){

        for (Enemy e : enemies) {
            drawEnemy(e);
        }
        drawPlayer();
        renderScore();

        PFont font = loadFont("src/ARDESTINE-48.vlw");
        textFont(font, 60);
        fill(255);
        textAlign(CENTER);
        text("Press space to resume", (WIDTH/2), HEIGHT/2);
        textAlign(LEFT);
    }

    /**
     * called when the gameState is gameover
     */
    private void gameOver(){
        fill(255);
        textAlign(CENTER);
        PFont font = loadFont("src/ARDESTINE-48.vlw");
        textFont(font, 60);
        text("YOU LOST :(", (WIDTH/2), HEIGHT/4);
        text("Score: "+score, (WIDTH/2), HEIGHT/2);
        textSize(40);
        text("Press space to try again", (WIDTH/2), HEIGHT-(HEIGHT/6));
        text("Press esc to exit", (WIDTH/2), HEIGHT-(HEIGHT/15));
        textAlign(LEFT);
    }

    private void drawTriangle(Triangle t){
        stroke(t.hue, 100, 100, 10);
        strokeWeight(1);
        fill(t.hue, 100, 100, 5);
        triangle(t.points[0].x + t.x, t.points[0].y + t.y,
                 t.points[1].x + t.x, t.points[1].y + t.y,
                 t.points[2].x + t.x, t.points[2].y + t.y);
    }

    public void renderScore(){
        textSize(60);
        fill(255);
        String scoreText = "Score: " + score;
        textAlign(LEFT);
        text(scoreText, (WIDTH-textWidth("Score: 0000")), HEIGHT/15);
    }

    /**
     * draws the specified enemy
     *
     * @param e
     */
    private void drawEnemy(Enemy e){
        strokeWeight(1);
        if (e.attraction) {
            fill(0, 60, 80);
            stroke(0, 100, 100);
            float innerRadius = e.radius*.4f;
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
            fill(200, 60, 80);
            stroke(200, 100, 100);
            ellipse(e.center.x, e.center.y, e.radius*2, e.radius*2);
        }
    }

    /**
     * draws the specified player
     */
    private void drawPlayer(){

//        //fill(120, 100, 100, 10);
//        noFill();
//        stroke(120, 100, 100, 20);
//        rectMode(CENTER);
//        for (int i = 0; i < player.history.size(); i++){
//            //fill(120, 100, 100, i);
//            Point p = player.history.get(i);
//            pushMatrix();
//            translate(p.x, p.y);
//            rotate((((i)%16)/16f)*PI*2);
//            rect(0, 0, 10, 10);
//            popMatrix();
//        }
//        rectMode(CORNER);

        if(menuScreen){
            player.center = new Point(WIDTH/6, HEIGHT/11);
            scale(3);
            player.leftStrafe = true;
            player.rightStrafe = true;
            player.pEngines = true;
        }

        pushMatrix();
        strokeWeight(1);
        fill(120, 100, 20);
        stroke(120, 100, 100);
        translate(player.center.x, player.center.y);
        rotate((float) player.angle + PI / 2);
        translate(-player.center.x, -player.center.y);


        if (player.leftStrafe) {
            stroke(60, 100, 100);
            fill(20, 100, 100);
            triangle(player.center.x - 20, player.center.y + 11,
                    player.center.x - 20, player.center.y + 7,
                    player.center.x - 27 - abs(frameCount % 8 - 4), player.center.y + 9);
            fill(120, 100, 20);
            stroke(120, 100, 100);
        }
        rect(player.center.x - 20, player.center.y + 6, 8, 6);


        if (player.rightStrafe) {
            stroke(60, 100, 100);
            fill(20, 100, 100);
            triangle(player.center.x + 20, player.center.y + 11,
                    player.center.x + 20, player.center.y + 7,
                    player.center.x + 27 + abs(frameCount % 8 - 4), player.center.y + 9);
            fill(120, 100, 20);
            stroke(120, 100, 100);
        }
        rect(player.center.x + 12, player.center.y + 6, 8, 6);

        if (player.reverse) {
            stroke(60, 100, 100);
            fill(20, 100, 100);
            triangle(player.center.x - 12, player.center.y - 11,
                    player.center.x - 7, player.center.y - 11,
                    player.center.x - 10.5f, player.center.y - 15 - abs(frameCount % 8 - 4));
            triangle(player.center.x + 12, player.center.y - 11,
                    player.center.x + 7, player.center.y - 11,
                    player.center.x + 10.5f, player.center.y - 17 - abs(frameCount % 8 - 4));
            fill(120, 100, 20);
            stroke(120, 100, 100);
        }
        rect(player.center.x - 12, player.center.y - 12, 5, 10);
        rect(player.center.x + 7, player.center.y - 12, 5, 10);

        if (player.pEngines) {
            stroke(60, 100, 100);
            fill(20, 100, 100);
            triangle(player.center.x - 8, player.center.y + 16,
                    player.center.x + 8, player.center.y + 16,
                    player.center.x, player.center.y + 32 + abs(frameCount % 8 - 4) * 2);
            fill(120, 100, 20);
            stroke(120, 100, 100);
        }
        rect(player.center.x - 8, player.center.y + 16, 16, 8);

        fill(40);
        triangle(player.center.x, player.center.y - 36, player.center.x - 16, player.center.y + 16, player.center.x + 16, player.center.y + 16);

        fill(120, 0, 100);
        quad(player.center.x - 1, player.center.y - 26,
                player.center.x + 1, player.center.y - 26,
                player.center.x + 3, player.center.y - 20,
                player.center.x - 3, player.center.y - 20);
        popMatrix();


        noStroke();
        if(!menuScreen) {
            if (player.center.x < PORTAL || player.center.x > WIDTH - PORTAL) {
                float dist = Math.min(player.center.x, WIDTH - player.center.x);
                float perc = (PORTAL - dist) / PORTAL;
                perc *= perc;
                fill(120, 100, 100, (1f - perc) * 20);

                fill(120, 100, 100, 10);
                for (int i = 10; i > 0; i--) {
                    ellipse(0, player.center.y, i * 20 * perc, i * 40 * perc);
                    ellipse(WIDTH, player.center.y, i * 20 * perc, i * 40 * perc);
                }
            }
            if (player.center.y < PORTAL || player.center.y > HEIGHT - PORTAL) {
                float dist = Math.min(player.center.y, HEIGHT - player.center.y);
                float perc = ((PORTAL - dist) / PORTAL);
                perc *= perc;
                fill(120, 100, 100, (1f - perc) * 20);

                for (int i = 10; i > 0; i--) {
                    ellipse(player.center.x, 0, i * 40 * perc, i * 20 * perc);
                    ellipse(player.center.x, HEIGHT, i * 40 * perc, i * 20 * perc);
                }
            }
        }
        scale(1);
    }

    @Override
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) player.pEngines = true;
            if (keyCode == DOWN) player.reverse = true;
            if (keyCode == LEFT) player.leftStrafe = true;
            if (keyCode == RIGHT) player.rightStrafe = true;
        }
    }

    public void keyReleased(){
        if (key == CODED) {
            if (keyCode == UP) player.pEngines = false;
            if (keyCode == DOWN) player.reverse = false;
            if (keyCode == LEFT) player.leftStrafe = false;
            if (keyCode == RIGHT) player.rightStrafe = false;
        }
        if(key == ' '){
            if(gameState == GameState.menu){
                player.center = new Point(WIDTH/2, HEIGHT/2);
                player.leftStrafe = false;
                player.rightStrafe = false;
                player.pEngines = false;
                gameState = GameState.playing;
            }
            else if(gameState == GameState.playing) gameState = GameState.paused;
            else if(gameState == GameState.paused) gameState = GameState.playing;
            else if(gameState == GameState.gameover){
                init();
                gameState = GameState.playing;
            }
        }
    }

    public static void main(String[] args){
        Main m = new Main();
        PApplet.runSketch(new String[]{"Main"}, m);
    }

    public void themeMusic(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(""));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(1000000000);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
