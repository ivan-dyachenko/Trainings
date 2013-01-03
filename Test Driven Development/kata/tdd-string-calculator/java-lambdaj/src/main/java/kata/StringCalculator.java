package kata;
import ch.lambdaj.function.convert.Converter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.lessThan;

public class StringCalculator {
    public int add(String text) {
        if (!text.isEmpty()) {
            List<Integer> numbers = getNumbers(text);
            ensureAllNonNegative(numbers);
            return sum(numbers).intValue();
        }
        return 0;
    }

    private List<Integer> getNumbers(String text) {
        String[] tokens = tokenize(text);
        return convert(tokens, toInt());
    }

    private void ensureAllNonNegative(List<Integer> numbers) {
        List<Integer> negatives = filter(lessThan(0), numbers);
        if (negatives.size() > 0)
            throw new RuntimeException("Negative not allowed: " + join(negatives, ", "));
    }

    private boolean isNegative(int number) {
        return number < 0;
    }

    private String[] tokenize(String text) {
        if (usesCustomDelimiterSyntax(text)) {
            return splitUsingCustomDelimiter(text);
        }
        return splitUsingCommaAndNewline(text);
    }

    private boolean usesCustomDelimiterSyntax(String text) {
        return text.startsWith("//");
    }

    private String[] splitUsingCommaAndNewline(String text) {
        return text.split(",|\\n");
    }

    private String[] splitUsingCustomDelimiter(String text) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbers = matcher.group(2);
        return numbers.split(Pattern.quote(customDelimiter));
    }

    private Converter<String, Integer> toInt() {
        return new Converter<String, Integer>() {
            @Override
            public Integer convert(String number) {
                return Integer.valueOf(number);
            }
        };
    }
}
