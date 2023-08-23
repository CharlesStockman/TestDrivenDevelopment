package rover.CommandPatterns.userCommands;

import rover.CompassPoint;

import java.util.HashMap;

/**
 * A command to change the direction the rover is facing.
 */
public class ChangeDirectionCommandRight extends ChangeDirectionCommand {

    {
        commandName = "R";
        getNewCompassPoint = new HashMap<>();
        getNewCompassPoint.put("R" + CompassPoint.N.name(), CompassPoint.E);
        getNewCompassPoint.put("R" + CompassPoint.E.name(), CompassPoint.S);
        getNewCompassPoint.put("R" + CompassPoint.S.name(), CompassPoint.W);
        getNewCompassPoint.put("R" + CompassPoint.W.name(), CompassPoint.N);
    }
}
