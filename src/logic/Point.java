package logic;

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
}
