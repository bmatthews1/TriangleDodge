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
    double angle;
    double magnitude;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();

    public Player(Point center, double magnitude, double angle){
        this.center= center;
        this.magnitude = magnitude;
        this.angle = angle;
    }

    @Override
    void explode() {

    }

    @Override
    void rotate(double angle) {

    }

    @Override
    void update() {

    }
    //public void renderExplode(){}
}
