package TraingleGame;

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
     */
    public void render(){}

    /**
     * Renders the exploding animation
     */
    //public void renderExplode(){}
}
