import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
    private Random random;

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

        deployUnits();
    }

    public void deployUnits() {
        //duck
        generateRandomDucks(3);

        //rock
        generateRandomRocks(4);

        //Waterlily
        for (int i=0; i<1; i++) {
            this.lilies.add(new WaterLily("Lily" + i));
        }
    }

    public void generateRandomDucks(int amount) {
        for (int i=0; i<amount; i++) {
            int xCoor = (int) (Math.random() * this.width + 1); //this will get us a random value between 0 and width
            int yCoor = (int) (Math.random() * this.height + 1); //this will get us a random value between 0 and height
            Duck duck = new Duck("Duck"+i, xCoor, yCoor);
            this.ducks.add(duck);
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
        Display display = new Display(title, width, height);
        display.setDucks(ducks);
        display.setRocks(rocks);
        display.setLilies(lilies);

        //Duck duck1 = new Duck("duck1");
        //WaterLily lily1 = new WaterLily();
        //System.out.println(duck1.getWeight());
        this.scheduleDucksWeightLoss();
    }


    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() throws InterruptedException {
        if (!running)
            return;
        thread.join();
    }

    public void tick(){

    }

    public void render(){ // render means draw things to the screen basically :D


    }

    @Override
    public void run() {
        int secondsElapsed = 0;

        try {
            init();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        while (running) {
//            try {
//                Thread.sleep(1000);
//                secondsElapsed++;
//                System.out.println("Seconds elapsed: " + secondsElapsed);
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
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
