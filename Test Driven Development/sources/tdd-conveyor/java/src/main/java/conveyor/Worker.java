package conveyor;

import java.util.ArrayList;
import java.util.List;

public class Worker {
    private Dice dice;
    private final List<Item> queue = new ArrayList<Item>();

    public Worker(Dice dice) {
        this.dice = dice;
    }

    public List<Item> tick() {
        return queue;
    }

    public void enqueue(List<Item> items) {
        int rollValue = dice.roll();
        rollValue = rollValue > items.size() ? items.size() : rollValue;
        queue.addAll(items.subList(0, rollValue));
    }
}
