package TraingleGame;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
public class Enemy extends Movables{
    boolean attraction;
    Player player;
    float angle;
    float magnitude;
    Point center;
    float radius;
    int critProx = 30;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();


    public Enemy(Point center, float radius, boolean attraction, Player player){
        this.center = center;
        this.radius = radius;
        this.attraction = attraction;
        this.player = player;
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
            if(Math.pow((center.x-player.x), 2)+Math.pow((center.y-player.y),2) <= critProx) {
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
    }
    //public void renderExplode(){}
}
