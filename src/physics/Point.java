package physics;

import logic.Maths;

public class Point extends java.awt.Point {

    public Point() {
        super();
    }

    public Point(int x, int y) {
        super(x, y);
    }

    public Point scalarMultiplyBy(int k) {
        return new Point(this.x*k, this.y*k);
    }

    public Point scalarMultiplyBy(float k) {
        int kInt = (int) k;
        return new Point(this.x*kInt, this.y*kInt);
    }

    public static Point generateRandom(Rectangle boundaries) {
        double x = Maths.getRandomNumberInRange(boundaries.getMinX(), boundaries.getMaxX());
        double y = Maths.getRandomNumberInRange(boundaries.getY(), boundaries.getMaxY());
        return new Point((int) x, (int) y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
