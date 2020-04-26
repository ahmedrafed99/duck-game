package ahmedr.duckGame.task;

import ahmedr.duckGame.Game;
import ahmedr.duckGame.physics.Point;
import ahmedr.duckGame.model.Duck;
import ahmedr.duckGame.physics.Navigation;
import ahmedr.duckGame.physics.Rectangle;

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
            Point nextStep = path.remove(0);
            Rectangle nextSlot = duck.getBounds();
            nextSlot.translate(nextStep.x, nextStep.y);
            if (Game.getInstance().isSlotWalkable(nextSlot)) { // when it's not walkable, the first "if" statement will be reexecuted,
                                                                 // and the step will be removed, this will repeat until the path becomes null,
                                                                // and therefore the "else" statement will be executed and a new path will be generated.
                duck.moveByStep(nextStep);
            }
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
