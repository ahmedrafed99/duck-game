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
    private BufferedImage image;

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

    public abstract void draw(Graphics g);

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void die() {
        setAlive(false);
        setVisible(false);
        if (!isAlive) {
            System.out.println(getName() + " is dead");
        }
    }

    public BufferedImage getImage(){
        return image;
    }

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

    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setVisible(boolean visible){
        this.isVisible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(){
        width = image.getWidth();
    }

    public void setHeight(){
        height = image.getHeight();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }


}
