package ahmedr.duckGame.physics;

import ahmedr.duckGame.Game;
import ahmedr.duckGame.model.Duck;
import ahmedr.duckGame.model.GameObject;

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

    public static List<GameObject> predictCollisions(Duck duck, Rectangle nextSlot) {
        List<GameObject> collidedGameObjects = new ArrayList<>();
        for (GameObject gameObject: Game.getInstance().getGameObjects()) {
            if (gameObject.equals(duck)) {
                continue;
            }

            if (gameObject.collidesWith(nextSlot)) {
                collidedGameObjects.add(gameObject);
            }
        }

        return collidedGameObjects;
    }
}
