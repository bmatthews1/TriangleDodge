package TraingleGame;

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
    void rotate(float angle) {

    }

    @Override
    void update() {

    }
    //public void renderExplode(){}
    /**
     * checks to see if location is out of bounds
     * @param objectCenter
     * @return false if location is not out of bounds
     * @return true if location is out of bounds
     */
    public boolean locationOOB(Point objectCenter){
        Main m = new Main();
        if(objectCenter.x>m.WIDTH || objectCenter.x<0 || objectCenter.y>m.HEIGHT || objectCenter.y<0){
            return true;
        }
        else return false;
    }
    /**
     * checks to see if incoming object's center is within the bounds of this object
     * @param incomingCenter
     * @return true if there is collision, false if no collision
     */
    public boolean hasCollide(Point incomingCenter){
        if(pointInTriangle(incomingCenter, a, b, c)){
            return true;
        }
        else return false;
    }

    /**
     * does the heavy lifting for hasCollide() above
     *
     * @param pt
     * @param v1
     * @param v2
     * @param v3
     * @return true if point is in triangle, false if not
     */
    private boolean pointInTriangle (Point pt, Point v1, Point v2, Point v3)
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) < 0.0f;
        b2 = sign(pt, v2, v3) < 0.0f;
        b3 = sign(pt, v3, v1) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }

    /**
     * helper method for pointInTriangle()
     * @param p1
     * @param p2
     * @param p3
     * @return float with sign
     */
    private float sign (Point p1, Point p2, Point p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }
}
