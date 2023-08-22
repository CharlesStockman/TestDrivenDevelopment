package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CompassPoint;
import rover.RoverData;

import java.util.Map;

/**
 *
 */
public abstract class ChangeDirectionCommand implements CommandInterface {

    protected static Map<String, CompassPoint> getNewCompassPoint = null;

    @Override
    public RoverData execute(String command, CompassPoint compassPoint, Position position ) {
        CompassPoint newCompassPoint = getNewCompassPoint.get(command + compassPoint.name());
        addEventHistory( command, newCompassPoint, position, "");
        return create(newCompassPoint, position, false );
    }

}
