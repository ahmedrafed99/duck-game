import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.List;

public class Display extends JPanel {

    private int width, height;
    private String title;
    private List<Duck> ducks;
    private List<Rock> rocks;


    private List<WaterLily> lilies;
    BufferedImage testImg;
    ImageLoader loader = new ImageLoader();



    public Display(String title, int width, int height)  {
        this.title = title;
        this.width = width;
        this.height = height;

        createUI();

    }


    public void paint(Graphics g){
        g.clearRect(0, 0, width, height);
        g.setColor(new Color(112, 222, 230)); //light blue color
        g.fillRect(0,0, width, height);
        //drawGrid(g); //we draw the grid
//        testImg = loadImage("psyduck.png");
//        g.drawImage(testImg, 50, 50, 50, 35,  null);
//        BufferedImage lily = loadImage("Lily.png");
//        g.drawImage(lily, 0, 0, 50, 35, null);

//        testImg = loader.loadImage("psyduck.png");
//        g.drawImage(testImg, 50, 50, 50, 35, null);

      for (Duck duck: getDucks()) {
            BufferedImage duckImg = loader.loadImage("psyduck.png");
            g.drawImage(duckImg, duck.getX(), duck.getY(), 50, 40,  null);
      }

      for(Rock rock: getRocks()){
          BufferedImage rockImg = loader.loadImage("rock.png");
          g.drawImage(rockImg, rock.getX(), rock.getY(), 40, 40, null);
      }

      for(WaterLily lily: getLilies()){
            BufferedImage lilyImg = loader.loadImage("Lily.png");
            g.drawImage(lilyImg, lily.getX(), lily.getY(), 40, 40, null);
      }
    }

//    private ImageIcon resizeImage(ImageIcon imageIcon){
//        Image image = imageIcon.getImage();
//        int width = imageIcon.getIconWidth();
//        int height = imageIcon.getIconHeight();
//        Image resized = image.getScaledInstance(40, 50, Image.SCALE_SMOOTH);
//        imageIcon = new ImageIcon(resized);
//        return imageIcon;
//    }

    private BufferedImage loadImage(String filename){
        try {
             testImg = ImageIO.read(new File("C:\\Users\\Ahmed\\IdeaProjects\\DuckGame\\assets\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testImg;
    }


//    public void importUnitImage(String filename) throws IOException {
//        //ImageIcon unitImage = new ImageIcon("C:\\Users\\Ahmed\\IdeaProjects\\Duck Game\\assets\\" + filename);
//        testImg = ImageIO.read(new URL("C:\\Users\\Ahmed\\IdeaProjects\\Duck Game\\assets\\psyduck.png"));
//    }



    private void createUI() {
        JFrame frame = new JFrame();
        setPreferredSize(new Dimension(width, height));
        frame.add(this);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centres the frame in the middle of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void drawGrid(Graphics g){
        for (int i = 0; i < width; i++){
            g.drawLine(i * 5, 0, i*5, height);
        }
        for (int i = 0; i < height; i++){
            g.drawLine(0, i*5, width, i*5);
        }

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    public List<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(List<Rock> rocks) {
        this.rocks = rocks;
    }

    public List<WaterLily> getLilies() {
        return lilies;
    }

    public void setLilies(List<WaterLily> lilies) {
        this.lilies = lilies;
    }

}