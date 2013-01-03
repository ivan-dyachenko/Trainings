package kata;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StringCalculator();
    }

    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void shouldReturnNumberIfStringIsNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void shouldAddNumbersSeparatedByComma() {
        assertEquals(7, calculator.add("3,4"));
    }

    @Test
    public void shouldAddUnknownAmountOfNumbers() {
        assertEquals(21, calculator.add("1,2,3,4,5,6"));
    }

    @Test
    public void shouldAcceptNewlineAsValidDelimiter() {
        assertEquals(7, calculator.add("1,2\n4"));
    }

    @Test
    public void shouldAcceptCustomDelimiterSyntax() {
        assertEquals(7, calculator.add("//;\n1;2;4"));
    }

    @Test
    public void customDelimiterCouldBeAlsoARegExpSpecialChar() {
        assertEquals(7, calculator.add("//.\n1.2.4"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldRaiseExceptionOnNegatives() {
        calculator.add("-1,2,3");
    }

    @Test
    public void expectationMessageShouldContainTheNegativeNumber() {
        try {
            calculator.add("-1,-2,3");
            fail("Exception expected");
        }
        catch (RuntimeException ex) {
            assertEquals("Negative not allowed: -1, -2", ex.getMessage());
        }
}
}
