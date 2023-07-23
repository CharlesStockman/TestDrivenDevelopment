import java.lang.reflect.Method;
import java.security.InvalidParameterException;

public class FizzBuzz {

    private String convert(int number) {
        StringBuilder result = new StringBuilder();
        if ( number % 3 != 0 && number % 5 != 0 )
            result.append(String.valueOf(number));
        else {
            if (number % 3 == 0)
                result.append("fizz");
            if (number % 5 == 0)
                result.append("buzz");
        }

        return result.toString();
    }

    public void display(int number) {

        if ( number < 1 ) throw new InvalidParameterException("The number must be greater than 0");

        String result;
        for ( int index = 1; index <= number; index++) {
            result = convert(index);
            System.out.println(result);
        }
    }
}
