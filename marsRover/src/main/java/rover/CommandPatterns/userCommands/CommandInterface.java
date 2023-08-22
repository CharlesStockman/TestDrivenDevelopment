package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CommandPatterns.History;
import rover.CompassPoint;
import rover.RoverData;

/**
 * The interface for the Commands used by Rover.  For example @Link { rover.CommandPatterns.internalCommands.MoveCommand }
 * @param <T>   The return type of the command.
 */
public interface CommandInterface<T> {
     T  execute(CompassPoint compassPoint, Position position );

    default void addEventHistory(String command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.getInstance().addEvent(command, compassPoint, position);
    }

    default RoverData create( CompassPoint compassPoint, Position position, Boolean isObstructed ) {
        return new RoverData(compassPoint, position, isObstructed);
    }
}
