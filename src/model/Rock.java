package model;

import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rock extends GameObject {

    public Rock() {
        super();
    }

    public Rock(String name) {
        super(name);
    }

    public Rock(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage img = ImageLoader.load("rock.png");
        int scale = 15;
        int width = img.getWidth()/scale;
        int height = img.getHeight()/scale;
        g.drawImage(img, getX(), getY(), width, height,  null);
    }
}
