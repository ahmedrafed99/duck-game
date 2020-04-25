import logic.Maths;
import logic.Physics;
import logic.Rectangle;
import model.Duck;
import model.WaterLily;

import javax.swing.*;
import java.awt.*;
import static logic.Maths.*;

public class Main {

    public static void main(String[] args) {
        // Create view.UI

        // Somehow trigger Game startùù
        Game game = new Game("duck game", 700, 700);
        game.start();
        // ... Interaction with the game ...

        // Listen for game pause / stop / exit ...
    }

}
