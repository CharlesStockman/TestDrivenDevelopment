package rover.CommandPatterns.userCommands;

import rover.CompassPoint;

import java.util.HashMap;

/**
 * A command to change the direction the rover is facing.
 */
public class ChangeDirectionCommandLeft extends ChangeDirectionCommand {

    {
        commandName = "L";
        getNewCompassPoint = new HashMap<>();
        getNewCompassPoint.put("L" + CompassPoint.N.name(), CompassPoint.W);
        getNewCompassPoint.put("L" + CompassPoint.W.name(), CompassPoint.S);
        getNewCompassPoint.put("L" + CompassPoint.S.name(), CompassPoint.E);
        getNewCompassPoint.put("L" + CompassPoint.E.name(), CompassPoint.N);
    }
}
