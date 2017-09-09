package TraingleGame;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
public class Player extends Movables{
    Point a;
    Point b;
    Point c;
    Point center;
    Double angle;
    Double magnitude;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();

    public Player(Point center, Double magnitude, Double angle){
        this.center= center;
        this.magnitude = magnitude;
        this.angle = angle;
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
    void update() {

    }
    //public void renderExplode(){}
}
