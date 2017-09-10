package TraingleGame;

import static TraingleGame.Main.*;

/**
 * Created by Ben on 9/9/2017.
 */
public class Triangle {
    public Point[] points = new Point[3];
    public float hue, x, y, angle, radius;
    public float speed = 5;

    public void update(){
        x += Math.cos(angle)*speed;
        y += Math.sin(angle)*speed;
    }

    public boolean inBounds(){
        return (x > -radius && x < WIDTH + radius && y > -radius && y < HEIGHT + radius);
    }

    public Triangle(float radius){
        this.radius = radius;
        points[0] = new Point();
        points[1] = new Point();
        points[2] = new Point();

        hue = (float) Math.random()*360;
        if (Math.random() < .5){ //top bottom
            x = (Math.random() < .5) ? -radius : WIDTH + radius;
            y = (float)Math.random()*(HEIGHT + radius*2) - radius;
        } else { //left right
            x = (float)Math.random()*(WIDTH + radius*2) - radius;
            y = (Math.random() < .5) ? -radius : HEIGHT;
        }

        //angle = (float)Math.atan2(CENTER_Y - y, CENTER_X - x);

        angle = (float)(Math.random()*Math.PI*2);
        update();
    }

    public Triangle(Point p1, Point p2, Point p3){
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
    }

    public Triangle(float x1, float y1, float x2, float y2, float x3, float y3){
        points[0] = new Point(x1, y1);
        points[1] = new Point(x2, y2);
        points[2] = new Point(x3, y3);
    }

    static Triangle getRandomTriangle(float radius){
        Triangle t = new Triangle(radius);
        double angle = Math.random()*Math.PI*(2/3d);

        for (int i = 0; i < 3; i++){
            angle += Math.random()*Math.PI*(1/3d);
            float dx = (float) Math.cos(angle)*radius;
            float dy = (float) Math.sin(angle)*radius;

            t.points[i].x = dx;
            t.points[i].y = dy;
            angle += Math.PI*(2/3d);
        }

        return t;
    };

    @Override
    public String toString(){
        return points[0].toString() + ", " + points[1].toString() + ", " + points[2].toString() + "\n" +
                "angle: " + angle + "\n" + "x: " + x + "\n" + "y: " + y;
    }
}
