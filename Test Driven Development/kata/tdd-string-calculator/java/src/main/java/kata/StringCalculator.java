package kata;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String text) {
        if (!text.isEmpty()) {
            String[] tokens = tokenize(text);
            List<Integer> numbers = getNumbers(tokens);
            ensureAllNonNegative(numbers);
            return sum(numbers);
        }
        return 0;
    }

    private int sum(List<Integer> numbers) {
        int total = 0;
        for (Integer number : numbers) {
            total += number;
        }
        return total;
    }

    private void ensureAllNonNegative(List<Integer> numbers) {
        String message = "";
        for (Integer number : numbers) {
            if (isNegative(number))
                if (message.isEmpty())
                    message = number.toString();
                else
                    message += ", " + number.toString();
        }
        if (!message.isEmpty())
            throw new RuntimeException("Negative not allowed: " + message);
    }

    private List<Integer> getNumbers(String[] tokens) {
        List<Integer> result = new ArrayList<Integer>();
        for (String token : tokens) {
            result.add(toInt(token));
        }
        return result;
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

    private int toInt(String number) {
        return Integer.valueOf(number);
    }
}
