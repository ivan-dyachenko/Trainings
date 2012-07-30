package conveyor;

import java.util.List;

public class Conveyor {

    private List<Worker> workers;

    public Conveyor(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Item> tick(final List<Item> input) {
        if (workers.isEmpty())
            return input;

        final Worker firstWorker = workers.get(0);
        firstWorker.enqueue(input);
        List<Item> output = firstWorker.tick();

        for (int i = 1; i < workers.size(); i++) {
            final Worker worker = workers.get(i);
            final List<Item> tmp = worker.tick();
            worker.enqueue(output);
            output = tmp;
        }

        return output;
    }
}


