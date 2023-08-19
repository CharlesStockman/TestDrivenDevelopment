package rover.CommandPattern.commands.commands;

import common.Position;
import rover.CommandPattern.commands.History;
import rover.CompassPoint;

public interface CommandInterface<T> {
     T  execute(Character command, CompassPoint compassPoint, Position position );

    default void addEventHistory(Character command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.Event event = new History.Event(command, compassPoint, position);
        History.getInstance().addEvent(command, compassPoint, position);
    }

}
