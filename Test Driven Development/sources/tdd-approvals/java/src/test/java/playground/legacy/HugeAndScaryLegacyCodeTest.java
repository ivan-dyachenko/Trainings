package playground.legacy;

import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.KSDiffReporter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyAll;

@UseReporter(KSDiffReporter.class)
public class HugeAndScaryLegacyCodeTest {

    @Test
    public void should_work_some_how() throws Exception {
        verify(
                HugeAndScaryLegacyCode.
                TheUgliesMethodYouMightEverSeen("someinput", 10, 'c')
        );
    }

    @Test
    public void should_try_to_cover_it() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String[] strings = {"", "approvals", "xpdays", "^stangeword^"};
        List<String> result = new ArrayList<String>();

        for (int number : numbers) {
            for (char c : chars) {
                for (String string : strings) {
                    result.add(
                            HugeAndScaryLegacyCode.
                            TheUgliesMethodYouMightEverSeen(string, number, c)
                    );
                }
            }
        }

        verifyAll("legacy", result.toArray());
    }
}
