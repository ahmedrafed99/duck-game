package view;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private String title;
    private Scene scene;

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

    public Scene getScene() {
        return scene;
    }

    @Override
    public void repaint() {
        super.repaint();
        this.scene.repaint();
    }
}