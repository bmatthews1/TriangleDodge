package TraingleGame;

/**
 * Created by Ben on 9/9/2017.
 */
public class Point {
    float x, y;

    public Point(){
        this(0, 0);
    }

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Point)) return false;
        Point p = (Point)o;
        return (p.x == x && p.y == y);
    }

    public float distTo(Point p){
        float xDiff = x - p.x;
        float yDiff = y - p.y;
        return (float)Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
