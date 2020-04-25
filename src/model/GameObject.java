package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import logic.Point;
import view.ImageLoader;

public abstract class GameObject {
    private String name;
    private Dimension dimension;
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
        g.drawImage(getImage(), getX(), getY(), (int) getWidth(), (int) getHeight(),  null);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.position, this.dimension);
    }

    public void die() {
        setAlive(false);
        setVisible(false);
        if (!isAlive) {
            System.out.println(getName() + " is dead");
        }
    }

    public int getX() {
        return this.position.x;
    }

    public void setX(int x) {
        this.position.x = x;
    }

    public int getY() {
        return this.position.y;
    }

    public void setY(int y) {
        this.position.y = y;
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
