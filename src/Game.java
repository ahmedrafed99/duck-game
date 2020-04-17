import model.Duck;
import model.Rock;
import model.WaterLily;
import view.ImageLoader;
import view.UI;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Game implements Runnable {

    private String title;
    private int width, height;
    private Thread thread;
    private Timer timer;
    private ImageLoader loader;
    private boolean running;
    private List<Duck> ducks;
    private List<Rock> rocks;
    private List<WaterLily> lilies;
    UI userInterface;


    public List<WaterLily> getLilies() {
        return lilies;
    }

    public void setLilies(List<WaterLily> lilies) {
        this.lilies = lilies;
    }

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        this.timer = new Timer();
        this.ducks = new ArrayList<>();
        this.rocks = new ArrayList<>();
        this.lilies = new ArrayList<>();
        this.userInterface = new UI(title, width, height);

        deployUnits();
    }

    public void deployUnits() {
        //duck
        generateRandomDucks(3);

        //rock
        generateRandomRocks(4);

        //Waterlily
        generateRandomLillies(5);
    }

    public void generateRandomDucks(int amount) {
        for (int i=0; i<amount; i++) {
            int xCoor = (int) (Math.random() * this.width  + 1); //this will get us a random value between 0 and width
            int yCoor = (int) (Math.random() * this.height + 1); //this will get us a random value between 0 and height
            Duck duck = new Duck("model.Duck"+i, xCoor, yCoor);
            duck.setWeight(500);
            this.ducks.add(duck);
        }
    }

    public void generateRandomLillies(int amount){
        for (int i=0; i<amount; i++) {
            int xCoor = (int) (Math.random() * this.width + 1 ); //this will get us a random value between 0 and width
            int yCoor = (int) (Math.random() * this.height + 1); //this will get us a random value between 0 and height
            WaterLily lily = new WaterLily("lily"+i, xCoor, yCoor);
            this.lilies.add(lily);
        }
    }

    public void generateRandomRocks(int amount) {
        for (int i=0; i<amount; i++) {
            int xCoor = (int) (Math.random() * this.width + 1 ); //this will get us a random value between 0 and width
            int yCoor = (int) (Math.random() * this.height + 1); //this will get us a random value between 0 and height
            Rock rock = new Rock("rock"+i, xCoor, yCoor);
            this.rocks.add(rock);
        }
    }

    public void scheduleDucksWeightLoss(){
        DuckWeightDecreaserTask duckWeightDecreaser = new DuckWeightDecreaserTask();
        duckWeightDecreaser.setDucks(this.ducks);
        this.timer.scheduleAtFixedRate(duckWeightDecreaser, 5*1000, 5*1000);
    }

    public void init() throws IOException, InterruptedException {
        userInterface.setDucks(ducks);
        userInterface.setRocks(rocks);
        userInterface.setLilies(lilies);

        userInterface.setVisible(true);

        this.scheduleDucksWeightLoss();
    }


    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void update() {
        ducks = getDucks();
        for (Duck duck: ducks){
            int xCoor = (int) (Math.random()*((1-(-1))+1))-1;
            int yCoor = (int) (Math.random()*((1-(-1))+1))-1;
            duck.setX(duck.getX() + xCoor);
            duck.setY(duck.getY() + yCoor);
        }

        lilies = getLilies();
        for (WaterLily lily: lilies){
            if (lily.isAlive()){
                return;
            } else {
                generateRandomLillies(1);
            }
        }
    }

    public synchronized void stop() throws InterruptedException {
        if (!running)
            return;
        thread.join();
    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        while (running) {
            try {
                Thread.sleep(10);
                update();
                this.userInterface.repaint();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    public List<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(List<Rock> rocks) {
        this.rocks = rocks;
    }
}



class DuckWeightDecreaserTask extends TimerTask {

    private List<Duck> ducks = new ArrayList<>();

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    @Override
    public void run()
    {
        for (Duck duck: ducks) {
            if (duck.isAlive()) {
                duck.loseWeight();
            }
        }
    }
}
