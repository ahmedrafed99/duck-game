package model;

import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Duck extends GameObject {
    private int size;
    private int weight;
    private Color color;
    private boolean isHeadDuck;
    private static final int maxWeight = 3000;
    private static final int  maxDuckWeight = 2000;
    private static final int minWeight = 200;
    private Random random;
    private String duckImg;

    public Duck() {
        super();
        this.size = 10;
        this.weight = 500;
    }

    public Duck(String name) {
        super(name);
    }

    public Duck(String name, int x, int y) {
        super(name, x, y);
    }

    public Duck (String name, int xCoor, int yCoor, Boolean isHeadDuck) {
        this(name, xCoor, yCoor);
        this.isHeadDuck = isHeadDuck;
    }

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
        if (!isHeadDuck && isAlive()){
            isHeadDuck = true;
            setColor(Color.blue);
            setSize(getSize() * 2);
            System.out.println(getName() + " has been promoted into a head duck");
        }
    }

    public void loseWeight() {
        if (getWeight() <= minWeight){
            die();
        }
        else {
            setWeight(getWeight() - 200);
            System.out.println( "the duck " + getName() + " lost weight by 200g, new weight is: " + getWeight() +" g");
        }
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage img = ImageLoader.load("psyduck.png");
        int scale = 12;
        int width = img.getWidth()/scale;
        int height = img.getHeight()/scale;
        g.drawImage(img, getX(), getY(), width, height,  null);
    }

    @Override
    public void die() {
        setVisible(false);
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
