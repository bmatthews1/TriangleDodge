package TraingleGame;

import java.awt.*;

/**
 * Created by Divya on 9/8/2017.
 */
abstract public class Movables {
    public Point center;
    public float radius;

    /**
     * Called when the angle of the object is updated.
     * Ideally, updatePosition is called with the newly
     * calculated a, b, and c points
     * @param angle
     */
    abstract void rotate(float angle);

    /**
     * updates the object
     */
    abstract void update();

    /**
     * Renders the exploding animation
     */
    //public void renderExplode(){}
    /**
     * Checks to see if a given location is out of bounds
     *
     */
    abstract boolean locationOOB(Point objectCenter);
    abstract boolean hasCollide(Movables m);
}
