package TraingleGame;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
public class Enemy extends Movables{
    Boolean attraction;
    Player player;
    Double angle;
    Double magnitude;
    Point center;
    Double radius;
    private int critProx = 30;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();


    public Enemy(Point center, Double radius, Boolean attraction, Player player){
        this.center = center;
        this.radius = radius;
        this.attraction = attraction;
        this.player = player;

    }

    @Override
    void explode() {

    }

    @Override
    void rotate(Double angle) {

    }

    @Override
    void setPosition(Movables m) {

    }

    @Override
    void render() {

    }

    @Override
    void update() {
        if(!attraction){
            if(Math.pow((center.x-player.x), 2) + Math.pow((center.y-player.y),2)<=critProx) {
                center.x += (Math.cos(angle) * (-1) * (magnitude));
                center.y += (Math.sin(angle) * (-1) * (magnitude));
            }
        }
        else {
            center.x += (Math.cos(angle) * (-1) * (magnitude));
            center.y += (Math.sin(angle) * (-1) * (magnitude));
        }
    }
    //public void renderExplode(){}
}
