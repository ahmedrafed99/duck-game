package ahmedr.duckGame.logic;

import java.util.Random;

public class Maths {

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    public static double getRandomNumberInRange(double min, double max) {
        Random r = new Random();
        return r.doubles(min, (max + 1)).findFirst().getAsDouble();
    }
}
