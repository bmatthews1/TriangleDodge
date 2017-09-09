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

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();

    public void explode(){}
    public void rotate(Double angle){}
    public void updatePosition(Point a, Point b, Point c){}
    public void setPosition(Movables m){}
    public void render(double x, double y){}
    //public void renderExplode(){}
}
