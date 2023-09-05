package rover.CommandPatterns.userCommands;

import utilities.Position;
import gridPlateau.GridPlateau;
import rover.CommandPatterns.History;
import rover.CompassPoint;
import rover.RoverData;

/**
 * The interface for the Commands used by Rover.  For example @Link { rover.CommandPatterns.internalCommands.MoveCommand }
 * @param <T>   The return type of the command.
 */
public interface CommandInterface<T> {
     T  execute(RoverData roverData);

    default void addEventHistory(String command, RoverData roverData) {
        History.getInstance().addEvent(command, roverData);
    }

    default RoverData create(CompassPoint compassPoint, Position position, Boolean isObstructed, GridPlateau gridPlateau) {
        return new RoverData(compassPoint, position, isObstructed, gridPlateau);
    }
}
