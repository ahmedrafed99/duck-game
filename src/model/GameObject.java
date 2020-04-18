package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public abstract class GameObject {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;
    private boolean isVisible = false;

    public GameObject() {
    }

    public GameObject(String name) {
        this.name = name;
    }

    public GameObject(String name, int x, int y) {
        this(name);
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive(){
        isAlive = true;
        return true;
    }

    public void setVisible(Boolean visible){
        this.isVisible = visible;
    }

    public void die(){
        isAlive = false;
        setVisible(false);
    }
}
