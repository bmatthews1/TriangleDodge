package TraingleGame;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Divya on 9/8/2017.
 */
abstract public class Movables {

    /**
     * Called when this object dies
     */
    public void explode(){}

    /**
     * Called when the angle of the object is updated.
     * Ideally, updatePosition is called with the newly
     * calculated a, b, and c points
     * @param angle
     */
    public void rotate(Double angle){}

    /**
     * Sets the position of the object after rotation according to
     * the gravitational pull
     * @param m Movables object
     */
    public void setPosition(Movables m){}

    /**
     * Renders the object
     * @param x x coordinate where the object needs to be drawn
     * @param y x coordinate where the object needs to be drawn
     */
    public void render(double x, double y){}

    /**
     * Renders the exploding animation
     */
    //public void renderExplode(){}
}
