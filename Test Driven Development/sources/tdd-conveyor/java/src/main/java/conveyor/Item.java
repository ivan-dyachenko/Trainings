package conveyor;

public class Item {
    private int lifeTime;

    public int lifeTime() {
        return lifeTime;
    }

    public void tick() {
        lifeTime++;
    }
}
