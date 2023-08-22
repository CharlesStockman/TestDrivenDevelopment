package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CompassPoint;
import rover.RoverData;

import java.util.Map;

/**
 *
 */
public abstract class ChangeDirectionCommand implements CommandInterface {

    protected String commandName = "";

    protected static Map<String, CompassPoint> getNewCompassPoint = null;

    @Override
    public RoverData execute(CompassPoint compassPoint, Position position ) {
        CompassPoint newCompassPoint = getNewCompassPoint.get(commandName + compassPoint.name());
        addEventHistory( commandName, newCompassPoint, position, "");
        return create(newCompassPoint, position, false );
    }

}
