package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CommandPatterns.History;
import rover.CompassPoint;

/**
 * The interface for the Commands used by Rover.  For example @Link { rover.CommandPatterns.internalCommands.MoveCommand }
 * @param <T>   The return type of the command.
 */
public interface CommandInterface<T> {
     T  execute(Character command, CompassPoint compassPoint, Position position );

    default void addEventHistory(Character command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.getInstance().addEvent(command, compassPoint, position);
    }
}
