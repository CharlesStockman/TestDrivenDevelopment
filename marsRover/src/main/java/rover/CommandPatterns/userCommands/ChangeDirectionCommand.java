package rover.CommandPatterns.userCommands;

import rover.CompassPoint;
import rover.RoverData;

import java.util.Map;

/**
 * An abstract command that changes the direction
 */
public abstract class ChangeDirectionCommand implements CommandInterface<RoverData> {

    protected String commandName = "";

    protected static Map<String, CompassPoint> getNewCompassPoint = null;

    @Override
    public RoverData execute(RoverData roverData) {
        CompassPoint newCompassPoint = getNewCompassPoint.get(commandName + roverData.getCompassPoint().name());
        addEventHistory( commandName, newCompassPoint, roverData.getPosition(), "");
        return create(newCompassPoint, roverData.getPosition(), false );
    }

}
