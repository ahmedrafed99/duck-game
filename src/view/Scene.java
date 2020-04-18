package view;

import model.Duck;
import model.GameObject;
import model.Rock;
import model.WaterLily;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Scene extends JPanel {

    private int width;
    private int height;
    private List<Duck> ducks;
    private List<Rock> rocks;
    private List<WaterLily> lilies;
    private List<GameObject> gameObjects;
    ImageLoader imageLoader;

    public Scene() {
        this.imageLoader = new ImageLoader();
        this.ducks = new ArrayList<>();
        this.rocks = new ArrayList<>();
        this.lilies = new ArrayList<>();
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
            gameObject.draw(g);
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

    public List<WaterLily> getLilies() {
        return lilies;
    }

    public void setLilies(List<WaterLily> lilies) {
        this.lilies = lilies;
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
