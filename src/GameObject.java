
public abstract class GameObject {
    private String name;
    private int x;
    private int y;
    private boolean isAlive = true;

    public GameObject() {
    }

    public GameObject(String name) {
        this.name = name;
    }

    public GameObject(String name, int x, int y) {
        this(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive(){
        isAlive = true;
        return true;
    }

    public void die(){
        isAlive = false;
    }
}
