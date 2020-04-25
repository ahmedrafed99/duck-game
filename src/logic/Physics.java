package logic;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Physics {

    public boolean checkCollisions(Rectangle r1, Rectangle r2) {
         return r1.intersects(r2);
    }

    public static List<Point> getPathTo(Point from, Point to){
        List<Point> path = new ArrayList<>();

        return path;
    }
}
