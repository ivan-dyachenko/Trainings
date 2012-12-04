package conveyor;

import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class ItemTest {
    @Test
    public void shouldHaveZeroLifeTimeAfterCreation() {
        // given
        final Item item = new Item();
        // when
        int lifeTime = item.lifeTime();
        // then
        assertThat(lifeTime).isZero();
    }

    @Test
    public void shouldIncrementLifeTimeDuringTick() {
        // given
        final Item item = new Item();
        // when
        item.tick();
        // then
        assertThat(item.lifeTime()).isEqualTo(1);
    }
}
