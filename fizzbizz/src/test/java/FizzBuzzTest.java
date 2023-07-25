import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import util.CaptureOutput;

public class FizzBuzzTest {

    CaptureOutput captureOutput = null;
    static Map<Integer, String > answers = null;

    @BeforeAll
    public static void setupBeforeAll() {
        answers = generateFizzBuzzOutput();
    }

    @BeforeEach
    public void setupBeforeEachTest() {
        captureOutput = new CaptureOutput();
        captureOutput.start();
    }

    @AfterEach
    public void tearDownAfterEachTest() {
        captureOutput.end();
    }

    private static Map<Integer, String> generateFizzBuzzOutput() {
        StringBuffer answer = new StringBuffer();
        Map<Integer, String> answers = new HashMap<>();

        answer.append("1\n");
        answers.put(1, answer.toString());

        answer.append("2\n");
        answers.put(2, answer.toString());

        answer.append("fizz\n");
        answers.put(3, answer.toString());

        answer.append("4\n");
        answers.put(4, answer.toString());

        answer.append("buzz\n");
        answers.put(5, answer.toString());

        answer.append("fizz\n");
        answers.put(6, answer.toString());

        answer.append("7\n");
        answers.put(7, answer.toString());

        answer.append("8\n");
        answers.put(8, answer.toString());

        answer.append("fizz\n");
        answers.put(9, answer.toString());

        answer.append("buzz\n");
        answers.put(10, answer.toString());

        answer.append("11\n");
        answers.put(11, answer.toString());

        answer.append("fizz\n");
        answers.put(12, answer.toString());

        answer.append("13\n");
        answers.put(13, answer.toString());

        answer.append("14\n");
        answers.put(14, answer.toString());

        answer.append("fizzbuzz\n");
        answers.put(15, answer.toString());

        return answers;

    }

    @Test public void print_fizzbuzz_input_1() {  _print_fizzbuzz_input(1); }
    @Test public void print_fizzbuzz_input_2() {  _print_fizzbuzz_input(2); }
    @Test public void print_fizzbuzz_input_3() {  _print_fizzbuzz_input(3); }
    @Test public void print_fizzbuzz_input_4() {  _print_fizzbuzz_input(4); }
    @Test public void print_fizzbuzz_input_5() {  _print_fizzbuzz_input(5); }
    @Test public void print_fizzbuzz_input_6() {  _print_fizzbuzz_input(6); }
    @Test public void print_fizzbuzz_input_7() {  _print_fizzbuzz_input(7); }
    @Test public void print_fizzbuzz_input_8() {  _print_fizzbuzz_input(8); }
    @Test public void print_fizzbuzz_input_9() {  _print_fizzbuzz_input(9); }
    @Test public void print_fizzbuzz_input_10() {  _print_fizzbuzz_input(10); }
    @Test public void print_fizzbuzz_input_11() {  _print_fizzbuzz_input(11); }
    @Test public void print_fizzbuzz_input_12() {  _print_fizzbuzz_input(12); }
    @Test public void print_fizzbuzz_input_13() {  _print_fizzbuzz_input(13); }
    @Test public void print_fizzbuzz_input_14() {  _print_fizzbuzz_input(14); }
    @Test public void print_fizzbuzz_input_15() {  _print_fizzbuzz_input(15); }

    @Test public void print_fizzbuzz_invalid_argument() {
        InvalidParameterException exception =
                Assertions.assertThrows(InvalidParameterException.class, () -> new FizzBuzz().display(-1));
        Assertions.assertEquals(exception.getMessage(), "The number must be greater than 0");
    }

    private void _print_fizzbuzz_input(int inputNumber) {
        final Consumer<Integer> display = number -> new FizzBuzz().display(number);
        display.accept(inputNumber);
        Assertions.assertEquals(
                answers.get(inputNumber),
                captureOutput.getText(),
                "_print_fizzbuzz_input method fails at " + inputNumber);

    }
}

