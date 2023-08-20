package rover.CommandPatterns.internalCommands;

import common.Position;
import rover.CommandPatterns.History;
import rover.CompassPoint;

/**
 * The interface for the internal commands .  For example @Link { rover.CommandPatterns.internalCommands.StartCommand }
 * @param <T>   The return type of the command.
 */
public interface CommandInterface<T> {
     T  execute();

    default void addEventHistory(Character command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.getInstance().addEvent(command, compassPoint, position);
    }

}
