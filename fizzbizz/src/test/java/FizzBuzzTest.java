import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

    @Test
    void convert_1() {
        Assertions.assertEquals("1", new FizzBuzz().convert(1));
    }

    @Test
    void convert_2() {
        Assertions.assertEquals("2", new FizzBuzz().convert(2));
    }

    @Test
    void convert_4() {
        Assertions.assertEquals("4", new FizzBuzz().convert(4));
    }

    @Test
    void convert_3_to_fizz() {
        Assertions.assertEquals("fizz", new FizzBuzz().convert(3));
    }
}
