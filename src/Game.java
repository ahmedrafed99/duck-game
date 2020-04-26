import physics.Collision;
import physics.Rectangle;
import model.Duck;
import model.GameObject;
import model.Rock;
import model.WaterLily;
import task.DuckWeightDecreaserTask;
import task.NavigatorTask;
import view.UI;
import static logic.Maths.*;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Game implements Runnable {

    private String title;
    private Thread thread;
    private Timer timer;
    private boolean running;
    private List<GameObject> gameObjects;
    private UI userInterface;


    public Game(String title, int width, int height){
        this.title = title;
        this.timer = new Timer();
        this.gameObjects = new ArrayList<>();
        this.userInterface = new UI(title, width, height);

        deployUnits();
    }

    public void deployUnits() {
        //duck
        generateRandomDucks(3);

        //rock
        generateRandomRocks(4);

        //Waterlily
        generateRandomLillies(10);

    }

    public void generateRandomDucks(int amount) {
        for (int i=0; i<amount; i++) {
            Duck duck = new Duck("duck"+i, 0, 0);
            double xCoor = getRandomNumberInRange(0, getWidth() - duck.getWidth());
            double yCoor = getRandomNumberInRange(0, getHeight() - duck.getHeight());
            duck.setX(xCoor);
            duck.setY(yCoor);
            duck.setWeight(500);
            duck.setVisible(true);
            this.gameObjects.add(duck);
        }
    }

    public void generateRandomLillies(int amount){
        for (int i=0; i<amount; i++) {
            WaterLily lily = new WaterLily("lily"+i, 0, 0);
            double xCoor = getRandomNumberInRange(0, getWidth() - lily.getWidth());
            double yCoor = getRandomNumberInRange(0, getHeight() - lily.getHeight());
            lily.setX(xCoor);
            lily.setY(yCoor);
            lily.setAlive(true);
            lily.setVisible(true);
            this.gameObjects.add(lily);
        }
    }

    public void generateRandomRocks(int amount) {
        for (int i=0; i<amount; i++) {
            Rock rock = new Rock("rock"+i, 0, 0);
            double xCoor = getRandomNumberInRange(0, getWidth() - rock.getWidth());
            double yCoor = getRandomNumberInRange(0, getHeight() - rock.getHeight());
            rock.setX(xCoor);
            rock.setY(yCoor);
            rock.setVisible(true);
            this.gameObjects.add(rock);
        }
    }

    public void scheduleDucksWeightLoss(){
        DuckWeightDecreaserTask duckWeightDecreaser = new DuckWeightDecreaserTask();
        duckWeightDecreaser.setDucks(this.getDucks());
        this.timer.scheduleAtFixedRate(duckWeightDecreaser, 5*1000, 5*1000);
    }

    private void scheduleDucksNavigation() {
        for (Duck duck: getDucks()) {
            Rectangle boundaries = new Rectangle(getWidth() - duck.getWidth(), getHeight() - duck.getHeight());
            NavigatorTask navigatorTask = new NavigatorTask(duck, boundaries);
            this.timer.scheduleAtFixedRate(navigatorTask, 0, 10);
        }
    }

    public void init() throws IOException, InterruptedException {
        userInterface.getScene().setGameObjects(gameObjects);
        userInterface.setVisible(true);
        this.scheduleDucksWeightLoss();
        this.scheduleDucksNavigation();
    }

    private void kill(GameObject gameObject) {
        this.gameObjects.removeIf(gameObject1 ->
            gameObject.equals(gameObject1)
        );
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void update() {
        List<Collision> collisions = this.detectCollisions();
        for (Collision collision: collisions) {
            GameObject otherObject = collision.getOtherGameObject();
            if (otherObject instanceof WaterLily) {
                kill(otherObject);
                collision.getDuck().eat((WaterLily) otherObject);
            }
            else if (otherObject instanceof Rock) {
                // TODO: handle collision with rock
            }
            else if (otherObject instanceof Duck) {
                // TODO: handle collision with other duck
            }
        }

//        lilies = getLilies();
//        for (WaterLily lily: lilies){
//            if (lily.isAlive()){
//                return;
//            } else {
//                generateRandomLillies(1);
//            }
//        }
    }

    public synchronized void stop() throws InterruptedException {
        if (!running)
            return;
        thread.join();
    }

    public List<Collision> detectCollisions() {
        List<Collision> collisions = new ArrayList<>();
        for (Duck duck: this.getDucks()) {
            for (GameObject gameObject: this.gameObjects) {
                if (gameObject.equals(duck)) {
                    continue;
                }

                if (duck.collidesWith(gameObject)) {
                    collisions.add(new Collision(duck, gameObject));
                }
            }
        }

        return collisions;
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
        List<Duck> ducks = new ArrayList<>();
        for (GameObject gameObject: gameObjects) {
            if (gameObject instanceof Duck) {
                ducks.add((Duck) gameObject);
            }
        }

        return ducks;
    }

    public List<WaterLily> getLilies() {
        List<WaterLily> lillies = new ArrayList<>();
        for (GameObject gameObject: gameObjects) {
            if (gameObject instanceof WaterLily) {
                lillies.add((WaterLily) gameObject);
            }
        }

        return lillies;
    }

    public List<Rock> getRocks() {
        List<Rock> rocks = new ArrayList<>();
        for (GameObject gameObject: gameObjects) {
            if (gameObject instanceof Rock) {
                rocks.add((Rock) gameObject);
            }
        }

        return rocks;
    }

    public int getWidth() {
        return userInterface.getWidth();
    }

    public int getHeight() {
        return userInterface.getHeight();
    }
}



