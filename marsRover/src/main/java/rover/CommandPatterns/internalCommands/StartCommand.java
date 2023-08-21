package rover.CommandPatterns.internalCommands;

import common.Position;
import rover.CompassPoint;
import rover.CommandPatterns.History;

/**
 * The commands that is executed first to rest the history log and log the default information
 */
public class StartCommand implements CommandInterface<Boolean> {

    Character command;
    CompassPoint compassPoint;
    Position position;

    public StartCommand(Character command, CompassPoint compassPoint, Position position) {
        this.command = command;
        this.compassPoint = compassPoint;
        this.position = position;
    }

    @Override
    public Boolean execute() {
        History.getInstance().clearHistory();
        addEventHistory(String.valueOf(Character.MIN_VALUE), compassPoint, position, "");
        return true;
    }
}
