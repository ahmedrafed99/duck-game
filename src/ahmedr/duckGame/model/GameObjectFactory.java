package ahmedr.duckGame.model;

import ahmedr.duckGame.physics.Rectangle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static ahmedr.duckGame.logic.Maths.getRandomNumberInRange;

public class GameObjectFactory {

    public static Duck generateDuck(String name, Rectangle boundaries) {
        return (Duck) GameObjectFactory.generateGameObject(Duck.class, name, boundaries);
    }

    public static WaterLily generateWaterLily(String name, Rectangle boundaries) {
        return (WaterLily) GameObjectFactory.generateGameObject(WaterLily.class, name, boundaries);
    }

    public static Rock generateRock(String name, Rectangle boundaries) {
        return (Rock) GameObjectFactory.generateGameObject(Rock.class, name, boundaries);
    }

    private static GameObject generateGameObject(Class<?> type, String name, Rectangle boundaries) {
        try {
            Class<?> clazz = Class.forName(type.getName());
            Constructor<?> constructor = clazz.getConstructor(String.class, Integer.TYPE, Integer.TYPE);
            GameObject object = (GameObject) constructor.newInstance(name, 0, 0);
            double xCoor = getRandomNumberInRange(boundaries.getMinX(), boundaries.getWidth() - object.getWidth());
            double yCoor = getRandomNumberInRange(boundaries.getMinY(), boundaries.getHeight() - object.getHeight());
            object.setX(xCoor);
            object.setY(yCoor);
            object.setVisible(true);
            object.setAlive(true);
            return object;
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            throw new CouldNotGenerateGameObjectException("Could not generate gameObject of type '" + type);
        }
    }
}
