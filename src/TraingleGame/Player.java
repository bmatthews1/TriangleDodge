package TraingleGame;

import java.util.*;

import static TraingleGame.Main.HEIGHT;
import static TraingleGame.Main.WIDTH;

/**
 * Created by Divya on 9/8/2017.
 */
public class Player extends Movables{
    public static final float DRAG = .96f;
    public static final float playerScale = .8f;
    public static final float angleMod = .08f;
    public static final double[] angles = {Math.PI*(-3/4d), Math.PI*(3/4d), 0};
    public static final double[] lengths = {22.627, 22.627, 36};

    Point a;
    Point b;
    Point c;

    int count = 0;

    double angle;
    double magnitude;
    double xVel = 0;
    double yVel = 0;

    boolean pEngines = false;
    boolean reverse = false;
    boolean leftStrafe = false;
    boolean rigthStrafe = false;
    Random random;
    boolean dead;
    boolean rightStrafe = false;

    public ArrayList<Point> history = new ArrayList<>();

    public Player(Point center, double magnitude, double angle){
        this.center = center;
        this.magnitude = magnitude;
        this.angle = angle;
        a = new Point(center.x, center.y - 32);
        b = new Point(center.x - 16, center.y + 16);
        c = new Point(center.x + 16, center.y + 16);
        history.add(new Point(center.x, center.y));
    }

    @Override
    void rotate(float angle) {

    }

    @Override
    void update() {
        if (pEngines){
            xVel += Math.cos(angle)*magnitude;
            yVel += Math.sin(angle)*magnitude;
        }
        if (reverse){
            xVel -= Math.cos(angle)*magnitude/3;
            yVel -= Math.sin(angle)*magnitude/3;
        }
        if (rightStrafe){
            angle += angleMod;
        }
        if (leftStrafe){
            angle -= angleMod;
        }

        center.x += xVel;
        a.x = (float)(Math.cos(angles[0] + angle)*lengths[0]) + center.x;
        b.x = (float)(Math.cos(angles[1] + angle)*lengths[1]) + center.x;
        c.x = (float)(Math.cos(angles[2] + angle)*lengths[2]) + center.x;

        center.y += yVel;
        a.y = (float)(Math.sin(angles[0] + angle)*lengths[0]) + center.y;
        b.y = (float)(Math.sin(angles[1] + angle)*lengths[1]) + center.y;
        c.y = (float)(Math.sin(angles[2] + angle)*lengths[2]) + center.y;

        if (center.x < 0){
            center.x += WIDTH;
            a.x += WIDTH;
            b.x += WIDTH;
            c.x += WIDTH;
        }
        if (center.y < 0){
            center.y += HEIGHT;
            a.y += HEIGHT;
            b.y += HEIGHT;
            c.y += HEIGHT;
        }
        if (center.x > WIDTH){
            center.x -= WIDTH;
            a.x -= WIDTH;
            b.x -= WIDTH;
            c.x -= WIDTH;
        }
        if (center.y > HEIGHT){
            center.y -= HEIGHT;
            a.y -= HEIGHT;
            b.y -= HEIGHT;
            c.y -= HEIGHT;
        }

        xVel *= DRAG;
        yVel *= DRAG;

        float dist = center.distTo(history.get(history.size() - 1));
        if (dist > 10) history.add(new Point(center.x, center.y));
        if (history.size() > 40) history.remove(0);
        count++;
    }

    /**
     * checks to see if incoming object's center is within the bounds of this object
     * @return true if there is collision, false if no collision
     */
    public boolean hasCollide(Movables m){
        return a.distTo(m.center) <= m.radius ||
               b.distTo(m.center) <= m.radius ||
               c.distTo(m.center) <= m.radius ||
               center.distTo(m.center) <= m.radius;
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

    public boolean locationOOB(Point objectCenter){
        return false;
    }

}
