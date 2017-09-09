package TraingleGame;

import java.util.Deque;
import java.util.LinkedList;

import static TraingleGame.Main.HEIGHT;
import static TraingleGame.Main.WIDTH;

/**
 * Created by Divya on 9/8/2017.
 */
public class Enemy extends Movables{
    boolean attraction;
    Player player;
    float angle;
    float magnitude = 1;
    float angleMod;
    Point center;
    float radius;
    int critProx = 200;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();


    public Enemy(Point center, float radius, boolean attraction, Player player){
        this.center = center;
        this.radius = radius;
        this.attraction = attraction;
        this.player = player;
        this.angle = (float)(Math.random()*Math.PI*2);
        angleMod = (float)(Math.random()*.1);
    }

    @Override
    void explode() {

    }

    @Override
    void rotate(float angle) {

    }

    @Override
    void update() {
        if(!attraction){
            if(Math.pow((center.x-player.center.x), 2)+Math.pow((center.y-player.center.y),2) <= critProx) {
                center.x += Math.cos(angle) * magnitude * (-1);
                center.y += Math.sin(angle) * magnitude * (-1);
            }
            else{
                center.x += Math.cos(angle) * magnitude ;
                center.y += Math.sin(angle) * magnitude ;
            }
        }
        else{
            center.x += Math.cos(angle) * magnitude ;
            center.y += Math.sin(angle) * magnitude ;
        }

        angle += angleMod;
        if (Math.random() < .05) angleMod *= -1;

        center.x = Math.min(Math.max(center.x, 0), WIDTH);
        center.y = Math.min(Math.max(center.y, 0), HEIGHT);
    }
    //public void renderExplode(){}

    /**
     * checks to see if location is out of bounds
     * @param objectCenter
     * @return false if location is not out of bounds
     *         true if location is out of bounds
     */
    public boolean locationOOB(Point objectCenter){
        Main m = new Main();
        if(objectCenter.x>m.WIDTH || objectCenter.x<0 || objectCenter.y>m.HEIGHT || objectCenter.y<0){
            return true;
        }
        else return false;
    }

    /**
     * checks to see if incoming object's center is within the bounds of this object
     * @param incomingCenter
     * @return true if there is collision, false if no collision
     */
    public boolean hasCollide(Point incomingCenter){
        if(Math.pow((incomingCenter.x-center.x),2)+Math.pow((incomingCenter.y-center.y),2)<=Math.pow(radius, 2)){
            return true;
        }
        else return false;
    }
}
