package TraingleGame;

/**
 * Created by Divya on 9/8/2017.
 */
abstract public class Movables {
    public double x, y;

    /**
     * Called when this object dies
     */
    abstract void explode();

    /**
     * Called when the angle of the object is updated.
     * Ideally, updatePosition is called with the newly
     * calculated a, b, and c points
     * @param angle
     */
    abstract void rotate(Double angle);

    /**
     * Sets the position of the object after rotation according to
     * the gravitational pull
     * @param m Movables object
     */
    abstract void setPosition(Movables m);

    /**
     * updates the object
     */
    abstract void update();

    /**
     * Renders the exploding animation
     */
    //public void renderExplode(){}
}
