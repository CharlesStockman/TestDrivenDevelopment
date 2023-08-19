package rover.CommandPattern.commands.internalCommands;

import rover.CommandPattern.commands.internalCommands.CommandInterface;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCommand implements CommandInterface {

    private String input;

    public ValidateCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute() {

        input = input.toUpperCase();

        Pattern invalidCommand = Pattern.compile("([^LRM])+");
        Matcher matcher = invalidCommand.matcher(input);

        StringBuffer buffer = new StringBuffer();
        StringBuffer invalidIndexes = new StringBuffer();
        StringBuffer invalidCommands = new StringBuffer();

        buffer.append("Command String : " + input + "\n");
        int initialLength = buffer.length();
        while ( matcher.find()) {
            invalidIndexes.append("," + matcher.start());
            invalidCommands.append("," + matcher.group());
        }

        if ( invalidIndexes.length() > 0 )
            buffer.append("0 base index(s) are " + invalidIndexes.substring(1) + "\n");

        if ( invalidCommands.length() > 0 )
            buffer.append("Incorrect command(s) are " + invalidCommands.substring(1));

        if ( buffer.length() != initialLength )
            throw new InvalidParameterException(buffer.toString());

        return input;
    }
}
