package ahmedr.duckGame.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage load(String filename){
        try {
            return ImageIO.read(new File("C:\\Users\\Ahmed\\IdeaProjects\\DuckGame\\assets\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
