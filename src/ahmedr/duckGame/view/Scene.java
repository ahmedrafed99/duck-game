package ahmedr.duckGame.view;

import ahmedr.duckGame.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scene extends JPanel {

    private int width;
    private int height;
    private List<GameObject> gameObjects;
    ImageLoader imageLoader;

    public Scene() {
        this.imageLoader = new ImageLoader();
        this.gameObjects = new ArrayList<>();
    }

    public Scene(int width, int height) {
        this();
        this.setWidth(width);
        this.setHeight(height);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, width, height);
        g.setColor(new Color(112, 222, 230)); //light blue color
        g.fillRect(0,0, width, height);


        for (GameObject gameObject: gameObjects) {
            if (gameObject.isAlive()){
                gameObject.draw(g);
            }
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
