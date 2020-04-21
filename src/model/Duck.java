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
    private boolean isHeadDuck = false;
    private static final int maxWeight = 3000;
    private static final int  maxDuckWeight = 2000;
    private static final int minWeight = 200;
    private String duckImg;
    private int scale;

    public Duck() {
        super();
        this.size = 10;
        this.weight = 500;
    }

    public Duck(String name, int x, int y) {
        super(name, x, y);
        setScale(12);
        setDuckImg("psyduck.png");
    }


    public void eat(WaterLily lily) {
        setWeight(getWeight() + 150);
        lily.die();
        System.out.println("duck eats and gains 150g, new weight is: " + getWeight() + " g");
        if (getWeight() >= maxDuckWeight ){
            promote();
        } else if ( getWeight() >= maxWeight){
            return;
        }
    }

    public void promote() {
        if (!isHeadDuck && isAlive()){
            setHeadDuck(true);
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

        BufferedImage img = ImageLoader.load(duckImg);
        int width = img.getWidth()/scale;
        int height = img.getHeight()/scale;
        g.drawImage(img, getX(), getY(), width, height,  null);

    }

    public void moveRandomly(){
        if (isAlive()){
            int xCoor = (int) (Math.random()*((1-(-1))+1))-1;
            int yCoor = (int) (Math.random()*((1-(-1))+1))-1;

            setX(getX() + xCoor);
            setY(getY() + yCoor);
        } else {
            setVisible(false);
        }
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

    public void setDuckImg(String duckImg){
        this.duckImg = duckImg;
    }

    public String getDuckImg(){
        return duckImg;
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public int getScale(){
        return scale;
    }

    public void setHeadDuck(Boolean headDuck){
        isHeadDuck = headDuck;
        setColor(Color.blue);
        setScale(8);
        setDuckImg("BluePsyduck.png");
    }

}
