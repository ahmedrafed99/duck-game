package logic;

public class Rectangle extends java.awt.Rectangle {

    public Rectangle(double width, double height) {
        this(0, 0, width, height);
    }

    public Rectangle(double x, double y, double width, double height) {
        super((int) x, (int) y, (int) width, (int) height);
    }
}
