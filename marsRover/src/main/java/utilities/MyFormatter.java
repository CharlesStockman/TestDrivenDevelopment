package utilities;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Set the Formatter for the Logger -- A formatter describe the format each LogRecord
 */
public class MyFormatter extends SimpleFormatter {
    @Override
    public String format(LogRecord record) {

        System.out.println("Currently executing format");
        StringBuilder builder = new StringBuilder();
        builder.append(record.getSourceClassName()).append(".").append(record.getSourceMethodName());
        builder.append(" : ").append(record.getMessage());

        System.out.println("The result is " + builder.toString());

        return(builder.toString());
    }
}
