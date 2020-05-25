import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {

    private String delimiter;
    private String numbers;

    public StringCalculator(String delimiter, String numbers) {
        this.delimiter = delimiter;
        this.numbers = numbers;
    }

    public static int sum(String input) {

        if (input.isEmpty())
            return 0;
        return parseInput(input).getSum();
    }

    private int getSum() {
        checkNegative();
        return getNumers()
                .sum();
    }

    private void checkNegative() {
        String negativeNumbers = getNumers().filter(number -> number < 0)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
        if (!negativeNumbers.isEmpty())
            throw new IllegalArgumentException("Negative number not allowed: " + negativeNumbers);

    }

    private IntStream getNumers() {
        return Arrays.stream(numbers.split(delimiter))
                .mapToInt(Integer::parseInt).filter(number->number<1000);
    }

    private static StringCalculator parseInput(String input) {
        StringCalculator calculator;
        if (input.startsWith("//")) {
            String[] parts = input.split("\n", 2);

            calculator = new StringCalculator(parseDelimiter(parts[0]), parts[1]);
        } else {
            calculator = new StringCalculator(",|\n", input);
        }
        return calculator;
    }

    private static String parseDelimiter(String header) {
        String delimiter=header.substring(2);
        if(delimiter.startsWith("[")){
           delimiter= delimiter.substring(1,delimiter.length()-1);

        }
        return  Stream.of(delimiter.split("]\\["))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }


}
