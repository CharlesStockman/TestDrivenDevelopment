import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class StringCalculatorTest {

    @Test
    public void handle_input_is_empty_string() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void handle_input_is_comma() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(0, stringCalculator.add(","));
    }

    @Test void  handle_input_is_newline() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(0, stringCalculator.add("\n"));
    }

    @Test void handle_input_has_spaces_and_comma() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(0, stringCalculator.add("    ,     "));

    }

    @Test
    public void handle_input_has_2_commas_together() {
        StringCalculator stringCalculator = new StringCalculator();

        InvalidParameterException exception = Assertions.assertThrows(
                InvalidParameterException.class, () -> stringCalculator.add(",,"));
        Assertions.assertEquals(exception.getMessage(), "The 1st parameter cannot have a comma and no digit after");
    }

    @Test
    public void handle_input_has_2_commas_together_with_valid_numbers() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(5, stringCalculator.add("2,,3"));
    }

    @Test
    public void handle_input_is_one_number() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(4, stringCalculator.add("4"));
    }

    @Test
    public void handle_input_is_one_number_and_comma() {
        StringCalculator stringCalculator = new StringCalculator();
        InvalidParameterException exception;

        exception = Assertions.assertThrows(
                InvalidParameterException.class, () -> stringCalculator.add("1,2,"));
        Assertions.assertEquals(exception.getMessage(), "The 1st parameter cannot have a comma and no digit after");

        exception = Assertions.assertThrows(
                InvalidParameterException.class, () -> stringCalculator.add("1,2,   "));
        Assertions.assertEquals(exception.getMessage(), "The 1st parameter cannot have a comma and no digit after");
    }

    @Test
    public void handle_input_is_comma_and_one_number() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(4, stringCalculator.add(",4"));
    }

    @Test
    public void handle_input_is_two_number() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(10, stringCalculator.add("4,6"));
    }


    @Test
    public void handle_input_separator_are_both_comma_and_newline() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(21, stringCalculator.add("1,2\n3,4,5\n6"));
    }

    @Test
    public void handle_input_both_are_invalid() {
        StringCalculator stringCalculator = new StringCalculator();
        InvalidParameterException invalidParameterException = Assertions.assertThrows(
                InvalidParameterException.class, () -> (new StringCalculator()).add("dog,cat"));
        Assertions.assertEquals(invalidParameterException.getMessage(), "Invalid number -- dog, cat");
    }

    @Test
    public void handle_input_some_inputs_are_invalid() {
        StringCalculator stringCalculator = new StringCalculator();
        InvalidParameterException invalidParameterException = Assertions.assertThrows(
                InvalidParameterException.class, () -> (new StringCalculator()).add("dog,cat,1,3,bird"));
        Assertions.assertEquals(invalidParameterException.getMessage(), "Invalid number -- dog, cat, bird");
    }

    @Test
    public void handle_input_where_both_number_are_negative() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(-7, stringCalculator.add("-5,-2"));
    }

    @Test
    public void handle_input_where_1st_number_is_negative() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(3, stringCalculator.add("5,-2"));
    }

    @Test
    public void handle_input_where_2nd_number_is_negative() {
        StringCalculator stringCalculator = new StringCalculator();
        Assertions.assertEquals(3, stringCalculator.add("5,-2"));
    }

    @Test
    public void handle_inputs_is_null() {
        StringCalculator stringCalculator = new StringCalculator();
        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
                () -> stringCalculator.add(null));
        Assertions.assertEquals(
                nullPointerException.getMessage(),
                "Cannot invoke \"String.length()\" because \"str\" is null");
    }

    @Test
    public void handle_input_variable_amount() {
        StringCalculator stringCalculator = new StringCalculator();
        for (Map.Entry<String, Integer> test : createMultipleInputArgs().entrySet()) {
            Assertions.assertEquals(test.getValue(), stringCalculator.add(test.getKey()));
        }
    }

    /**
     * Create a Map of inputs so we verify that the function can work with different number of inputs.
     *
     * key = Input to be use as a parameter to the add function
     * value = The return value of the function
     *
     * @return   A list of 6 different to determine if the add() can accept multiple arguments.
     */
    private Map<String, Integer> createMultipleInputArgs() {
        Map<String, Integer> inputs = new HashMap<>();

        inputs.put("0,1,2", 3);
        inputs.put("0,1,2,3", 6);
        inputs.put("0,1,2,3,4", 10);
        inputs.put("0,1,2,3,4,5", 15);
        inputs.put("0,1,2,3,4,5,6", 21);
        inputs.put("0,1,2,3,4,5,6,7", 28);

        return inputs;

    }
}
