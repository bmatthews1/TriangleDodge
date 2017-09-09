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
    Double angle;
    Double magnitude;

    /**
     * Deque to hold and update the after-images of the object
     */
    Deque trails = new LinkedList<>();

    public void explode(){}
    public void rotate(Double angle){}
    public void setPosition(Movables m){}
    public void render(){}
    //public void renderExplode(){}
}
