package conveyor;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ConveyorTest {

    @Test
    public void shouldReturnInputAsOutputIfNoWorkers() {
        // given
        final List<Worker> workers = Collections.emptyList();
        final Conveyor emptyConveyor = new Conveyor(workers);
        final List<Item> someInput = createItems(2);

        // when
        final List<Item> output = emptyConveyor.tick(someInput);

        // then
        assertThat(output).isEqualTo(someInput);
    }

    @Test
    public void shouldEnqueueInputToFirstWorkerBeforeProcessing() {
        // given
        final List<Item> someInput = createItems(3);

        final Worker firstWorker = mock(Worker.class);
        final Worker secondWorker = mock(Worker.class);

        final List<Worker> workers = Arrays.asList(
                firstWorker, secondWorker);
        final Conveyor conveyor = new Conveyor(workers);

        // when
        conveyor.tick(someInput);

        // then
        final InOrder order = inOrder(firstWorker);
        order.verify(firstWorker, times(1)).enqueue(someInput);
        order.verify(firstWorker, times(1)).tick();
    }

    @Test
    public void shouldReturnOutputOfLastWorker() {
        // given
        final List<Item> someOutput = createItems(2);

        final Worker firstWorker = mock(Worker.class);
        final Worker secondWorker = mock(Worker.class);
        when(secondWorker.tick()).thenReturn(someOutput);

        final List<Worker> workers = Arrays.asList(firstWorker, secondWorker);
        final Conveyor conveyor = new Conveyor(workers);
        final List<Item> someInput = createItems(1);

        // when
        final List<Item> output = conveyor.tick(someInput);

        // then
        assertThat(output).isEqualTo(someOutput);
    }

    @Test
    public void shouldEnqueueOutputOfPreviousWorkerToTheNextAfterProcessing() {
        // given
        final List<Item> outputOfFirstWorker = createItems(2);
        final List<Item> outputOfSecondWorker = createItems(3);

        final Worker firstWorker = mock(Worker.class);
        when(firstWorker.tick()).thenReturn(outputOfFirstWorker);

        final Worker secondWorker = mock(Worker.class);
        when(secondWorker.tick()).thenReturn(outputOfSecondWorker);

        final Worker thirdWorker = mock(Worker.class);

        final List<Worker> workers = Arrays.asList(
                firstWorker, secondWorker, thirdWorker);
        final Conveyor conveyor = new Conveyor(workers);

        final List<Item> someInput = Arrays.asList(new Item());

        // when
        conveyor.tick(someInput);

        // then
        InOrder secondWorkerOrder = inOrder(secondWorker);
        secondWorkerOrder.verify(secondWorker).tick();
        secondWorkerOrder.verify(secondWorker).enqueue(outputOfFirstWorker);

        InOrder thirdWorkerOrder = inOrder(thirdWorker);
        thirdWorkerOrder.verify(thirdWorker).tick();
        thirdWorkerOrder.verify(thirdWorker).enqueue(outputOfSecondWorker);
    }

    private List<Item> createItems(int count) {
        List<Item> list = new ArrayList<Item>(count);
        for (int i = 0; i < count; i++) {
            list.add(new Item());
        }
        return list;
    }


}
