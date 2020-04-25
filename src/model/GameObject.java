package model;

import java.awt.*;
import java.awt.image.BufferedImage;

import physics.Point;
import physics.Rectangle;
import view.ImageLoader;

public abstract class GameObject {
    private String name;
    private boolean isAlive = true;
    private boolean isVisible = false;
    private Point position;
    private BufferedImage image;
    private double scale;

    public boolean drawBounds = true;

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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(),  null);
        if (drawBounds) {
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(2));
            Rectangle bounds = getBounds();
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
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

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof GameObject) {
            GameObject gameObject = (GameObject) object;
            return this.getName().equals(gameObject.getName());
        }
        return false;
    }

    public boolean collidesWith(GameObject gameObject) {
        return getBounds().intersects(gameObject.getBounds());
    }

    public boolean isDynamic() {
        return false;
    }
}
