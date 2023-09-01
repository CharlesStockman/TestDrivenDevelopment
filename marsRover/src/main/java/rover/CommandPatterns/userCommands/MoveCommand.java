package rover.CommandPatterns.userCommands;

import utilities.Position;
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

    @Override
    public RoverData execute(RoverData roverData) {

        final String commandName = "M";

        Position newPosition = movements.get(roverData.getCompassPoint()).apply(roverData.getPosition()).wrap(
                roverData.getGridPlateau().getLength(), roverData.getGridPlateau().getWidth());

        boolean isObstructed = roverData.getGridPlateau().isCellObstructed(newPosition);
        String otherInformation = ( isObstructed) ? "obstructed:true" : "obstructed:false";
        Position finalPosition = ( isObstructed ) ? roverData.getPosition() : newPosition;

        addEventHistory( commandName, roverData.getCompassPoint(), finalPosition, otherInformation);
        return create(roverData.getCompassPoint(), finalPosition, isObstructed, roverData.getGridPlateau());
    }
}
