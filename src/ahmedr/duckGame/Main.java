package ahmedr.duckGame;

import ahmedr.duckGame.Game;

public class Main {

    public static void main(String[] args) {
        // Create ahmedr.duckGame.view.UI

        // Somehow trigger ahmedr.duckGame.Game startùù
        Game game = Game.getInstance();
        game.start();
        // ... Interaction with the game ...

        // Listen for game pause / stop / exit ...
    }

}
