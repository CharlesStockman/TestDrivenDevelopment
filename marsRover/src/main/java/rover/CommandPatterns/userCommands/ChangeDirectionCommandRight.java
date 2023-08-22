package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CompassPoint;
import rover.RoverData;

import java.util.HashMap;
import java.util.Map;

/**
 * A command to change the direction the rover is facing.
 */
public class ChangeDirectionCommandRight extends ChangeDirectionCommand {

    {
        getNewCompassPoint = new HashMap<>();
        getNewCompassPoint.put("R" + CompassPoint.N.name(), CompassPoint.E);
        getNewCompassPoint.put("R" + CompassPoint.E.name(), CompassPoint.S);
        getNewCompassPoint.put("R" + CompassPoint.S.name(), CompassPoint.W);
        getNewCompassPoint.put("R" + CompassPoint.W.name(), CompassPoint.N);
    }
}
