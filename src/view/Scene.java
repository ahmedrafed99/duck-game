package view;

import model.Duck;
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
    ImageLoader imageLoader;

    public Scene() {
        this.imageLoader = new ImageLoader();
        this.ducks = new ArrayList<>();
        this.rocks = new ArrayList<>();
        this.lilies = new ArrayList<>();
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

        for (Duck duck: getDucks()) {
            BufferedImage duckImg = imageLoader.loadImage("psyduck.png");
            g.drawImage(duckImg, duck.getX(), duck.getY(), 50 , 40,  null);
        }

        for(Rock rock: getRocks()){
            BufferedImage rockImg = imageLoader.loadImage("rock.png");
            g.drawImage(rockImg, rock.getX(), rock.getY(), 40, 40, null);
        }

        for(WaterLily lily: getLilies()){
            BufferedImage lilyImg = imageLoader.loadImage("Lily.png");
            g.drawImage(lilyImg, lily.getX(), lily.getY(), 40, 40, null);
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
}
