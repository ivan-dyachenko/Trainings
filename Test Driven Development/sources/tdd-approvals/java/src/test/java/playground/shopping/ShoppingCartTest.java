package playground.shopping;

import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.KSDiffReporter;
import org.junit.Test;

import static org.approvaltests.Approvals.verify;

@UseReporter(KSDiffReporter.class)
public class ShoppingCartTest {

    @Test
    public void should_work_some_how() throws Exception {
        // do
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new Product("iPad", 500));
        shoppingCart.add(new Product("Mouse", 20));
        shoppingCart.confirm();

        // verify
        verify(shoppingCart.toString());
    }
}
