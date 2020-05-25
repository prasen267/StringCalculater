import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.security.cert.Extension;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    public void sumsEmptyStringToZero() {

        assertEquals(StringCalculator.sum(""), 0);
    }

    @Test
    public void sumSingleNumberToSelf() {
        assertEquals(StringCalculator.sum("5"), 5);
        assertEquals(StringCalculator.sum("42"), 42);
    }

    @Test
    public void sumTwoNumbersSeperatedbyComma() {
        assertEquals(StringCalculator.sum("1,2"), 3);
        assertEquals(StringCalculator.sum("1,3"), 4);
    }

    @Test
    public void sumThreeNumbersSeperatedbyComma() {
        assertEquals(StringCalculator.sum("1,2,3"), 6);

    }

    @Test
    public void sumThreeNumbersSeperatedNewLine() {
        assertEquals(StringCalculator.sum("1\n2"), 3);

    }

    @Test
    public void sumNumbersDelimitedByCommaorNewline() {
        assertEquals(StringCalculator.sum("1,3\n2"), 6);

    }

    @Test
    public void useDemilitorSpecified() {
        assertEquals(StringCalculator.sum("//;\n1;2"), 3);
        assertEquals(StringCalculator.sum("//.\n1.2"), 3);
    }



    @Test()
    public void throwsIfNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringCalculator.sum("-3,-1");
        });
        String expectedMessage = "Negative number not allowed: -3,-1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void filterNumberGreaterThan1000(){
        assertEquals(StringCalculator.sum("//;\n1000;2"), 2);
    }
    @Test
    public void acceptsDelimiterofAnyLength(){
        assertEquals(StringCalculator.sum("//[***]\n1***2***3"), 6);
    }
    @Test
    public void acceptsMultipleDelimiter(){
        assertEquals(StringCalculator.sum("//[-][;]\n1-2;3"), 6);
        assertEquals(StringCalculator.sum("//[--][..]\n2--3..4"), 9);
    }
}