package task;

import physics.Point;
import model.Duck;
import physics.Navigation;
import physics.Rectangle;

import java.util.List;
import java.util.TimerTask;

public class NavigatorTask extends TimerTask {

    private Duck duck;
    private Point destination;

    private List<Point> path;
    private Rectangle boundaries;

    public NavigatorTask(Duck duck, Point destination) {
        super();
        this.setDuck(duck);
        this.setDestination(destination);
        boundaries = new Rectangle(0, 0);
    }

    public NavigatorTask(Duck duck, Rectangle boundaries) {
        this(duck, Point.generateRandom(boundaries));
        this.setBoundaries(boundaries);
    }

    @Override
    public void run() {
        if (path.size() > 0) {
            Point nextPoint = path.remove(0);
            duck.moveByStep(nextPoint);
        }
        else {
            Point newDestination = Point.generateRandom(boundaries);
            this.setDestination(newDestination);
        }
    }

    public Duck getDuck() {
        return duck;
    }

    public void setDuck(Duck duck) {
        this.duck = duck;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
        this.path = Navigation.getPathTo(duck.getPosition(), destination);
        System.out.println(duck.getName() + " is going to " + destination);
    }

    public void setBoundaries(Rectangle boundaries) {
        this.boundaries = boundaries;
    }
}
