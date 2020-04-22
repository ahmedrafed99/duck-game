package logic;

import java.util.Random;

public class Maths  {

    public static int generateRandomNumber(int min, int max){
        int randomNumber = (int) (Math.random() * ((max-(min)) + 1)) - min;
        return randomNumber;
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }
}
