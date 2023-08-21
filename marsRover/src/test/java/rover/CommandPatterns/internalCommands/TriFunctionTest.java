package rover.CommandPatterns.internalCommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rover.CommandPatterns.TriFunction;

public class TriFunctionTest {

    @Test
    public void testTriFunction() {
        TriFunction<Integer, Integer, Integer, Integer> chuck = TriFunctionTest::addNumbers;
        TriFunction<Integer, Integer, Integer, String> chuck2 = chuck.andThen(TriFunctionTest::multiplyByFive);

        Assertions.assertEquals(6,  chuck.apply(1,2,3));
        Assertions.assertEquals("30", chuck2.apply(1,2,3));
    }

    public static Integer addNumbers(Integer... numbers) {
        Integer total = 0;
        for ( Integer number : numbers )
            total += number;
        return total;
    }

    public static String multiplyByFive(Integer number) {
        number = 5 * number;
        return Integer.toString(number);
    }



}
