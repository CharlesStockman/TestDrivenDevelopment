package rover.CommandPattern.commands.internalCommands;

import common.Position;
import rover.CompassPoint;
import rover.CommandPattern.commands.History;

public interface CommandInterface<T> {
     T  execute();

    default void addEventHistory(Character command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.Event event = new History.Event(command, compassPoint, position);
        History.getInstance().addEvent(command, compassPoint, position);
    }

}
