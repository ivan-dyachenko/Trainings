package conveyor;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkerTest {
    @Test
    public void shouldReturnNothingIfNothingToDo() {
        // given
        final Worker worker = new Worker(null);
        // when
        final List<Item> output = worker.tick();
        // then
        assertThat(output).isEmpty();
    }

    @Test
    public void shouldProcessNotGreaterThanItemsInQueue() {
        // given                                  ]
        final Dice dice = createDiceStub(4);
        final Worker worker = new Worker(dice);
        final List<Item> items = Arrays.asList(new Item(), new Item());
        worker.enqueue(items);
        // when
        final List<Item> output = worker.tick();
        // then
        assertThat(output).isEqualTo(items);
    }

    private Dice createDiceStub(int rollValue) {
        final Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(rollValue); return dice;
    }
}
