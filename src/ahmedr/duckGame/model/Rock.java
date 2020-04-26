package ahmedr.duckGame.model;

public class Rock extends GameObject {

    public Rock(String name, int x, int y) {
        super(name, x, y);
        this.setImage("rock.png");
        this.setScale(0.1);
    }
}
