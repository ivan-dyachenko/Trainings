package conveyor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        // given
        final Dice dice = createDiceStub(4);
        final Worker worker = new Worker(dice);
        final List<Item> items = createItems(2);
        worker.enqueue(items);
        // when
        final List<Item> output = worker.tick();
        // then
        assertThat(output).isEqualTo(items);
    }

    @Test
    public void shouldProcessNotGreaterThanRolledValue() {
        // given
        final int rollValue = 3;
        final Dice dice = createDiceStub(rollValue);
        final Worker worker = new Worker(dice);
        final List<Item> items = createItems(4);
        worker.enqueue(items);
        // when
        final List<Item> output = worker.tick();
        // then
        assertThat(output).isEqualTo(items.subList(0, rollValue));
    }

    @Test
    public void shouldAddItemsInQueue() {
        // given
        final Dice dice = createDiceStub(3);
        final Worker worker = new Worker(dice);

        final List<Item> firstPortionOfItems = createItems(2);
        worker.enqueue(firstPortionOfItems);

        final List<Item> secondPortionOfItems = createItems(6);
        worker.enqueue(secondPortionOfItems);

        // when
        final List<Item> output = worker.tick();

        // then
        final List<Item> expectedResult = new LinkedList<Item>() {{
            addAll(firstPortionOfItems);
            add(secondPortionOfItems.get(0));
        }};

        assertThat(output).isEqualTo(expectedResult);
    }

    @Test
    public void shouldRemoveProcessedItemsFromQueue() {
        // given
        final int rollValue = 2;
        final Dice dice = createDiceStub(rollValue);
        final Worker worker = new Worker(dice);

        final List<Item> items = createItems(3);
        worker.enqueue(items);

        // when
        final List<Item> firstOutput = worker.tick();
        final List<Item> secondOutput = worker.tick();

        // then
        assertThat(firstOutput).isEqualTo(items.subList(0, rollValue));
        assertThat(secondOutput).isEqualTo(items.subList(rollValue, items.size()));
    }

    @Test
    public void shouldRollDiceOnlyOnceDuringTick() {
        // given
        final Dice dice = createDiceStub(3);
        final Worker worker = new Worker(dice);
        final List<Item> items = createItems(4);
        worker.enqueue(items);
        // when
        worker.tick();
        // then
        verify(dice, times(1)).roll();
    }

    @Test
    public void shouldCallTickForAllItemsInQueue() {
        // given
        final Dice dice = createDiceStub(1);
        final Worker worker = new Worker(dice);
        final Item firstItem = mock(Item.class);
        final Item secondItem = mock(Item.class);
        final List<Item> items = Arrays.asList(firstItem, secondItem);
        worker.enqueue(items);
        // when
        worker.tick();
        // then
        verify(firstItem, times(1)).tick();
        verify(secondItem, times(1)).tick();
    }

    private List<Item> createItems(int count) {
        List<Item> list = new ArrayList<Item>(count);
        for (int i = 0; i < count; i++) {
            list.add(new Item());
        }
        return list;
    }

    private Dice createDiceStub(int rollValue) {
        final Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(rollValue);
        return dice;
    }
}
