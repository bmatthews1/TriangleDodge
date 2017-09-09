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
    abstract void rotate(float angle);

    /**
     * updates the object
     */
    abstract void update();

    /**
     * Renders the exploding animation
     */
    //public void renderExplode(){}
}
