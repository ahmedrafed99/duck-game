package physics;

import model.Duck;
import model.GameObject;

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
}
