package task;

import model.Duck;

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
            }
        }
    }
}
