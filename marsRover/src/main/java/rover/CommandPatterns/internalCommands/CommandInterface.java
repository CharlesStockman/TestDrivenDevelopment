package rover.CommandPatterns.internalCommands;

import rover.RoverData;
import rover.CommandPatterns.History;

/**
 * The interface for the internal commands .  For example @Link { rover.CommandPatterns.internalCommands.StartCommand }
 * @param <T>   The return type of the command.
 */
public interface CommandInterface<T> {
     T  execute();

    default void addEventHistory(String command, RoverData roverData) {
        History.getInstance().addEvent(command, roverData);
    }
}
