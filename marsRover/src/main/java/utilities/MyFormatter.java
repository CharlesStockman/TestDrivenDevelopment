package utilities;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;


/**
 * Set the Formatter for the Logger -- A formatter describe the format each LogRecord
 */
public class MyFormatter extends SimpleFormatter {
    @Override
    public String format(LogRecord record) {

        String builder = record.getSourceClassName() + "." + record.getSourceMethodName() +
                " : " + record.getMessage();

        return(builder);
    }
}
