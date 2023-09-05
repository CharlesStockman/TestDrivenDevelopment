package rover.CommandPatterns.internalCommands;

import rover.CommandPatterns.History;
import rover.RoverData;

/**
 * The commands that is executed first to rest the history log and log the default information
 */
public class StartCommand implements CommandInterface<Boolean> {

    Character command;

    RoverData roverData;
    public StartCommand(Character command, RoverData initialRoverData) {
        this.command = command;
        this.roverData = initialRoverData;
    }

    @Override
    public Boolean execute() {
        History.getInstance().clearHistory();
        addEventHistory(String.valueOf(Character.MIN_VALUE), roverData);
        return true;
    }
}
