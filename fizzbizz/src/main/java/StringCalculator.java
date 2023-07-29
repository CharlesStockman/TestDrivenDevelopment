import com.sun.source.tree.WhileLoopTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    /**
     * Convert the command delimited input into a list of numbers
     *
     * @param numbers  A comma delimited String with numbers
     *
     * @return  A list of numbers parsed from the numbers parameters
     *
     * @exception NumberFormatException  -- One or more of the entries is not a number
     */
    private List<Integer> retrieveNumbers(String numbers) throws IOException {
        Integer result = null;
        List<Integer> values = new ArrayList<>();
        StringBuffer buffer = null;

        if ( numbers == null )
            throw new NullPointerException("Parameter numbers cannot be null");

        if ( numbers.length() != numbers.trim().length() )
            throw new InvalidParameterException(
                    "Parameter numbers cannot have spaces at the beginning or end of the string");

        if ( numbers.equals("") || numbers.equals("\n") || numbers.equals(",")) {
            values.add(0);
            return values;
        }

        if ( numbers.length() > 1 && ( numbers.endsWith(",") || numbers.startsWith(","))) {
            throw new InvalidParameterException("Parameter numbers cannot start or end with a comma");
        }

        String tokens[] = numbers.split("[\n,]");
        for ( String nextToken : tokens ) {
              try {
                  values.add(Integer.parseInt(nextToken));
              } catch ( NumberFormatException exception ) {
                  if ( nextToken.length() == 0 ) nextToken = "<empty string>";
                  buffer = ( buffer == null ) ? new StringBuffer("Invalid number -- " + nextToken) : buffer.append(", " + nextToken);
              }
        }

        if ( buffer != null && buffer.length() > 0 )
            throw new InvalidParameterException(buffer.toString());

        return values;
    }
    public Integer add(String numbers) throws IOException {

        List<Integer> values = retrieveNumbers(numbers);
        return values.stream().reduce(0, (a,b) -> a + b);
    }
}
