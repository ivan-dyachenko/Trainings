package conveyor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Worker {
    private Dice dice;
    private final Queue<Item> queue = new LinkedList<Item>();

    public Worker(final Dice dice) {
        this.dice = dice;
    }

    public List<Item> tick() {
        tickEachItem();

        List<Item> output = new ArrayList<Item>();
        int howMuchToProcess = howMuchToProcess();
        while (howMuchToProcess-- > 0 && !queue.isEmpty()) {
            output.add(queue.poll());
        }

        return output;
    }

    private int howMuchToProcess() {
        int rollValue = dice != null ? dice.roll() : queue.size();
        rollValue = rollValue > queue.size() ? queue.size() : rollValue;
        return rollValue;
    }

    private void tickEachItem() {
        for (Item item : queue) {
            item.tick();
        }
    }

    public void enqueue(List<Item> items) {
        queue.addAll(items);
    }
}
