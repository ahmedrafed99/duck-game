package physics;

import model.Duck;
import model.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Collision {
    private Duck duck;
    private GameObject otherObject;

    public Collision(Duck duck, GameObject other) {
        this.duck = duck;
        this.otherObject = other;
    }

    public Duck getDuck() {
        return duck;
    }

    public GameObject getOtherGameObject() {
        return otherObject;
    }

    public static List<Collision> detectCollisions(List<Duck> ducks, List<GameObject> gameObjects) {
        List<Collision> collisions = new ArrayList<>();
        for (Duck duck: ducks) {
            for (GameObject gameObject: gameObjects) {
                if (gameObject.equals(duck)) {
                    continue;
                }

                if (duck.collidesWith(gameObject)) {
                    collisions.add(new Collision(duck, gameObject));
                }
            }
        }

        return collisions;
    }
}
