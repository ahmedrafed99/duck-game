package ahmedr.duckGame.model;


public class WaterLily extends GameObject {

    public WaterLily(String name, int x, int y) {
        super(name, x, y);
        this.setScale(0.07);
        this.setImage("Lily.png");
    }
}

