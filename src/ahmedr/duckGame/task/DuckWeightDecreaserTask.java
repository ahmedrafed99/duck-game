package ahmedr.duckGame.task;

import ahmedr.duckGame.Game;
import ahmedr.duckGame.model.Duck;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DuckWeightDecreaserTask extends TimerTask {

    private List<Duck> ducks = new ArrayList<>();

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    @Override
    public void run()
    {
        for (Duck duck: ducks) {
            if (duck.isAlive()) {
                duck.loseWeight();
                // TODO: access game instance and kill that duck if it reaches minimum weight
            } else {
                Game.getInstance().kill(duck);
            }
        }
    }
}
