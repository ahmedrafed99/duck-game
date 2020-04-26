import model.*;
import physics.Collision;
import physics.Rectangle;
import task.DuckWeightDecreaserTask;
import task.NavigatorTask;
import view.UI;
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
        generateRandomLillies(30);

    }

    public void generateRandomDucks(int amount) {
        for (int i=0; i<amount; i++) {
            Duck duck =  GameObjectFactory.generateDuck("Duck" + i, new Rectangle(getWidth(), getHeight()));
            this.gameObjects.add(duck);
        }
    }

    public void generateRandomLillies(int amount){
        for (int i=0; i<amount; i++) {
            WaterLily lily = GameObjectFactory.generateWaterLily("Lily" + i, new Rectangle(getWidth(), getHeight()));
            this.gameObjects.add(lily);
        }
    }

    public void generateRandomRocks(int amount) {
        for (int i=0; i<amount; i++) {
            Rock rock = GameObjectFactory.generateRock("Rock" + i, new Rectangle(getWidth(), getHeight()));
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
        List<Collision> collisions = Collision.detectCollisions(this.getDucks(), this.gameObjects);
        for (Collision collision: collisions) {
            GameObject otherObject = collision.getOtherGameObject();
            if (otherObject instanceof WaterLily) {
                kill(otherObject);
                collision.getDuck().eat((WaterLily) otherObject);
                generateRandomLillies(1);
            }
            else if (otherObject instanceof Rock) {
                // TODO: handle collision with rock
            }
            else if (otherObject instanceof Duck) {
                // TODO: handle collision with other duck
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

    public List getGameObjectsOfType(Class<?> type) {
        return gameObjects.stream()
                .filter(obj -> obj.getClass().equals(type))
                .collect(Collectors.toList());
    }

    public List<Duck> getDucks() {
        return getGameObjectsOfType(Duck.class);
    }

    public List<WaterLily> getLilies() {
        return getGameObjectsOfType(WaterLily.class);
    }

    public List<Rock> getRocks() {
        return getGameObjectsOfType(Rock.class);
    }

    public int getWidth() {
        return userInterface.getWidth();
    }

    public int getHeight() {
        return userInterface.getHeight();
    }
}



