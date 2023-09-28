package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
<<<<<<< HEAD
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
=======
>>>>>>> pom_additions

public class ExpressionEvaluatorTest {

    @Test
    public void handle_input_is_empty_string() throws IOException {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        Assertions.assertEquals(0, expressionEvaluator.add(""));
    }

<<<<<<< HEAD
    @Test
    public void verify_antlr_g4_exist() {
        Path path = Paths.get("src/main/antlr4/org/example");
        Assertions.assertTrue(Files.exists(path));
    }

=======
>>>>>>> pom_additions
//    @Test
//    public void handle_input_is_comma() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(0, stringCalculator.add(","));
//    }
//
//    @Test
//    void handle_input_is_newline() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("    2,3"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameter numbers cannot have spaces at the beginning or end of the string");
//    }
//
//    @Test
//    void handle_input_has_spaces_and_comma() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("    2,3"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameter numbers cannot have spaces at the beginning or end of the string");
//
//    }
//
//    @Test
//    void handle_input_should_have_spaces_at_the_start_or_end() {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception;
//
//        exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("   2,3    "));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameter numbers cannot have spaces at the beginning or end of the string");
//
//        exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("2,3    "));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameter numbers cannot have spaces at the beginning or end of the string");
//
//        exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("    2,3"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameter numbers cannot have spaces at the beginning or end of the string");
//
//    }
//
//
//    @Test
//    public void handle_input_has_2_commas_together() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add(",,"));
//        Assertions.assertEquals(exception.getMessage(), "Parameters cannot start or end with a delimiter");
//    }
//
//    @Test
//    public void handle_input_has_2_commas_together_with_valid_numbers() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("2,,3"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Delimiters must be separated by one delimiter only such as comma or newline");
//    }
//
//    @Test
//    public void handle_input_is_one_number() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(4, stringCalculator.add("4"));
//    }
//
//    @Test
//    public void handle_input_is_one_number_and_comma() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception;
//
//        exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("1,"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameters cannot start or end with a delimiter");
//
//        exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("1,"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameters cannot start or end with a delimiter");
//    }
//
//    @Test
//    public void handle_input_is_comma_and_one_number() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add(",4"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Parameters cannot start or end with a delimiter");
//    }
//
//    @Test
//    public void handle_input_when_there_is_no_number_between_delimiters() {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException exception = Assertions.assertThrows(
//                InvalidParameterException.class, () -> stringCalculator.add("4,\n4"));
//        Assertions.assertEquals(
//                exception.getMessage(), "Delimiters must be separated by one delimiter only such as comma or newline");
//    }
//
//    @Test
//    public void handle_input_is_two_number() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(10, stringCalculator.add("4,6"));
//    }
//
//
//    @Test
//    public void handle_input_separator_are_both_comma_and_newline() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(21, stringCalculator.add("1,2\n3,4,5\n6"));
//    }
//
//    @Test
//    public void handle_input_both_are_invalid() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException invalidParameterException = Assertions.assertThrows(
//                InvalidParameterException.class, () -> (new StringCalculator()).add("dog,cat"));
//        Assertions.assertEquals(invalidParameterException.getMessage(), "Invalid number -- dog, cat");
//    }
//
//    @Test
//    public void handle_input_some_inputs_are_invalid() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        InvalidParameterException invalidParameterException = Assertions.assertThrows(
//                InvalidParameterException.class, () -> (new StringCalculator()).add("dog,cat,1,3,bird"));
//        Assertions.assertEquals(invalidParameterException.getMessage(), "Invalid number -- dog, cat, bird");
//    }
//
//    @Test
//    public void handle_input_where_both_number_are_negative() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(-7, stringCalculator.add("-5,-2"));
//    }
//
//    @Test
//    public void handle_input_where_1st_number_is_negative() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(3, stringCalculator.add("5,-2"));
//    }
//
//    @Test
//    public void handle_input_where_2nd_number_is_negative() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        Assertions.assertEquals(3, stringCalculator.add("5,-2"));
//    }
//
//    @Test
//    public void handle_inputs_is_null() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class,
//                () -> stringCalculator.add(null));
//        Assertions.assertEquals(
//                nullPointerException.getMessage(),
//                "Parameter numbers cannot be null");
//    }
//
//    @Test
//    public void handle_input_variable_amount() throws IOException {
//        StringCalculator stringCalculator = new StringCalculator();
//        for (Map.Entry<String, Integer> test : createMultipleInputArgs().entrySet()) {
//            Assertions.assertEquals(test.getValue(), stringCalculator.add(test.getKey()));
//        }
//    }
//
//
//    /**
//     * Create a Map of inputs so we verify that the function can work with different number of inputs.
//     *
//     * key = Input to be use as a parameter to the add function
//     * value = The return value of the function
//     *
//     * @return   A list of 6 different to determine if the add() can accept multiple arguments.
//     */
//    private Map<String, Integer> createMultipleInputArgs() throws IOException {
//        Map<String, Integer> inputs = new HashMap<>();
//
//        inputs.put("0,1,2", 3);
//        inputs.put("0,1,2,3", 6);
//        inputs.put("0,1,2,3,4", 10);
//        inputs.put("0,1,2,3,4,5", 15);
//        inputs.put("0,1,2,3,4,5,6", 21);
//        inputs.put("0,1,2,3,4,5,6,7", 28);
//
//        return inputs;
//
//    }


}
