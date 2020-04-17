package view;

import model.Duck;
import model.Rock;
import model.WaterLily;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UI extends JFrame {

    private String title;
    Scene scene;

    public UI(String title, int width, int height)  {
        this.title = title;
        this.scene = new Scene(width, height);
        this.add(this.scene);

        this.setPreferredSize(new Dimension(width, height));

        this.setTitle(title);
        this.setResizable(true);
        //this.setLocationRelativeTo(null); //centres the frame in the middle of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.List<Duck> getDucks() {
        return this.scene.getDucks();
    }

    public void setDucks(java.util.List<Duck> ducks) {
        this.scene.setDucks(ducks);
    }

    public java.util.List<Rock> getRocks() {
        return this.scene.getRocks();
    }

    public void setRocks(java.util.List<Rock> rocks) {
        this.scene.setRocks(rocks);
    }

    public java.util.List<WaterLily> getLilies() {
        return this.scene.getLilies();
    }

    public void setLilies(List<WaterLily> lilies) {
        this.scene.setLilies(lilies);
    }

    @Override
    public void repaint() {
        super.repaint();
        this.scene.repaint();
    }
}