import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Duck extends GameObject {
    private int size;
    private int weight;
    private Color color;
    private int height;
    private int width;
    private boolean isHeadDuck;
    private static final int maxWeight = 3000;
    private static final int  maxDuckWeight = 2000;
    private static final int minWeight = 200;
    private boolean isAlive = true;
    private Random random;
    private String duckImg;
    String filename;
    private ImageLoader loader;
    Graphics g;

    public Duck(String name) {
        super(name);
    }

    public Duck(String name, int x, int y) {
        super(name, x, y);
    }

    public Duck() {
        super();
        this.size = 10;
        this.weight = 500;
    }

    public Duck (String name, int xCoor, int yCoor, int width, int height, Boolean isHeadDuck) {
        super(name, xCoor, yCoor);
        this.height = height;
        this.width = width;
        this.isHeadDuck = isHeadDuck;
    }

//    public void draw (){
//        g.drawImage(duckImg, xCoor, yCoor, width, height,  null);
//    }

    public void eat(WaterLily lily) {
        setWeight(getWeight() + 150);
        System.out.println("duck eats and gains 150g, new weight is: " + getWeight() + " g");
        if (getWeight() >= maxDuckWeight ){
            promote();
        } else if ( getWeight() >= maxWeight){
            return;
        }
        lily.die();
    }

    public void promote() {
        if (!isHeadDuck){
            isHeadDuck = true;
            setColor(Color.blue);
            setSize(getSize() * 2);
            System.out.println(getName() + " has been promoted into a head duck");
        }

    }

    public void moveRandomly(Display display) {
        int randX = (int) (Math.random() * (display.getWidth()));
        int randY = (int) (Math.random() * (display.getHeight()));
        setX(randX);
        setY(randY);
        loseWeight();
    }

    public void loseWeight() {
        setWeight(getWeight() - 200);
        System.out.println( "the duck " + getName() + " lost weight by 200g, new weight is: " + getWeight() +" g");
        if (getWeight() <= minWeight){
            die();
        }
    }

    public void die() {
        isAlive = false;
        System.out.println(getName() + " is dead");
    }

    public static void whistle(Duck ducks){
        lineUp(ducks);
    }

    public static List<Duck> lineUp(Duck duck){
        List<Duck> ducks = new ArrayList<>();
        if (duck.isHeadDuck){           // we add the headDuck to the head of the line.
            ducks.add(0, duck);
        }
        ducks.add(duck); // we add the rest of the ducks
        return ducks;
    }

    //Getters and setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int getMaxWeight() {
        return maxWeight;
    }

    public static int getMaxDuckWeight() {
        return maxDuckWeight;
    }

    public static int getMinWeight() {
        return minWeight;
    }
}
