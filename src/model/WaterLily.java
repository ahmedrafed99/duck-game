package model;

import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterLily extends GameObject {

    public WaterLily() {
        super();
    }

    public WaterLily(String name) {
        super(name);
    }

    public WaterLily(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage img = ImageLoader.load("Lily.png");
        int scale = 17;
        int width = img.getWidth()/scale;
        int height = img.getHeight()/scale;
        g.drawImage(img, getX(), getY(), width, height,  null);
    }
}

