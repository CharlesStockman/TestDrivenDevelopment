package rover.CommandPatterns.userCommands;

import common.Position;
import rover.CompassPoint;
import rover.RoverData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A command to move the rover one position in the direction that the rover is pointing in.
 */
public class MoveCommand implements CommandInterface<RoverData> {

    private static final Map<CompassPoint, Function<Position, Position>> movements;

    static {
        movements = new HashMap<>();
        movements.put(CompassPoint.N, Position::moveVerticalUp);
        movements.put(CompassPoint.E, Position::moveHorizontalRight);
        movements.put(CompassPoint.S, Position::moveVerticalDown);
        movements.put(CompassPoint.W, Position::moveHorizontalLeft);
    }

    private final String commandName = "M";

    @Override
    public RoverData execute(RoverData roverData) {

        String otherInformation;

        Position newPosition = movements.get(roverData.getCompassPoint()).apply(roverData.getPosition()).wrap(
                roverData.getGridPlateau().getLength(), roverData.getGridPlateau().getWidth());

        RoverData newRoverData = new RoverData();
        newRoverData.setCompassPoint(roverData.getCompassPoint());

        if (roverData.getGridPlateau().isCellObstructed(newPosition)) {
            otherInformation = "obstructed:true";
            newRoverData.setIsObstructed(true);
            newRoverData.setPosition(roverData.getPosition());
        } else {
            otherInformation = "obstructed:false";
            newRoverData.setIsObstructed(false);
            newRoverData.setPosition(newPosition);
        }

        addEventHistory( commandName, newRoverData.getCompassPoint(), newRoverData.getPosition(), otherInformation);
        return create(newRoverData.getCompassPoint(), newRoverData.getPosition(), newRoverData.getIsObstructed(), roverData.getGridPlateau());
    }
}
