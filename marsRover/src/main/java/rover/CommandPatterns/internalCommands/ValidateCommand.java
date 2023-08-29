package rover.CommandPatterns.internalCommands;

import lombok.extern.java.Log;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The command used to validate that the command in the Command Rover String are valid.
 */
@Log
public class ValidateCommand implements CommandInterface<String> {

    private String input;

    public ValidateCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute() {

        input = input.toUpperCase();

        // Pattern that represent the Correct Characters for rover command String
        Pattern invalidCommand = Pattern.compile("([^LRM])+");
        Matcher matcher = invalidCommand.matcher(input);

        StringBuilder buffer = new StringBuilder();
        StringBuilder invalidIndexes = new StringBuilder();
        StringBuilder invalidCommands = new StringBuilder();

        while ( matcher.find()) {
            invalidIndexes.append(",").append(matcher.start());
            invalidCommands.append(",").append(matcher.group());
        }

        if ( invalidIndexes.length() > 0 )
            buffer.append("0 base index(s) are ").append(invalidIndexes.substring(1)).append("\n");

        if ( invalidCommands.length() > 0 )
            buffer.append("Incorrect command(s) are ").append(invalidCommands.substring(1));

        if ( buffer.length() > 0  )
            throw new InvalidParameterException(buffer.insert(0, "Command String : " + input + "\n").toString());

        log.info("Validate over command string is " + input);

        return input;
    }
}
