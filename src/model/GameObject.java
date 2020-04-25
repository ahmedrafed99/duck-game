package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import logic.Point;
import view.ImageLoader;

public abstract class GameObject {
    private String name;
    private boolean isAlive = true;
    private boolean isVisible = false;
    private logic.Point position;
    private BufferedImage image;
    private double scale;

    public GameObject() {
    }

    public GameObject(String name) {
        this.name = name;
    }

    public GameObject(String name, int x, int y) {
        this(name);
        this.position = new Point(x, y);
    }

    public void draw(Graphics g) {
        g.drawImage(getImage(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(),  null);
    }

    public void die() {
        setAlive(false);
        setVisible(false);
        if (!isAlive) {
            System.out.println(getName() + " is dead");
        }
    }

    public double getX() {
        return this.position.getX();
    }

    public void setX(double x) {
        this.position.x = (int) x;
    }

    public double getY() {
        return this.position.y;
    }

    public void setY(double y) {
        this.position.y = (int) y;
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

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImage(String fileName) {
        BufferedImage image = ImageLoader.load(fileName);
        setImage(image);
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getWidth() {
        return getImage().getWidth()*getScale();
    }

    public double getHeight() {
        return getImage().getHeight()*getScale();
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position){
        this.position = position;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
