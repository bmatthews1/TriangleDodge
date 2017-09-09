package TraingleGame;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
public class Enemy extends Movables{
    Boolean attraction;
    Double angle;
    Double magnitude;
    Point center;
    Double radius;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();


    public Enemy(Point center, Double radius, Boolean attraction){
        this.center = center;
        this.radius = radius;
        this.attraction = attraction;
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

    }
    //public void renderExplode(){}
}
