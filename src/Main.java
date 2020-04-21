import logic.Maths;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Create view.UI
        for (int i=0; i<100; i++){
            int randomNumber = (int) ((Math.random()*((1-(-1))+1)) + (-1));
            System.out.println(randomNumber);
        }

        // Somehow trigger Game startùù
        Game game = new Game("duck game", 700, 700);
        game.start();
        // ... Interaction with the game ...

        // Listen for game pause / stop / exit ...
    }

}
