package model;

import physics.Point;
import java.util.ArrayList;
import java.util.List;

public class Duck extends GameObject {
    private int weight;
    private boolean isHeadDuck = false;
    public static final int maxWeight = 3000;
    public static final int  maxDuckWeight = 2000;
    public static final int minWeight = 200;

    public Duck(String name, int x, int y) {
        super(name, x, y);
        setScale(0.08);
        this.setImage("psyduck.png");
    }

    public void eat(WaterLily lily) {
        setWeight(getWeight() + 150);
        System.out.println("duck eats and gains 150g, new weight is: " + getWeight() + " g");
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

    public void moveByStep(Point point) {
        this.getPosition().translate(point.x, point.y);
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight <= maxDuckWeight) {
            this.weight = weight;
            if (this.weight >= maxDuckWeight ){
                promote();
            }
        }
    }

    public void setHeadDuck(Boolean headDuck){
        isHeadDuck = headDuck;
        this.setImage("BluePsyduck.png");
        setScale(0.1);
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
