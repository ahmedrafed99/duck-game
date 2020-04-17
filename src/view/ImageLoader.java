package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private BufferedImage testImg;

    public BufferedImage loadImage(String filename){
        try {
            testImg = ImageIO.read(new File("C:\\Users\\Ahmed\\IdeaProjects\\DuckGame\\assets\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testImg;
    }

    public BufferedImage getTestImg() {
        return testImg;
    }

    public void setTestImg(BufferedImage testImg) {
        this.testImg = testImg;
    }


}
