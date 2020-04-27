package ahmedr.duckGame.task;

import ahmedr.duckGame.Game;
import ahmedr.duckGame.model.GameObject;
import ahmedr.duckGame.model.Rock;
import ahmedr.duckGame.model.WaterLily;
import ahmedr.duckGame.physics.Collision;
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
            Point nextStep = path.remove(0); // Remove point from path even if collision occurs
            Rectangle nextSlot = duck.getBounds();
            nextSlot.translate(nextStep.x, nextStep.y);
            Game game = Game.getInstance();
            List<GameObject> objectsDuckWillHit = Collision.predictCollisions(duck, nextSlot);
            if (objectsDuckWillHit.size() == 0) {
                duck.moveByStep(nextStep);
            }
            else {
                for (GameObject objectDuckWillHit: objectsDuckWillHit) {
                    if (objectDuckWillHit instanceof WaterLily) {
                        WaterLily lilly = (WaterLily) objectDuckWillHit;
                        game.onDuckCollidedWithWaterLilly(duck, lilly);
                        duck.moveByStep(nextStep);
                    }
                }
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
