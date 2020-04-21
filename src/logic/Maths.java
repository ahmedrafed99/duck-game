package logic;

public class Maths  {

    public static int generateRandomNumber(int min, int max){
        int randomNumber = (int) (Math.random() * ((max-(min)) + 1)) - min;
        return randomNumber;
    }
}
