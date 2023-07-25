import com.sun.source.tree.WhileLoopTree;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.StringTokenizer;

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
    private List<Integer> retrieveNumbers(String numbers) {
        Integer result = null;
        List<Integer> values = new ArrayList<>();
        StringBuffer buffer = null;

        if ( numbers == null )
            throw new NullPointerException("Cannot invoke \"String.length()\" because \"str\" is null");

        StringTokenizer tokenizer = new StringTokenizer(numbers, ",");
        while  ( tokenizer.countTokens() > 0 ) {
              String nextToken = tokenizer.nextToken();
              try {
                  values.add(Integer.parseInt(nextToken));
              } catch ( NumberFormatException exception ) {
                  buffer = ( buffer == null ) ?new StringBuffer("Invalid number -- " + nextToken) : buffer.append(", " + nextToken);
              }
        }

        if ( buffer != null && buffer.length() > 0 )
            throw new InvalidParameterException(buffer.toString());

        return values;
    }
    public Integer add(String numbers) {

        List<Integer> values = retrieveNumbers(numbers);
        return values.stream().reduce(0, (a,b) -> a + b);
    }
}
