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

        int diffX = to.x - from.x;
        int diffY = to.y - from.y;

        for (int i=0; i < Math.abs(diffX); i++) {
            Point point = new Point(1, 0);
            float signOfDiffX = Math.signum(diffX);
            point = point.scalarMultiplyBy(signOfDiffX);
            path.add(point);
        }

        for (int i=0; i < Math.abs(diffY); i++) {
            Point point = new Point(0, 1);
            float signOfDiffY = Math.signum(diffY);
            point = point.scalarMultiplyBy(signOfDiffY);
            path.add(point);
        }

        return path;
    }
}
