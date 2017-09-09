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


    public Enemy(Point position, Double radius){}

    public void explode(){}
    public void setPosition(Movables m){}
    public void render(){}
    //public void renderExplode(){}
}
