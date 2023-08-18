package rover.commandPattern;

import common.Position;
import gridPlateau.GridPlateau;
import rover.CompassPoint;

import java.util.List;

public interface CommandInterface<T> {
     T  execute();

    default void addEventHistory(Character command, CompassPoint compassPoint, Position position, String otherInformation) {
        History.Event event = new History.Event(command, compassPoint, position);
        History.getInstance().addEvent(command, compassPoint, position);
    }

}
