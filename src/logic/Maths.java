package logic;

import java.util.Random;

public class Maths  {

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    public static double getRandomNumberInRange(double min, double max) {
        Random r = new Random();
        return r.doubles(min, (max + 1)).findFirst().getAsDouble();
    }

    public static Point generateRandomPoint(int max1, int max2){
        int x = getRandomNumberInRange(0, max1);
        int y = getRandomNumberInRange(0, max2);

        return new Point(x, y);
    }
}
