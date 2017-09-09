package TraingleGame;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
public class Enemy extends Movables{
    private Boolean attraction;
    private Double speed;
    private int xvel;
    int yvel;
    Point a;
    Point b;
    Point c;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();


    public Enemy(Point a, Point b, Point c){}

    public void explode(){}
    public void rotate(Double angle){}
    public void setPosition(Movables m){}
    public void render(double x, double y){}
    //public void renderExplode(){}
}
