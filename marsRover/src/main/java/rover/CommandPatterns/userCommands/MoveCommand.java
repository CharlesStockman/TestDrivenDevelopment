package rover.CommandPatterns.userCommands;

import common.Position;
import gridPlateau.GridPlateau;
import lombok.Data;
import rover.CompassPoint;
import rover.RoverData;

/**
 * A command to move the rover one position in the direction that the rover is pointing in.
 */
public class MoveCommand implements CommandInterface<RoverData> {

    private String commandName = "M";

    private final GridPlateau gridPlateau;

    public MoveCommand(GridPlateau gridPlateau) {
        this.gridPlateau = gridPlateau;
    }

    @Override
    public RoverData execute(RoverData rover) {

        Position newPosition = null;
        String otherInformation;

        if (rover.getCompassPoint() == CompassPoint.N)
            newPosition = rover.getPosition().moveVerticalUp();
        else if (rover.getCompassPoint() == CompassPoint.E)
            newPosition = rover.getPosition().moveHorizontalRight();
        else if (rover.getCompassPoint() == CompassPoint.S)
            newPosition = rover.getPosition().moveVerticalDown();
        else if (rover.getCompassPoint() == CompassPoint.W)
            newPosition = rover.getPosition().moveHorizontalLeft();

        newPosition = newPosition.wrap(10,10);

        PositionData positionData = new PositionData();
        if (gridPlateau != null && gridPlateau.isCellObstructed(newPosition)) {
            otherInformation = "obstructed:true";
            positionData.setObstructed(true);
            positionData.setPosition(rover.getPosition());
        } else {
            otherInformation = "obstructed:false";
            positionData.setObstructed(false);
            positionData.setPosition(newPosition);
        }

        addEventHistory( commandName, rover.getCompassPoint(), positionData.getPosition(), otherInformation);
        return create(rover.getCompassPoint(), positionData.getPosition(), positionData.isObstructed);
    }

    @Data
    public static class PositionData {
        private Position position;
        private boolean isObstructed;
    }
}
